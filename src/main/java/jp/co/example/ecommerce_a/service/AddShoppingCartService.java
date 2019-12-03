package jp.co.example.ecommerce_a.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.example.ecommerce_a.domain.Order;
import jp.co.example.ecommerce_a.domain.OrderItem;
import jp.co.example.ecommerce_a.repository.OrderItemRepository;
import jp.co.example.ecommerce_a.repository.OrderRepository;

@Service
public class AddShoppingCartService {

	@Autowired
	private HttpSession session;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderItemRepository orderItemRepository;

	public void addShoppingCart(Integer loginUserId) {
		Integer userId = (Integer) session.getAttribute("userId");
		Order preOrder = orderRepository.findByUserIdAndStatus(userId, 0);
		Order order = orderRepository.findByUserIdAndStatus(loginUserId, 0);

		if(order != null) {
			if (preOrder != null) {
				for (OrderItem preOrderItem : preOrder.getOrderItemList()) {
					preOrderItem.setOrderId(order.getId());
					orderItemRepository.update(preOrderItem);
				}
			}
			
		}
		if(order == null){
			if( preOrder != null) {
				Order newOrder = new Order();
				newOrder.setStatus(0);
				newOrder.setTotalPrice(0);
				newOrder.setUserId(loginUserId);
				Integer id = orderRepository.insert(newOrder).getId();
				for (OrderItem preOrderItem : preOrder.getOrderItemList()) {
					preOrderItem.setOrderId(id);
					orderItemRepository.update(preOrderItem);
				}
				newOrder.setStatus(0);
				newOrder.setTotalPrice(0);
				System.err.println("新規登録");
			}
		}

	}

}
