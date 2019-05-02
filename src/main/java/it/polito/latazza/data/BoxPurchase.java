package it.polito.latazza.data;

public class BoxPurchase extends Transaction {
	private static final long serialVersionUID = -6802904038958433577L;
	
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
