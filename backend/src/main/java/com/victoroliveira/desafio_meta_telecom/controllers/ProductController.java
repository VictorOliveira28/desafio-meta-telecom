package com.victoroliveira.desafio_meta_telecom.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.victoroliveira.desafio_meta_telecom.dto.ProductDTO;
import com.victoroliveira.desafio_meta_telecom.services.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/products")
@Tag(name = "Produtos", description = "Endpoints para o gerenciamento de produtos")
public class ProductController {

	private final ProductService service;

	ProductController(ProductService service) {
		this.service = service;
	}

	@Operation(summary = "Listar todos os produtos", description = "Retorna uma lista com todos os produtos cadastrados no banco de dados")
	@ApiResponse(responseCode = "200", description = "Lista de produtos retornada com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDTO.class)))
	@GetMapping
	public ResponseEntity<List<ProductDTO>> findAll() {
		List<ProductDTO> list = service.findAll();
		return ResponseEntity.ok(list);
	}

	@Operation(summary = "Criar um novo produto", description = "Cadastra um novo produto no sistema e retorna os dados do produto criado.")
	@ApiResponse(responseCode = "201", description = "Produto criado com sucesso.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDTO.class)))
	@ApiResponse(responseCode = "400", description = "Requisição inválida (ex: dados do produto incompletos)", content = @Content)
	@CrossOrigin(origins = "*")
	@PostMapping("/insert")
	public ResponseEntity<ProductDTO> insert(@RequestBody ProductDTO dto) {
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.id()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}

	@Operation(summary = "Atualizar um produto existente", description = "Atualiza os dados de um produto com base no seu ID.")
	@ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDTO.class)))
	@ApiResponse(responseCode = "404", description = "Produto não encontrado", content = @Content)
	@ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content)
	@CrossOrigin(origins = "*")
	@PutMapping(value = "/{id}")
	public ResponseEntity<ProductDTO> update(@PathVariable Long id, @RequestBody ProductDTO dto) {
		dto = service.update(id, dto);
		return ResponseEntity.ok().body(dto);
	}

	@Operation(summary = "Excluir um produto", description = "Remove um produto do sistema com base no seu ID.")
	@ApiResponse(responseCode = "204", description = "Produto excluído com sucesso")
	@ApiResponse(responseCode = "404", description = "Produto não encontrado", content = @Content)
	@CrossOrigin(origins = "*")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
