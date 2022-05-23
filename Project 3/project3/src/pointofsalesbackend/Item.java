//Dakota Leslie
//Object Oriented Software Development in Java
package pointofsalesbackend;

public class Item {
	
	private String product; //name of the item
	private double price; //price of the item
	private int id; //id of the item
	private String category; //the category or description the item would fit into
	private int stock; //how much of the item the store is carrying at the moment
	
	//default constructor
	public Item() {
		id = 0;
		product = "No Item";
		price = 0.00;
		category = "No Info";
		stock = 0;
	}
	
	//main constructor
	public Item(int id, String product, double price, String category, int stock) {
		this.id = id;
		this.product = product.toLowerCase();
		this.price = price;
		this.category = category.toLowerCase();
		this.stock = stock;
	}
	
	//sets the id variable
	public void setID(int id) {
		this.id = id;
	}
	
	//sets the product variable
	public void setProduct(String product) {
		if (product != null) {
			this.product = product.toLowerCase();
		}
		else {
			this.product = product;
		}
	}
	
	//sets the price variable
	public void setPrice(double price) {
		this.price = price;
	}
	
	//sets the category variable
	public void setCategory(String category) {
		if (category != null) {
			this.category = category.toLowerCase();
		}
		else {
			this.category = category;
		}
	}
	
	//sets the stock variable
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	//retrieves the id variable
	public int getID() {
		return id;
	}
	
	//retrieves the product variable
	public String getProduct() {
		return product;
	}
	
	//retrieves the price variable
	public double getPrice() {
		return price;
	}
	
	//retrieves the category variable
	public String getCategory() {
		return category;
	}
	
	//retrieves the stock variable
	public int getStock() {
		return stock;
	}
	
	//increases the stock variable (can add more than one item at a time)
	public void addStock(int num) {
		for (int i = 1; i <= num; i++) {
			stock++;
		}
	}
	
	//decreases the stock variable (can remove more than one item at a time)
	public void removeStock(int num) {
		for (int i = 1; i <= num; i++) {
			stock--;
		}
	}
	
	//string representation of data
	public String toString() {
		String item = id + " " + product + " " + price + " " + category + " " + stock;
		
		return item;
	}
	
}
