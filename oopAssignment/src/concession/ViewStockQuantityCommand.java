package concession;

import java.util.ArrayList;
import java.util.Map;

import main.Command;


public class ViewStockQuantityCommand implements Command<Void> {
	public Void execute() {
		
		ArrayList<ConcessionItem> items = ConcessionItemStorage.getAllItems();
		Map<String,Double> stockQuantity = ConcessionItemStorage.getStockQuantity();

		System.out.println("\n=============================================================");
        System.out.println("                         STOCK QUANTITY                      ");
        System.out.println("=============================================================");
        System.out.printf("| %-25s | %-15s | %-10s |%n", "Item Name", "Type", "Quantity","Unit");
        System.out.println("-------------------------------------------------------------");
		
        if (items == null || items.isEmpty()) {
            System.out.println("No items found in the inventory.");
        } 
        else {
        	for (ConcessionItem item : items) {
                if (item instanceof Combo) {
                    System.out.printf("| %-25s | %-15s | %-10s |%n", item.getName(), "Combo", "N/A");
                } 
                else {
                    double quantity = stockQuantity.get(item.getName()); 
                    String status = "";
                    if(((IndividualItem) item).getUnit() == UnitType.ML) {
                    	status = (quantity < 500) ? " (LOW STOCK)" : "";
                    }
                    if(((IndividualItem) item).getUnit() == UnitType.UNIT) {
                    	status = (quantity < 10) ? " (LOW STOCK)" : "";
                    }
                    System.out.printf("| %-25s | %-15s | %-8.2f%s |%s%n", item.getName(), "Individual", quantity, (((IndividualItem) item).getUnit()),status);
                }
            }
        }
        System.out.println("=============================================================");
		return null;
	}
}
