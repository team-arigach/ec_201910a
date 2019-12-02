package jp.co.example.ecommerce_a.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CheckController {
	
	@Autowired
	private HttpSession session;
	
	@RequestMapping("/check")
	public String checkUrl() {
		String url = (String) session.getAttribute("referer");
		return "redirect:"+url;
	}
	
	

}
