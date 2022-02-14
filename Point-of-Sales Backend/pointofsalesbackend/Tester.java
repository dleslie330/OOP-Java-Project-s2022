package pointofsalesbackend;

import java.util.ArrayList;

public class Tester {

	public static void main(String[] args) {
		Itemlist inventory = new Itemlist();
		boolean running = true;
		
		do {
			System.out.println(inventory.findProduct(1) + "\n");
			
			inventory.addProduct(new Item(4, "Potatoes", 2.30, "vegetable", 35));
			System.out.println("add product test: " + inventory.findProduct(4));
			
			ArrayList<Item> collection = inventory.collectionOfProducts("vegetable");
			System.out.println("Collection test with \"vegetable\":\n");
			System.out.println(collection + "\n");
			
			ArrayList<Item> suggestion = inventory.suggestion(inventory.findProduct(1));
			System.out.println("suggestion test with \"beans\": \n");
			System.out.println(suggestion + "\n");
			
			inventory.purchase(1, 7);
			System.out.println("purchase test: " + inventory.findProduct(1).getStock() + "\n");
			
			inventory.restock(1, 7);
			System.out.println("restock test: " + inventory.findProduct(1).getStock() + "\n");
			
//			inventory.removeProduct(4);
			
			running = false;
		} while(running);
		
		inventory.writeFile();
	}

}
