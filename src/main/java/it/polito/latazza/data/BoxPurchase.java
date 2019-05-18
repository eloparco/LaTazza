package it.polito.latazza.data;

import it.polito.latazza.exceptions.BeverageException;

public class BoxPurchase extends Transaction {
	private static final long serialVersionUID = -6802904038958433577L;
	
	private Beverage beverage;
	private int boxQuantity;

	public BoxPurchase(Beverage beverage, int boxQuantity) throws BeverageException{
		if (beverage == null || boxQuantity < 0)
			throw new BeverageException();
		this.beverage = beverage;
		this.boxQuantity = boxQuantity;
	}
	
	@Override
	public String toString() {
		return super.toString() + " BUY " + this.beverage + " " + this.boxQuantity;
	}
}
