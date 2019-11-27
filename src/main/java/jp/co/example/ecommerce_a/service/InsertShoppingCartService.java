package jp.co.example.ecommerce_a.service;

import java.sql.Timestamp;
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
import jp.co.example.ecommerce_a.repository.ToppingRepository;

@Service
public class InsertShoppingCartService {

	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private OrderItemRepository orderItemRepository;
	@Autowired
	private HttpSession session;
	@Autowired
	private ToppingRepository toppingRepository;
	
	
	
	public void insertOrder(OrderItemForm orderItemForm) {
		Order order = new Order();
		OrderItem orderItem = new OrderItem();
		Integer userId = session.getId().hashCode();
		Timestamp deliveryTime = new Timestamp(System.currentTimeMillis());
		Order order3 = orderRepository.findByUserIdAndStatus(userId, 0);
		if (order3 == null) {

			order.setUserId(userId);
			order.setStatus(0);
			order.setTotalPrice(0);
			order.setDeliveryTime(deliveryTime);
			orderRepository.insert(order);
			Order sameOrder = orderRepository.findByUserIdAndStatus(order.getUserId(), 0);
			orderItem.setItemId(orderItemForm.getIntItemId());
			orderItem.setOrderId(sameOrder.getId());
			orderItem.setSize(orderItemForm.getCharSize());
			orderItem.setQuantity(0);
			orderItemRepository.insert(orderItem);
			
			List<Integer> orderToppingIdList = orderItemForm.getOrderToppingIdList();
			System.out.println("List<integer>定義後");
			for (Integer orderToppingId : orderToppingIdList) {
				System.out.println("for文の中");
				OrderTopping orderTopping = new OrderTopping();
				orderTopping.setToppingId(orderToppingId);
				orderTopping.setTopping(toppingRepository.load(orderTopping.getToppingId()));
				orderTopping.setOrderItemId(orderItem.getId());

				
			}
		}else {
			Order sameOrder = orderRepository.findByUserIdAndStatus(order.getUserId(), 0);
			orderItem.setItemId(orderItemForm.getIntItemId());
			orderItem.setOrderId(sameOrder.getId());
			orderItem.setSize(orderItemForm.getCharSize());
			orderItem.setQuantity(0);
			orderItemRepository.insert(orderItem);
			
			List<Integer> orderToppingIdList = orderItemForm.getOrderToppingIdList();
			System.out.println("List<integer>定義後");
			for (Integer orderToppingId : orderToppingIdList) {
				System.out.println("for文の中");
				OrderTopping orderTopping = new OrderTopping();
				orderTopping.setToppingId(orderToppingId);
				orderTopping.setTopping(toppingRepository.load(orderTopping.getToppingId()));
				orderTopping.setOrderItemId(orderItem.getId());
			}
			
			
		}
			}
}
