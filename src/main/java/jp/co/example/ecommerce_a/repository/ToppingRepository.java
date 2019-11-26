package jp.co.example.ecommerce_a.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.example.ecommerce_a.domain.Topping;

/**
 * toppingsテーブルを操作するリポジトリ
 * 
 * @author yukiando
 *
 */
@Repository
public class ToppingRepository {

	public static final RowMapper<Topping> TOPPING_ROW_MAPPER = (rs, i) -> {
		Topping topping = new Topping();
		topping.setId(rs.getInt("id"));
		topping.setName(rs.getString("name"));
		topping.setPriceM(rs.getInt("price_m"));
		topping.setPriceL(rs.getInt("price_l"));
		return topping;
	};
	
	@Autowired
	private NamedParameterJdbcTemplate template; 
	
	/**
	 * IDの昇順でトッピング情報を全件取得します.
	 * 
	 * @return トッピング情報リスト
	 */
	public List<Topping> findAll(){
		String sql = "SELECT id, name, price_m, price_l FROM toppings ORDER BY id";
		List<Topping> toppingList = template.query(sql, TOPPING_ROW_MAPPER);
		return toppingList;
	}
}
