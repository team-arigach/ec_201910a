package jp.co.example.ecommerce_a.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_a.domain.Item;
import jp.co.example.ecommerce_a.service.ShowItemDetailService;

/**
 * 商品詳細表示の処理を行うコントローラ.
 * 
 * @author yukiando
 *
 */
@Controller
@RequestMapping("detail")
public class ShowItemDetailController {

	@Autowired
	private ShowItemDetailService showItemDetailService;
	
	/**
	 * 商品詳細を表示します.
	 * 
	 * @param id ID
	 * @return 商品詳細ページ
	 */
	@RequestMapping("")
	public String showItemDetail(Integer id, Model model) {
		Item item = showItemDetailService.showItemDetail(id);
		model.addAttribute("item", item);
		return "item_detail";
	}
	
	/**
	 * ショッピングカートへ遷移します.
	 * 
	 * @return　ショッピングカートページ
	 */
	@RequestMapping("toShoppingCart")
	public String toShoppingCart() {
		return "cart_list";
	}
	
}
