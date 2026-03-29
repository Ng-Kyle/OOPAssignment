package concession;

//This can be anything like a drink, a box of popcorn and even hotdogs and other things in the future.
public class IndividualItem extends ConcessionItem {
    private UnitType unit;
    private SizeType size;
    private StockReductionStrategy strategy;

    public IndividualItem(String name, double price, SizeType size, 
				UnitType unit, StockReductionStrategy strategy) {
    	super(name,price);
        this.size = size;
        this.unit = unit;
        this.strategy = strategy;
    }
    
    public SizeType getSize() {
    	return size;
    }
    public void setSize(SizeType size) {
    	this.size = size;
    }

    public UnitType getUnit() {
    	return unit;
    }
    
    public void setUnit(UnitType unit) {
    	this.unit = unit;
    }
    public StockReductionStrategy getStrategy() {
    	return strategy;
    }
    public void setStrategy(StockReductionStrategy strategy) {
    	this.strategy = strategy;
    }
    public void reduceStock() {
    	strategy.reduceStock(this);
    }
    @Override
    public String toString() {
        return String.format("[%s] %s", size, super.toString());
    }
}
