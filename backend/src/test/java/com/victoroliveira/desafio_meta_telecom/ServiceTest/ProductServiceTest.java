package com.victoroliveira.desafio_meta_telecom.ServiceTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.victoroliveira.desafio_meta_telecom.dto.ProductDTO;
import com.victoroliveira.desafio_meta_telecom.entities.Product;
import com.victoroliveira.desafio_meta_telecom.repositories.ProductRepository;
import com.victoroliveira.desafio_meta_telecom.services.ProductService;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock 
    private ProductRepository repository;

    @InjectMocks 
    private ProductService service;

    private Product product;
    private ProductDTO productDTO;

    @BeforeEach
    void setUp() {
        
        product = new Product(1L, "Roteador", "desc",399.90, 10);
        productDTO = new ProductDTO(1L, "Roteador", 399.90, "desc", 10);
    }

    @Test
    @DisplayName("Deve retornar uma lista de produtos com sucesso")
    void findAll_shouldReturnListOfProducts() {
        
        when(repository.findAll()).thenReturn(List.of(product));

        
        List<ProductDTO> result = service.findAll();

        
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).name()).isEqualTo(product.getName());
        verify(repository, times(1)).findAll(); //
    }

    @Test
    @DisplayName("Deve deletar um produto com sucesso")
    void delete_shouldCompleteSuccessfully() {
        // Arrange
        Long productId = 1L;
        when(repository.existsById(productId)).thenReturn(true);
        doNothing().when(repository).deleteById(productId);

        // Act
        service.delete(productId);

        // Assert
        verify(repository, times(1)).deleteById(productId);
    }
}