package com.victoroliveira.desafio_meta_telecom.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.victoroliveira.desafio_meta_telecom.entities.Product;

@Repository
public interface ProductRepository  extends JpaRepository<Product, Long>{

}
