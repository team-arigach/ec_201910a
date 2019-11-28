package jp.co.example.ecommerce_a.form;

import java.util.List;



public class OrderItemForm {

	/** 決済方法 */
	private String quanity;
	/** サイズ */
	private String size;
	/** ID */
	private String itemId;
	/** トッピングIDリスト */
	private List<Integer> orderToppingIdList;

	/**
	 * アイテムIDをInteger型で返すメソッド.
	 * 
	 * @return itemId
	 */
	public Integer getIntItemId() {
		return Integer.parseInt(itemId);
	}

	/**
	 * サイズをCharacter型で返すメソッド.
	 * 
	 * @return size
	 */
	public Character getCharSize() {
		return size.charAt(0);
	}

	public String getQuanity() {
		return quanity;
	}

	public void setQuanity(String quanity) {
		this.quanity = quanity;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public List<Integer> getOrderToppingIdList() {
		return orderToppingIdList;
	}

	public void setOrderToppingIdList(List<Integer> orderToppingIdList) {
		this.orderToppingIdList = orderToppingIdList;
	}

	@Override
	public String toString() {
		return "OrderItemForm [quanity=" + quanity + ", size=" + size + ", itemId=" + itemId + ", orderToppingIdList="
				+ orderToppingIdList + "]";
	}




}
