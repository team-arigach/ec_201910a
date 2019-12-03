package jp.co.example.ecommerce_a.form;


public class UserForm {

	private String name;
	private String email;
	private String telephone;
	private String password;
	private String passwordconfomation;
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
