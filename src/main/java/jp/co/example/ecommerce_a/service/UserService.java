package jp.co.example.ecommerce_a.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.example.ecommerce_a.domain.User;
import jp.co.example.ecommerce_a.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	/**
	 * メールアドレスとパスワードでユーザーを検索.
	 * 
	 * @param email メールアドレス
	 * @param password パスワード
	 * @return メールアドレスとパスワードで検索されたユーザー 一件も返ってこなければ、例外が発生しnullを返す。
	 */
	public User userLogin(String email, String password) {
		User user = userRepository.findByEmailAndPassword(email, password);
		return user;
	}

}
