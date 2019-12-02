package jp.co.example.ecommerce_a.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.example.ecommerce_a.domain.Order;
import jp.co.example.ecommerce_a.repository.OrderRepository;

/**
 * 注文履歴画面を表示するためのサービス.
 * 
 * @author yukiando
 *
 */
@Service
@Transactional
public class OrderHistoryService {

	@Autowired
	private OrderRepository orderRepository;
	
	/**
	 * 注文履歴画面を表示する.
	 * 
	 * @param userId ユーザーID
	 * @param status　支払状況
	 * @return　注文情報
	 */
	public Order showOrderHistory(Integer userId, Integer status) {
		Order order = orderRepository.findByUserIdAndStatus(userId, status);
		return order;
	}
}
