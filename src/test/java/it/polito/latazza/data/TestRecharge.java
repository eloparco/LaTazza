package it.polito.latazza.data;

import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;
import java.util.Locale;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.polito.latazza.exceptions.EmployeeException;

/**
 * Integration tests for class Recharge + Employee
 * 
 * Since the used technique for integration is incremental bottom up
 * (i.e. unit tests have been done previously on units this class depends on),
 * no stubs are used.
 * 
 * @author s261072
 *
 */
class TestRecharge {
	
	private Recharge r;
	private Employee e;
	private int rechargeAmount;
	
	@BeforeEach
	void setUp() throws Exception {
		rechargeAmount = 1000;
		e = new Employee(0, "Mario", "Rossi");
		r = new Recharge(e, rechargeAmount);
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	/*
	 *		BLACK BOX
	 */

	@Test
	void tc1() {
		assertSame(e, r.getEmployee());
		assertEquals(rechargeAmount, r.getAmount());
	}
	
	@Test
	void tc2() {
		try {
			new Recharge(null, rechargeAmount);
			fail();
		} catch (EmployeeException e) {

		}
	}
	
	@Test
	void tc3() {
		Recharge r;
		try {
			r = new Recharge(e, -1000);
			assertSame(e, r.getEmployee());
			assertEquals(-1000, r.getAmount());
		} catch (EmployeeException e1) {
			fail();
		}
	}
	
	@Test
	void tc4() {
		try {
			new Recharge(null, -1000);
			fail();
		} catch (EmployeeException e) {

		}
	}
	
	// boundary: amount = 0
	@Test
	void tc5() {
		Recharge r;
		try {
			r = new Recharge(e, 0);
			assertSame(e, r.getEmployee());
			assertEquals(0, r.getAmount());
		} catch (EmployeeException e1) {
			fail();
		}
	}
	
	// boundary: amount = MAXINT
	@Test
	void tc6() {
		Recharge r;
		try {
			r = new Recharge(e, Integer.MAX_VALUE);
			assertSame(e, r.getEmployee());
			assertEquals(Integer.MAX_VALUE, r.getAmount());
		} catch (EmployeeException e1) {
			fail();
		}
	}
	
	// boundary: amount = MININT
	@Test
	void tc7() {
		try {
			new Recharge(null, Integer.MIN_VALUE);
			fail();
		} catch (EmployeeException e) {

		}
	}
	
	/*
	 *		WHITE BOX
	 */
	
	@Test
	void tcToStringFormat() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String expected = format.format(r.getDate()) + " RECHARGE Mario Rossi " + String.format("%.2f", 10.0) + "€";
		assertEquals(expected, r.toString());
	}

}