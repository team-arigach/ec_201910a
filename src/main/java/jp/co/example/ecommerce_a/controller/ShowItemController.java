package jp.co.example.ecommerce_a.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_a.domain.Item;
import jp.co.example.ecommerce_a.repository.ItemRepository;
import jp.co.example.ecommerce_a.service.ShowItemListService;

@Controller
@RequestMapping("/")
public class ShowItemController {
	@Autowired
	private ShowItemListService showItemListService;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@RequestMapping("")
	public String showItemList(String name, Model model) {
		List<Item> itemList = null;
		if( name == null) {
			System.out.println(name);
			itemList = showItemListService.showItemList();
		}else {
			itemList = itemRepository.findByLikeName(name);
		}
		model.addAttribute("itemList", itemList);
		return "item_list";		
	}
	
	
}
