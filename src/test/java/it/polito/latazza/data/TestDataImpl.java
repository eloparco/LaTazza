package it.polito.latazza.data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.List;

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
	
	/*
	 * 	method: getBeverageName 
	 */
	
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
	
	/*
	 * 	method: getBeveragesId 
	 */
	
	@Test
	public void testGetBeveragesId1() {
		try {
			int id1 = data.createBeverage("Coffee", 20, 500);
			int id2 = data.createBeverage("Tea", 25, 400);
			List<Integer> beverages = data.getBeveragesId();
			assertTrue(beverages.size() == 2);
			assertTrue(beverages.contains(id1));
			assertTrue(beverages.contains(id2));
		} catch (BeverageException e) {
			fail();
		}
	}	
	
	@Test
	public void testGetBeveragesId2() {
		List<Integer> beverages = data.getBeveragesId();
		assertTrue(beverages.isEmpty());
	}	
	
}
