package it.polito.latazza.data;

import java.io.Serializable;

import it.polito.latazza.exceptions.EmployeeException;

public class Employee implements Serializable {
	private static final long serialVersionUID = -8150828679021620453L;
	
	private int id, balance;
	private String name, surname;
	
	public Employee(int id, String name, String surname) throws EmployeeException {
		this.id = id;
		setName(name);
		setSurname(surname);
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) throws EmployeeException {
		if (name == null || name.length() < 1)
			throw new EmployeeException();
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) throws EmployeeException {
		if (surname == null || surname.length() < 1)
			throw new EmployeeException();
		this.surname = surname;
	}
	
	public int getBalance() {
		return balance;
	}
	
	public void increaseBalance(int amount) throws EmployeeException {
		if (amount < 0 || (balance > 0 && balance+amount < 0))	// negative or overflow
			throw new EmployeeException();
		this.balance += amount;
	}

	public void decreaseBalance(int amount) throws EmployeeException {
		if (amount < 0 || (balance < 0 && balance-amount > 0))	// negative or overflow
			throw new EmployeeException();
		this.balance -= amount;
	}
	
	@Override
	public String toString() {
		return this.name + " " + this.surname;
	}
}
