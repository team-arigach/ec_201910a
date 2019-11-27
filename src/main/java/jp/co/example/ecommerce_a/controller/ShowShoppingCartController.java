package jp.co.example.ecommerce_a.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import jp.co.example.ecommerce_a.domain.Order;
import jp.co.example.ecommerce_a.service.ShowShoppingCartService;

@Controller
@RequestMapping("")
public class ShowShoppingCartController {
	
	
	@Autowired
	private ShowShoppingCartService showShoppingCartService;
	
	/**
	 * ショッピングカードを表示する.
	 * @param model モデル
	 * @return カートのリストを表示する
	 */
	@RequestMapping("/cartList")
	public String showCartList(Integer userId, Model model) {
		Order order = showShoppingCartService.showShoppingCart(18, 0);
		System.out.println(order);
		if(order != null) {
			model.addAttribute("order", order);
		}
		return "cart_list";
	}
	
	@RequestMapping("/mail")
	public String send() {
		System.err.println("メールを送信する。");
		showShoppingCartService.send();
		System.err.println("メールの送信完了");
		return null;
	}
	
	

}
