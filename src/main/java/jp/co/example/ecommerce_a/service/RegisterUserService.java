package jp.co.example.ecommerce_a.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jp.co.example.ecommerce_a.domain.User;
import jp.co.example.ecommerce_a.repository.UserRepository;

@Service
public class RegisterUserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public void insertUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}
	
	public boolean isCheckMailAddress(String email) {
		User user = userRepository.findByMailAddress(email);
		if ( user == null ) {
			return true;
		}
		return false;
	}
	

}
