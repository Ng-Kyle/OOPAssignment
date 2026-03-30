package concession;

public class OrderItem {
	ConcessionItem orderItem;
	SizeType size;
	
	public OrderItem(ConcessionItem orderItem, SizeType size) {
		this.orderItem = orderItem;
		this.size = size;
	}
	public SizeType getSize() {
		return size;
	}
	
	public ConcessionItem getOrderItem() {
		return orderItem;
	}
	
	public double calculatePrice() {
		return ItemPriceStorage.getItemPrices().get(orderItem.getName()).get(size);
	}
}
