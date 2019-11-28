package jp.co.example.ecommerce_a.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_a.form.OrderItemForm;
import jp.co.example.ecommerce_a.service.InsertShoppingCartService;

//import jp.co.example.ecommerce_a.service.InsertShoppingCartService;

/**
 * ショッピングカートにアイテムを登録するコントローラ.
 * 
 * @author yosuke.yamada
 *
 */
@Controller
@RequestMapping("")
public class ShoppingCartController {

	@Autowired
	private InsertShoppingCartService insertShoppingService;


	/**
	 * ショッピングカートにアイテムを登録するメソッド.
	 * 
	 * @param orderItemForm リクエストパラメータ
	 * @return ショッピングカートリストに遷移
	 */
	@RequestMapping("/shoppingCart")
	public String additem(OrderItemForm orderItemForm) {
		orderItemForm.setItemId("2");
		orderItemForm.setQuanity("2");
		orderItemForm.setSize("M");
		List<Integer> orderToppingIdList = new ArrayList<>();
		orderToppingIdList.add(2);
		orderToppingIdList.add(3);
		orderToppingIdList.add(4);
		orderItemForm.setOrderToppingIdList(orderToppingIdList);
		insertShoppingService.insertOrder(orderItemForm);
		

		return "redirect:/cartList";
	}

}
