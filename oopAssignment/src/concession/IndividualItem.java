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

    public IndividualItem() {
    	this.unit = null;
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
    
    public void reduceStock(int quantity) {
    	for(int i = 0; i < quantity; i++) {
    		stockReductionStrategy.reduceStock(this);
    	}
    }
    
    @Override
    public String toString() {
        return String.format("%s", super.toString());
    }
    public String toCSV() {
    	return super.getName() + "," + "INDIVIDUAL" + "," + unit + "," + stockReductionStrategy.getClass().getSimpleName();
    }
    public static IndividualItem fromCSV(String content) {
    	String[] values = content.split(",");
    	UnitType unitValue = UnitType.valueOf(values[2]);
    	StockReductionStrategy strategy = null;
    	
    	switch(values[3]) {
    	case "PopcornReductionStrategy":
    		strategy = new PopcornReductionStrategy();
    		break;

    	case "DrinkReductionStrategy":
    		strategy = new DrinkReductionStrategy();
    		break;
    	default:
    		break;
    	}
    	return new IndividualItem(values[0],unitValue,strategy);
    }
}
