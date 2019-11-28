package jp.co.example.ecommerce_a.form;

public class CreditInfoForm {
	
	/** カード番号 */
	private Integer cardNumber;
	/** 有効期限年 */
	private Integer cardExpYear;
	/** 有効期限月 */
	private Integer cardExpMonth;
	/** カード名義人 */
	private String cardName;
	/** セキュリティーコード */
	private Integer cardCvv;
	
	public Integer getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(Integer cardNumber) {
		this.cardNumber = cardNumber;
	}
	public Integer getCardExpYear() {
		return cardExpYear;
	}
	public void setCardExpYear(Integer cardExpYear) {
		this.cardExpYear = cardExpYear;
	}
	public Integer getCardExpMonth() {
		return cardExpMonth;
	}
	public void setCardExpMonth(Integer cardExpMonth) {
		this.cardExpMonth = cardExpMonth;
	}
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	public Integer getCardCvv() {
		return cardCvv;
	}
	public void setCardCvv(Integer cardCvv) {
		this.cardCvv = cardCvv;
	}
	
	@Override
	public String toString() {
		return "CreditInfo [cardNumber=" + cardNumber + ", cardExpYear=" + cardExpYear + ", cardExpMonth="
				+ cardExpMonth + ", cardName=" + cardName + ", cardCvv=" + cardCvv + "]";
	}

	
}
