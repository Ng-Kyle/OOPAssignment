package concession;

import java.util.ArrayList;
import java.util.Map;

import main.Command;

public class ViewItemCommand implements Command<Void> {
	public Void execute() { //TODO: DISPLAY ITEMS AND FOR COMBOS DISPLAY THEIR INDIVIDUAL ITEMS AS WELL
		ArrayList<ConcessionItem> showItems = ConcessionItemStorage.getAllItems();
		Map<String,Map<SizeType,Double>> showPrice = ItemPriceStorage.getItemPrices();
        System.out.println("+==============================================+");
        System.out.println("|                     MENU                     |");
        System.out.println("+==============================================+");;
        for(int i = 0; i < showItems.size() ;i++) {
        	System.out.print("| " + (i + 1) + ". " + showItems.get(i).getName());

            if (showItems.get(i) instanceof Combo) {
                Double itemPrice = showPrice.get(showItems.get(i).getName()).get(SizeType.NONE);
                System.out.printf(" | RM%.2f |%n", itemPrice); 
            } 
            else {
                System.out.printf("| Medium " + "RM%.2f" + " |", showPrice.get(showItems.get(i).getName()).get(SizeType.MEDIUM));
                System.out.printf(" Large " + "RM%.2f" + " |%n", showPrice.get(showItems.get(i).getName()).get(SizeType.LARGE));
            }
        }
		System.out.println("+==============================================+");
		return null;
	}
}
