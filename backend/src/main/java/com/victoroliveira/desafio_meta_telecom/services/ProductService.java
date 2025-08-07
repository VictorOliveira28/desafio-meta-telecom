package com.victoroliveira.desafio_meta_telecom.services;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.victoroliveira.desafio_meta_telecom.dto.ProductDTO;
import com.victoroliveira.desafio_meta_telecom.entities.Product;
import com.victoroliveira.desafio_meta_telecom.exceptions.ResourceNotFoundException;
import com.victoroliveira.desafio_meta_telecom.repositories.ProductRepository;
import com.victoroliveira.desafio_meta_telecom.services.exceptions.DatabaseException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {

	private final ProductRepository repository;

	ProductService(ProductRepository repository) {
		this.repository = repository;
	}

	@Transactional(readOnly = true)
	public List<ProductDTO> findAll() {
		return repository
				.findAll()
				.stream()
				.map(ProductDTO :: new)
				.toList();
	}	
	
	@Transactional
	public ProductDTO insert (ProductDTO dto) {
		Product entity = new Product();
		
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		
		return new ProductDTO(entity);
	}
	
	@Transactional
	public ProductDTO update(Long id, ProductDTO dto) {
		
		try {
			Product entity = repository.getReferenceById(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new ProductDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found" + id);
		}
		
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public void delete(Long id) {
		if (!repository.existsById(id)) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Referential Integrity Failure");
		}
	}
	
	private void copyDtoToEntity(ProductDTO dto, Product entity) {
		entity.setName(dto.name());
		entity.setDescription(dto.description());		
		entity.setPrice(dto.price());
		entity.setQuantity(dto.quantity());
	}
}
