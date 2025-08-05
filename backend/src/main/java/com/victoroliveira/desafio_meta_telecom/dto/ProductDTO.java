package com.victoroliveira.desafio_meta_telecom.dto;

import com.victoroliveira.desafio_meta_telecom.entities.Product;

public record ProductDTO(Long id, String name, Double price, String description, Integer quantity) {
	public ProductDTO{
		if(price < 0) {
			throw new IllegalArgumentException("Price can't be negative!");
		}
	}
	
	public ProductDTO(Product entity) {
        this(entity.getId(), entity.getName(), entity.getPrice(), entity.getDescription(), entity.getQuantity());
    }
}
