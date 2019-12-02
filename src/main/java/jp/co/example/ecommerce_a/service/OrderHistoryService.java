package jp.co.example.ecommerce_a.service;

import java.util.List;

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
	 * @return　注文情報リスト
	 */
	public List<Order> showOrderHistory(Integer userId) {
		List<Order> orderList = orderRepository.findAllByUserId(userId);	 
		return orderList;
	}
}
