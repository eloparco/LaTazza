package it.polito.latazza.data;

import it.polito.latazza.exceptions.NotEnoughCapsules;

public class Beverage {
	private int id, boxPrice, capsulesPerBox, availableQuantity;
	private String name;

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
		return availableQuantity;
	}
	
	public void increaseAvailableQuantity(int amount) {
		this.availableQuantity += amount*this.capsulesPerBox;
	}
	
	public void decreaseAvailableQuantity(int amount) throws NotEnoughCapsules {
		if (this.availableQuantity < amount)
			throw new NotEnoughCapsules();
		this.availableQuantity -= amount;
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
