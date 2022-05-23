//Dakota Leslie
//Object Oriented Software Development in Java
package pointofsalesbackend;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

public class Itemlist {
	
	private String fileName; //the file that is initially read by the program
	HashMap<Integer, Item> inventory; //The collection of items
	
	//default constructor
	public Itemlist() {
		fileName = "./pointofsalesbackend/data.txt";
		inventory = new HashMap<Integer, Item>();
		readFile(); //reads file on initialization
	}
	
	//reads the file and turns the information into an item object and stores each item into the hashmap collection
	private void readFile () {
		BufferedReader lineReader = null;

		try {
			FileReader fr = new FileReader(fileName);
			lineReader = new BufferedReader(fr);
			String line = null;
			while ((line = lineReader.readLine())!=null) {
				int id = Integer.parseInt(line);
				String name = lineReader.readLine();
				double price = Double.parseDouble(lineReader.readLine());
				String description = lineReader.readLine();
				int stock = Integer.parseInt(lineReader.readLine());
				inventory.put(id, new Item(id, name, price, description, stock));
		}
		} catch (Exception e) {
			System.err.println("there was a problem with the file reader, try different read type.");
			e.printStackTrace();
			try {
				lineReader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(fileName.substring(1))));
				String line = null;
				while ((line = lineReader.readLine())!=null) {
					int id = Integer.parseInt(line);
					String name = lineReader.readLine();
					double price = Double.parseDouble(lineReader.readLine());
					String description = lineReader.readLine();
					int stock = Integer.parseInt(lineReader.readLine());
					inventory.put(id, new Item(id, name, price, description, stock));			}
			} catch (Exception e2) {
				System.err.println("there was a problem with the file reader, try again.  either no such file or format error");
			} finally {
				if (lineReader != null)
					try {
						lineReader.close();
					} catch (IOException e2) {
						System.err.println("could not close BufferedReader");
					}
			}			
		} finally {
			if (lineReader != null)
				try {
					lineReader.close();
				} catch (IOException e) {
					System.err.println("could not close BufferedReader");
				}
		}
	} // end of readFile method
	
	//adds a product to the hashmap
	public void addProduct (Item product) {
		if (inventory.containsKey(product.getID())) {} //checks if the id (item) is already in the hashmap 
		else {
			inventory.put(product.getID(), product);
		}
	}
	
	//finds a product in the hashmap
	public Item findProduct(int id) {
		return inventory.get(id);
	}
	
	public Item findProduct(String name) {
		for (int i : inventory.keySet()) {
			if (inventory.get(i).getProduct().equals(name)) {
				return inventory.get(i);
			}
		}
		
		return null;
	}
	
	//returns an array list of items with similar "categories"
	public Vector<Item> collectionOfProducts(String category) {
		Vector<Item> collection = new Vector<Item>();
		if (category.equals("all") || category.equals("All")) {
			for (int i: inventory.keySet()) {
				collection.add(inventory.get(i));
			}
			return collection;
		}
		for (int i : inventory.keySet()) {
			if (inventory.get(i).getCategory().contains(category) || category.contains(inventory.get(i).getCategory()) || inventory.get(i).getProduct().contains(category) || category.contains(inventory.get(i).getProduct())) { //checks if the passed in category is within the item category or vice versa (checking for similarity)
				collection.add(inventory.get(i));
			}
		}
			
		return collection;
	}
	
	//removes an item from the hashmap
	public Item removeProduct(int id) {
		return inventory.remove(id);
	}
	
	//returns an array list of items that are similar to the item that is passed in
	public ArrayList<Item> suggestion(Item item) {
		ArrayList<Item> collection = new ArrayList<Item>();
		
		for (int i : inventory.keySet()) {
			if (inventory.get(i).getProduct().contains(item.getProduct()) || item.getProduct().contains(inventory.get(i).getProduct())) { //checks if the product names are similar
				collection.add(inventory.get(i));
			}
			else if (inventory.get(i).getCategory().contains(item.getCategory()) || item.getCategory().contains(inventory.get(i).getCategory())) { //checks if the categories are similar (like collectionOfProducts function)
				collection.add(inventory.get(i));
			}
		}
		
		return collection; //returns if the names are similar or if the categories are similar
	}
	
	//calls the removeStock function in the Item class for someone to purchase any amount of an item within the hashmap
	public void purchase(int id, int num) {
		inventory.get(id).removeStock(num);
	}
	
	//calls the addStock function in the Item class for someone to restock any amount of an item within the hashmap
	public void restock(int id, int num) {
		inventory.get(id).addStock(num);
	}
	
	//writes the hashmap into the data file
	public void writeFile () {
		// overloaded method: this calls doWrite with file used to read data
		// use this for saving data between runs
		doWrite(fileName);
	} // end of writeFile method
	
	//writes the hashmap into a new data file if needed
	public void writeFile(String altFileName) {
		// overloaded method: this calls doWrite with different file name 
		// use this for testing write
		doWrite(altFileName);		
	}// end of writeFile method
	
	//the write method that puts the information of each item into the data file
	private void doWrite(String fn) {
		// this method writes all of the data in the persons array to a file
		try
		{
			FileWriter fw = new FileWriter(fn);
			BufferedWriter myOutfile = new BufferedWriter(fw);			
			
			for (int i : inventory.keySet()) {
				Item item = inventory.get(i);
					myOutfile.write (item.getID() + "\n");
					myOutfile.write (item.getProduct()+"\n");
					myOutfile.write (item.getPrice()+"\n");
					myOutfile.write (item.getCategory()+"\n");
					myOutfile.write (item.getStock()+"\n");
			}
			myOutfile.flush();
			myOutfile.close();
		}
		catch (Exception e) {
			e.printStackTrace();
			System.err.println("Didn't save to " + fn);
		}
	}	
	
	//string representation of data
	public String toString() {
		String itemList = "";
		
		for (int i : inventory.keySet()) {
			itemList = inventory.get(i) + "; ";
		}
		
		return itemList;
	}

}
