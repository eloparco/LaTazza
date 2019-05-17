package it.polito.latazza.data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import javax.security.auth.login.FailedLoginException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.polito.latazza.exceptions.BeverageException;

public class TestDataImpl {

	DataInterface data;
	
	@BeforeEach
	void setUp() throws Exception {
		data = new DataImpl();
		data.reset();
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Test
	public void testGetBeverageName1() {
		try {
			int beverageId = data.createBeverage("Coffee", 20, 500);
			assertTrue(beverageId >= 0);
			String beverageName = data.getBeverageName(beverageId);
			assertEquals(beverageName, "Coffee");
		} catch (BeverageException e) {
			fail();
		}
	}
	
	@Test
	public void testGetBeverageName2() {
		try {
			data.getBeverageName(10);
			fail();
		} catch (BeverageException e) {

		}
	}	
	
	@Test
	public void testGetBeverageName3() {
		try {
			data.getBeverageName(-10);
			fail();
		} catch (BeverageException e) {

		}
	}	
}
