package jp.co.example.ecommerce_a.domain;

public class CreditStatus {
	
	/** 通信結果（success OR failed） */
	private String status;
	/** メッセージ */
	private String message;
	/** エラーコード */
	private String errorCode;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	@Override
	public String toString() {
		return "CreditStatus [status=" + status + ", message=" + message + ", errorCode=" + errorCode + "]";
	}
	
	
	
	

}
