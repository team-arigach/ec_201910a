package jp.co.example.ecommerce_a.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jp.co.example.ecommerce_a.domain.CreditInfo;
import jp.co.example.ecommerce_a.domain.CreditStatus;

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
	public CreditStatus getStatusByCreditInfo(CreditInfo creditInfo) {
		String url = "http://192.168.56.102:8080/sample-credit-card-web-api/credit-card/payment";
		CreditStatus creditStatus = restTemplate.postForObject(url, creditInfo, CreditStatus.class);
		return creditStatus;
	}
	
	/**
	 * クレジットカード情報が、使用可能か判定を行う.
	 * 
	 * @return 真偽値
	 */
	public Boolean isCheckCreditInfo(CreditInfo creditInfo) {
		CreditStatus creditStatus = getStatusByCreditInfo(creditInfo);
		System.err.println(creditStatus);
		if("success".equals( creditStatus.getStatus() )) {
			return true;
		} else {
			return false;
		}
	}

}
