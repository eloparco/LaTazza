package it.polito.latazza.data;

import java.io.Serializable;
import it.polito.latazza.exceptions.NotEnoughCapsules;

public class Beverage implements Serializable {
	private static final long serialVersionUID = 3420502585988933185L;
	
	// all prices are expressed in cents
	private int id, boxPrice, capsulesPerBox, availableCapsules;
	private String name;
	
	public Beverage(int id, String name, int boxPrice , int capsulePerBox) {
		this.name=name;
		this.capsulesPerBox=capsulePerBox;
		this.availableCapsules=0;
		this.boxPrice=boxPrice;
		this.id = id;
	}

	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
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
	
	public int getAvailableCapsules() {
		return availableCapsules;
	}
	
	public int getCapsulesPrice() {
		return boxPrice / capsulesPerBox;
	}
	
	public void increaseAvailableCapsules(int numberOfBoxes) {
		this.availableCapsules += numberOfBoxes * this.capsulesPerBox;
	}
	
	public void decreaseAvailableCapsules(int numberOfCapsules) throws NotEnoughCapsules {
		if (this.availableCapsules < numberOfCapsules)
			throw new NotEnoughCapsules();
		this.availableCapsules -= numberOfCapsules;
	}

	@Override
	public String toString() {
		return this.name;
	}
}
