package concession;

//This can be anything like a drink, a box of popcorn and even hotdogs and other things in the future.
public class IndividualItem extends ConcessionItem {
    private UnitType unit; 
    private StockReductionStrategy stockReductionStrategy;
    
    public IndividualItem(String name, UnitType unit, StockReductionStrategy stockReductionStrategy) {
    	super(name);
    	this.unit = unit;
    	this.stockReductionStrategy = stockReductionStrategy;
    }

    public IndividualItem(String name,SizeType size,StockReductionStrategy stockReductionStrategy) {
    	super(name,size);
    	this.stockReductionStrategy = stockReductionStrategy;
    }


    public IndividualItem() {
    	this.stockQuantity = 0;
    	this.unit = null;
    	this.size = null;
    }
    
    public double getStockQuantity() {
        return this.stockQuantity;
    }

    public void setStockQuantity(double stockQuantity) {
        this.stockQuantity = stockQuantity;

    }

    public UnitType getUnit() {
    	return unit;
    }
    
    public void setUnit(UnitType unit) {
    	this.unit = unit;
    	
    }
    public StockReductionStrategy getStockReductionStrategy() {
    	return stockReductionStrategy;
    }
    public void setStockReductionStrategy(StockReductionStrategy stockReductionStrategy) {
    	this.stockReductionStrategy = stockReductionStrategy;
    }
    
    public void reduceStock() {
    	stockReductionStrategy.reduceStock(this);
    }
    
    @Override
    public String toString() {
        return String.format("%s", super.toString());
    }
}
