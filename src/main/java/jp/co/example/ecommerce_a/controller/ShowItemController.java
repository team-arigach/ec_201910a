package jp.co.example.ecommerce_a.controller;

import java.util.ArrayList;
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
	 * @param name  検索するワード
	 * @param model
	 * @return 商品全件画面か検索結果画面
	 */
//	@RequestMapping("/")
//	public String showItemList(String name, Model model, @AuthenticationPrincipal LoginUser loginUser) {
//		List<List<Item>> bigItemList = showItemListService.findByLikeName(name);
//		if (bigItemList.isEmpty()) {
//			model.addAttribute("message", "該当する商品はありません");
//			bigItemList = showItemListService.findByLikeName("");
//		}
//		model.addAttribute("bigItemList", bigItemList);
//
//		if (loginUser != null && session.getAttribute("userId") != null) {
//			addShoppingCartService.addShoppingCart(loginUser.getUser().getId());
//		}
//
//		// オートコンプリート用にJavaScriptの配列の中身を文字列で作ってスコープへ格納
//		StringBuilder itemListForAutocomplete = showItemListService.getItemListForAutocomplete(name);
//		model.addAttribute("itemListForAutocomplete", itemListForAutocomplete);
//
//		return "item_list";
//	}

	@RequestMapping("/")
	public String showItemListAboutSum(String name, Integer pageNumber, Model model,
			@AuthenticationPrincipal LoginUser loginUser, Integer page,Integer check) {
		Integer count = 6; // 1ページ当たりの表示件数を設定
		Integer offSet = showItemListService.makeOffSet(pageNumber, count);
		if (pageNumber == null) {
			offSet = 0;
		}
		List<List<Item>> bigItemList = showItemListService.findByLikeNameAboutSum(name,offSet);
		List<List<Item>> bigItemList2 = showItemListService.findByLikeName(name);
		if (bigItemList.isEmpty()) {
			model.addAttribute("message", "該当する商品はありません");
			bigItemList = showItemListService.findByLikeNameAboutSum("",offSet);
			bigItemList2 = showItemListService.findByLikeName("");
		}
		
		List<Item> itemList = new ArrayList<>();
		for (List<Item> middleItemList : bigItemList2) {
			for (Item item : middleItemList) {
				itemList.add(item);
			}
		}
		Integer itemCount = itemList.size();// 抽出したデータ数を確認
		List<Integer> pageList = showItemListService.makeByPageList(count, itemCount); // 表示するページ番号を決定しリスト化
		showItemListService.makeOffSet(pageNumber, count); // 表示するoffsetの値を決める
		System.out.println(itemCount);
		System.out.println(pageList);
		System.out.println(showItemListService.makeOffSet(pageNumber, itemCount));
		System.out.println(bigItemList);
		System.out.println(bigItemList2);
		

		model.addAttribute("bigItemList", bigItemList);
		model.addAttribute("pageList", pageList);
		model.addAttribute("name",name);
		// オートコンプリート用にJavaScriptの配列の中身を文字列で作ってスコープへ格納
		StringBuilder itemListForAutocomplete = showItemListService.getItemListForAutocomplete(name);
		model.addAttribute("itemListForAutocomplete", itemListForAutocomplete);

		return "item_list";
	}

}
