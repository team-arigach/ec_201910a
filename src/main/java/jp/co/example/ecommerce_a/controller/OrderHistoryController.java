package jp.co.example.ecommerce_a.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_a.domain.LoginUser;
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
	 * 注文履歴画面を表示する.
	 * 
	 * @param model　リクエストスコープ
	 * @param order　注文情報
	 * @param loginUser　ログインユーザー
	 * @return　注文履歴画面
	 */
	@RequestMapping("/orderHistory")
	public String toOrderHistory(Model model, @AuthenticationPrincipal LoginUser loginUser) {
		Integer userId = loginUser.getUser().getId();
		List<Order> orderList = orderHistoryService.showOrderHistory(userId);
		System.err.println("orderList-> "+orderList);
		System.err.println("userId -> " + userId);
		for (int i = 0; i < orderList.size(); i++) {
			Integer status = orderList.get(i).getStatus();
			if(!(status == 1 || status == 2)) {
				orderList.remove(i);
			}
		}
		System.err.println("交信後のorderList-> "+orderList);
		model.addAttribute("orderList", orderList);
		return "order_history";
	}
	
	@RequestMapping("/toShowItemList")
	public String toShowItemList() {
		return "item_list";
	}
}
