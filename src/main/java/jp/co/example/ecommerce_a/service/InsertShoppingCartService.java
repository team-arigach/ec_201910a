package jp.co.example.ecommerce_a.service;

import java.sql.Timestamp;
import java.util.ArrayList;
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
import jp.co.example.ecommerce_a.repository.ToppingRepository;

/**
 * ショッピングカートにアイテムを登録するサービス.
 * 
 * @author yosuke.yamada
 *
 */
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
	@Autowired
	private OrderToppingRepository orderToppingRepository;

	/**
	 * ショッピングカートにアイテムを登録するメソッド.
	 *
	 * @param orderItemForm リクエストパラメータ
	 */
	public void insertOrder(OrderItemForm orderItemForm) {
		OrderItem orderItem = new OrderItem();
		orderItem.setItemId(orderItemForm.getIntItemId());
		orderItem.setQuantity(orderItemForm.getIntQuantity());
		orderItem.setSize(orderItemForm.getCharSize());
		List<OrderTopping> orderToppingList = new ArrayList<OrderTopping>();
		orderItem.setOrderToppingList(orderToppingList);
		for (Integer orderToppingId : orderItemForm.getOrderToppingIdList()) {
			OrderTopping orderTopping = new OrderTopping();
			orderTopping.setToppingId(orderToppingId);
			orderTopping.setTopping(toppingRepository.load(orderTopping.getToppingId()));
			orderTopping.setOrderItemId(orderItem.getId());
		}
		// Session_idをハッシュコードで取得
		Integer userId = session.getId().hashCode();
		Timestamp deliveryTime = new Timestamp(System.currentTimeMillis());

		// DBに存在するオーダーオブジェクト
		Order existedOrder = orderRepository.findByUserIdAndStatus(userId, 0);
		if (existedOrder == null) {
			Order order = new Order();
			order.setUserId(userId);
			order.setStatus(0);
			order.setTotalPrice(0);
			order.setDeliveryTime(deliveryTime);
			Integer orderId = orderRepository.insert(order).getId();
			orderItem.setOrderId(orderId);
			Integer orderItemId = orderItemRepository.insert(orderItem).getId();
			for(OrderTopping orderTopping : orderToppingList) {
				orderTopping.setOrderItemId(orderItemId);
				orderToppingRepository.insert(orderTopping);
			}
			

		} else {
			existedOrder.getOrderItemList().add(orderItem);
			orderItem.setOrderId(existedOrder.getId());
			Integer orderItemId = orderItemRepository.insert(orderItem).getId();
			for (OrderTopping orderTopping : orderToppingList) {
				orderTopping.setOrderItemId(orderItemId);
				orderToppingRepository.insert(orderTopping);
			}
		}
	}
}
