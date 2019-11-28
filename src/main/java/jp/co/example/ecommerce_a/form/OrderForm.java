package jp.co.example.ecommerce_a.form;



import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;




public class OrderForm {

	/**	宛名氏名 */
	@NotBlank(message="　名前を入力して下さい")
	private String destinationName;
	
	/**	宛名Eメール */
	@NotBlank(message="　メールアドレスを入力して下さい")
	private String destinationEmail;
	
	/**	宛名郵便番号 */
	private String destinationZipcode;
	
	/**	宛名住所 */
	@NotBlank(message="　住所を入力して下さい")	
	private String destinationAddress;
	
	/**	宛名電話番号 */
	@NotBlank(message="　電話番号を入力して下さい")
	private String destinationTel;
	
	/**	配達年月日 */
	@NotNull(message = "配達日時を指定して下さい")
	private LocalDate deliveryTime;

	/**	配達時間 */
	@NotNull(message="配達時間を指定して下さい")
	private Integer deliveryHour;
	

	/**	支払方法 */
	private Integer paymentMethod;
	

	//以下getter/setter


	public String getDestinationName() {
		return destinationName;
	}


	public LocalDate getDeliveryTime() {
		return deliveryTime;
	}


	public void setDeliveryTime(LocalDate deliveryTime) {
		this.deliveryTime = deliveryTime;
	}


	public void setDestinationName(String destinationName) {
		this.destinationName = destinationName;
	}

	public String getDestinationEmail() {
		return destinationEmail;
	}

	public void setDestinationEmail(String destinationEmail) {
		this.destinationEmail = destinationEmail;
	}

	public String getDestinationZipcode() {
		return destinationZipcode;
	}

	public void setDestinationZipcode(String destinationZipcode) {
		this.destinationZipcode = destinationZipcode;
	}

	public String getDestinationAddress() {
		return destinationAddress;
	}

	public void setDestinationAddress(String destinationAddress) {
		this.destinationAddress = destinationAddress;
	}

	public String getDestinationTel() {
		return destinationTel;
	}

	public void setDestinationTel(String destinationTel) {
		this.destinationTel = destinationTel;
	}

//	public Date getDeliveryTime() {
//		return deliveryTime;
//	}
//
//	public void setDeliveryTime(Date deliveryTime) {
//		this.deliveryTime = deliveryTime;
//	}

	public Integer getDeliveryHour() {
		return deliveryHour;
	}

	public void setDeliveryHour(Integer deliveryHour) {
		this.deliveryHour = deliveryHour;
	}

	public Integer getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(Integer paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

}
