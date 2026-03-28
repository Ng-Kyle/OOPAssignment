package concession;

public abstract class ConcessionItem {
	private String name;
	private double price;
		
	public ConcessionItem(String name, double price) {
        this.name = name;
        this.price = price;
    }
	

    public String getName() { 
    	return name;
    }
    public double getPrice() { 
    	return price; 
    }
    
    public void setName(String name) { 
    	this.name = name; 
    }
    public void setPrice(double price) { 
    	this.price = price; 
    }

    @Override
    public String toString() {
        return String.format("%s - $%.2f", name, price);
    }
}