package jp.co.example.ecommerce_a.repository;


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
	
	private static RowMapper<OrderItem> ORDER_ITEM_ROW_MAPPER = (rs,i) ->{
		
		OrderItem orderItem = new OrderItem();
		orderItem.setId(rs.getInt("id"));
		orderItem.setOrderId(rs.getInt("order_id"));
		orderItem.setQuantity(rs.getInt("quantity"));
		orderItem.setItemId(rs.getInt("item_id"));
		orderItem.setSize(rs.getString("size").charAt(0));
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
	
	public void update(OrderItem orderItem) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(orderItem);
		String sql = "UPDATE order_items SET id = :id, item_id = :itemId, order_id = :orderId, quantity = :quantity, size = :size WHERE id = :id;";
		template.update(sql, param);
	}
	
	public OrderItem load(Integer id) {
		try {
			String sql = "SELECT id, order_id, quantity, item_id, size FROM order_items WHERE id = :id;";
			SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
			return template.queryForObject(sql, param, ORDER_ITEM_ROW_MAPPER);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	

	
}
