package concession;

import java.util.Map;

import customerMain.InsufficientStockException;

public class DrinkReductionStrategy implements StockReductionStrategy {
	public void reduceStock(ConcessionItem item) {
		double reductionAmount;
		switch(item.getSize()) {
		case MEDIUM:
			reductionAmount = 625;
			break;
		case LARGE:
			reductionAmount = 910;
			break;
		default:
			reductionAmount = 0;
			break;
		}	
		Map<String,Double> stockQuantity = ConcessionItemStorage.getStockQuantity();
		if(stockQuantity.get(item.getName()) - reductionAmount < 0) {
			throw new InsufficientStockException();
		}
		ConcessionItemStorage.reduceStock(item.getName(),reductionAmount);
	}
}
