package jp.co.example.ecommerce_a.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MailSenderService {
	
	@Autowired
	private MailSender mailSender;
	
	/**
	 * メールを送信する
	 */
	@Async
	public void send() {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo("federer.tennis.nadal.1023@gmail.com");
		msg.setFrom("federer.tennis.nadal.1023@gmail.com");
		msg.setSubject("ご注文ありがとうございました。");
		msg.setText("▼タイトル例\r\n" + 
				"【ラクラクピザ】ご注文ありがとうございます（自動送信メール）\r\n" + 
				"\r\n" + 
				" 様\r\n" + 
				"このたびは、「ラクラクピザ」をご利用いただきありがとうございます。\r\n" + 
				"お客様のご注文を承りました。\r\n");
		mailSender.send(msg);
	}

}
