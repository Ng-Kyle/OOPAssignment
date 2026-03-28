package concession;

public class ConcessionItemFactory {
	public static ConcessionItem selectConcessionItem(int choice) {
		switch (choice){
		case 1:
			return new IndividualItem("Popcorn", 18.00, 1, SizeType.MEDIUM, UnitType.ML,new PopcornReductionStrategy());
		default:
			break;
		}
		return null;
	}
}
