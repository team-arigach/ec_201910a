package jp.co.example.ecommerce_a.repository;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import jp.co.example.ecommerce_a.domain.OrderItem;

@Repository
public class OrderItemRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private SimpleJdbcInsert insert;

	@PostConstruct
	public void init() {
		SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert((JdbcTemplate) template.getJdbcOperations());
		SimpleJdbcInsert withTableName = simpleJdbcInsert.withTableName("order_items");
		insert = withTableName.usingGeneratedKeyColumns("id");
	}
	
	private static final RowMapper<OrderItem> ORDER_ITEM_ROW_MAPPER = (rs,i) ->{
		
		OrderItem orderItem = new OrderItem();
		orderItem.setId(rs.getInt("id"));
		return orderItem;
	};


	/**
	 * アイテムを追加する.
	 * 
	 * @param orderItem アイテム
	 * @return アイテム
	 */
	public OrderItem insert(OrderItem orderItem) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(orderItem);
		Number key = insert.executeAndReturnKey(param);
		orderItem.setId(key.intValue());
		return orderItem;
	}
	
	public void delete(Integer orderItemId) {
		String sql = "DELETE FROM order_items WHERE id=:orderItemId";
		SqlParameterSource param = new MapSqlParameterSource().addValue("orderItemId", orderItemId);
		template.update(sql, param);		
	}
	
	public void updateOrderId(Integer orderId) {
		String sql = "UPDATE order_items SET order_id=:orderId";
		SqlParameterSource param = new MapSqlParameterSource().addValue("orderId", orderId);
		template.update(sql, param);
	}
	

	
	public void update(Integer id) {
		String sql = "UPDATE order_id SET order_id=:Id WHERE order_id=:orderId";
		SqlParameterSource param = new MapSqlParameterSource().addValue("orderId", id).addValue("id", id);
		template.update(sql, param);
	}
	

	
}
