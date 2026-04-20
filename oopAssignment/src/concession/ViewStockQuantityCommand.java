package concession;

import java.util.ArrayList;

import main.Command;


public class ViewStockQuantityCommand implements Command<Void> {
	public Void execute() {
		
		ArrayList<ConcessionItem> items = ConcessionItemStorage.getAllItems();
		
		System.out.println("\n=============================================================");
        System.out.println("                         STOCK QUANTITY                      ");
        System.out.println("=============================================================");
        System.out.printf("| %-25s | %-15s | %-10s |%n", "Item Name", "Type", "Quantity");
        System.out.println("-------------------------------------------------------------");
        

		
        if (items == null || items.isEmpty()) {
            System.out.println("No items found in the inventory.");
        } else {
            for (ConcessionItem item : items) {
            
                if (item instanceof Combo) {
                    System.out.printf("| %-25s | %-15s | %-10s |%n", 
                                      item.getName(), "Combo", "N/A");
                } else {

                    int quantity = item.getQuantity(); 
                    
                    String status = (quantity < 10) ? " (LOW STOCK)" : "";
                    
                    System.out.printf("| %-25s | %-15s | %-10d%s |%n", 
                                      item.getName(), "Individual", quantity, status);
                }
            }
        }
        System.out.println("=============================================================");

		return null;
	}
}
