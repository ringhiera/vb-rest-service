package com.impe.demo.vb.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.impe.demo.vb.logic.ProductService;
import com.impe.demo.vb.model.Product;
import com.impe.demo.vb.model.ProductDto;
import com.impe.demo.vb.repository.ProductRepository;

@RestController
@RequestMapping("/api/v0.0")
public class ProductController {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	ProductService productService;


	// Get All Products
	@GetMapping(path = "/products", produces = "application/json; charset=utf-8")
	public List<Product> getAllProductsWithStatusStandard() {
		return productService.findByRegistrationStatusStandard();
	}

	// Create a new Product
	@PostMapping(path = "/products", produces = "application/json; charset=utf-8")
	public Product createProduct(@Valid @RequestBody ProductDto productDto) {
		
		return productService.save(productDto);
	}

	// Update a Note
	@PutMapping(path = "/products/{id}", produces = "application/json; charset=utf-8")
	public Product updateProduct(@PathVariable(value = "id") Long productId, @Valid @RequestBody ProductDto updatedProductDto) {
		
		return productService.update(productId, updatedProductDto);
	}
	
}
