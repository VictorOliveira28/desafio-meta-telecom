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

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/products")
public class ProductController {
	
	private final ProductService service;
	
	ProductController(ProductService service){
		this.service = service;
	}
	
	@GetMapping
	public ResponseEntity<List<ProductDTO>> findAll(){
		List<ProductDTO> list = service.findAll();
		return ResponseEntity.ok(list);		
	}
	
	@CrossOrigin(origins = "*")
	@PostMapping("/insert")
	public ResponseEntity<ProductDTO> insert( @RequestBody ProductDTO dto){
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(dto.id()).toUri();		
		return ResponseEntity.created(uri).body(dto);
	}
	
	@CrossOrigin(origins = "*")
	@PutMapping(value = "/{id}")
	public ResponseEntity<ProductDTO> update(@PathVariable Long id,  @RequestBody ProductDTO dto){
		dto = service.update(id, dto);			
		return ResponseEntity.ok().body(dto);
	}
	
	@CrossOrigin(origins = "*")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);			
		return ResponseEntity.noContent().build();
	}

}
