package paymentprocessing;

import main.FilePath;

public class Ticket {
	private Order orderDetails;

	public Ticket(Order orderDetails) {
		this.orderDetails = orderDetails;
		QRCode.generateQR(Integer.toString(orderDetails.getOrderID()), FilePath.RECEIPT.toString() + "receipt_" + orderDetails.getOrderID() + ".png");
	}	
	
	public Order getOrderDetails() {
		return orderDetails;
	}
}