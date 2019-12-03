package jp.co.example.ecommerce_a.controller;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_a.domain.Item;
import jp.co.example.ecommerce_a.form.InsertItemForm;
import jp.co.example.ecommerce_a.service.InsertItemService;

@Controller
@RequestMapping("/insertItem")
public class InsertItemController {
	
	@Autowired
	private InsertItemService itemService;
	
	
	@RequestMapping("/input")
	public String input(InsertItemForm insertItemForm) throws IOException {
		Item item = new Item();
		BeanUtils.copyProperties(insertItemForm, item);
		System.err.println("insertitemForm = "+insertItemForm);
		
		byte[] encoded = Base64.getEncoder().encode(insertItemForm.getImagePath().getBytes());
		Charset charset = StandardCharsets.UTF_8;
		
		String base64 = new String(encoded, charset);
		String fileExtension = insertItemForm.getImagePath().getOriginalFilename().substring(insertItemForm.getImagePath().getOriginalFilename().length() - 3);
		StringBuilder base64image = new StringBuilder();
		if("jpg".equals(fileExtension)) {
			base64image.append("data:image/jpeg;base64,");
		}
		if("png".equals(fileExtension)) {
			base64image.append("data:image/png;base64,");
		}
		base64image.append(base64);
		item.setImagePath(base64image.toString());
		System.err.println("item = "+item);
		item.setId(itemService.itemCount()+1);
		
		itemService.insertItem(item);
		
		return "redirect:/";
	}
	
	@RequestMapping("")
	public String insertItem() {
		return "new_item";
	}
	

}
