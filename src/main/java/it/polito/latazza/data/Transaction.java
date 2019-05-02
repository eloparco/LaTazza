package it.polito.latazza.data;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Transaction {
	protected Date date;
	
	public Transaction() {

	}
	
	public Transaction(Date date) {
		this.date = date;
	}

	public Date getDate() {
		return date;
	}
	
	@Override
	public String toString() {
		// date format compliant with GUI (in the requirements)
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(date);
	}
}
