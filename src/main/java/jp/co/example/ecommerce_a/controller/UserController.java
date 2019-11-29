package jp.co.example.ecommerce_a.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * ユーザーを操作するコントローラー.
 * 
 * @author takahiro.suzuki
 *
 */
@Controller
@RequestMapping("")
public class UserController {
	
	/**
	 * ログイン画面を表示する.
	 * @return ログイン画面
	 */
	@RequestMapping("/login")
	public String toLogin(Model model, @RequestParam(required = false) String error) {
		System.err.println("ログインエラー" + error);
		if(error != null) {
			System.err.println("ログイン失敗");
			model.addAttribute("loginError", "メールアドレスまたはパスワードが違います。");
		}
		return "login";
	}

}
