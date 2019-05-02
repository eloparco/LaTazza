package it.polito.latazza.data;

import java.util.Date;

public class Recharge extends Transaction {
	private Employee employee;
	private int amount;

	public Recharge(Employee employee, int amount) {
		this.date = new Date();
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
