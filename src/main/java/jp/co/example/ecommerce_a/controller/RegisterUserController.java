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
@RequestMapping("/registerUser")
public class RegisterUserController {
	
	@Autowired
	private RegisterUserService registerService;
	
	@ModelAttribute
	public UserForm userForm() {
		return new UserForm();
	}
	
	@RequestMapping("")
		public String index() {
			return "register_user";
		}
	
	
	@RequestMapping("/register")
	public String register(@Validated UserForm form,BindingResult result,Model model) {
		boolean ischeck = registerService.isCheckMailAddress(form.getEmail());
		if(ischeck == false) {
			result.rejectValue("email",null, "そのメールアドレスは登録されています");
		}
		String password = form.getPassword();
		System.out.println(form.getPassword());
		if(password != null &&(password.equals(form.getPasswordconfomation()))) {
			result.rejectValue("notMuchPassword",null, "パスワードと確認用パスワードが一致しません");
		}
		if(result.hasErrors()) {
			return index();
		}
		User user = new User();
		BeanUtils.copyProperties(form, user);
		registerService.insertUser(user);
		return "redirect:/registerUser";
	}


}
