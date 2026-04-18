package concession;

public abstract class ConcessionItem {
	private String name;
	private SizeType size;
		
	public ConcessionItem(String name) {
        this.name = name;
    }
	public ConcessionItem(String name,SizeType size) {
        this.name = name;
        this.size = size;
    }
	
	public ConcessionItem() {
		this.name = "";
<<<<<<< Upstream, based on origin/master
<<<<<<< Upstream, based on origin/master

=======
		
>>>>>>> 10e4d52 test
=======
>>>>>>> 5ecb9b7 test
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