package concession;

public abstract class ConcessionItem {
	private String name;
		
	public ConcessionItem(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return String.format("%s", name);
    }
}