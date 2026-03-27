package concession;

public class DrinkReductionStrategy implements StockReductionStrategy {
	public void reduceStock(IndividualItem item) {
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
		item.setStockQuantity(item.getStockQuantity()-reductionAmount);
	}
}
