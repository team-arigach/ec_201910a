package jp.co.example.ecommerce_a.form;

import org.springframework.web.multipart.MultipartFile;

public class InsertItemForm {
	
	/** 商品ID */
	private Integer id;
	/** 商品名 */
	private String name;
	/** 商品説明 */
	private String description;
	/** Mサイズの価格 */
	private Integer priceM;
	/** Lサイズの価格 */
	private Integer priceL;
	/** 画像パス */
	private MultipartFile imagePath;
	/** 削除フラグ */
	private Boolean deleted;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getPriceM() {
		return priceM;
	}
	public void setPriceM(Integer priceM) {
		this.priceM = priceM;
	}
	public Integer getPriceL() {
		return priceL;
	}
	public void setPriceL(Integer priceL) {
		this.priceL = priceL;
	}
	public MultipartFile getImagePath() {
		return imagePath;
	}
	public void setImagePath(MultipartFile imagePath) {
		this.imagePath = imagePath;
	}
	public Boolean getDeleted() {
		return deleted;
	}
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	
	

}
