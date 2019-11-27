package jp.co.example.ecommerce_a.form;

import java.util.List;

import jp.co.example.ecommerce_a.domain.OrderTopping;

public class OrderItemForm {
	
	private String quanity;
	private String size;
	private String itemId;
	private List<OrderTopping> orderToppingList;
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
	public List<OrderTopping> getOrderToppingList() {
		return orderToppingList;
	}
	public void setOrderToppingList(List<OrderTopping> orderToppingList) {
		this.orderToppingList = orderToppingList;
	}
	@Override
	public String toString() {
		return "OrderItemForm [quanity=" + quanity + ", size=" + size + ", itemId=" + itemId + ", orderToppingList="
				+ orderToppingList + "]";
	}
	
	

}
