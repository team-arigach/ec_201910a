package jp.co.example.ecommerce_a.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_a.domain.Order;
import jp.co.example.ecommerce_a.service.OrderHistoryService;

/**
 * 注文履歴を操作するコントローラー.
 * 
 * @author takahiro.suzuki
 *
 */
@Controller
@RequestMapping("")
public class OrderHistoryController {
	
	@Autowired
	private OrderHistoryService orderHistoryService;
	
	/**
	 * 注文履歴を表示.
	 * 
	 * @return 注文履歴ページ
	 */
	@RequestMapping("/orderHistory")
	public String toOrderHistory(Model model, Integer userId, Integer status) {
		if(status == 1 || status == 2) {
			Order order = orderHistoryService.showOrderHistory(userId, status);
			model.addAttribute("order", order);
		}
		return "order_history";
	}

}
