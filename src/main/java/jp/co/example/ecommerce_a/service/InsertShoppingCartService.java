package jp.co.example.ecommerce_a.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import jp.co.example.ecommerce_a.domain.LoginUser;
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
	public void insertOrder(OrderItemForm orderItemForm, @AuthenticationPrincipal LoginUser loginUser) {

		
		Integer userId = session.getId().hashCode();
		Order preOrder = null;
		Integer orderId = 0;
		
		// これは必ず生成する。
		// =============================================================================

		// orderItemListに格納するOderItemオブジェクトを作成
		OrderItem orderItem = new OrderItem();
		orderItem.setItemId(orderItemForm.getIntItemId());
		orderItem.setQuantity(orderItemForm.getIntQuantity());
		orderItem.setSize(orderItemForm.getCharSize());
		List<OrderTopping> orderToppingList = new ArrayList<OrderTopping>();
		orderItem.setOrderToppingList(orderToppingList);
		if (orderItemForm.getOrderToppingIdList() != null) {
			for (Integer orderToppingId : orderItemForm.getOrderToppingIdList()) {
				OrderTopping orderTopping = new OrderTopping();
				orderTopping.setToppingId(orderToppingId);
				orderTopping.setTopping(toppingRepository.load(orderTopping.getToppingId()));
				orderTopping.setOrderItemId(orderItem.getId());
				orderToppingList.add(orderTopping);
			}
		}
		// ==============================================================================

		if (loginUser != null) {
			userId = loginUser.getUser().getId();
			Integer preLoginId = (Integer) session.getAttribute("userId");
			preOrder = orderRepository.findByUserIdAndStatus(preLoginId, 0);
		} else {
			// Session_idをハッシュコードで取得
			session.setAttribute("userId", userId);
		}
		
		// DBに存在するオーダーオブジェクト
		Order existedOrder = orderRepository.findByUserIdAndStatus(userId, 0);
		
		if (existedOrder == null) {
			Order order = new Order();
			order.setUserId(userId);
			order.setStatus(0);
			order.setTotalPrice(0);
			orderId = orderRepository.insert(order).getId();
			orderItem.setOrderId(orderId);
			System.err.println("新規登録");
		} else {
			orderId = existedOrder.getId();
			existedOrder.getOrderItemList().add(orderItem);
			orderItem.setOrderId(existedOrder.getId());
		}
		
		if( preOrder != null ) {
			for (OrderItem preOrderItem : preOrder.getOrderItemList()) {
				preOrderItem.setOrderId(orderId);
				orderItemRepository.update(preOrderItem);
			}
		}
		
		Integer orderItemId = orderItemRepository.insert(orderItem).getId();
		for (OrderTopping orderTopping : orderToppingList) {
			orderTopping.setOrderItemId(orderItemId);
			if (orderItemForm.getOrderToppingIdList().size() != 0) {
				orderToppingRepository.insert(orderTopping);
			}
			System.err.println("追加登録");
		}
	}

	/**
	 * ショッピングカートのアイテムを消去するメソッド
	 * 
	 * @param orderItemid orderItemId
	 */
	public void deteleOrder(Integer orderItemid) {
		orderToppingRepository.delete(orderItemid);
		orderItemRepository.delete(orderItemid);

	}
}
