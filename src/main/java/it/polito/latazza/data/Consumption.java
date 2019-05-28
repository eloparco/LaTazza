package it.polito.latazza.data;

import it.polito.latazza.exceptions.BeverageException;
import it.polito.latazza.exceptions.EmployeeException;
import it.polito.latazza.exceptions.NotEnoughBalance;
import it.polito.latazza.exceptions.NotEnoughCapsules;

public class Consumption extends Transaction {
	private static final long serialVersionUID = -5668236158408773554L;
	
	private Employee employee;
	private Beverage beverage;
	private boolean fromAccount;
	private int numberOfCapsules;
	
	public Consumption(Beverage beverage, Integer numberOfCapsules) throws BeverageException, NotEnoughCapsules {
		if(beverage == null)
			throw new BeverageException();
		if(numberOfCapsules == null || numberOfCapsules < 0)
			throw new NotEnoughCapsules();
		this.beverage = beverage;
		this.numberOfCapsules = numberOfCapsules;
	}
	
	public Consumption(Employee employee, Beverage beverage, boolean fromAccount, Integer numberOfCapsules) throws EmployeeException, BeverageException, NotEnoughCapsules {
		if(employee == null)
			throw new EmployeeException();
		if(beverage == null)
			throw new BeverageException();
		if(numberOfCapsules == null || numberOfCapsules < 0)
			throw new NotEnoughCapsules();
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
