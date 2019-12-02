package jp.co.example.ecommerce_a.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import jp.co.example.ecommerce_a.repository.ToppingRepository;

/**
 * 注文商品を表すドメイン.
 * 
 * @author takahiro.suzuki
 *
 */
public class OrderItem {
	
	@Autowired
	private ToppingRepository toppingRepository;
	
	/** 注文商品ID */
	private Integer id;
	/** 商品ID */
	private Integer itemId;
	/** 注文ID */
	private Integer orderId;
	/** 数量ID */
	private Integer quantity;
	/** サイズ */
	private Character size;
	/** 商品 */
	private Item item;
	/** 注文トッピングリスト */
	private List<OrderTopping> orderToppingList;
	/** 注文していないトッピングリスト*/
	private List<Topping> nonOrderToppingList;

	public List<Topping> getNonOrderToppingList() {
		return nonOrderToppingList;
	}

	public void setNonOrderToppingList(List<Topping> nonOrderToppingList) {
		this.nonOrderToppingList = nonOrderToppingList;
	}

	public int getSubTotal() {
		Integer toppingItems = 0;
		if (orderToppingList != null) {
			toppingItems = this.orderToppingList.size();
		}
		Integer subTotal = 0;
		if (this.size == 'M') {
			Integer toppingPrice = 200;
			subTotal = (toppingItems * toppingPrice) + (item.getPriceM() * this.quantity);
		}
		if (this.size == 'L') {
			Integer toppingPrice = 300;
			subTotal = (toppingItems * toppingPrice) + (item.getPriceL() * this.quantity);

		}

		return subTotal;
	}

	// 以下、 getter / setter
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Character getSize() {
		return size;
	}

	public void setSize(Character size) {
		this.size = size;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public List<OrderTopping> getOrderToppingList() {
		return orderToppingList;
	}

	public void setOrderToppingList(List<OrderTopping> orderToppingList) {
		this.orderToppingList = orderToppingList;
	}

	@Override
	public String toString() {
		return "OrderItem [toppingRepository=" + toppingRepository + ", id=" + id + ", itemId=" + itemId + ", orderId="
				+ orderId + ", quantity=" + quantity + ", size=" + size + ", item=" + item + ", orderToppingList="
				+ orderToppingList + ", nonOrderToppingList=" + nonOrderToppingList + "]";
	}

	

}
