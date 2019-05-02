package it.polito.latazza.data;

public class BoxPurchase extends Transaction {
	private Beverage beverage;
	private int boxQuantity;

	public BoxPurchase(Beverage beverage, int boxQuantity) {
		this.beverage = beverage;
		this.boxQuantity = boxQuantity;
	}
	
	@Override
	public String toString() {
		return super.toString() + " BUY " + this.beverage + " " + this.boxQuantity;
	}
}
