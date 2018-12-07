package com.impe.demo.vb.logic;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.impe.demo.vb.exceptions.MalformedRequestException;
import com.impe.demo.vb.exceptions.ResourceNotFoundException;
import com.impe.demo.vb.model.Order;
import com.impe.demo.vb.model.OrderDto;
import com.impe.demo.vb.model.Product;
import com.impe.demo.vb.repository.OrderRepository;
import com.impe.demo.vb.repository.ProductRepository;

@Service
public class OrderService {

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	ProductRepository productRepository;

	
	/**
	 * Returns all orders between the two dates 
	 * 
	 * @param from start date
	 * @param to end date
	 * @return
	 */
	public List<Order> getOrderByDateFromTo(Date from, Date to){
		return orderRepository.getOrderByDateFromTo(from, to); 
	}
	
	/**
	 * Retirns the order
	 * @param orderId the identifier of the order
	 * @return the order or Exception
	 */
	public Order getOrderById(Long orderId) {
		Order order = orderRepository.findById(orderId)
				.orElseThrow(() -> new ResourceNotFoundException("Order", "id", orderId));
		return order;
	}
	
	/**
	 * Validates the order for teh given DTO, if sucessful creates the order else fails with exception.
	 * @param orderDto A Data transfer object with the specs for creating an order
	 * @return the order or throws exception
	 */
	public Order placeOrder(OrderDto orderDto){
		List<Long> productsIds = orderDto.getProductIds();
		List<Product> products = productRepository.findProducts(productsIds);
		// validate product ids and validate their count, 
		// Ideally we could do a better job returning the list of 
		// invalid products, if any missing 
		Set<Product> productsSet = new HashSet<> (products);
		Set<Long> productIdsSet = new HashSet<> (productsIds);
		if(productsSet.size() != productIdsSet.size())
			throw new MalformedRequestException("OrderDto", "productIds", productsIds );
		Order order = new Order();
		order.setEmail(orderDto.getEmail());
		order.setProducts(products);
		return orderRepository.save(order);
	}
}
