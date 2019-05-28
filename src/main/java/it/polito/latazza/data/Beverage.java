package it.polito.latazza.data;

import java.io.Serializable;

import it.polito.latazza.exceptions.BeverageException;
import it.polito.latazza.exceptions.NotEnoughCapsules;

public class Beverage implements Serializable {
	private static final long serialVersionUID = 3420502585988933185L;
	
	// all prices are expressed in cents
	private int id, boxPrice, capsulesPerBox, availableCapsules;
	private String name;
	
	public Beverage(Integer id, String name, Integer boxPrice , Integer capsulePerBox) throws BeverageException {
		if (id < 0)
			throw new BeverageException();
		this.id = id;
		
		this.availableCapsules = 0;
		setName(name);
		setCapsulesPerBox(capsulesPerBox);
		setBoxPrice(boxPrice);		
	}

	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) throws BeverageException {
		if (name == null || name.equals(""))
			throw new BeverageException();
		this.name = name;
	}
	
	public int getBoxPrice() {
		return boxPrice;
	}
	
	public void setBoxPrice(Integer boxPrice) throws BeverageException {
		if(boxPrice == null || boxPrice < 0)
			throw new BeverageException();
		this.boxPrice = boxPrice;
	}
	
	public int getCapsulesPerBox() {
		return capsulesPerBox;
	}
	
	public void setCapsulesPerBox(Integer capsulesPerBox) throws BeverageException {
		if(capsulesPerBox == null || capsulesPerBox < 0)
			throw new BeverageException();
		this.capsulesPerBox = capsulesPerBox;
	}
	
	public int getAvailableCapsules() {
		return availableCapsules;
	}
	
	public int getCapsulesPrice() {
		return boxPrice / capsulesPerBox;
	}
	
	public void increaseAvailableCapsules(int numberOfBoxes) throws BeverageException {
		// handle negative value and overflow
		if (numberOfBoxes < 0 || this.availableCapsules + numberOfBoxes * this.capsulesPerBox < 0)
			throw new BeverageException();
		
		this.availableCapsules += numberOfBoxes * this.capsulesPerBox;
	}
	
	public void decreaseAvailableCapsules(int numberOfCapsules) throws NotEnoughCapsules, BeverageException {
		if (this.availableCapsules < numberOfCapsules )
			throw new NotEnoughCapsules();
		
		// handle negative value and overflow
		if (numberOfCapsules < 0 || this.availableCapsules - numberOfCapsules < 0)
			throw new BeverageException();
				
		this.availableCapsules -= numberOfCapsules;
	}

	@Override
	public String toString() {
		return this.name;
	}
}
