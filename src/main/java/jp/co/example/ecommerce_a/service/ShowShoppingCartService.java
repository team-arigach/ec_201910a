package jp.co.example.ecommerce_a.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
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
	
	@Autowired
	private MailSender mailSender;
	
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
	
	/**
	 * メールを送信する
	 */
	public void send() {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo("federer.tennis.nadal.1023@gmail.com");
		msg.setFrom("federer.tennis.nadal.1023@gmail.com");
		msg.setSubject("ご注文ありがとうございました。");
		msg.setText("テストです。");
		mailSender.send(msg);
	}
	

}
