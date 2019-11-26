package jp.co.example.ecommerce_a.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shoppingCart")
public class ShoppingCartController {
	
	@RequestMapping("")
	public String index() {
		return "item_detail";
	}
	
	@RequestMapping("/insert")
	public String insert() {return "";}
}
