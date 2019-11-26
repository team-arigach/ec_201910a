package jp.co.example.ecommerce_a.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.example.ecommerce_a.domain.Item;

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
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	/**
	 * 商品メニュー一覧を取得します.
	 * 
	 * @return 商品メニュー一覧を返します。
	 */
	public List<Item> findAll(){
		String sql = "SELECT id,name,description,price_m,price_l,image_path,deleted FROM items";
		List<Item> itemList = template.query(sql, ITEM_ROW_MAPPER);
		return itemList;
	}
	
	/**
	 * 商品メニューの曖昧検索を取得します.
	 * 
	 * @param name 絞り込み検索の条件
	 * @return　商品情報リスト
	 */
	public List<Item> findByLikeName(String name){
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id,name,description,price_m,price_l,image_path,deleted FROM items WHERE name LIKE :name");
		String escName = "%" + name + "%";
		SqlParameterSource param = new MapSqlParameterSource().addValue("name", escName);
		List<Item> itemList = template.query(sql.toString(), param,ITEM_ROW_MAPPER);
		return itemList;
	}
	
	/**
	 * 主キーから商品情報を取得します.
	 *
	 * @param id ID
	 * @return 商品情報
	 */
	public Item laod(Integer id) {
		String  sql = "SELECT id, name, description, price_m, price_l, image_path, deleted FROM items WHERE id = :id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		Item item = template.queryForObject(sql, param, ITEM_ROW_MAPPER);
		return item;
	}
}
