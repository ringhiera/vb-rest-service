package com.impe.demo.vb.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.impe.demo.vb.model.Order;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	// here we override using join - fetch to 
	// avoid eager fetch and 
	// tug the n+1 queries required by populating after lazy join 
	@Override
	@Transactional
    @Query("SELECT o FROM Order o JOIN FETCH o.products WHERE o.id = :id")
	Optional<Order> findById(Long id);
}

