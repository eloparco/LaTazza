package it.polito.latazza.data;

import java.util.Locale;

import it.polito.latazza.exceptions.EmployeeException;

public class Recharge extends Transaction {
	private static final long serialVersionUID = -3843956786039203483L;
	
	private Employee employee;
	private int amount;

	public Recharge(Employee employee, int amount) throws EmployeeException {
		if (employee == null || amount < 0)
			throw new EmployeeException();
		this.employee = employee;
		this.amount = amount;
	}

	public Employee getEmployee() {
		return employee;
	}
	
	public int getAmount() {
		return amount;
	}
	
	@Override
	public String toString() {
		Float amount = (float) this.amount / 100;
		Locale.setDefault(Locale.US);
		return super.toString() +  " RECHARGE " + this.employee + " " + String.format("%.2f", amount) + " €";
	}
}
