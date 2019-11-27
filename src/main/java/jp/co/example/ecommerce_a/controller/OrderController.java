package jp.co.example.ecommerce_a.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import jp.co.example.ecommerce_a.domain.Order;
import jp.co.example.ecommerce_a.form.CreditInfoForm;
import jp.co.example.ecommerce_a.form.OrderForm;
import jp.co.example.ecommerce_a.service.OrderService;
import jp.co.example.ecommerce_a.service.ShowShoppingCartService;

@Controller
@RequestMapping("/order")
public class OrderController {
	

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ShowShoppingCartService showShoppingCartService;
	
	@ModelAttribute
	public OrderForm setUpOrderForm() {
		return new OrderForm();
	}
	
	/**
	 * 注文確認画面を表示する.
	 * 
	 * @param model　リクエストスコープ
	 * @return　注文確認画面
	 */
	@RequestMapping("")
	public String index(Model model) {
		List<Integer> deliveryTimeList = new ArrayList<>();
		for( int i = 10; i <= 21; i++){
			deliveryTimeList.add(i);
		}
		model.addAttribute("deliveryTimeList", deliveryTimeList);
		model.addAttribute("order", showShoppingCartService.showShoppingCart(1, 0));
		
		return "order_confirm";
	}
	
	/**
	 * 注文する.
	 * 
	 * @param orderForm　注文フォーム
	 * @param result　BindingResult
	 * @param model　リクエストスコープ
	 * @return　エラー出たら注文確認画面に戻り、そうでなければ注文完了画面へリダイレクト
	 */
	@RequestMapping("/input")
	public String order(@Validated OrderForm orderForm, CreditInfoForm creditInfoForm, BindingResult result, Model model) {
		System.err.println(orderForm);
		System.err.println(creditInfoForm);
		if(result.hasErrors()) {
			return index(model);
		}
		
		Order order = new Order();
		BeanUtils.copyProperties(orderForm, order);
		System.out.println(order);
		orderService.order(order);
		return "redirect:/order/toOrderFinish";
	}
	
	/**
	 * 注文完了画面に遷移.
	 * 
	 * @return 注文完了画面
	 */
	@RequestMapping("/toOrderFinish")
	public String toOrderFinish() {
		return "order_finished";
	}
	

	
}
