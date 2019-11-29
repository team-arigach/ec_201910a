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
		// オートコンプリートで使用しているのでメソッド化したい
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
		List<Item> itemList = null;
		if (name == null || name.equals("")) {
			// 検索文字列が空なら全件検索
			itemList = itemRepository.findAll();
		} else {
			// 検索文字列があれば曖昧検索
			itemList = itemRepository.findByLikeName(name);
		}

		StringBuilder itemListForAutocompleate = new StringBuilder();
		for (int i = 0; i < itemList.size(); i++) {
			if (i != 0) {
				itemListForAutocompleate.append(",");
			}
			Item item = itemList.get(i);
			itemListForAutocompleate.append("\"");
			itemListForAutocompleate.append(item.getName());
			itemListForAutocompleate.append("\"");
		}
		return itemListForAutocompleate;
	}

	public List<List<Item>> findByLikeNameAboutSum(String name,Integer offSet) {
		if(offSet==null) {
			offSet=0;
		}
		System.out.println(name);
		// オートコンプリートで使用しているのでメソッド化したい
		List<Item> itemList = null;
		if (name == null || name.equals("")) {
			// 検索文字列が空なら全件検索
			itemList = itemRepository.findAllAboutSum(offSet);
		} else {
			// 検索文字列があれば曖昧検索
			itemList = itemRepository.findByLikeNameAboutSum(name,offSet);
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

	public Integer findByStartPoint(Integer page) {
		if (page == null) {
			return 0;
		}
		return (page - 1) * 8;
	}
	
	public StringBuilder getItemListForAutocompleteAboutSum(String name,Integer count,Integer pageNumber) {
		List<Item> itemList = null;
		Integer offSet = makeOffSet(pageNumber, count);
		if (name == null || name.equals("")) {
			// 検索文字列が空なら全件検索
			itemList = itemRepository.findAllAboutSum(offSet);
		} else {
			// 検索文字列があれば曖昧検索
			itemList = itemRepository.findByLikeName(name);
		}

		StringBuilder itemListForAutocompleate = new StringBuilder();
		for (int i = 0; i < itemList.size(); i++) {
			if (i != 0) {
				itemListForAutocompleate.append(",");
			}
			Item item = itemList.get(i);
			itemListForAutocompleate.append("\"");
			itemListForAutocompleate.append(item.getName());
			itemListForAutocompleate.append("\"");
		}
		return itemListForAutocompleate;
	}

	/**
	 * ページのリストを返す. 追加開発
	 * 
	 * @param totalItemCount
	 * @return ページのリストを返す.
	 */
	public List<Integer> makeByPageList(Integer viewCountOfOnePage,Integer itemCount) {
		Integer totalPage = null;
		if (itemCount % viewCountOfOnePage == 0) {
			totalPage = itemCount / viewCountOfOnePage;
		} else {
			totalPage = itemCount / viewCountOfOnePage + 1;
		}
		List<Integer> pageList = new ArrayList<>();
		for (int i = 1; i <= totalPage; i++) {
			pageList.add(i);
		}
		return pageList;
	}

	/**
	 * offSetの数値を決める.
	 * 
	 * @param pageNumber クリックされたページ数
	 * @param viewCountOfOnetePage 1ページ辺りに表示したい件数
	 * @return
	 */
	public Integer makeOffSet(Integer pageNumber, Integer viewCountOfOnetePage) {
		// offSetの数字を決める
		if (pageNumber == null) {
			return 0;
		} else {
			return (pageNumber- 1) * viewCountOfOnetePage;
		}
		
	}

}