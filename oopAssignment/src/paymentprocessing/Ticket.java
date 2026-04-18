package paymentprocessing;


public class Ticket {
	private Order orderDetails;

	public Ticket(Order orderDetails) {
		this.orderDetails = orderDetails;
		QRCode.generateQR(Integer.toString(orderDetails.getOrderID()), "storedData/receipts/receipt_" + orderDetails.getOrderID() + ".png");
	}	
<<<<<<< HEAD
	
	public Order getOrderDetails() {
		return orderDetails;
	}
	
=======
	//to get the order details for receipt
	public Order getOrderDetails() {
		return this.orderDetails;
	}
>>>>>>> branch 'master' of git@github.com:Ng-Kyle/OOPAssignment.git
	
}
