package com.impe.demo.vb.model;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Currency;

import javax.persistence.Column;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

@ApiModel(description = "Class representing a ProductDto tracked by the application.")
public class ProductDto implements Serializable {
    /**
	 * 
	 */
	
	
	private static final long serialVersionUID = 1L;

	@NotBlank
	@Column(nullable = false, updatable = false)
	private String name;

    @Digits(integer=16, fraction=2)
    @Column(nullable = false, updatable = false)
    private BigDecimal price;
    
//  Currency is validated by the marshaller and fails if not matching white-listed strings
    @Column(nullable = false, updatable = false)
    private Currency currency;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

}