package oopAssignment;

//This can be anything like a drink, a box of popcorn and even hotdogs and other things in the future.
public class IndividualItem extends ConcessionItem{
	private double stockQuantity;
	private UnitType unit;
	private SizeType size;
	private StockReductionStrategy strategy;
	
	public SizeType getSize() {
		return this.size;
	}
	public double getStockQuantity() {
		return 0;
	}
	public void setStockQuantity(double stockQuantity) {
		
	}
	public void reduceStock() {
		strategy.reduceStock(this);
	}
}
