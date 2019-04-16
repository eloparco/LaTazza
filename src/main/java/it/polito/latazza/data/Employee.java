package it.polito.latazza.data;

import it.polito.latazza.exceptions.NotEnoughBalance;

public class Employee {
	private int id;
	private String name, surname;
	private int balance;
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
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
