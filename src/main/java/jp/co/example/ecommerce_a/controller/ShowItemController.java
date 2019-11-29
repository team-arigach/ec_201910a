package jp.co.example.ecommerce_a.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_a.domain.Item;
import jp.co.example.ecommerce_a.domain.LoginUser;
import jp.co.example.ecommerce_a.service.AddShoppingCartService;
import jp.co.example.ecommerce_a.service.ShowItemListService;

/**
 * 商品一覧の表示と商品検索一覧を表示するコントローラー.
 * 
 * @author suzukiryouhei
 *
 */
@Controller
@RequestMapping("")
public class ShowItemController {
	
	@Autowired
	private ShowItemListService showItemListService;
	@Autowired
	private AddShoppingCartService addShoppingCartService;	
	@Autowired
	private HttpSession session;
	
	/**
	 * 商品一覧を表示する.
	 * 
	 * @param name 検索するワード
	 * @param model
	 * @return 商品全件画面か検索結果画面
	 */
	@RequestMapping("/")
	public String showItemList(String name, Model model, @AuthenticationPrincipal LoginUser loginUser) {
		List<List<Item>> bigItemList = showItemListService.findByLikeName(name);
		// 空文字かnullだった場合エラメッセージを表示
		if(bigItemList.isEmpty()) {
			model.addAttribute("message", "該当する商品はありません");
			bigItemList = showItemListService.findByLikeName("");
		}
		model.addAttribute("bigItemList", bigItemList);
		
		if(loginUser != null && session.getAttribute("userId") != null) {
			addShoppingCartService.addShoppingCart(loginUser.getUser().getId());	
		}
		
		// オートコンプリート用に配列の中身を文字列で作ってスコープへ格納
		StringBuilder itemListForAutocomplete = showItemListService.getItemListForAutocomplete(name);
		model.addAttribute("itemListForAutocomplete", itemListForAutocomplete);
		
		return "item_list";
	}
		
		@RequestMapping("/showItemListAbout8")
		public String showItemListAbout8(String name, Model model, @AuthenticationPrincipal LoginUser loginUser, Integer page) {
			List<List<Item>> bigItemList = showItemListService.findByLikeName(name);
			if(bigItemList.isEmpty()) {
				model.addAttribute("message", "該当する商品はありません");
				bigItemList = showItemListService.findByLikeName("");
			}
			model.addAttribute("bigItemList", bigItemList);
			
			// オートコンプリート用にJavaScriptの配列の中身を文字列で作ってスコープへ格納
			StringBuilder itemListForAutocomplete = showItemListService.getItemListForAutocomplete(name);
			model.addAttribute("itemListForAutocomplete", itemListForAutocomplete);
			
			return "item_list";
	}
	
}
