package jp.co.example.ecommerce_a.repository;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jp.co.example.ecommerce_a.domain.Item;
import jp.co.example.ecommerce_a.domain.Topping;

/**
 * Itemテーブルを操作するレポジトリ.
 * 
 * @author suzukiryouhei
 *
 */
@Repository
public class ItemRepository {

	public static final RowMapper<Item> ITEM_ROW_MAPPER = (rs,i) -> {
		Item item = new Item();
		item.setId(rs.getInt("id"));
		item.setName(rs.getString("name"));
		item.setDescription(rs.getString("description"));
		item.setPriceM(rs.getInt("price_m"));
		item.setPriceL(rs.getInt("price_l"));
		item.setImagePath(rs.getString("image_path"));
		item.setDeleted(rs.getBoolean("deleted"));
		return item;
	};
	
}
