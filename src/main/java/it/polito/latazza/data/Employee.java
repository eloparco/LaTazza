package it.polito.latazza.data;

public class Employee {
	private int id, balance;
	private String name, surname;
	
	public Employee(int id, String name, String surname, int balance) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.balance = balance;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public int getBalance() {
		return balance;
	}
	
	public void increaseBalance(int amount) {
		this.balance += amount;
	}
	
	public void decreaseBalance(int amount) {
		this.balance -= amount;
	}
		
	@Override
	public int hashCode() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return this.name + " " + this.surname;
	}
}
