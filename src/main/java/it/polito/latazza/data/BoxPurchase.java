package it.polito.latazza.data;

public class BoxPurchase extends Transaction {
	private Beverage beverage;
	private int boxQuantity;

	public Beverage getBeverage() {
		return beverage;
	}

	public void setBeverage(Beverage beverage) {
		this.beverage = beverage;
	}
	
	public int getboxQuantity() {
		return boxQuantity;
	}

	public void setboxQuantity(int boxQuantity) {
		this.boxQuantity = boxQuantity;
	}

	@Override
	public String toString() {
		// TODO: check date format
		// TODO: check amount format
		return this.getDate().toGMTString() + " BUY " + this.beverage + " " + this.boxQuantity;
	}
}
