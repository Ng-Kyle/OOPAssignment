package concession;

public abstract class ConcessionItem {
	private String name;
		
	public ConcessionItem(String name) {
        this.name = name;
    }
	
	public ConcessionItem() {
		this.name = "";
<<<<<<< HEAD
=======
		
>>>>>>> branch 'Classes_Sub' of git@github.com:Ng-Kyle/OOPAssignment.git
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