package main;

import paymentprocessing.Ticket;

public class DisplayReceiptCommand implements Command<Void>{
	private Ticket userReceipt;
	public DisplayReceiptCommand(Ticket userReceipt) {
		this.userReceipt = userReceipt;
	}
	public Void execute() {
		//TODO: Display receipt Yas/Emir
		
		return null;
	}
}
