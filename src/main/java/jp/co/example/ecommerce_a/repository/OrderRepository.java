package jp.co.example.ecommerce_a.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.example.ecommerce_a.domain.Order;
import jp.co.example.ecommerce_a.domain.OrderItem;
import jp.co.example.ecommerce_a.domain.OrderTopping;

@Repository
public class OrderRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	/**
	 * オーダー(注文)オブジェクトを操作するRowmapper.
	 */
	private final static ResultSetExtractor<List<Order>> ORDER_ROW_MAPPER = (rs) -> {
		int preId = 0;
		int preOrderItemId = 0;
		List<OrderItem> orderItemList = null;
		List<OrderTopping> orderToppingList = null;
		List<Order> orderList = new ArrayList<>();
		while( rs.next()) {
			if(preId != rs.getInt("id")) {
				Order order = new Order();
				order.setId(rs.getInt("id"));
				order.setUserId(rs.getInt("user_id"));
				order.setStatus(rs.getInt("status"));
				order.setTotalPrice(rs.getInt("total_price"));
				order.setOrderDate(rs.getDate("order_date"));
				order.setDestinationName(rs.getString("destination_name"));
				order.setDestinationEmail(rs.getString("destination_email"));
				order.setDestinationZipcode(rs.getString("destination_zipcode"));
				order.setDestinationAddress(rs.getString("destination_address"));
				order.setDestinationTel(rs.getString("destination_tel"));
				order.setPaymentMethod(rs.getInt("payment_method"));
				order.setDeliveryTime(rs.getTimestamp("delivery_time"));
				orderItemList = new ArrayList<>();
				order.setOrderItemList(orderItemList);
				orderList.add(order);
				preId = order.getId();
			}
			if( rs.getInt("order_item_id") != 0 || rs.getInt("order_id") != preOrderItemId ) {
				OrderItem orderItem = new OrderItem();
				orderItem.setId(rs.getInt("id"));
				orderItem.setItemId(rs.getInt("item_id"));
				orderItem.setQuantity(rs.getInt("quantity"));
				orderItem.setOrderId(rs.getInt("order_id"));
				orderItem.setSize(rs.getString("size").charAt(0));
				orderToppingList = new ArrayList<>();
				orderItem.setOrderToppingList(orderToppingList);
				orderItemList.add(orderItem);
				preOrderItemId = rs.getInt("order_id");
			}
			if( rs.getInt("order_topping_id") != 0) {
				OrderTopping orderTopping = new OrderTopping();
				orderTopping.setId(rs.getInt("order_topping_id"));
				orderTopping.setOrderItemId(rs.getInt("order_item_id"));
				orderTopping.setToppingId(rs.getInt("topping_id"));
				orderToppingList.add(orderTopping);
			}
		}
		return orderList;
	};
	
	/**
	 * @param id
	 * @return
	 */
	public Order load(Integer id) {
		String sql = "SELECT o.id AS id, "
				+ "o.user_id, "
				+ "o.status, "
				+ "total_price, "
				+ "order_date, "
				+ "destination_name, "
				+ "destination_email, "
				+ "destination_zipcode, "
				+ "destination_address, "
				+ "destination_tel, "
				+ "delivery_time, "
				+ "payment_method, "
				+ "oi.id AS order_item_id, "
				+ "oi.item_id, "
				+ "t.id AS order_topping_id, "
				+ "t.topping_id, "
				+ "t.order_item_id AS order_item_id"
				+ "FROM orders o "
				+ "LEFT OUTER JOIN "
				+ "order_items oi "
				+ "ON o.id = oi.order_id "
				+ "LEFT OUTER JOIN order_toppings t "
				+ "ON t.order_item_id = oi.id "
				+ "ORDER BY o.id ,oi.id;";
		List<Order> orderList = template.query(sql, ORDER_ROW_MAPPER);
		if( orderList.size() > 0) {
			return orderList.get(0);
		}
		return null;
	}

}
