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
import jp.co.example.ecommerce_a.domain.OrderItem;
import jp.co.example.ecommerce_a.domain.OrderTopping;
import jp.co.example.ecommerce_a.form.CatchToppingForm;
import jp.co.example.ecommerce_a.form.CreditInfoForm;
import jp.co.example.ecommerce_a.form.OrderForm;
import jp.co.example.ecommerce_a.form.OrderItemForm;
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
		sortItemService.sortOrderItemByM(order);
		sortItemService.sortOrderItemByL(order);
		order = showShoppingCartService.showShoppingCart(loginUser.getUser().getId(), 0);

		for (OrderItem oi : order.getOrderItemList()) {
			oi.setNonOrderToppingList(sortItemService.setOrderItemNonTopping(oi));
		}

		order.setUser(orderService.setUser(order.getUserId()));
		model.addAttribute("order", order);
		model.addAttribute("orderForm",orderService.setOrderForm(order.getUser()));

		// quantityに表示する要素数
		int[] count = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 };

		// 配達時間の表示
		List<Integer> deliveryTimeList = new ArrayList<>();
		for (int i = 10; i <= 21; i++) {
			deliveryTimeList.add(i);
		}
		model.addAttribute("toppingListAll", orderService.toppingList());
		model.addAttribute("deliveryTimeList", deliveryTimeList);
		model.addAttribute("count", count);
		return "order_confirm";
	}

	/**
	 * 注文する.
	 * 
	 * @param orderForm 注文フォーム
	 * @param result    BindingResult
	 * @param model     リクエストスコープ
	 * @return エラー出たら注文確認画面に戻り、そうでなければ注文完了画面へリダイレクト
	 */
	@RequestMapping("/input")
	public String order(@Validated OrderForm orderForm
			, BindingResult result
			,OrderItemForm orderItemForm
			, CreditInfoForm creditInfoForm
			, Model model
			, @AuthenticationPrincipal LoginUser loginUser) {
	
		System.err.println(orderForm);
		LocalDate localDate = orderForm.convertLocalDate(orderForm.getDeliveryTime());
		int year = localDate.getYear();
		int month = localDate.getMonthValue();
		int date = localDate.getDayOfMonth();
		int hour = orderForm.getDeliveryHour();
		int minute = 0;
		LocalDateTime localDateTime = LocalDateTime.of(year, month, date, hour, minute);
		LocalDateTime localDateTimeNow = LocalDateTime.now().plusHours(1);
		boolean isAfter = localDateTime.isAfter(localDateTimeNow);
		
		if(!(isAfter)) {
			result.rejectValue("deliveryTime", "", "現在時刻の１時間以降を選択してください");
		}
		
		if(result.hasErrors()) {
			return index(model, loginUser);
		}
		Order order = new Order();
		BeanUtils.copyProperties(orderForm, order);
		// クレジット処理に関して
		if (orderForm.getPaymentMethod() == 2) {
			CreditInfo creditInfo = new CreditInfo();
			BeanUtils.copyProperties(creditInfoForm, creditInfo);
			if (!creditInfoService.isCheckCreditInfo(creditInfo)) {
				model.addAttribute("creditError", "カード情報が正しくありません。");
				return index(model, loginUser);
			}
		}

		// パラメータで取得したdeliverryTimeとdeliveryHourTimestamp型に変換してOrderオブジェクトにセット
		//LocalDate localDate = orderForm.convertLocalDate(orderForm.getDeliveryTime());
		//int year = localDate.getYear();
		//int month = localDate.getMonthValue();
		//int date = localDate.getDayOfMonth();
		//int hour = orderForm.getDeliveryHour();
		//int minute = 0;
		//LocalDateTime localDateTime = LocalDateTime.of(year, month, date, hour, minute);
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

	/**
	 * 数量を変更する.
	 * 
	 * @param id       オーダーアイテムID
	 * @param quantity 数量
	 * @return 注文画面
	 */
	@RequestMapping("/postQuantity")
	public String postQuantity(Integer id, Integer quantity) {
		OrderItem orderItem = orderService.loadOrderItem(id);
		orderItem.setQuantity(quantity);
		orderService.updateOrderItem(orderItem);
		return "redirect:/order";
	}

	/**
	 * トッピングを変更する.
	 * @param form フォーム
	 * @return 注文画面
	 */
	@RequestMapping("/postTopping")
	public String postTopping(CatchToppingForm form) {
		List<OrderTopping> orderToppingList = orderService.orderToppingList(form.getOrderItemId());
		for (OrderTopping orderTopping : orderToppingList) {
			orderService.orderToppingDelete(orderTopping.getId());
		}
		List<Integer> toppingIdList = form.getCheckedTopping();
		if (toppingIdList != null) {
			for (Integer toppingId : toppingIdList) {
				OrderTopping orderTopping = new OrderTopping();
				orderTopping.setOrderItemId(form.getOrderItemId());
				orderTopping.setToppingId(toppingId);
				orderService.InsertOrderTopping(orderTopping);
			}
		}
		List<Integer> newToppingIdList = form.getNewCheck();
		if (newToppingIdList != null) {
			for (Integer toppingId : newToppingIdList) {
				OrderTopping orderTopping = new OrderTopping();
				orderTopping.setOrderItemId(form.getOrderItemId());
				orderTopping.setToppingId(toppingId);
				orderService.InsertOrderTopping(orderTopping);
			}
		}
		return "redirect:/order";
	}

}
