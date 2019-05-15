package it.polito.latazza.data;

import java.io.Serializable;

public class Employee implements Serializable {
	private static final long serialVersionUID = -8150828679021620453L;
	
	private int id, balance;
	private String name, surname;
	
	public Employee(int id, String name, String surname) {
		this.id = id;
		this.name = name;
		this.surname = surname;
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
	public String toString() {
		return this.name + " " + this.surname;
	}
}
