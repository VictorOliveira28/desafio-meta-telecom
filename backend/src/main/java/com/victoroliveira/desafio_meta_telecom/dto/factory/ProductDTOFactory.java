package com.victoroliveira.desafio_meta_telecom.dto.factory;

import com.victoroliveira.desafio_meta_telecom.dto.ProductDTO;
import com.victoroliveira.desafio_meta_telecom.entities.Product;

public class ProductDTOFactory {
	public static ProductDTO fromEntity(Product product) {
		return new ProductDTO(
				product.getId(),
				product.getName(),
				product.getPrice(),
				product.getDescription(),
				product.getQuantity());
	}

}
