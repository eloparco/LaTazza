package it.polito.latazza.data;

import java.util.Date;

public class BoxPurchase extends Transaction {
	private Beverage beverage;
	private int boxQuantity;

	public BoxPurchase(Beverage beverage, int boxQuantity, Date date) {
		super(date);
		this.beverage = beverage;
		this.boxQuantity = boxQuantity;
	}
	
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
		return this.getDateFormatted() + " BUY " + this.beverage + " " + this.boxQuantity;
	}
}
