package it.polito.latazza.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import it.polito.latazza.exceptions.BeverageException;
import it.polito.latazza.exceptions.NotEnoughCapsules;

public class Beverage implements Serializable {
	private static final long serialVersionUID = 3420502585988933185L;
	
	// all prices are expressed in cents
	private int id;
	private List<BeverageData> beverageData;
	private String name;
	
	
	public Beverage(Integer id, String name, Integer boxPrice , Integer capsulePerBox) throws BeverageException {
		if (id < 0)
			throw new BeverageException();
		this.id = id;
		
		this.beverageData = new ArrayList<Beverage.BeverageData>();
		this.beverageData.add(new BeverageData());
		setName(name);
		setCapsulesPerBox(capsulePerBox);
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
		BeverageData bd = this.beverageData.get(this.beverageData.size()-1); //take last updated beverage data
		return bd.getBoxPrice();
	}
	
	private void setBoxPrice(Integer boxPrice) throws BeverageException {
		BeverageData bd = this.beverageData.get(this.beverageData.size()-1); //take last updated beverage data
		if(boxPrice == null || boxPrice < 0)
			throw new BeverageException();
		bd.setBoxPrice(boxPrice);
	}
	
	public int getCapsulesPerBox() {
		BeverageData bd = this.beverageData.get(this.beverageData.size()-1); //take last updated beverage data
		return bd.getCapsulesPerBox();
	}
	
	private void setCapsulesPerBox(Integer capsulesPerBox) throws BeverageException {
		BeverageData bd = this.beverageData.get(this.beverageData.size()-1); //take last updated beverage data
		if(capsulesPerBox == null || capsulesPerBox < 0)
			throw new BeverageException();
		bd.setCapsulesPerBox(capsulesPerBox);
	}
	
	public void updateBeverageData(String name, Integer boxPrice , Integer capsulePerBox) throws BeverageException {
		this.beverageData.add(new BeverageData());
		this.setName(name);
		this.setBoxPrice(boxPrice);
		this.setCapsulesPerBox(capsulePerBox);
	}
	
	public int getAvailableCapsules() {
		BeverageData bd = this.beverageData.get(this.beverageData.size()-1); //take last updated beverage data
		return bd.getAvailableCapsules();
	}
	
	public int getCapsulesPrice() {
		BeverageData bd = this.beverageData.get(this.beverageData.size()-1); //take last updated beverage data
		return bd.getBoxPrice() / bd.getCapsulesPerBox();
	}
	
	public void increaseAvailableCapsules(int numberOfBoxes) throws BeverageException {
		// handle negative value and overflow
		BeverageData bd = this.beverageData.get(this.beverageData.size()-1); //take last updated beverage data
		if (numberOfBoxes < 0 || bd.getAvailableCapsules() + numberOfBoxes * bd.capsulesPerBox < 0)
			throw new BeverageException();
		
		bd.setAvailableCapsules(bd.getAvailableCapsules() + (numberOfBoxes * bd.getCapsulesPerBox()));
	}
	
	public void decreaseAvailableCapsules(int numberOfCapsules) throws NotEnoughCapsules, BeverageException {
		BeverageData bd = this.beverageData.get(0); //take remaining beverage data
		Integer totalAvailable = this.beverageData.stream().collect(java.util.stream.Collectors.summingInt(l -> l.getAvailableCapsules()));
		if (totalAvailable < numberOfCapsules )
			throw new NotEnoughCapsules();
		
		// handle negative value and overflow
		if (numberOfCapsules < 0 || totalAvailable - numberOfCapsules < 0)
			throw new BeverageException();
				
		while(numberOfCapsules > 0) {
			// take min between number of capsules to decrease or avaialbe capsules with not updated price
			int min = (bd.getAvailableCapsules() > numberOfCapsules) ? numberOfCapsules : bd.getAvailableCapsules();
			//remove the min number of capsules
			bd.setAvailableCapsules(bd.getAvailableCapsules() - min);
			//update the amount of capsules to sell
			numberOfCapsules -= min;
			//if still capsuels to sell => finished not updated capsules => remove them
			if(numberOfCapsules > 0)
				this.beverageData.remove(0);
			//update the capsules to sell
			bd = this.beverageData.get(0);
		}
	}

	@Override
	public String toString() {
		return this.name;
	}
	
	
	
	
	
	private class BeverageData {
		private Integer boxPrice,capsulesPerBox, availableCapsules;
		
		public BeverageData(Integer boxPrice, Integer capsulesPerBox, Integer availableCapsules) {
			this.boxPrice=boxPrice;
			this.capsulesPerBox=capsulesPerBox;
			this.availableCapsules=availableCapsules;
		}
		
		public BeverageData() {
			this.boxPrice=0;
			this.capsulesPerBox=0;
			this.availableCapsules=0;
		}
		
		public Integer getAvailableCapsules() {
			return availableCapsules;
		}
		
		public Integer getBoxPrice() {
			return boxPrice;
		}
		
		public Integer getCapsulesPerBox() {
			return capsulesPerBox;
		}
		
		public void setAvailableCapsules(Integer availableCapsules) {
			this.availableCapsules = availableCapsules;
		}
		
		public void setCapsulesPerBox(Integer capsulesPerBox) {
			this.capsulesPerBox = capsulesPerBox;
		}
		
		public void setBoxPrice(Integer boxPrice) {
			this.boxPrice = boxPrice;
		}
	}
	
	
	
}
