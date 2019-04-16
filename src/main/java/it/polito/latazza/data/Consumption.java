package it.polito.latazza.data;

public class Consumption extends Transaction {
	private Employee employee;
	private Beverage beverage;
	private boolean fromAccount;
	private int numberOfCapsules;
		
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
		// TODO: check date format
		// TODO: check amount format
		return this.getDate().toGMTString() +  ( (this.fromAccount) ? " BALANCE " : " CASH " ) + ( (this.employee == null) ? "VISITOR" : this.employee ) + " " + this.beverage + " " + this.numberOfCapsules;
	}
}
