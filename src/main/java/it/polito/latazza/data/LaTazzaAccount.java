package it.polito.latazza.data;
import it.polito.latazza.exceptions.NotEnoughBalance;

public class LaTazzaAccount {
	private int balance = 0;

	public int getBalance() {
		return balance;
	}

	public void increaseBalance(int amount) {
		this.balance += amount;
	}
	
	public void decreaseBalance(int amount) throws NotEnoughBalance {
		if (this.balance < amount)
			throw new NotEnoughBalance();
		this.balance -= amount;
	}
}
