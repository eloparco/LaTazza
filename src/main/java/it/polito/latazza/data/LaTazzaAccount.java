package it.polito.latazza.data;

import java.io.Serializable;
import it.polito.latazza.exceptions.NotEnoughBalance;

public class LaTazzaAccount implements Serializable {
	private static final long serialVersionUID = -8680112381552867658L;
	private int balance = 0;

	public int getBalance() {
		return balance;
	}

	public void increaseBalance(int amount) {
		if (amount > 0 && this.balance + amount >= 0)   //no exception to call
			this.balance += amount;
	}
	
	public void decreaseBalance(int amount) throws NotEnoughBalance {
		if (amount <= 0) return;
		if (amount > balance)
			throw new NotEnoughBalance();
			this.balance -= amount;
	}
}
