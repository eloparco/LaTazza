package it.polito.latazza.data;

public class Recharge extends Transaction {
	private static final long serialVersionUID = -3843956786039203483L;
	
	private Employee employee;
	private int amount;

	public Recharge(Employee employee, int amount) {
		this.employee = employee;
		this.amount = amount;
	}

	public Employee getEmployee() {
		return employee;
	}
	
	@Override
	public String toString() {
		return super.toString() +  " RECHARGE " + this.employee + " " + (float) this.amount / 100 + "€";
	}
}
