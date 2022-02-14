package pointofsalesbackend;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Itemlist {
	
	private String fileName;
	HashMap<Integer, Item> inventory;
	
	public Itemlist() {
		fileName = "./pointofsalesbackend/data.txt";
		inventory = new HashMap<Integer, Item>();
		readFile();
	}
	
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
					int id = lineReader.read();
					lineReader.readLine();
					String name = lineReader.readLine();
					double price = lineReader.read();
					lineReader.readLine();
					String description = lineReader.readLine();
					int stock = lineReader.read();
					lineReader.readLine();
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
	
	public void addProduct (Item product) {
		inventory.put(product.getID(), product);
	}
	
	public Item findProduct(int id) {
		return inventory.get(id);
	}
	
	public ArrayList<Item> collectionOfProducts(String category) {
		ArrayList<Item> collection = new ArrayList<Item>();
		
		for (int i : inventory.keySet()) {
			if (inventory.get(i).getCategory().contains(category)) {
				collection.add(inventory.get(i));
			}
		}
			
		return collection;
	}
	
	public Item removeProduct(int id) {
		return inventory.remove(id);
	}
	
	public ArrayList<Item> suggestion(Item item) {
		ArrayList<Item> collection = new ArrayList<Item>();
		
		for (int i : inventory.keySet()) {
			if (inventory.get(i).getProduct().contains(item.getProduct()) || item.getProduct().contains(inventory.get(i).getProduct())) {
				collection.add(inventory.get(i));
			}
			else if (inventory.get(i).getCategory().contains(item.getCategory()) || item.getCategory().contains(inventory.get(i).getCategory())) {
				collection.add(inventory.get(i));
			}
		}
		
		return collection;
	}
	
	public void purchase(int id, int num) {
		inventory.get(id).removeStock(num);
	}
	
	public void restock(int id, int num) {
		inventory.get(id).addStock(num);
	}
	
	public void writeFile () {
		// overloaded method: this calls doWrite with file used to read data
		// use this for saving data between runs
		doWrite(fileName);
	} // end of writeFile method
	
	public void writeFile(String altFileName) {
		// overloaded method: this calls doWrite with different file name 
		// use this for testing write
		doWrite(altFileName);		
	}// end of writeFile method
	
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

}
