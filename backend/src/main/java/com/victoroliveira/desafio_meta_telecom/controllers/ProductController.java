package com.victoroliveira.desafio_meta_telecom.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.victoroliveira.desafio_meta_telecom.dto.ProductDTO;
import com.victoroliveira.desafio_meta_telecom.services.ProductService;

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

}
