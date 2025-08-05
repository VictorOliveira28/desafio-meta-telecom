package com.victoroliveira.desafio_meta_telecom.dto;

public record ProductDTO(Long id, String name, Double price, String description) {
	public ProductDTO{
		if(price < 0) {
			throw new IllegalArgumentException("Price can't be negative!");
		}
	}
}
