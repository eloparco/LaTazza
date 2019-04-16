package it.polito.latazza.data;

import it.polito.latazza.exceptions.NotEnoughCapsules;

public class Beverage {
	private int id, boxPrice, capsulesPerBox, availableCapsules;
	private String name;
	
	public Beverage(int id, String name, int capsulePerBox, int availableCapsules) {
		this.name=name;
		this.capsulesPerBox=capsulePerBox;
		this.availableCapsules=availableCapsules;
		this.id = id;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getBoxPrice() {
		return boxPrice;
	}
	
	public void setBoxPrice(int boxPrice) {
		this.boxPrice = boxPrice;
	}
	
	public int getCapsulesPerBox() {
		return capsulesPerBox;
	}
	
	public void setCapsulesPerBox(int capsulesPerBox) {
		this.capsulesPerBox = capsulesPerBox;
	}
	
	public int getAvailableQuantity() {
		return availableCapsules;
	}
	
	public void increaseAvailableQuantity(int numberOfBoxes) {
		this.availableCapsules += numberOfBoxes * this.capsulesPerBox;
	}
	
	public void decreaseAvailableQuantity(int numberOfCapsules) throws NotEnoughCapsules {
		if (this.availableCapsules < numberOfCapsules)
			throw new NotEnoughCapsules();
		this.availableCapsules -= numberOfCapsules;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getCapsulesPrice() {
		return boxPrice/capsulesPerBox;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
}
