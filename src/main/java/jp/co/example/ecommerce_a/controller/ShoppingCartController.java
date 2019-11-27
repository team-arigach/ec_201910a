package jp.co.example.ecommerce_a.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_a.domain.Order;
import jp.co.example.ecommerce_a.form.OrderItemForm;
import jp.co.example.ecommerce_a.service.InsertShoppingCartService;

@Controller
@RequestMapping("/shoppingCart")
public class ShoppingCartController {

	@Autowired
	private HttpSession session;
	private InsertShoppingCartService insertShoppingCartService;
	
	@RequestMapping("")
	public String index(HttpSession session) {
		System.out.println(session.getId());
		return "item_detail";
	}

//	@RequestMapping("/insert")
//	public String insert(OrderItemForm orderItemForm) {
//		insertShoppingCartService.insertOrder(session, order);
//		Order order = new Order();
//
//		return "";
//	}
}
