package com.impe.demo.vb.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.impe.demo.vb.exceptions.ResourceNotFoundException;
import com.impe.demo.vb.model.Product;
import com.impe.demo.vb.model.ProductDto;
import com.impe.demo.vb.model.RegistrationStatus;
import com.impe.demo.vb.model.transformer.ProductTransformer;
import com.impe.demo.vb.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	ProductTransformer productTransformer;
	
	/**
	 * Delegate to Repository method 
	 * @return
	 */
	public List<Product> findByRegistrationStatusStandard() {
		return productRepository.findByRegistrationStatus(RegistrationStatus.STANDARD);
	}
	
	/**
	 * Delegate to repository method
	 * @param product
	 * @return
	 */
	public Product save(ProductDto productDto) {
		Product product = productTransformer.dtoToEntity(productDto);
		return productRepository.save(product);
	}
	
	/**
	 * Updates a Product by instantiating a new one and superseding the old one.
	 * Either complete all operations or rollback. 
	 * Rollback Requires DB to support transactions
	 * 
	 * @param productId
	 * @param updatedProduct
	 * @return The updated product
	 */
	@Transactional
	public Product update(Long productId, ProductDto updatedProductDto) {
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));
		product.setRegistrationStatus(RegistrationStatus.SUPERSEDED);
		product = productRepository.save(product);
		Product updatedProduct = productTransformer.dtoToEntity(updatedProductDto);
		return productRepository.save(updatedProduct);
	}
}
