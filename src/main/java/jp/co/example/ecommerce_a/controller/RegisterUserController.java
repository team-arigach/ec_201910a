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
import jp.co.example.ecommerce_a.form.UserForm;
import jp.co.example.ecommerce_a.service.RegisterUserService;

@Controller
@RequestMapping("/register")
public class RegisterUserController {
	
	@Autowired
	private RegisterUserService registerService;
	
	@ModelAttribute
	public UserForm userForm() {
		return new UserForm();
	}
	
//	@RequestMapping("index"){
//		public String index() {
//			return "register_user";
//		}
//	}
	
	@RequestMapping("")
	public String register(@Validated UserForm form,BindingResult result,Model model) {
		boolean ischeck = registerService.isCheckMailAddress(form.getEmail());
		if(ischeck) {
			result.rejectValue("email", "そのメールアドレスは登録されています");
		}
		if(!(form.getPassword().equals(form.getPasswordconfomation()))) {
			result.rejectValue("notMuchPassword", "パスワードと確認用パスワードが一致しません");
		}
		if(result.hasErrors()) {
			return "forward:/register_user";
		}
		User user = new User();
		BeanUtils.copyProperties(form, user);
		registerService.insertUser(user);
		return "redirect:/login";
	}

}
