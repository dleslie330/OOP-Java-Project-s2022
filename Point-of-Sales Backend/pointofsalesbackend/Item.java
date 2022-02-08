package pointofsalesbackend;

public class Item {
	
	private String product;
	private double price;
	private int id;
	private String category;
	private int stock;
	
	public Item() {
		id = 0;
		product = "No Item";
		price = 0.00;
		category = "No Info";
		stock = 0;
	}
	
	public Item(int id, String product, double price, String category, int stock) {
		this.id = id;
		this.product = product;
		this.price = price;
		this.category = category;
		this.stock = stock;
	}
	
	public void setID(int id) {
		this.id = id;
	}
	
	public void setProduct(String product) {
		this.product = product;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public int getID() {
		return id;
	}
	
	public String getProduct() {
		return product;
	}
	
	public double getPrice() {
		return price;
	}
	
	public String getCategory() {
		return category;
	}
	
	public int getStock() {
		return stock;
	}
	
	public String toString() {
		String item = id + " " + product + " " + price + " " + category + " " + stock;
		
		return item;
	}
	
}
