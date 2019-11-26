package jp.co.example.ecommerce_a.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.example.ecommerce_a.domain.OrderTopping;

/**
 * オーダートッピングオブジェクトを操作するリポジトリ.
 * 
 * @author takahiro.suzuki
 *
 */
@Repository
public class OrderToppingRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	/**
	 * オーダートッピングオブジェクトを操作するRowMapper.
	 */
	private final static RowMapper<OrderTopping> ORDER_TOPPING_ROW_MAPPER = (rs, i) -> {
		OrderTopping orderTopping = new OrderTopping();
		orderTopping.setId(rs.getInt("id"));
		orderTopping.setOrderItemId(rs.getInt("item_id"));
		orderTopping.setToppingId(rs.getInt("topping_id"));
		return orderTopping;
	};
	
	public OrderTopping load(Integer id) {
		String sql = "SELECT id, order_item_id, topping_id FROM order_toppings WHERE id = :id;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		OrderTopping orderTopping = template.queryForObject(sql, param, ORDER_TOPPING_ROW_MAPPER);
		return orderTopping;
	}

}