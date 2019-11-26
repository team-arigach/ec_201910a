package jp.co.example.ecommerce_a.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.example.ecommerce_a.domain.Item;
import jp.co.example.ecommerce_a.domain.Topping;
import jp.co.example.ecommerce_a.repository.ItemRepository;
import jp.co.example.ecommerce_a.repository.ToppingRepository;

/**
 * 商品関連の業務処理を行うサービス.
 * 
 * @author yukiando
 *
 */
@Service
@Transactional
public class ShowItemDetailService {

	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private ToppingRepository toppingRepository;
	
	/**
	 * 商品の詳細を表示します.
	 * 
	 * @param id ID
	 * @return 商品情報
	 */
	public Item showItemDetail(Integer id) {
		Item item = itemRepository.laod(id);
		List<Topping> toppingList = toppingRepository.findAll();
		item.setToppingList(toppingList);
		return item;
	}
}
