package it.polito.latazza.data;

public class Consumption extends Transaction {
	private static final long serialVersionUID = -5668236158408773554L;
	
	private Employee employee;
	private Beverage beverage;
	private boolean fromAccount;
	private int numberOfCapsules;
	
	public Consumption(Beverage beverage, int numberOfCapsules) {
		this.beverage = beverage;
		this.numberOfCapsules = numberOfCapsules;
	}
	
	public Consumption(Employee employee, Beverage beverage, boolean fromAccount, int numberOfCapsules) {
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
