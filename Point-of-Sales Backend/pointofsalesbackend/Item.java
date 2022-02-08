package pointofsalesbackend;

public class Item {
	
	private String product;
	private double price;
	private int id;
	private String category;
	private int stock;
	
	public Item() {
		product = "No Item";
		price = 0.00;
		id = 0;
		category = "No Info";
		stock = 0;
	}
	
	public Item(String product, double price, int id, String category, int stock) {
		this.product = product;
		this.price = price;
		this.id = id;
		this.category = category;
		this.stock = stock;
	}
	
	public void setProduct(String product) {
		this.product = product;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public void setID(int id) {
		this.id = id;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public String getProduct() {
		return product;
	}
	
	public double getPrice() {
		return price;
	}
	
	public int getID() {
		return id;
	}
	
	public String getCategory() {
		return category;
	}
	
	public int getStock() {
		return stock;
	}
	
}
