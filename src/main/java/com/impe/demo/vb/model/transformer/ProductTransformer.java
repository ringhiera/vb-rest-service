package com.impe.demo.vb.model.transformer;

import org.springframework.stereotype.Service;

import com.impe.demo.vb.model.Product;
import com.impe.demo.vb.model.ProductDto;

@Service
public class ProductTransformer {

	/**
	 * Maps a Dto to not managed fields of the entity
	 * @param productDto
	 * @return
	 */
	public Product dtoToEntity(ProductDto productDto) {
		Product product = new Product();
		product.setName(productDto.getName());
		product.setPrice(productDto.getPrice());
		product.setCurrency(productDto.getCurrency());
		return product;
	}
	
	
}
