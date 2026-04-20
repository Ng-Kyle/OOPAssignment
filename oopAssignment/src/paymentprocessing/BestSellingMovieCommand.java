package paymentprocessing;

import java.util.ArrayList;

import main.Command;

public class BestSellingMovieCommand implements Command<Void> {
	private ArrayList<Order> storedOrders;
	public BestSellingMovieCommand(ArrayList<Order> storedOrders) {
		this.storedOrders = storedOrders;
	}
	public Void execute() {
		if(storedOrders == null){
			System.out.println("No avalable data");
			return null;
		}
		java.util.Map<String, Integer> reportData = new java.util.HashMap<>();

		for (Order order : storedOrders) {
			if (order.getShowtime() != null && order.getShowtime().getMovie() != null) {
				String title = order.getShowtime().getMovie().getTitle();
				int tickets = order.getSeatBooking().size();
				
				reportData.put(title, reportData.getOrDefault(title, 0) + tickets);
			}
		}
		System.out.println("\n=============================================================");
		System.out.println("                 BEST SELLING MOVIES REPORT                  ");
		System.out.println("=============================================================");
		System.out.printf("%-40s | %s\n", "Movie Title", "Tickets Sold");
		System.out.println("-------------------------------------------------------------");
		
		reportData.entrySet().stream()
			.sorted(java.util.Map.Entry.<String, Integer>comparingByValue().reversed())
			.forEach(entry -> System.out.printf("%-40s | %d\n", entry.getKey(), entry.getValue()));
			
		System.out.println("=============================================================");

		return null;
	}
}
