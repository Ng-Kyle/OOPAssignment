package concession;

import main.CSVStored;

public abstract class ConcessionItem implements CSVStored{
	private String name;
	private SizeType size;
	private int quantity;
		
	public ConcessionItem(String name) {
        this.name = name;
    }
	public ConcessionItem(String name,SizeType size) {
        this.name = name;
        this.size = size;
    }
	
	public ConcessionItem() {
		this.name = "";
	}

    public String getName() { 
    	return name;
    }
    
    public void setName(String name) { 
    	this.name = name; 
    }
    
    public SizeType getSize() {
    	return size;
    }
    public void setSize(SizeType size) {
    	this.size = size;
    }
    
    public int getQuantity() {
    	return quantity;
    };
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public abstract void reduceStock(int quantity);
    public abstract String toCSV();
	public double calculatePrice() {
		return ItemPriceStorage.getItemPrices().get(name).get(size);
	}

    @Override
    public String toString() {
        return String.format("%s", name);
    }
}