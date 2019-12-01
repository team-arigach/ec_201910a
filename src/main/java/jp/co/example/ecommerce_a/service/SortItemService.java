package jp.co.example.ecommerce_a.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.example.ecommerce_a.domain.Order;
import jp.co.example.ecommerce_a.domain.OrderItem;
import jp.co.example.ecommerce_a.domain.OrderTopping;
import jp.co.example.ecommerce_a.domain.Topping;
import jp.co.example.ecommerce_a.repository.OrderItemRepository;
import jp.co.example.ecommerce_a.repository.OrderToppingRepository;
import jp.co.example.ecommerce_a.repository.ToppingRepository;

@Service
public class SortItemService {

	@Autowired
	private OrderItemRepository orderItemRepository;

	@Autowired
	private OrderToppingRepository orderToppingRepository;
	
	@Autowired
	private ToppingRepository toppingRepository;

	public void sortOrderItemByM(Order order) {
		/* orderから取得したOrderItemListで
		 * Lサイズのものは取り除く
		 * */
		List<OrderItem> orderItemList = order.getOrderItemList();
		for (int i = 0; i < orderItemList.size(); i++) {
			if (orderItemList.get(i).getSize() == 'L') {
				orderItemList.remove(i);
			}
		}

		/* オーダーアイテムID順に並び替え
		 * */
		sortOrderItemListByAsc(orderItemList);
		sortOrderItem(orderItemList);

		Integer preItemId = 0;
		Integer preToppingId = 0;
		for (int i = 0; i < orderItemList.size(); i++) {
			OrderItem orderItem = orderItemList.get(i);
			if (orderItem.getItemId() == preItemId) {
				OrderItem subItem = orderItemList.get(i - 1);
				subItem.setQuantity(subItem.getQuantity() + 1);
				if (orderItem.getOrderToppingList().size() != 0) {
					for (OrderTopping orderTopping : orderItem.getOrderToppingList()) {
						orderTopping.setOrderItemId(subItem.getId());
						orderToppingRepository.update(orderTopping);
						subItem.getOrderToppingList().add(orderTopping);
					}
					Collections.sort(subItem.getOrderToppingList(), new Comparator<OrderTopping>() {
						@Override
						public int compare(OrderTopping o1, OrderTopping o2) {
							return Integer.compare(o1.getToppingId(), o2.getToppingId());
						}
					});
					for (int j = 0; j < subItem.getOrderToppingList().size(); j++) {
						if (subItem.getOrderToppingList().get(j).getToppingId() == preToppingId) {
							orderToppingRepository.deleteByPk(subItem.getOrderToppingList().get(j).getId());
							subItem.getOrderToppingList().remove(j);
							j -= 1;
						}
						preToppingId = subItem.getOrderToppingList().get(j).getToppingId();
					}
				}
				orderItemRepository.delete(orderItem.getId());
				orderItemList.remove(i);
				orderItemRepository.update(subItem);
				i -= 1;
			}
			preItemId = orderItem.getItemId();
		}
	}
	
	public void sortOrderItemByL(Order order) {
		List<OrderItem> orderItemList = order.getOrderItemList();
		for (int i = 0; i < orderItemList.size(); i++) {
			if (orderItemList.get(i).getSize() == 'M') {
				orderItemList.remove(i);
			}
		}

		sortOrderItemListByAsc(orderItemList);
		sortOrderItem(orderItemList);

		Integer preItemId = 0;
		Integer preToppingId = 0;
		for (int i = 0; i < orderItemList.size(); i++) {
			OrderItem orderItem = orderItemList.get(i);
			if (orderItem.getItemId() == preItemId) {
				OrderItem subItem = orderItemList.get(i - 1);
				subItem.setQuantity(subItem.getQuantity() + 1);
				if (orderItem.getOrderToppingList().size() != 0) {
					for (OrderTopping orderTopping : orderItem.getOrderToppingList()) {
						orderTopping.setOrderItemId(subItem.getId());
						orderToppingRepository.update(orderTopping);
						subItem.getOrderToppingList().add(orderTopping);
					}
					Collections.sort(subItem.getOrderToppingList(), new Comparator<OrderTopping>() {
						@Override
						public int compare(OrderTopping o1, OrderTopping o2) {
							return Integer.compare(o1.getToppingId(), o2.getToppingId());
						}
					});
					for (int j = 0; j < subItem.getOrderToppingList().size(); j++) {
						if (subItem.getOrderToppingList().get(j).getToppingId() == preToppingId) {
							orderToppingRepository.deleteByPk(subItem.getOrderToppingList().get(j).getId());
							subItem.getOrderToppingList().remove(j);
							j -= 1;
						}
						preToppingId = subItem.getOrderToppingList().get(j).getToppingId();
					}
				}
				orderItemRepository.delete(orderItem.getId());
				orderItemList.remove(i);
				orderItemRepository.update(subItem);
				i -= 1;
			}
			preItemId = orderItem.getItemId();
		}
	}

	/**
	 * 注文された商品をID順に並び変える.
	 * 
	 * @param orderItemList
	 */
	private void sortOrderItemListByAsc(List<OrderItem> orderItemList) {
		// オーダーの中のオーダーアイテムを比較し、同じIDなら一覧から削除する
		Collections.sort(orderItemList, new Comparator<OrderItem>() {
			@Override
			public int compare(OrderItem o1, OrderItem o2) {
				return Integer.compare(o1.getItemId(), o2.getItemId());
			}
		});
	}

	/**
	 * 注文された商品のトッピングをID順に並び変える.
	 * 
	 * @param orderItemList 注文商品リスト
	 */
	private void sortOrderItem(List<OrderItem> orderItemList) {
		for (OrderItem orderItem : orderItemList) {
			Collections.sort(orderItem.getOrderToppingList(), new Comparator<OrderTopping>() {
				@Override
				public int compare(OrderTopping o1, OrderTopping o2) {
					return Integer.compare(o1.getToppingId(), o2.getToppingId());
				}
			});
		}
	}
	
	public List<Topping> setOrderItemNonTopping(OrderItem orderItem) {
		
		List<Topping> nonOrderToppingList = toppingRepository.findAll();
		
		Integer preId = 0;
		
		for (OrderTopping topping : orderItem.getOrderToppingList()) {
			nonOrderToppingList.add(topping.getTopping());
		}
		Collections.sort(nonOrderToppingList, new Comparator<Topping>() {
			@Override
			public int compare(Topping o1, Topping o2) {
				return Integer.compare(o1.getId(), o2.getId());
			}
		});
			
		for (int i = 0; i < nonOrderToppingList.size(); i++) {
			if( preId == nonOrderToppingList.get(i).getId()) {
				nonOrderToppingList.remove(i);
				nonOrderToppingList.remove(i-1);
				i -= 1;
				
			}
			if( i != nonOrderToppingList.size()) {
				preId = nonOrderToppingList.get(i).getId();
			}
		}
		return nonOrderToppingList;
	}

}
