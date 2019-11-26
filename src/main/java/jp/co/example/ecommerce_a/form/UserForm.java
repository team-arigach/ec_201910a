package jp.co.example.ecommerce_a.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UserForm {

	@NotBlank(message = "お名前を入力して下さい")
	private String name;
	@NotBlank(message = "メールアドレスを入力して下さい")
	@Email
	private String email;
	@NotBlank(message = "電話番号を入力して下さい")
	private String telephone;
	@NotBlank(message = "パスワードを入力して下さい")
	private String password;
	@NotBlank(message = "確認用パスワードを入力して下さい")
	private String passwordconfomation;
	@NotBlank(message = "住所を入力して下さい")
	private String address;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordconfomation() {
		return passwordconfomation;
	}

	public void setPasswordconfomation(String passwordconfomation) {
		this.passwordconfomation = passwordconfomation;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "UserForm [name=" + name + ", email=" + email + ", telephone=" + telephone + ", password=" + password
				+ ", passwordconfomation=" + passwordconfomation + ", address=" + address + "]";
	}

}
