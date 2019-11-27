package jp.co.example.ecommerce_a.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.example.ecommerce_a.domain.Order;
import jp.co.example.ecommerce_a.repository.OrderRepository;

/**
 * 商品確認画面を表示するためのサービスクラス.
 * 
 * z@author suzukiryouhei
 *
 */
@Service
@Transactional
public class ShowConfirmService {
	@Autowired
	private OrderRepository orderRepository;

	/**
	 * 商品確認画面を表示する.
	 * 
	 * @param userId ユーザーのID
	 * @param status ユーザーの状況
	 * @return
	 */
	public Order showOrderConfirm(Integer userId, Integer status) {
		Order order = orderRepository.findByUserIdAndStatus(userId, status);
		return order;
	}
}
