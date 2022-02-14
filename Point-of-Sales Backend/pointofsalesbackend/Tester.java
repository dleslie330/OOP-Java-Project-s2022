package pointofsalesbackend;

public class Tester {

	public static void main(String[] args) {
		Itemlist inventory = new Itemlist();
		boolean running = true;
		
		do {
			System.out.println(inventory.findProduct(1) + "\n");
			
			inventory.addProduct(new Item(4, "Potatoes", 2.30, "vegetable", 35));
			System.out.println("add product test: " + inventory.findProduct(4));
			
			int[] collection = inventory.collectionOfProducts("vegetable");
			System.out.println("Collection test with \"vegitable\":\n");
			for (int i = 0; i < collection.length; i++) {
				System.out.println(inventory.findProduct(collection[i]) + "\n");
			}
			
			int[] suggestion = inventory.suggestion(inventory.findProduct(1));
			System.out.println("suggestion test with \"beans\": \n");
			for (int i = 0; i < suggestion.length; i++) {
				System.out.println(inventory.findProduct(suggestion[i]) + "\n");
			}
			
			inventory.purchase(1, 7);
			System.out.println("purchase test: " + inventory.findProduct(1).getStock() + "\n");
			
			inventory.restock(1, 7);
			System.out.println("restock test: " + inventory.findProduct(1).getStock() + "\n");
			
			inventory.removeProduct(4);
			
			running = false;
		} while(running);
		
		inventory.writeFile("data file");
	}

}
