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
	 * 商品リスト情報を全件取得します.
	 * 
	 * @return 商品一覧
	 */
//	public List<Item> showItemList() {
//		List<Item> itemList = itemRepository.findAll();
//		return itemList;
//	}

	public List<List<Item>> findByLikeName(String name) {
		List<Item> itemList = null;
		if(name == null || name.equals("")) {
			itemList = itemRepository.findAll();
		}else {
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

}
