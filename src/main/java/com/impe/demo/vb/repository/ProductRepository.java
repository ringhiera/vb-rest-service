package com.impe.demo.vb.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.impe.demo.vb.model.Product;
import com.impe.demo.vb.model.RegistrationStatus;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	List<Product> findByRegistrationStatus(RegistrationStatus registrationStatus);
	
	@Transactional
    @Query("SELECT p FROM Product p WHERE p.id in :productIds")
	List<Product> findProducts(List<Long>productIds);
	
}

