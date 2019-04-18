package it.polito.latazza.data;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Transaction {
	private Date date;
//	private int amount; // TODO: remove amount from class diagram -> amount different meanings in different contexts (Consumption, BoxPurchase..)
	
	public Transaction(Date date) {
		this.date = date;
	}

	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
//	public int getAmount() {
//		return amount;
//	}
//	
//	public void setAmount(int amount) {
//		this.amount = amount;
//	}	
	
	// date format compliant with GUI (in the requirements)
	public String getDateFormatted() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(date);
	}
}