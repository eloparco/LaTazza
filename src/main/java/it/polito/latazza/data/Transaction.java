package it.polito.latazza.data;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Transaction implements Serializable {
	private static final long serialVersionUID = -754087344543485509L;
	private Date date;

	public Transaction() {
		this.date = new Date();
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
