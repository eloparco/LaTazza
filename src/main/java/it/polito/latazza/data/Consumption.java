package it.polito.latazza.data;

import java.util.Date;

public class Consumption extends Transaction {
	private Employee employee;
	private Beverage beverage;
	private boolean fromAccount;
	private int numberOfCapsules;
	
	public Consumption(Employee employee, Beverage beverage, boolean fromAccount, int numberOfCapsules, Date date) {
		super(date);
		this.employee = employee;
		this.beverage = beverage;
		this.fromAccount = fromAccount;
		this.numberOfCapsules = numberOfCapsules;
	}

	public Employee getEmployee() {
		return employee;
	}
	
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Beverage getBeverage() {
		return beverage;
	}

	public void setBeverage(Beverage beverage) {
		this.beverage = beverage;
	}

	public boolean isFromAccount() {
		return fromAccount;
	}

	public void setFromAccount(boolean fromAccount) {
		this.fromAccount = fromAccount;
	}

	public int getNumberOfCapsules() {
		return numberOfCapsules;
	}

	public void setNumberOfCapsules(int numberOfCapsules) {
		this.numberOfCapsules = numberOfCapsules;
	}

	@Override
	public String toString() {
		String s = " VISITOR ";
		if (this.employee != null)
			s = (this.fromAccount ? " BALANCE " : " CASH ") + this.employee + " ";
		
		return this.getDateFormatted() + s + this.beverage + " " + this.numberOfCapsules;
//		return this.getDateFormatted() +  ( (this.fromAccount) ? " BALANCE " : " CASH " ) + ( (this.employee == null) ? "VISITOR" : this.employee ) + " " + this.beverage + " " + this.numberOfCapsules;
	}
}
