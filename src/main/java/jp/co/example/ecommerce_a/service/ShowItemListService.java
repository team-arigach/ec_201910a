package jp.co.example.ecommerce_a.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.example.ecommerce_a.domain.Item;
import jp.co.example.ecommerce_a.repository.ItemRepository;

@Service
@Transactional
public class ShowItemListService {
	@Autowired
	private ItemRepository itemRepository;

	/**
	 * 商品リスト情報を取得します.(最初のログインまたは指定なしでの検索の場合商品情報を全件表示します)
	 * 
	 * @return 商品一覧画面
	 */

	public List<List<Item>> findByLikeName(String name) {
		//オートコンプリートで使用しているのでメソッド化したい
		List<Item> itemList = null;
		if (name == null || name.equals("")) {
			// 検索文字列が空なら全件検索
			itemList = itemRepository.findAll();
		} else {
			// 検索文字列があれば曖昧検索
			itemList = itemRepository.findByLikeName(name);
		}
		List<List<Item>> bigItemList = new ArrayList<>();
		List<Item> smallItemsList = null;
		for (int i = 0; i < itemList.size(); i++) {
			if (i == 0 || i % 3 == 0) {
				smallItemsList = new ArrayList<>();
				bigItemList.add(smallItemsList);
			}
			smallItemsList.add(itemList.get(i));
		}
		return bigItemList;
	}

	/**
	 * オートコンプリート用にJavaScriptの配列の中身を文字列で作ります.
	 * 
	 * @return 商品情報一覧
	 */
	public StringBuilder getItemListForAutocomplete(String name) {
		//商品情報取得で使用しているのでメソッド化したい
		List<Item> itemList = null;
		if (name == null || name.equals("")) {
			// 検索文字列が空なら全件検索
			itemList = itemRepository.findAll();
		} else {
			// 検索文字列があれば曖昧検索
			itemList = itemRepository.findByLikeName(name);
		}
		
		StringBuilder itemListForAutocompleate = new StringBuilder();
		for(int i = 0; i < itemList.size(); i++) {
			if(i != 0) {
				itemListForAutocompleate.append(",");
			}
			Item item = itemList.get(i);
			itemListForAutocompleate.append("\"");
			itemListForAutocompleate.append(item.getName());
			itemListForAutocompleate.append("\"");
		}
		return itemListForAutocompleate;
	}
}
