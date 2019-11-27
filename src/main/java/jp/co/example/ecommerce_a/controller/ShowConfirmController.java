package jp.co.example.ecommerce_a.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_a.domain.Order;
import jp.co.example.ecommerce_a.domain.OrderItem;
import jp.co.example.ecommerce_a.domain.OrderTopping;
import jp.co.example.ecommerce_a.service.ShowConfirmService;
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
	
	@Autowired
	private ShowConfirmService showConfirmService;
	
	/**
	 * 購入商品を表示する.
	 * 
	 * @param userId　ログインid
	 * @param model
	 * @return　商品確認画面
	 */
	@RequestMapping("/showConfirm")
	public String showConfirm(Integer userId, Model model) {
		
		Order order = testDataService.testOrder();
		
		
		model.addAttribute("order",order);
		System.out.println(order);
		return "order_confirm"; 
	}
}
