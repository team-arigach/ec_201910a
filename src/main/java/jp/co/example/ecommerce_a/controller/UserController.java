package jp.co.example.ecommerce_a.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_a.domain.User;
import jp.co.example.ecommerce_a.repository.OrderRepository;
import jp.co.example.ecommerce_a.service.UserService;

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
	private UserService userService;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private OrderRepository orderRepository;
	
	/**
	 * ログイン画面を表示する.
	 * @return ログイン画面
	 */
	@RequestMapping("/login")
	public String toLogin() {
		return "login";
	}
	
	/**
	 * ログイン処理を行う.
	 * 
	 * ログイン成功時は、ピザ画面に遷移 失敗時はログイン画面を表示
	 * @param email メールアドレス
	 * @param password パスワード
	 * @param model モデル
	 * @return ピザ一覧画面
	 */
	@RequestMapping("/userLogin")
	public String login(String email, String password, Model model) {
		User user = userService.userLogin(email, password);
		if( user == null) {
			model.addAttribute("loginError", "メールアドレスかパスワードが一致しません。");
			return toLogin();
		}
//		Integer sessionId = session.getId().hashCode();
//		Integer orderId = orderRepository.findByUserIdAndStatus(sessionId, 0).getId();
		
		return "redirect:/showItemList";
	}
	
	/**
	 * ログアウトをする.
	 * 
	 * @return ログイン画面
	 */
	@RequestMapping("/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/login";
	}
	
	
	
	

}
