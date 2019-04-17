package it.polito.latazza.data;

import java.util.Date;

public class Recharge extends Transaction {
	private Employee employee;
	private int amount;

	public Recharge(Employee employee, int amount, Date date) {
		super(date);
		this.employee = employee;
		this.setAmount(amount);
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	@Override
	public String toString() {
		return this.getDateFormatted() +  " RECHARGE " + this.employee + " " + (float) this.getAmount() / 100 + "€";
	}
}
