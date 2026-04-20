package paymentprocessing;

import java.util.ArrayList;
import java.util.Scanner;

import main.CSVLoader;
import main.Command;
import main.FilePath;

public class ValidateQRTicketCommand implements Command<Void> {
	private Scanner scan;
	private ArrayList<Order> storedOrders;
	
	public ValidateQRTicketCommand(Scanner scan, ArrayList<Order> storedOrders) {
		this.scan = scan;
		this.storedOrders = storedOrders;
	}
	
	public Void execute() {
		while(true) {
			System.out.println("Enter the QR code file name.");
			String filePath = scan.nextLine().trim();
			int orderID = Integer.parseInt(QRCode.readQR("storedData/receipts/" + filePath));
			for(int i = 0; i<storedOrders.size(); i++) {
				if(storedOrders.get(i).getOrderID() == orderID && !storedOrders.get(i).getUsed()) {
					storedOrders.get(i).setUsed();
					CSVLoader.saveCSV(storedOrders, FilePath.ORDERS.toString());
					System.out.println("Ticket Redeemed!");
					break;
				}
				if(storedOrders.get(i).getOrderID() == orderID && storedOrders.get(i).getUsed()) {
					System.out.println("This ticket has been used!");
					break;
				}
			}
			String menuInput = scan.nextLine().trim();
			if(menuInput.isEmpty() ||Character.toUpperCase(menuInput.charAt(0)) != 'Y') {
				break;
			}
		}
		return null;
	}
}
