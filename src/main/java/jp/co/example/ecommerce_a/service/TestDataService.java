package jp.co.example.ecommerce_a.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.example.ecommerce_a.domain.Order;
import jp.co.example.ecommerce_a.domain.OrderItem;
import jp.co.example.ecommerce_a.domain.OrderTopping;
import jp.co.example.ecommerce_a.repository.ItemRepository;
import jp.co.example.ecommerce_a.repository.ToppingRepository;
import jp.co.example.ecommerce_a.repository.UserRepository;

@Service
public class TestDataService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private ToppingRepository toppingRepository;
	
	public Order testOrder() {
		Order order = new Order();
		order.setId(1);
		order.setStatus(0);
		order.setTotalPrice(1000);
		order.setUserId(1);
		order.setUser(userRepository.load(1));
		List<OrderItem> orderItemList = new ArrayList<>();
		order.setOrderItemList(orderItemList);
		OrderItem orderItem = new OrderItem();
		orderItem.setId(1);
		orderItem.setItemId(3);
		orderItem.setOrderId(1);
		orderItem.setQuantity(3);
		orderItem.setSize('M');
		orderItem.setItem(itemRepository.laod(1));
		orderItemList.add(orderItem);
		List<OrderTopping> orderToppingList = new ArrayList<>();
		orderItem.setOrderToppingList(orderToppingList);
		OrderTopping orderTopping = new OrderTopping();
		orderTopping.setId(1);
		orderTopping.setToppingId(2);
		orderTopping.setTopping(toppingRepository.load(2));
		orderToppingList.add(orderTopping);
		return order;
		
	}
	
	

}
