package jp.co.example.ecommerce_a.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.example.ecommerce_a.domain.Item;
import jp.co.example.ecommerce_a.repository.ItemRepository;

/**
 * アイテムの登録を行うサービス.
 * 
 * @author takahiro.suzuki
 *
 */
@Service
public class InsertItemService {
	
	@Autowired
	private ItemRepository itemRepository;
	
	/**
	 * アイテムの登録処理.
	 */
	public void insertItem(Item item) {
		
		itemRepository.insert(item);
		
	}

}
