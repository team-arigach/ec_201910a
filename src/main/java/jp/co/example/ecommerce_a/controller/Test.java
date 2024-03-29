package jp.co.example.ecommerce_a.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_a.form.CreditInfoForm;
import jp.co.example.ecommerce_a.repository.OrderRepository;

@Controller
@RequestMapping("/test")
public class Test {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private HttpServletRequest request;
	
	@RequestMapping("/testNull")
	public String method() {
		System.err.println("null");
		System.err.println("nullが返る"+orderRepository.findByUserIdAndStatus(1, 5));
		return null;
	}
	
	@RequestMapping("/testCredit")
	public String testCredit() {
		CreditInfoForm form = new CreditInfoForm();
		form.setCard_name("takahiro");
		form.setCard_exp_year(2019);
		form.setCard_exp_month(12);
		form.setCard_cvv(123);
		form.setAmount(10000);
		form.setUserId(1);
		form.setOrder_number(12345678912345l);
		System.err.println(form);
		return null;
	}
	
	@RequestMapping("/testreferer")
	public String testReferer() {
		// 8080までで21
		System.err.println(request.getHeader("REFERER").substring(21).toString());
		return null;
	}

}
