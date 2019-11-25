package jp.co.example.ecommerce_a.form;

public class RegisterUserForm {

	
	private String name;
	private String mail;
	private String telephone;
	private String password;
	private String address;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "RegisterUserForm [name=" + name + ", mail=" + mail + ", telephone=" + telephone + ", password="
				+ password + ", address=" + address + "]";
	}

}
