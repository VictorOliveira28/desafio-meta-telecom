package com.victoroliveira.desafio_meta_telecom.ControllerTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.victoroliveira.desafio_meta_telecom.controllers.ProductController;
import com.victoroliveira.desafio_meta_telecom.dto.ProductDTO;
import com.victoroliveira.desafio_meta_telecom.services.ProductService;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc; 

    @SuppressWarnings("removal")
	@MockBean
    private ProductService service; 

    @Autowired
    private ObjectMapper objectMapper; 

    private ProductDTO productDTO;

    @BeforeEach
    void setUp() {
        productDTO = new ProductDTO(1L, "Roteador", 399.90, "desc", 10);
    }

    @Test
    @DisplayName("GET //produtucts deve retornar status 200 e uma lista de produtos")
    void findAll_shouldReturnOkAndProductList() throws Exception {
        
        when(service.findAll()).thenReturn(List.of(productDTO));

       
        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].name").value("Roteador"));
    }

    @Test
    @DisplayName("POST /products/insert deve retornar status 201 e o produto criado")
    void insert_shouldReturnCreatedAndProduct() throws Exception {
        
        when(service.insert(any(ProductDTO.class))).thenReturn(productDTO);

        
        mockMvc.perform(post("/products/insert")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productDTO)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(jsonPath("$.name").value("Roteador"));
    }
}