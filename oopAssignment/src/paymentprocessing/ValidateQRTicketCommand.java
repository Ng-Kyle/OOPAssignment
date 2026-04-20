package paymentprocessing;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
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
			System.out.println("\n-------------------------VALIDATE QR TICKET------------------------");
			System.out.println("Enter the QR code file name.(e.g. receipt_1.png)");
			System.out.println("The QR codes are in the storedData/receipts folder");
			System.out.println("File Name: ");
			String filePath = scan.nextLine().trim();
			if (filePath.isEmpty()) {
				System.out.println("Error: File name cannot be empty.");
				continue;
			}
			try {
				String fullPath = "storedData/receipts/" + filePath;
				File qrFile = new File(fullPath);
				
				if(!qrFile.exists()) {
					System.out.println("Error: The file name '" + filePath + "' was not found.");
				}else {
					String qrData = QRCode.readQR(fullPath);
					
					if (qrData == null) {
						System.out.println("Error: Could not decode QR code from image.");
					}else {
						int orderID = Integer.parseInt(qrData);
						boolean orderFound = false;
						
						for(int i = 0; i < storedOrders.size(); i++) {
                            Order currentOrder = storedOrders.get(i);
                            
                            if(currentOrder.getOrderID() == orderID) {
                                orderFound = true;
                                if(!currentOrder.getUsed()) {
                                    currentOrder.setUsed();
                                    CSVLoader.saveCSV(storedOrders, FilePath.ORDERS.toString());
                                    System.out.println(">>> Ticket Redeemed Successfully! [Order #" + orderID + "]");
                                } else {
                                    System.out.println(">>> DENIED: This ticket has already been used!");
                                }
                                break;
                            }
					}
					if (!orderFound) {
						System.out.println("Error: Order ID" + orderID + "does not exists in the system");
					}
					}
				}
			} catch (NumberFormatException e) {
				System.out.println("Error: QR Code has not contain a valid order ID");
			} catch (Exception e) {
				System.out.println("An unexpected error occured");
			}
			System.out.print("\nValidate another ticket? (Y/N)");
			String menuInput = scan.nextLine().trim();
			if(menuInput.isEmpty() ||Character.toUpperCase(menuInput.charAt(0)) != 'Y') {
				break;
			}
		}
		return null;
	}
}
