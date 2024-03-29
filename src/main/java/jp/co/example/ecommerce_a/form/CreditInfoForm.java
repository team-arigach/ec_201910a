package jp.co.example.ecommerce_a.form;


public class CreditInfoForm {
	
	private Integer userId;
	/** カード番号 */
	private String card_number;
	/** 有効期限年 */
	private Integer card_exp_year;
	/** 有効期限月 */
	private Integer card_exp_month;
	/** カード名義人 */
	private String card_name;
	/** セキュリティーコード */
	private Integer card_cvv;
	/** 金額 */
	private Integer amount;
	
	private long order_number;
	
	public Long getLongCardNumber(String cardNumber) {
		Long parseLong = Long.parseLong(cardNumber);
		return parseLong;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getCard_number() {
		return card_number;
	}

	public void setCard_number(String card_number) {
		this.card_number = card_number;
	}

	public Integer getCard_exp_year() {
		return card_exp_year;
	}

	public void setCard_exp_year(Integer card_exp_year) {
		this.card_exp_year = card_exp_year;
	}

	public Integer getCard_exp_month() {
		return card_exp_month;
	}

	public void setCard_exp_month(Integer card_exp_month) {
		this.card_exp_month = card_exp_month;
	}

	public String getCard_name() {
		return card_name;
	}

	public void setCard_name(String card_name) {
		this.card_name = card_name;
	}

	public Integer getCard_cvv() {
		return card_cvv;
	}

	public void setCard_cvv(Integer card_cvv) {
		this.card_cvv = card_cvv;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public long getOrder_number() {
		return order_number;
	}

	public void setOrder_number(long order_number) {
		this.order_number = order_number;
	}

	@Override
	public String toString() {
		return "CreditInfoForm [userId=" + userId + ", card_number=" + card_number + ", card_exp_year=" + card_exp_year
				+ ", card_exp_month=" + card_exp_month + ", card_name=" + card_name + ", card_cvv=" + card_cvv
				+ ", amount=" + amount + ", order_number=" + order_number + "]";
	}
	
}