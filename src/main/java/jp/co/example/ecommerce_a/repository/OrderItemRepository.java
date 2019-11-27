package jp.co.example.ecommerce_a.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.example.ecommerce_a.domain.OrderItem;

@Repository
public class OrderItemRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
//	private static final RowMapper<OrderItem> ORDER_ITEM_ROW_MAPPER = (rs,i) ->{
//		OrderItem orderItem = new OrderItem();
//		orderItem.setId();
//		
//		
//		return orderItem;
//	};


	public void insert(OrderItem orderItem) {
		String sql = "INSERT INTO order_items(item_id,order_id,quantity,size) VALUES(:itemId,:orderId,:quantity,:size)";
		SqlParameterSource param = new BeanPropertySqlParameterSource(orderItem);
		template.update(sql, param);
	}
	
	

	
}
