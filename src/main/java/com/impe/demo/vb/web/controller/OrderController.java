package com.impe.demo.vb.web.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.impe.demo.vb.logic.OrderService;
import com.impe.demo.vb.model.Order;
import com.impe.demo.vb.model.OrderDto;

@RestController
@RequestMapping("/api/v0.0/")
public class OrderController {

	
	/**
	 * Provides the logic to serve requests 
	 */
	@Autowired
	OrderService orderService;

    
	// Get an Order
	/**
	 * Returns an order
	 * @param orderId the id of the order to retrieve
	 * @return
	 */
	@GetMapping(path = "/orders/{id}", produces = "application/json; charset=utf-8")
	public Order getOrder(@PathVariable(value = "id") Long orderId) {
		return orderService.getOrderById(orderId);
	}

	// Get an Order
	/**
	 * Returns an order
	 * @param orderId the id of the order to retrieve
	 * @return
	 */
	@GetMapping(path = "/orders/from/{from}/to/{to}", produces = "application/json; charset=utf-8")
	public List<Order> getOrdersFromTo(
			@PathVariable(value = "from") @Valid @DateTimeFormat(pattern="yyyy-MM-dd")Date from, 
			@PathVariable(value = "to") @Valid @DateTimeFormat(pattern="yyyy-MM-dd")Date to) {
		return orderService.getOrderByDateFromTo(from,to);
	}

	
	// Create a new Order
	/**
	 * Creates an order
	 * @param orderDto The Data transfer object for the order
	 * @return the order if created successfully else an error
	 */
	@PostMapping(path = "/orders", produces = "application/json; charset=utf-8")
	public Order createOrder(@Valid @RequestBody OrderDto orderDto) {
		return orderService.placeOrder(orderDto);
	}


}
