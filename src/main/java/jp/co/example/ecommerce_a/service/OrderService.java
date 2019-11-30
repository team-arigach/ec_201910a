package jp.co.example.ecommerce_a.service;

import java.util.Date;

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
		// DBから取得したオーダーのuser_idを取得し、インサートするオーダーにセット
		order.setUserId(updateOrder.getUserId());
		// DBから取得したオーダーの合計金額を取得し、インサートするオーダーにセット
		order.setTotalPrice(updateOrder.getCalcTotalPrice());
		// 現在時刻を取得し、オーダーにセット
		Date date = new Date();
		order.setOrderDate(date);
		if(order.getPaymentMethod() == 1) {
			order.setStatus(1);
		}
		if(order.getPaymentMethod() == 2) {
			order.setStatus(2);
		}
		System.err.println("最終情報構成=>" + order);
		orderRepository.update(order);
	}
	
	public Order showOrder(Integer id) {
		return orderRepository.load(id);
	}
}
