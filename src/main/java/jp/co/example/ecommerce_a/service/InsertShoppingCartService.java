package jp.co.example.ecommerce_a.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.example.ecommerce_a.domain.Order;
import jp.co.example.ecommerce_a.domain.OrderItem;
import jp.co.example.ecommerce_a.domain.OrderTopping;
import jp.co.example.ecommerce_a.domain.Topping;
import jp.co.example.ecommerce_a.repository.OrderItemRepository;
import jp.co.example.ecommerce_a.repository.OrderRepository;
import jp.co.example.ecommerce_a.repository.OrderToppingRepository;

@Service
public class InsertShoppingCartService {

	@Autowired
	private OrderRepository orderRepository;
	private OrderItemRepository orderItemRepository;
	private OrderToppingRepository orderToppingRepository;

	public void insertOrder(Order order) {
		Integer userId = order.getUserId();
		Integer status = order.getStatus();

		Order checkOrder = orderRepository.findByUserIdAndStatus(userId, status);
		List<OrderTopping> orderToppingList = null;
		List<OrderItem> orderItemList = null;
		System.out.println(order.getOrderItemList().get(0).getItem().getToppingList().get(0));

		if (checkOrder == null) {
			orderRepository.insert(order);
			orderItemList = order.getOrderItemList();
			for (OrderItem orderItem : orderItemList) {
				orderItemRepository.insert(orderItem);

			}
			orderToppingList = order.getOrderItemList().get(0).getOrderToppingList();
			for (OrderTopping topping : orderToppingList) {
				orderToppingRepository.insert(topping);

			}
		} else
			toppingList = order.getOrderItemList().get(0).getItem().getToppingList();
		orderItemRepository.insert(order.getOrderItemList().get(0));
	}
}
