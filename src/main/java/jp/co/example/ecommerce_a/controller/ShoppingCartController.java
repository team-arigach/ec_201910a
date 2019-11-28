package jp.co.example.ecommerce_a.controller;


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
@RequestMapping("/shoppingCart")
public class ShoppingCartController {

	@Autowired
	private InsertShoppingCartService insertShoppingService;


	/**
	 * ショッピングカートにアイテムを登録するメソッド.
	 * 
	 * @param orderItemForm リクエストパラメータ
	 * @return ショッピングカートリストに遷移
	 */
	@RequestMapping("/registerItem")
	public String additem(OrderItemForm orderItemForm) {
		insertShoppingService.insertOrder(orderItemForm);
		

		return "cart_list";
	}

}
