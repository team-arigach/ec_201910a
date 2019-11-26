package jp.co.example.ecommerce_a.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class ShowShoppingCartController {
	
	@RequestMapping("/cart_list")
	public String showCartList(Model model) {
		
		
		return "cart_list";
	}

}
