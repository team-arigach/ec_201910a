package jp.co.example.ecommerce_a.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 注文履歴を操作するコントローラー.
 * 
 * @author takahiro.suzuki
 *
 */
@Controller
@RequestMapping("")
public class OrderHistoryController {
	
	/**
	 * 注文履歴を表示.
	 * 
	 * @return 注文履歴ページ
	 */
	@RequestMapping("/orderHistory")
	public String toOrderHistory() {
		return "order_history";
	}

}
