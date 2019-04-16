package it.polito.latazza.data;

public class Recharge extends Transaction {
	private Employee employee;

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	@Override
	public String toString() {
		// TODO: check date format
		// TODO: check amount format
		return this.getDate().toGMTString() +  " RECHARGE " + this.employee + " " + this.getAmount() + "€cent";
	}
}
