package jp.co.example.ecommerce_a.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.example.ecommerce_a.domain.User;
import jp.co.example.ecommerce_a.repository.UserRepository;

@Service
public class RegisterUserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public void insertUser(User user) {
		userRepository.save(user);
	}
	
	public boolean isCheckMailAddress(String email) {
		List<User> userList = userRepository.findByMailAddress(email);
		if(userList.size() == 0) {
			return true;
		}
		return false;
	}

}
