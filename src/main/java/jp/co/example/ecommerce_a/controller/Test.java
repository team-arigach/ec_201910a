package jp.co.example.ecommerce_a.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_a.repository.OrderRepository;

@Controller
@RequestMapping("/test")
public class Test {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@RequestMapping("/testNull")
	public String method() {
		System.err.println("null");
		System.err.println("nullが返る"+orderRepository.findByUserIdAndStatus(1, 5));
		return null;
	}

}
