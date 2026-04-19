package paymentprocessing;


public class Ticket {
	private Order orderDetails;

	public Ticket(Order orderDetails) {
		this.orderDetails = orderDetails;
		QRCode.generateQR(Integer.toString(orderDetails.getOrderID()), "storedData/receipts/receipt_" + orderDetails.getOrderID() + ".png");
	}	
	
	public Order getOrderDetails() {
		return orderDetails;
	}
	

}
