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
	
	public OrderItem(String orderedItem, SizeType size, int quantity, double itemPrice){
		this.orderedItem = orderedItem;
		this.itemPrice = itemPrice;
		this.size = size;
		this.quantity = quantity;
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
		itemPrice += itemPrice;
	}
	public int getQuantity() {
		return quantity;
	}
	
	public String toCSV() {
		return orderedItem + ":" + size + ":" + quantity + ":" + itemPrice;
	}
	public static OrderItem fromCSV(String contents) {
			String[] orderItemDetails = contents.split(":");
			return new OrderItem(orderItemDetails[0],SizeType.valueOf(orderItemDetails[1]),Integer.parseInt(orderItemDetails[2]),Double.parseDouble(orderItemDetails[3]));
	}

}
