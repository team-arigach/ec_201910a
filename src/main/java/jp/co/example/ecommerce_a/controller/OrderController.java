package jp.co.example.ecommerce_a.controller;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_a.domain.CreditInfo;
import jp.co.example.ecommerce_a.domain.LoginUser;
import jp.co.example.ecommerce_a.domain.Order;
import jp.co.example.ecommerce_a.form.CreditInfoForm;
import jp.co.example.ecommerce_a.form.OrderForm;
import jp.co.example.ecommerce_a.service.AddShoppingCartService;
import jp.co.example.ecommerce_a.service.CreditInfoService;
import jp.co.example.ecommerce_a.service.MailSenderService;
import jp.co.example.ecommerce_a.service.OrderService;
import jp.co.example.ecommerce_a.service.ShowShoppingCartService;
import jp.co.example.ecommerce_a.service.SortItemService;


@Controller
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private MailSenderService mailSenderService;
	
	@Autowired
	private CreditInfoService creditInfoService;
	
	@Autowired
	private ShowShoppingCartService showShoppingCartService;
	
	@Autowired
	private SortItemService sortItemService;
	
	@Autowired
	private AddShoppingCartService addShoppingCartService;
	
	@ModelAttribute
	public OrderForm setUpOrderForm() {
		return new OrderForm();
	}
	
	/**
	 * 注文確認画面を表示する.
	 * 
	 * @param model リクエストスコープ
	 * @return 注文確認画面
	 */
	@RequestMapping("")
	public String index(Model model, @AuthenticationPrincipal LoginUser loginUser) {
		
		addShoppingCartService.addShoppingCart(loginUser.getUser().getId());
		Order order = showShoppingCartService.showShoppingCart(loginUser.getUser().getId(), 0);
		sortItemService.sortOrderItem(order);
		
		model.addAttribute("order", order);
		
		List<Integer> deliveryTimeList = new ArrayList<>();
		for( int i = 10; i <= 21; i++){
			deliveryTimeList.add(i);
		}
		model.addAttribute("deliveryTimeList", deliveryTimeList);
		
		return "order_confirm";
	}
	
	/**
	 * 注文する.
	 * 
	 * @param orderForm 注文フォーム
	 * @param result BindingResult
	 * @param model リクエストスコープ
	 * @return エラー出たら注文確認画面に戻り、そうでなければ注文完了画面へリダイレクト
	 */
	@RequestMapping("/input")
	public String order(@Validated OrderForm orderForm, BindingResult result, CreditInfoForm creditInfoForm, Model model, @AuthenticationPrincipal LoginUser loginUser) {
		if(result.hasErrors()) {
			return index(model, loginUser);
		}
		Order order = new Order();
		BeanUtils.copyProperties(orderForm, order);
		// クレジット処理に関して
		if( orderForm.getPaymentMethod() == 2) {
			CreditInfo creditInfo = new CreditInfo();
			BeanUtils.copyProperties(creditInfoForm, creditInfo);
			if ( !creditInfoService.isCheckCreditInfo(creditInfo)) {
				model.addAttribute("creditError", "カード情報が正しくありません。");
				return index(model, loginUser);
			}
		}
		
		//パラメータで取得したdeliverryTimeとdeliveryHourTimestamp型に変換してOrderオブジェクトにセット
		LocalDate localDate = orderForm.convertLocalDate(orderForm.getDeliveryTime());
		int year = localDate.getYear();
		int month = localDate.getMonthValue();
		int date = localDate.getDayOfMonth();
		int hour = orderForm.getDeliveryHour();
		int minute = 0;
		LocalDateTime localDateTime = LocalDateTime.of(year, month, date, hour, minute);
		Timestamp timestamp = Timestamp.valueOf(localDateTime);
		order.setDeliveryTime(timestamp);
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
		System.err.println("メールを送信する。");
		mailSenderService.send();
		System.err.println("メールの送信完了");
		return "order_finished";
	}
}
