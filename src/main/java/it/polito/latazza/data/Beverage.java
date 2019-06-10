package it.polito.latazza.data;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.stream.Collectors;

import it.polito.latazza.exceptions.BeverageException;
import it.polito.latazza.exceptions.NotEnoughCapsules;

public class Beverage implements Serializable {
	private static final long serialVersionUID = 3420502585988933185L;
	
	// all prices are expressed in cents
	private int id;
	private LinkedList<BeverageData> updatesHistory;
	private String name;
	
	public Beverage(Integer id, String name, Integer boxPrice , Integer capsulePerBox) throws BeverageException {
		if (id < 0)
			throw new BeverageException();
		this.id = id;
		
		this.updatesHistory = new LinkedList<Beverage.BeverageData>();
		this.updatesHistory.add(new BeverageData());
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
		BeverageData bd = this.updatesHistory.getLast(); //take last updated beverage data
		return bd.getBoxPrice();
	}
	
	private void setBoxPrice(Integer boxPrice) throws BeverageException {
		BeverageData bd = this.updatesHistory.getLast(); //take last updated beverage data
		if(boxPrice == null || boxPrice < 0)
			throw new BeverageException();
		bd.setBoxPrice(boxPrice);
	}
	
	public int getCapsulesPerBox() {
		BeverageData bd = this.updatesHistory.getLast(); //take last updated beverage data
		return bd.getCapsulesPerBox();
	}
	
	private void setCapsulesPerBox(Integer capsulesPerBox) throws BeverageException {
		BeverageData bd = this.updatesHistory.getLast(); //take last updated beverage data
		if(capsulesPerBox == null || capsulesPerBox < 0)
			throw new BeverageException();
		bd.setCapsulesPerBox(capsulesPerBox);
	}
	
	public void updateBeverageData(String name, Integer boxPrice , Integer capsulePerBox) throws BeverageException {
		this.updatesHistory.addLast(new BeverageData());
			
		String n = this.getName();
		
		try {
			this.setName(name);
			this.setBoxPrice(boxPrice);
			this.setCapsulesPerBox(capsulePerBox);
		} catch (Exception e) {
			this.setName(n);
			this.updatesHistory.removeLast();
			throw e;
		}
	}
	
	public int getAvailableCapsules() {
		return this.updatesHistory.stream().collect(Collectors.summingInt(l -> l.getAvailableCapsules()));
	}
	
	public int getCapsulesPrice() {
		BeverageData bd = this.updatesHistory.getLast(); //take last updated beverage data
		return bd.getBoxPrice() / bd.getCapsulesPerBox();
	}
	
	public void increaseAvailableCapsules(int numberOfBoxes) throws BeverageException {
		// handle negative value and overflow
		BeverageData bd = this.updatesHistory.getLast(); //take last updated beverage data
		if (numberOfBoxes < 0 || bd.getAvailableCapsules() + numberOfBoxes * bd.capsulesPerBox < 0)
			throw new BeverageException();
		
		bd.setAvailableCapsules(bd.getAvailableCapsules() + (numberOfBoxes * bd.getCapsulesPerBox()));
	}
	
	public int decreaseAvailableCapsules(int numberOfCapsules) throws NotEnoughCapsules, BeverageException {
		BeverageData bd = this.updatesHistory.getFirst(); //take remaining beverage data
		Integer totalAvailable = this.getAvailableCapsules();
		
		if (totalAvailable < numberOfCapsules )
			throw new NotEnoughCapsules();
		
		// handle negative value and overflow
		if (numberOfCapsules < 0 || totalAvailable - numberOfCapsules < 0)
			throw new BeverageException();
		
		int amount = 0;
		while (numberOfCapsules > 0) {
			// take min between number of capsules requested and number of capsules available from the least recent update
			int min = Math.min(bd.getAvailableCapsules(), numberOfCapsules);
			// remove the min number of capsules
			amount += min * bd.getBoxPrice() / bd.getCapsulesPerBox();
			bd.setAvailableCapsules(bd.getAvailableCapsules() - min);
			// update the amount of capsules to sell
			numberOfCapsules -= min;
			// if still capsules to sell => finished not updated capsules => remove them
			if (numberOfCapsules > 0)
				this.updatesHistory.removeFirst();
			// use capsules from the new least recent update
			bd = this.updatesHistory.getFirst();
		}
		return amount;
	}

	@Override
	public String toString() {
		return this.name;
	}
	
	private class BeverageData implements Serializable {
		private static final long serialVersionUID = 1L;
		private Integer boxPrice,capsulesPerBox, availableCapsules;
		
		public BeverageData() {
			this.boxPrice = 0;
			this.capsulesPerBox = 0;
			this.availableCapsules = 0;
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
