package jp.co.example.ecommerce_a.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.example.ecommerce_a.domain.Order;
import jp.co.example.ecommerce_a.domain.OrderItem;
import jp.co.example.ecommerce_a.domain.OrderTopping;
import jp.co.example.ecommerce_a.form.OrderItemForm;
import jp.co.example.ecommerce_a.repository.OrderItemRepository;
import jp.co.example.ecommerce_a.repository.OrderRepository;
import jp.co.example.ecommerce_a.repository.OrderToppingRepository;

@Service
public class InsertShoppingCartService {

	@Autowired
	private OrderRepository orderRepository;
	private OrderItemRepository orderItemRepository;
	private OrderToppingRepository orderToppingRepository;

	
	public void insertOrder(HttpSession session,OrderItemForm orderItemForm) {
		
		Order order = new Order();
//		Integer userId = session.getId();  // Integer化したい
		Integer status = 0; // 未注文の物を調べる
//		Order checkOrder = orderRepository.findByUserIdAndStatus(userId, status);
//		if (checkOrder == null) {
//			order.setUserId(session.getId());
//			order.setStatus(0);
//			order.setTotalPrice(0);
//			orderRepository.insert(order);
//			
//		} else {
//			// orderはinsertしない
//		}
//		
		
		
		Integer userId = order.getUserId();
//		Integer status = order.getStatus();
		String sessionId = session.getId();
		
		List<OrderTopping> orderToppingList = null;
		List<OrderItem> orderItemList = null;
		System.out.println(order.getOrderItemList().get(0).getItem().getToppingList().get(0));
		System.out.println(sessionId);
//
//		if (checkOrder == null) {
//			orderToppingList = order.getOrderItemList().get(0).getOrderToppingList();
//			for (OrderTopping orderTopping : orderToppingList) {
//				orderTopping.se
//				orderToppingRepository.insert(orderTopping);
//			}
//			OrderItem orderItem = order.getOrderItemList().get(0);
//				orderItemRepository.insert(orderItem);
//			orderRepository.insert(order);
//		} else
////			toppingList = order.getOrderItemList().get(0).getItem().getToppingList();
//		orderItemRepository.insert(order.getOrderItemList().get(0));
	}
}
