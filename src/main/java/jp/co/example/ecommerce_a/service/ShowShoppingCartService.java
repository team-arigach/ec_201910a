package jp.co.example.ecommerce_a.service;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import jp.co.example.ecommerce_a.domain.Order;
import jp.co.example.ecommerce_a.repository.OrderRepository;

/**
 * ショッピングカートを表示するためのサービスクラス.
 * 
 * @author takahiro.suzuki
 *
 */
@Service
public class ShowShoppingCartService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	/**
	 * ショッピングカートを表示する.
	 * 
	 * @param order_id 指定されたオーダーid
	 * @return
	 */
	public Order showShoppingCart(Integer userId, Integer status) {
		Order order = orderRepository.findByUserIdAndStatus(userId, status);
		return order;
	}
	
	

}
