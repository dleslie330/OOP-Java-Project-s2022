//Dakota Leslie
//Object Oriented Software Development in Java
package pointofsalesbackend;

import java.util.ArrayList;

public class Tester {

	public static void main(String[] args) {
		Itemlist inventory = new Itemlist();

			System.out.println(inventory.findProduct(1) + "\n");
			
			//testing adding already existing product
			inventory.addProduct(new Item(64, "tortilla", 2.78, "mexican or bread", 12));
			
			inventory.addProduct(new Item(16, "bread", 1.50, "bread", 65));
			System.out.println("add product test: " + inventory.findProduct(16));
			
			ArrayList<Item> collection = inventory.collectionOfProducts("vegetable");
			System.out.println("Collection test with \"vegetable\":\n");
			System.out.println(collection + "\n");
			
			collection = inventory.collectionOfProducts("bread");
			System.out.println("\nCollection test with \"bread\":\n");
			System.out.println(collection + "\n");
			
			ArrayList<Item> suggestion = inventory.suggestion(inventory.findProduct(1));
			System.out.println("suggestion test with \"beans\": \n");
			System.out.println(suggestion + "\n");
			
			suggestion = inventory.suggestion(new Item(15, "baking soda", 1.60, "baking", 650));
			System.out.println("suggestion test with \"baking soda\": \n");
			System.out.println(suggestion + "\n");
			
			inventory.purchase(1, 7);
			System.out.println("purchase test: " + inventory.findProduct(1) + "\n");
			
			inventory.restock(1, 7);
			System.out.println("restock test: " + inventory.findProduct(1) + "\n");
			
			inventory.addProduct(new Item(20, "hot sauce", 1.00, "condiment", 45));
			System.out.println("New add: " + inventory.findProduct(20) + "\n");
			inventory.removeProduct(20);
			System.out.println("After deletion: " + inventory.findProduct(20));
			
			//testing removing a non-existent item
			inventory.removeProduct(20);

			//to test file writing
			inventory.addProduct(new Item(0, "banana", .68, "fruit", 999));
		
		inventory.writeFile();
	}

}
