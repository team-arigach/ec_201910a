package jp.co.example.ecommerce_a.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.example.ecommerce_a.domain.Order;
import jp.co.example.ecommerce_a.domain.OrderItem;
import jp.co.example.ecommerce_a.domain.OrderTopping;
import jp.co.example.ecommerce_a.domain.Topping;
import jp.co.example.ecommerce_a.repository.OrderItemRepository;
import jp.co.example.ecommerce_a.repository.OrderRepository;
import jp.co.example.ecommerce_a.repository.OrderToppingRepository;
import jp.co.example.ecommerce_a.repository.ToppingRepository;

@Service
@Transactional
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private ToppingRepository toppingRepository;
	
	@Autowired
	private OrderToppingRepository orderToppingRepository;
	
	/**
	 * 注文する.
	 * 
	 * @param order　注文情報
	 */
	public void order(Order order) {
		Order updateOrder = orderRepository.load(order.getId());
		// DBから取得したオーダーのuser_idを取得し、インサートするオーダーにセット
		order.setUserId(updateOrder.getUserId());
		// DBから取得したオーダーの合計金額を取得し、インサートするオーダーにセット
		order.setTotalPrice(updateOrder.getCalcTotalPrice());
		// 現在時刻を取得し、オーダーにセット
		Date date = new Date();
		order.setOrderDate(date);
		if(order.getPaymentMethod() == 1) {
			order.setStatus(1);
		}
		if(order.getPaymentMethod() == 2) {
			order.setStatus(2);
		}
		System.err.println("最終情報構成=>" + order);
		orderRepository.update(order);
	}
	
	/**
	 * オーダーIDから取得.
	 * @param id ID
	 * @return IDで検索されたオーダー
	 */
	public Order showOrder(Integer id) {
		return orderRepository.load(id);
	}
	
	/**
	 * 
	 * OrderItemをIDで検索し、表示する.
	 * @param id ID
	 * @return 検索されたオーダーアイテム
	 */
	public OrderItem loadOrderItem(Integer id) {
		return orderItemRepository.load(id);
	}
	
	/**
	 * 
	 * 指定されたオーダーアイテムを更新.
	 * @param orderItem オーダーアイテム
	 * 
	 */
	public void updateOrderItem(OrderItem orderItem) {
		orderItemRepository.update(orderItem);
	}
	
	public List<Topping> toppingList(){
		return toppingRepository.findAll();
	}
	
	/**
	 * オーダーアイテムIDで、トッピングを検索.
	 * 
	 * @param orderItemId オーダーアイテムID
	 * @return 検索されたIDのオーダーアイテムList
	 */
	public List<OrderTopping> orderToppingList(Integer orderItemId){
		return orderToppingRepository.findByOrderItemId(orderItemId);
	}
	
	/**
	 * オーダートッピングをIDで消去.
	 * 
	 * @param id オーダートッピングID
	 */
	public void orderToppingDelete(Integer id) {
		orderToppingRepository.deleteByPk(id);
	}
	
	/**
	 * オーダートッピングをインサートする.
	 * @param orderTopping オーダートッピング
	 */
	public void InsertOrderTopping(OrderTopping orderTopping) {
		orderToppingRepository.insert(orderTopping);
	}
	
}
