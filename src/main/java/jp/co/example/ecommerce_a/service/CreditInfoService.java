package jp.co.example.ecommerce_a.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jp.co.example.ecommerce_a.domain.CreditStatus;
import jp.co.example.ecommerce_a.form.CreditInfoForm;

@Service
public class CreditInfoService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	
	/**
	 * クレジットカード情報を送信し、結果をJSON形式で取得する.
	 * 
	 * @return WebApiから返ってきたJSON
	 */
	public CreditStatus getStatusByCreditInfo(CreditInfoForm form) {
		String url = "http://192.168.56.101:8080/sample-credit-card-web-api/credit-card/payment";
		CreditStatus creditStatus = restTemplate.postForObject(url, form, CreditStatus.class);
		return creditStatus;
	}
	
	/**
	 * クレジットカード情報が、使用可能か判定を行う.
	 * 
	 * @return 真偽値
	 */
	public Boolean isCheckCreditInfo(CreditInfoForm form) {
		CreditStatus creditStatus = getStatusByCreditInfo(form);
		System.err.println(creditStatus);
		if("success".equals( creditStatus.getStatus() )) {
			return true;
		} else {
			return false;
		}
	}

}
