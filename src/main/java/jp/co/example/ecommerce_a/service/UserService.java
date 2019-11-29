package jp.co.example.ecommerce_a.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.example.ecommerce_a.domain.Order;
import jp.co.example.ecommerce_a.domain.OrderItem;
import jp.co.example.ecommerce_a.domain.User;
import jp.co.example.ecommerce_a.repository.OrderItemRepository;
import jp.co.example.ecommerce_a.repository.OrderRepository;
import jp.co.example.ecommerce_a.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private HttpSession session;
	/**
	 * メールアドレスとパスワードでユーザーを検索.
	 * 
	 * @param email メールアドレス
	 * @param password パスワード
	 * @return メールアドレスとパスワードで検索されたユーザー 一件も返ってこなければ、例外が発生しnullを返す。
	 */
	public User userLogin(String email, String password) {
		User user = userRepository.findByEmailAndPassword(email, password);
		session.setAttribute("user", user);
		return user;
	}
	
	public void userIdUpdate(Integer sessionId,Integer id) {
		Order order = orderRepository.findByUserIdAndStatus(sessionId, 0);
		// Ordersテーブルのログイン前のIDを取得
		System.out.println(order);
		if(order != null) {
		Integer orderId = order.getId();
		
		// ログイン前のsessionIDに紐づいているidを取得
		List<Order> orderList = orderRepository.findByOrderId(orderId);  
		for(Order checkorder:orderList) {
			orderItemRepository.updateOrderIdFromOrderItemsTable(checkorder.getId(),id);		
		}
		}
		
	}

}
