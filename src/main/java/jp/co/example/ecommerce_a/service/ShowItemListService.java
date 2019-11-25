package jp.co.example.ecommerce_a.service;

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
	 * @return 商品一覧
	 */
	public List<Item> ShowItemList(){
		List<Item> itemList = itemRepository.findAll();
		return itemList;
	}
	
}
