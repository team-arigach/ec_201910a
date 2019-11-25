package jp.co.example.ecommerce_a.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.example.ecommerce_a.domain.User;

@Repository
public class UserRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private final static RowMapper<User> USER_ROW_MAPPER = (rs, i) -> {
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setName(rs.getString("name"));
		user.setEmail(rs.getString("email"));
		user.setPassword(rs.getString("password"));
		user.setZipcode(rs.getString("zipcode"));
		user.setAddress(rs.getString("address"));
		user.setTelephone(rs.getString("telephone"));
		return user;
	};
	
	public User findByEmailAndPassword(String email, String password) {
		String sql = "SELECT id, name, email, password, zipcode, address, telephone FROM users WHERE email = :email AND password = :password";
		SqlParameterSource param = new MapSqlParameterSource().addValue("email", email).addValue("password", password);
		User user = template.queryForObject(sql, param, USER_ROW_MAPPER);
		return user;
	}
	
	public void insert() {
		
	}
	
	

}
