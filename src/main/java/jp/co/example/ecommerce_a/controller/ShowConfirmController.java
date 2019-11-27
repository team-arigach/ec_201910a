package jp.co.example.ecommerce_a.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_a.domain.Order;
import jp.co.example.ecommerce_a.service.ShowConfirmService;

/**
 * 商品確認画面を表示するためのコントローラー.
 * 
 * @author suzukiryouhei
 *
 */
@Controller
public class ShowConfirmController {
	
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
		Order order = showConfirmService.showOrderConfirm(1, 0);
		model.addAttribute("order",order);
		System.out.println(order);
		return "order_confirm"; 
	}
}
