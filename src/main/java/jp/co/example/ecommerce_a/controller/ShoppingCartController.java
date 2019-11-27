package jp.co.example.ecommerce_a.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_a.form.OrderItemForm;
import jp.co.example.ecommerce_a.service.InsertShoppingCartService;

//import jp.co.example.ecommerce_a.service.InsertShoppingCartService;

@Controller
@RequestMapping("/shoppingCart")
public class ShoppingCartController {

	@Autowired
	private InsertShoppingCartService insertShoppingService;

//	@Autowired
	// private InsertShoppingCartService insertShoppingCartService;

	@RequestMapping("")
	public String additem(OrderItemForm orderItemForm) {
		insertShoppingService.insertOrder(orderItemForm);
		

//	@RequestMapping("/insert")
//	public String insert(OrderItemForm orderItemForm) {
//		insertShoppingCartService.insertOrder(session, order);
//		Order order = new Order();
//
//		return "";
//	}
		return "cart_list";
	}

}
