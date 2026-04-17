package paymentprocessing;

import concession.SizeType;

public class OrderItem {
	private String orderedItem;
	private double itemPrice;
	private int quantity;
	private SizeType size;
	
	public OrderItem(String orderedItem, double itemPrice, SizeType size){
		this.orderedItem = orderedItem;
		this.itemPrice = itemPrice;
		this.size = size;
		quantity = 1;
	}
	
	public SizeType getSize() {
		return size;
	}
	public String getOrderedItem() {
		return orderedItem;
	}
	
	public double getItemPrice() {
		return itemPrice;
	}
	public void addQuantity() {
		quantity++;
	}
	public int getQuantity() {
		return quantity;
	}
}
