package com.victoroliveira.desafio_meta_telecom.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.victoroliveira.desafio_meta_telecom.entities.Product;
import com.victoroliveira.desafio_meta_telecom.repositories.ProductRepository;

@Service
public class ProductService {

	private final ProductRepository repository;

	ProductService(ProductRepository repository) {
		this.repository = repository;
	}

	public List<Product> findAll() {
		return repository.findAll();
	}
}
