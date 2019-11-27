package jp.co.example.ecommerce_a.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_a.domain.Order;
import jp.co.example.ecommerce_a.form.OrderForm;
import jp.co.example.ecommerce_a.service.TestDataService;

/**
 * 商品確認画面を表示するためのコントローラー.
 * 
 * @author suzukiryouhei
 *
 */
@Controller
@RequestMapping("")
public class ShowConfirmController {
	
	@Autowired
	private TestDataService testDataService;
	
	@ModelAttribute
	public OrderForm setUpOrderForm() {
		return new OrderForm();
	}
	
	/**
	 * 購入商品を表示する.
	 * 
	 * @param userId　ログインid
	 * @param model
	 * @return　商品確認画面
	 */
	@RequestMapping("/showConfirm")
	public String showConfirm(Model model) {
		
		Order order = testDataService.testOrder();
		model.addAttribute("order",order);
		System.out.println(order);
		return "order_confirm"; 
	}
}
