package it.polito.latazza.data;

import java.util.Date;

public abstract class Transaction {
	private Date date;
	private int amount;
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}	
}
