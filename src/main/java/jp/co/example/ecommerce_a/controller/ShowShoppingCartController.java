package jp.co.example.ecommerce_a.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_a.domain.LoginUser;
import jp.co.example.ecommerce_a.domain.Order;
import jp.co.example.ecommerce_a.service.ShowShoppingCartService;
import jp.co.example.ecommerce_a.service.SortItemService;

@Controller
@RequestMapping("")
public class ShowShoppingCartController {

	@Autowired
	private ShowShoppingCartService showShoppingCartService;

	@Autowired
	private SortItemService sortItemService;
	
	@Autowired
	private HttpSession session;

	/**
	 * ショッピングカードを表示する.
	 * 
	 * @param model モデル
	 * @return カートのリストを表示する
	 */
	@RequestMapping("/cartList")
	public String showCartList(Model model, @AuthenticationPrincipal LoginUser loginUser) {
		Integer userId = null;
		if (loginUser != null) {
			userId = loginUser.getUser().getId();
		} else {
			userId = session.getId().hashCode();
		}
		Order order = showShoppingCartService.showShoppingCart(userId, 0);

		System.out.println(order);
		if (order != null) {
			sortItemService.sortOrderItem(order);
			if (order.getOrderItemList().size() != 0) {
				model.addAttribute("order", order);
			}
		}
		return "cart_list";
	}

	

}
