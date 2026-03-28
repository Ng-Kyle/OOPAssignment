package concession;

//This can be anything like a drink, a box of popcorn and even hotdogs and other things in the future.
public class IndividualItem extends ConcessionItem {
    private double stockQuantity;
    private UnitType unit;
    private SizeType size;
    private StockReductionStrategy strategy;

    public IndividualItem(String name, double price, double stock, SizeType size, 
				UnitType unit, StockReductionStrategy strategy) {
    	super(name,price);
        this.stockQuantity = stock;
        this.size = size;
        this.unit = unit;
        this.strategy = strategy;
    }

    public double getStockQuantity() {
        return this.stockQuantity;
    }

    public void setStockQuantity(double stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
    
    public SizeType getSize() {
    	return size;
    }
    public void setSize(SizeType size) {
    	this.size = size;
    }

    public UnitType getUnitType() {
    	return unit;
    }
    
    public void setUnitType(UnitType unit) {
    	this.unit = unit;
    }
    public void reduceStock() {
        if (strategy != null) {
            strategy.reduceStock(this);
        }
    }
    @Override
    public String toString() {
        return String.format("[%s] %s", size, super.toString());
    }
}
