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
<<<<<<< Upstream, based on origin/master
		Map<String,Double> stockQuantity = ConcessionItemStorage.getStockQuantity();
		
		if(stockQuantity.get(item.getName()) - reductionAmount < 0) {
			throw new InsufficientStockException();
		}
		ConcessionItemStorage.reduceStock(item.getName(),reductionAmount);
		
=======
		ConcessionItemStorage.reduceStock(item.getName(),reductionAmount);
>>>>>>> 41c216c testing why is it im 4 behind even when i just open up my eclipse and just pull
	}
}
