package paymentprocessing;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.Command;

public class PeakHoursCommand implements Command<Void> {
	private ArrayList<Order> storedOrders;
	public PeakHoursCommand(ArrayList<Order> storedOrders) {
		this.storedOrders = storedOrders;
	}
	public Void execute() {
	    List<List<LocalTime>> bucket = new ArrayList<>();
	    Map<LocalTime, Integer> storedTimes = new HashMap<>();

	    for (int i = 0; i < storedOrders.size(); i++) {
	        LocalTime orderTime = storedOrders.get(i).getShowtime().getTime().toLocalTime();
	        storedTimes.put(orderTime, storedTimes.getOrDefault(orderTime, 0) + 1);
	    }

	    for (int i = 0; i <= storedOrders.size(); i++) {
	        bucket.add(null);
	    }

	    for (Map.Entry<LocalTime, Integer> entry : storedTimes.entrySet()) {
	        LocalTime key = entry.getKey();
	        int frequency = entry.getValue();

	        if (bucket.get(frequency) == null) {
	            bucket.set(frequency, new ArrayList<>());
	        }

	        bucket.get(frequency).add(key);
	    }

	    ArrayList<LocalTime> res = new ArrayList<>();
	    for (int i = bucket.size() - 1; i >= 0; i--) {
	        if (bucket.get(i) != null) {
	            res.addAll(bucket.get(i));
	        }
	    }
		System.out.println("\n=============================================================");
		System.out.println("                       PEAK HOURS REPORT                       ");
		System.out.println("=============================================================");
		System.out.printf("%-5s|%-31s | %s\n","No.", "Time", "Number of Occurences");
	    System.out.println("-------------------------------------------------------------");
	    for(int i = 0; i < Math.min(10, res.size()); i++) {
	        LocalTime timeVal = res.get(i);
	        int count = storedTimes.get(timeVal);
	        System.out.printf("%-4d | %-30s | %d\n", (i + 1), timeVal, count);
	    }
	    System.out.println("-------------------------------------------------------------");
	    System.out.println("=============================================================");
	    return null;
	}
}
