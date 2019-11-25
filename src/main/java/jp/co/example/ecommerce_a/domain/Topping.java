package jp.co.example.ecommerce_a.domain;

/**
 * トッピング情報を表すドメイン.
 * 
 * @author takahiro.suzuki
 *
 */
public class Topping {
	
	/** トッピングID */
	private Integer id;
	/** トッピング名 */
	private String name;
	/** ピザMサイズのトッピング価格 */
	private Integer priceM;
	/** ピザLサイズのトッピング価格 */
	private Integer priceL;
	
	// 以下 getter / setter
	
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
	@Override
	public String toString() {
		return "Topping [id=" + id + ", name=" + name + ", priceM=" + priceM + ", priceL=" + priceL + "]";
	}
	
	

	
}
