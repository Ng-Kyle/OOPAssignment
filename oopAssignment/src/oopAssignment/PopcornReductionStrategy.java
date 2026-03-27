package oopAssignment;

public class PopcornReductionStrategy implements StockReductionStrategy {
	public void reduceStock(IndividualItem item) {
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
		item.setStockQuantity(item.getStockQuantity()-reductionAmount);
	}
}
