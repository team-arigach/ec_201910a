package jp.co.example.ecommerce_a.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_a.domain.Order;
import jp.co.example.ecommerce_a.service.ShowConfirmService;

@Controller
@RequestMapping("")
public class ShowConfirmController {
	
	@Autowired
	private ShowConfirmService showConfirmService;
	
	@RequestMapping("/showConfirm")
	public String showConfirm(Integer userId, Model model) {
		Order order = showConfirmService.showOrderConfirm(userId, 0);
		model.addAttribute("order",order);
		System.out.println(order);
		return "order_confirm"; 
	}
}
