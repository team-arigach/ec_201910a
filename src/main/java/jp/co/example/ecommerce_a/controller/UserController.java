package jp.co.example.ecommerce_a.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class UserController {
	
	@RequestMapping("/login")
	public String toLogin() {
		return "login";
	}
	
	@RequestMapping("")
	public String login(String email, String password) {
		
		return "redirect:/item_list";
	}
	
	
	
	

}
