package jp.co.example.ecommerce_a.form;

import java.util.List;

public class CatchToppingForm {
	
	private Integer orderItemId;
	/** すでに選択しているトッピング */
	private List<Integer> checkedTopping;
	/** これから選択するトッピング */
	private List<Integer> newCheck;
	
	public Integer getOrderItemId() {
		return orderItemId;
	}
	public void setOrderItemId(Integer orderItemId) {
		this.orderItemId = orderItemId;
	}
	
	public List<Integer> getCheckedTopping() {
		return checkedTopping;
	}
	public void setCheckedTopping(List<Integer> checkedTopping) {
		this.checkedTopping = checkedTopping;
	}
	public List<Integer> getNewCheck() {
		return newCheck;
	}
	public void setNewCheck(List<Integer> newCheck) {
		this.newCheck = newCheck;
	}
	@Override
	public String toString() {
		return "CatchToppingForm [orderItemId=" + orderItemId + ", checkedTopping=" + checkedTopping + ", newCheck="
				+ newCheck + "]";
	}
	
	
	

}
