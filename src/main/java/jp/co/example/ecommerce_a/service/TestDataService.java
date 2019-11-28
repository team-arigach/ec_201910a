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
		
		OrderItem orderItem1 = new OrderItem();
		orderItem1.setId(1);
		orderItem1.setItemId(3);
		orderItem1.setOrderId(1);
		orderItem1.setQuantity(3);
		orderItem1.setSize('M');
		orderItem1.setItem(itemRepository.laod(3));
		orderItemList.add(orderItem1);
		
		OrderItem orderItem2 = new OrderItem();
		orderItem2.setId(2);
		orderItem2.setItemId(4);
		orderItem2.setOrderId(1);
		orderItem2.setQuantity(1);
		orderItem2.setSize('M');
		orderItem2.setItem(itemRepository.laod(4));
		orderItemList.add(orderItem2);
		
		OrderItem orderItem3 = new OrderItem();
		orderItem3.setId(3);
		orderItem3.setItemId(5);
		orderItem3.setOrderId(1);
		orderItem3.setQuantity(2);
		orderItem3.setSize('L');
		orderItem3.setItem(itemRepository.laod(5));
		orderItemList.add(orderItem3);
		
		List<OrderTopping> orderToppingList = new ArrayList<>();
		orderItem1.setOrderToppingList(orderToppingList);
		
		OrderTopping orderTopping = new OrderTopping();
		orderTopping.setId(1);
		orderTopping.setToppingId(1);
		orderTopping.setTopping(toppingRepository.load(1));
		orderToppingList.add(orderTopping);
		
		OrderTopping orderTopping2 = new OrderTopping();
		orderTopping2.setId(2);
		orderTopping2.setToppingId(2);
		orderTopping2.setTopping(toppingRepository.load(2));
		orderToppingList.add(orderTopping2);
		
		OrderTopping orderTopping3 = new OrderTopping();
		orderTopping3.setId(3);
		orderTopping3.setToppingId(3);
		orderTopping3.setTopping(toppingRepository.load(3));
		orderToppingList.add(orderTopping3);
		
		List<OrderTopping> orderToppingList2 = new ArrayList<>();
		orderItem1.setOrderToppingList(orderToppingList2);
		
		OrderTopping orderTopping4 = new OrderTopping();
		orderTopping4.setId(4);
		orderTopping4.setToppingId(4);
		orderTopping4.setTopping(toppingRepository.load(4));
		orderToppingList2.add(orderTopping4);
		
		OrderTopping orderTopping5 = new OrderTopping();
		orderTopping5.setId(5);
		orderTopping5.setToppingId(5);
		orderTopping5.setTopping(toppingRepository.load(5));
		orderToppingList2.add(orderTopping5);
		
		OrderTopping orderTopping6 = new OrderTopping();
		orderTopping6.setId(6);
		orderTopping6.setToppingId(6);
		orderTopping6.setTopping(toppingRepository.load(6));
		orderToppingList2.add(orderTopping6);
		return order;
		
	}
	
	

}
