package jp.co.example.ecommerce_a.controller;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import jp.co.example.ecommerce_a.domain.Order;
import jp.co.example.ecommerce_a.form.OrderForm;
import jp.co.example.ecommerce_a.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {
	

	@Autowired
	private OrderService orderService;
	
	@ModelAttribute
	public OrderForm setUpOrderForm() {
		return new OrderForm();
	}
	
	@RequestMapping("")
	public String index(Model model) {
		return "order_confirm";
	}
	
	/**
	 * 注文する.
	 * 
	 * @param orderForm 注文フォーム
	 * @return　注文完了画面へリダイレクト
	 */
	@RequestMapping("/input")
	public String order(OrderForm orderForm) {
		Order order = new Order();
		BeanUtils.copyProperties(orderForm, order);
		System.err.println(order);
		orderService.order(order);
		return "redirect:/order";
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
