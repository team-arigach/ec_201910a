package jp.co.example.ecommerce_a.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.example.ecommerce_a.domain.Order;
import jp.co.example.ecommerce_a.repository.OrderItemRepository;
import jp.co.example.ecommerce_a.repository.OrderRepository;

@Service
public class InsertShoppingCart {

	@Autowired
	private OrderRepository orderRepository;
	private OrderItemRepository orderItemRepository;

	public void insertOrder(Order order) {
		Integer userId = order.getUserId();
		Integer status = order.getStatus();
		Order checkOrder = orderRepository.findByUserIdAndStatus(userId, status);
		if (checkOrder == null) {
			orderRepository.insert(order);
		} else
			orderItemRepository.insert(order.getOrderItemList().get(0));
	}
}
