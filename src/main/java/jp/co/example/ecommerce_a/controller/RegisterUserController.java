package jp.co.example.ecommerce_a.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_a.domain.User;
import jp.co.example.ecommerce_a.form.RegisterUserForm;
import jp.co.example.ecommerce_a.service.RegisterUserService;

/**
 * ユーザー登録コントローラ.
 * 
 * @author yosuke.yamada
 *
 */
@Controller
@RequestMapping("/registerUser")
public class RegisterUserController {
	
	@Autowired
	private RegisterUserService registerService;
	
	@ModelAttribute
	public RegisterUserForm retisterUserForm() {
		return new RegisterUserForm();
	}
	
	/**
	 * ユーザ登録画面を表示する.
	 * 
	 * @return ユーザ登録画面に遷移
	 */
	@RequestMapping("")
		public String index() {
			return "register_user";
		}
	
	
	/**
	 * ユーザ登録をする.
	 * フォームに空欄等があればエラーを返す。
	 * 
	 * @param registerUserform 登録ユーザフォーム
	 * @param result エラーを格納
	 * @param model リクエストスコープ
	 * @return ログイン画面リダイレクト. フォームに空欄等があれば入力フォームにフォワード.
	 */
	@RequestMapping("/register")
	public String register(@Validated RegisterUserForm registerUserform
			, BindingResult result
			, Model model) {
		boolean ischeck = registerService.isCheckMailAddress(registerUserform.getEmail());
		if(ischeck == false) {
			result.rejectValue("email",null, "そのメールアドレスは登録されています");
		}
		String password = registerUserform.getPassword();
		System.out.println(password);
		if(!(password.equals(registerUserform.getPasswordconfomation()))) {
			result.rejectValue("password",null, "パスワードと確認用パスワードが一致しません");
		}
		if(result.hasErrors()) {
			return index();
		}
		User user = new User();
		BeanUtils.copyProperties(registerUserform, user);
		registerService.insertUser(user);
		return "redirect:/registerUser/toLoginPage";
	}
	
	/**
	 * ログイン画面に遷移するメソッド.
	 * 
	 * @return ログイン画面に遷移
	 */
	@RequestMapping("toLoginPage")
	public String toLoginPage() {
		return "login";
	}

}
