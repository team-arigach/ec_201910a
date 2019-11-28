package jp.co.example.ecommerce_a.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.example.ecommerce_a.domain.User;
import jp.co.example.ecommerce_a.repository.UserRepository;

/**
 * ユーザーを操作するコントローラー.
 * 
 * @author takahiro.suzuki
 *
 */
@Controller
@RequestMapping("")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	/**
	 * ログイン画面を表示する.
	 * @return ログイン画面
	 */
	@RequestMapping("/login")
	public String toLogin(Model model, @RequestParam(required = false) String error) {
		System.err.println("ログインエラー" + error);
		User user1 = userRepository.findByMailAddress("deviationvalue1023@ezweb.ne.jp");
		System.err.println(user1);
		if(error != null) {
			System.err.println("ログイン失敗");
			model.addAttribute("loginError", "メールアドレスまたはパスワードが違います。");
		}
		return "login";
	}
	
	

}
