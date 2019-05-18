package it.polito.latazza.data;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.polito.latazza.exceptions.NotEnoughBalance;
class TestLaTazzaAccount {
	LaTazzaAccount t;
	
	@BeforeEach
	void setUp() throws Exception {
		t = new LaTazzaAccount();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	/*
	 *		BLACK BOX
	 */
	
	@Test
	void tc1() {
		t.increaseBalance(10);
		assertEquals(10, t.getBalance());
	}
	
	@Test
	void tc2() {
		t.increaseBalance(2000000000);
		t.increaseBalance(2000000000);
		assertEquals(t.getBalance(), 2000000000);
	}
	
	@Test
	void tc3() {
		t.increaseBalance(-10);
		assertEquals(t.getBalance(), 0);
	}
	
	@Test
	void tc4() {
		t.increaseBalance(10);
		t.decreaseBalance(10);
		assertEquals(t.getBalance(), 0);
	}
	
	@Test
	void tc5()  {
		t.decreaseBalance(1);
		assertEquals(t.getBalance(), 0);
}
	
	@Test
	void tc6() {
		t.decreaseBalance(-10);
		assertEquals(t.getBalance(), 0);
	}

	/*
	 *		WHITE BOX
	 */
	
	@Test
	void tcGetBalance() {
		assertEquals(t.getBalance(), 0);
	}

}