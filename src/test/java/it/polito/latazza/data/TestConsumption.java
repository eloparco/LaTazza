package it.polito.latazza.data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Locale;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.polito.latazza.exceptions.BeverageException;
import it.polito.latazza.exceptions.EmployeeException;

/**
 * Integration tests for class Consumption + Beverage and Employee
 * 
 * Since the used technique for integration is incremental bottom up
 * (i.e. unit tests have been done previously on units this class depends on),
 * no stubs are used.
 * 
 * @author s265485
 *
 */
class TestConsumption {
	Beverage b;
	Employee e;
	Consumption c;
	String s;
	
	@BeforeEach
	void setUp() throws Exception {
		b = new Beverage(1, "tea", 10, 10);
		e = new Employee(1, "John", "Doe");
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	/*
	 *		BLACK BOX
	 */
	
	@Test
	void tc1() {
		try {
			new Consumption(e, b, true, 10);
			new Consumption(e, b, false, 10);
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	void tc2() {
		try {
			new Consumption(e, b, true, -10);
			fail();
		} catch (BeverageException er) {
			try {
				new Consumption(e, b, false, -10);
				fail();
			} catch (BeverageException e2) {
			} catch (Exception e) {
				fail();
			}
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	void tc3() {
		try {
			new Consumption(e, null, true, 10);
			fail();
		} catch (BeverageException er) {
			try {
				new Consumption(e, null, false, 10);
				fail();
			} catch (BeverageException e2) {
			} catch (Exception e) {
				fail();
			}
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	void tc4() {
		try {
			new Consumption(null, b, true, 10);
			fail();
		} catch (EmployeeException er) {
			try {
				new Consumption(null, b, false, 10);
				fail();
			} catch (EmployeeException e2) {
			} catch (Exception e) {
				fail();
			}
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	void tc5() {
		try {
			new Consumption(b, 10);
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	void tc6() {
		try {
			new Consumption(b, -10);
			fail();
		} catch (BeverageException er) {
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	void tc7() {
		try {
			new Consumption(null, 10);
			fail();
		} catch (BeverageException er) {
		} catch (Exception e) {
			fail();
		}
	}

	/*
	 *		WHITE BOX
	 */
	
	@Test
	void tcGetEmployee() {
		try {
			c = new Consumption(b, 10);
			if(c.getEmployee() != null)
				fail();
			c = new Consumption(e, b, true, 10);
			if(c.getEmployee().getId() != e.getId())
				fail();
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	void tcToStringFormat() {
		try {
			c = new Consumption(b, 10);
			s = c.toString();
			assertEquals(s.substring(19, s.length())," VISITOR tea 10");
			c = new Consumption(e, b, true, 10);
			s = c.toString();
			assertEquals(s.substring(19, s.length())," BALANCE John Doe tea 10");
			c = new Consumption(e, b, false, 10);
			s = c.toString();
			assertEquals(s.substring(19, s.length())," CASH John Doe tea 10");
		} catch (Exception e) {
			fail();
		}
	}

}