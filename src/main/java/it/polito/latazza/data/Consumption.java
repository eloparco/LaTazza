package it.polito.latazza.data;

import java.util.Date;

public class Consumption extends Transaction {
	private Employee employee;
	private Beverage beverage;
	private boolean fromAccount;
	private int numberOfCapsules;
	
	public Consumption(Beverage beverage, int numberOfCapsules) {
		this.date = new Date();
		this.beverage = beverage;
		this.numberOfCapsules = numberOfCapsules;
	}
	
	public Consumption(Employee employee, Beverage beverage, boolean fromAccount, int numberOfCapsules) {
		this.date = new Date();
		this.employee = employee;
		this.beverage = beverage;
		this.fromAccount = fromAccount;
		this.numberOfCapsules = numberOfCapsules;
	}

	public Employee getEmployee() {
		return employee;
	}
	
	@Override
	public String toString() {
		String s = " VISITOR ";
		if (this.employee != null)
			s = (this.fromAccount ? " BALANCE " : " CASH ") + this.employee + " ";
		
		return super.toString() + s + this.beverage + " " + this.numberOfCapsules;
	}
}
