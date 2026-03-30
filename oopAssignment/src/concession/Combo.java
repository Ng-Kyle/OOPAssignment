package concession;
/*A combo that contains multiple concession items
Example: Family Combo that includes 2 Popcorn boxes and 4 Drinks
*/

public class Combo extends ConcessionItem {
    private ConcessionItem[] comboItems;

    public Combo(String name,ConcessionItem[] comboItems) {
        super(name);
        this.comboItems = comboItems;
    }
    
    

    public ConcessionItem[] getComboItems() {
        return comboItems;
    }

    public void setComboItems(ConcessionItem[] comboItems) {
        this.comboItems = comboItems;
    }

    @Override
    public String toString() {
        return String.format("%s (Combo)", getName());
    }
}