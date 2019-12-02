package jp.co.example.ecommerce_a.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
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
		String sql = "SELECT id,name,description,price_m,price_l,image_path,deleted FROM items ORDER BY price_m";
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
		sql.append("SELECT id,name,description,price_m,price_l,image_path,deleted FROM items WHERE name ILIKE :name");
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
	
	/**
	 * 商品登録を行う.
	 * @param item 商品
	 */
	public void insert(Item item) {
		String sql = "INSERT INTO items (id, name, description, price_m, price_l, image_path, deleted) VALUES (:id, :name, :description, :priceM, :priceL, :imagePath, :deleted);";
		SqlParameterSource param = new BeanPropertySqlParameterSource(item);
		template.update(sql, param);
		System.err.println("一件の登録が完了しました。");
	}
	

	/**
	 * @param offSet
	 * @return
	 */
	public List<Item> findAllAboutSum(Integer limit,Integer offSet){
		String sql = "SELECT id,name,description,price_m,price_l,image_path,deleted FROM items limit :limit offset :offSet";
		SqlParameterSource param = new MapSqlParameterSource().addValue("limit", limit).addValue("offSet", offSet);
		List<Item> itemList = template.query(sql,param, ITEM_ROW_MAPPER);
		return itemList;
	}
	
	/**
	 * @param name
	 * @param offSet
	 * @return
	 */
	public List<Item> findByLikeNameAboutSum(String name,Integer limit, Integer offSet){
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id,name,description,price_m,price_l,image_path,deleted FROM items WHERE name LIKE :name limit :limit offset :offSet");
		String escName = "%" + name + "%";
		SqlParameterSource param = new MapSqlParameterSource().addValue("name", escName).addValue("limit", limit).addValue("offSet", offSet);
		List<Item> itemList = template.query(sql.toString(), param,ITEM_ROW_MAPPER);
		return itemList;
	}
}
