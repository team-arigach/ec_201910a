package jp.co.example.ecommerce_a.repository;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
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
	

	
}
