package customerMain;

import java.util.ArrayList;

import concession.ConcessionItem;
import main.Command;
import paymentprocessing.OrderItem;

public class ProcessItemsCommand implements Command<ArrayList<OrderItem>> {
	private ArrayList<ConcessionItem> userItems;
	
	ProcessItemsCommand(ArrayList<ConcessionItem> userItems){
		this.userItems = userItems;
	}
	
	public ArrayList<OrderItem> execute(){
		ArrayList<OrderItem> storedItems = new ArrayList<>();
		for(int i = 0; i < userItems.size(); i++) {
			ConcessionItem item = userItems.get(i);
			boolean repeatItem = false;
			for(int j = 0; j<storedItems.size(); j++) {
				if(storedItems.get(j).getOrderedItem().equals(item.getName())) {
					storedItems.get(j).addQuantity();
					repeatItem = true;
					break;
				}
			}
			if(!repeatItem) {
				storedItems.add(new OrderItem(item.getName(), item.calculatePrice(),item.getSize()));
			}
		}
		return storedItems;
	}
}
