package it.polito.latazza.data;

import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.polito.latazza.exceptions.EmployeeException;

class TestRecharge {
	
	private Date d;
	private Recharge r;
	private Employee e;
	private int rechargeAmount;
	
	@BeforeEach
	void setUp() throws Exception {
		rechargeAmount = 1000;
		d = new Date();
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
	
	/*
	 *		WHITE BOX
	 */
	
	@Test
	void tcToStringFormat() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String expected = format.format(d) + " RECHARGE Mario Rossi 10.00€";
		assertEquals(expected, r.toString());
	}

}
