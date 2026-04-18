package paymentprocessing;


public class Ticket {
	private Order orderDetails;

	public Ticket(Order orderDetails) {
		this.orderDetails = orderDetails;
		QRCode.generateQR(Integer.toString(orderDetails.getOrderID()), "receipt_" + orderDetails.getOrderID() + ".png");
	}	
	//to get the order details for receipt
	public Order getOrderDetails() {
		return this.orderDetails;
	}
	
}
