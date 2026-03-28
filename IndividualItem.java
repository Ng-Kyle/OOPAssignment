package oopAssignment;

//This can be anything like a drink, a box of popcorn and even hotdogs and other things in the future.
public class IndividualItem extends ConcessionItem {
    private double stockQuantity;
    private UnitType unit;
    private SizeType size;
    private StockReductionStrategy strategy;

    public IndividualItem(String name, double price, double stock, SizeType size, 
				UnitType unit, StockReductionStrategy strategy) {
        setName(name);
        setPrice(price);
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

    public void reduceStock() {
        if (strategy != null) {
            strategy.reduceStock(this);
        }

    @Override
    public String toString() {
        return String.format("[%s] %s", size, super.toString());
    }
}
}