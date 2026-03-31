package paymentprocessing;


public class Ticket {
	private Order orderDetails;

	public Ticket(Order orderDetails) {
		this.orderDetails = orderDetails;
		QRCode.generateQR(orderDetails.toString(), "receipt.png");
	}	
	
	
}
