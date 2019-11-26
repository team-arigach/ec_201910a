package jp.co.example.ecommerce_a.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_a.domain.Item;
import jp.co.example.ecommerce_a.service.ShowItemListService;

@Controller
@RequestMapping("")
public class ShowItemController {
	@Autowired
	private ShowItemListService showItemListService;
	
	@RequestMapping("/")
	public String showItemList(String name, Model model) {
		List<List<Item>> bigItemList = showItemListService.findByLikeName(name);
		if(bigItemList.isEmpty()) {
			model.addAttribute("message", "該当する商品はありません");
			bigItemList = showItemListService.findByLikeName("");
		}
		model.addAttribute("bigItemList", bigItemList);
		return "item_list";
	}
	
	
}
