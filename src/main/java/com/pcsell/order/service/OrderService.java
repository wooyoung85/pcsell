package com.pcsell.order.service;

import com.pcsell.order.domain.Order;
import com.pcsell.user.domain.User;

public interface OrderService {

	public abstract void order(Order order);
	public abstract void cancel(Long id, Order cancelOrder);
	public abstract Order historyView(User user);
}
