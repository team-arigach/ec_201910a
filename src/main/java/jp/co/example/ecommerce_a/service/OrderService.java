package jp.co.example.ecommerce_a.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.example.ecommerce_a.domain.Order;
import jp.co.example.ecommerce_a.repository.OrderRepository;

@Service
@Transactional
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	/**
	 * 注文する.
	 * 
	 * @param order　注文情報
	 */
	public void order(Order order) {
		Order updateOrder = orderRepository.load(order.getId());
		order.setUserId(updateOrder.getUserId());
		order.setTotalPrice(order.getTotalPrice());
		if(order.getPaymentMethod() == 1) {
			order.setStatus(1);
		}else if(order.getPaymentMethod() == 2) {
			order.setStatus(2);
		}
		System.err.println("最終情報構成=>" + order);
		orderRepository.update(order);
	}
	
	public Order showOrder(Integer id) {
		return orderRepository.load(id);
	}
}
