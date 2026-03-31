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
<<<<<<< Upstream, based on origin/Classes_Sub

=======
    public IndividualItem(String name,SizeType size,StockReductionStrategy stockReductionStrategy) {
    	super(name,size);
    	this.stockReductionStrategy = stockReductionStrategy;
    }
>>>>>>> f85c0da stock reduction functionality done
    public IndividualItem(String name,StockReductionStrategy stockReductionStrategy) {
    	super(name);
    	this.stockReductionStrategy = stockReductionStrategy;

    public IndividualItem() {
    	this.unit = null;
    }
<<<<<<< Upstream, based on origin/Classes_Sub
    
=======

>>>>>>> f85c0da stock reduction functionality done
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
