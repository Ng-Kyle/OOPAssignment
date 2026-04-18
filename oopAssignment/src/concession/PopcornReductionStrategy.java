package concession;

import java.util.Map;

import main.InsufficientStockException;

public class PopcornReductionStrategy implements StockReductionStrategy {
	public void reduceStock(ConcessionItem item) {
		double reductionAmount;
		switch(item.getSize()) {
		case MEDIUM:
			reductionAmount = 950;
			break;
		case LARGE:
			reductionAmount = 1300;
			break;
		default:
			reductionAmount = 0;
			break;
		}
<<<<<<< HEAD

		Map<String,Double> stockQuantity = ConcessionItemStorage.getStockQuantity();
		
		if(stockQuantity.get(item.getName()) - reductionAmount < 0) {
			throw new InsufficientStockException();
		}
		ConcessionItemStorage.reduceStock(item.getName(),reductionAmount);
		

		ConcessionItemStorage.reduceStock(item.getName(),reductionAmount);

=======
		Map<String,Double> stockQuantity = ConcessionItemStorage.getStockQuantity();
		
		if(stockQuantity.get(item.getName()) - reductionAmount < 0) {
			throw new InsufficientStockException();
		}
		ConcessionItemStorage.reduceStock(item.getName(),reductionAmount);
		
>>>>>>> branch 'Sub_Classes' of git@github.com:Ng-Kyle/OOPAssignment.git
	}
}
