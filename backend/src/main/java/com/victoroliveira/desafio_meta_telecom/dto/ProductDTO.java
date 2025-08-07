package com.victoroliveira.desafio_meta_telecom.dto;

import com.victoroliveira.desafio_meta_telecom.entities.Product;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Data Transfer Object (DTO) para representar um produto")
public record ProductDTO(

		@Schema(description = "ID único do produto gerado pelo banco de dados.", example = "1", accessMode = Schema.AccessMode.READ_ONLY) Long id,

		@Schema(description = "Nome do produto.", example = "Roteador Wi-Fi 6 Mesh", requiredMode = Schema.RequiredMode.REQUIRED) String name,

		@Schema(description = "Preço do produto. Não pode ser negativo.", example = "399.90", minimum = "0") Double price,

		@Schema(description = "Descrição detalhada do produto.", example = "Roteador com tecnologia Mesh para cobertura total da residência.") String description,

		@Schema(description = "Quantidade do produto em estoque.", example = "50") Integer quantity) {
	public ProductDTO {
		if (price < 0) {
			throw new IllegalArgumentException("Price can't be negative!");
		}
	}

	public ProductDTO(Product entity) {
		this(entity.getId(), entity.getName(), entity.getPrice(), entity.getDescription(), entity.getQuantity());
	}
}
