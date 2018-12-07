package com.impe.demo.vb.model;


import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;

@Entity
@Table(name = "orders")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"effectiveDate", "untilDate"}, allowGetters = true)
@ApiModel(description = "Class representing an Order tracked by the application.")
public class Order implements Serializable {
    /**
	 * 
	 */
	
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY)
    private Long id;

	// A reference to an object representing a Client might be a better idea in a production scenario
	// Here I am Sticking with the specs to keep scope reasonably limited for a demo 
	@Email
	@Column(nullable = false, updatable = false)
	private String email;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = {})
    @JoinTable(name = "order_product",
            joinColumns = { @JoinColumn(name = "order_id") },
            inverseJoinColumns = { @JoinColumn(name = "product_id") })
	private List<Product> products = new ArrayList<>();
	
   	// Since we are auditing we can keep doing it
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @JsonIgnore
    private Date effectiveDate;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    @JsonIgnore
    private Date untilDate;

    
    public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	// Assuming the same library is used by the client, to unmarshall the json it allows to get the total of the order
    public BigDecimal total() {
    	return products.stream().map((x) -> x.getPrice()).reduce((x, y) -> x.add(y)).orElse(BigDecimal.ZERO);
    }
    
    // Assuming the same library is used by the client, it allows to get the procise instant in time the order was placed
    public Instant getTime() {
    	return Instant.ofEpochMilli(effectiveDate.getTime());
    }
	
}