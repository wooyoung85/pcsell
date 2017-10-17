package com.pcsell.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pcsell.order.domain.Order;
import com.pcsell.product.domain.Product;
import com.pcsell.user.domain.User;

public interface OrderRepository extends JpaRepository<Order, Long> {
	Order findByOrderer(User user);
	int findByProduct(Product product);
	int findByCancel(User user);
}
