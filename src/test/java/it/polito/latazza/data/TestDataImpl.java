package it.polito.latazza.data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.security.auth.login.FailedLoginException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.polito.latazza.exceptions.BeverageException;
import it.polito.latazza.exceptions.EmployeeException;
import it.polito.latazza.exceptions.NotEnoughBalance;

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
			assertEquals("Coffee", beverageName);
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
	 * 	method: getBeverageCapsulesPerBox
	 */
	
	@Test
	public void testGetBeverageCapsulesPerBox1() {
		try {
			int beverageId = data.createBeverage("Coffee", 20, 500);
			assertTrue(beverageId >= 0);
			int capsulesPerBox = data.getBeverageCapsulesPerBox(beverageId);
			assertEquals(20, capsulesPerBox);
		} catch (BeverageException e) {
			fail();
		}
	}
	
	@Test
	public void testGetBeverageCapsulesPerBox2() {
		try {
			data.getBeverageCapsulesPerBox(10);
			fail();
		} catch (BeverageException e) {

		}
	}	
	
	@Test
	public void testGetBeverageCapsulesPerBox3() {
		try {
			data.getBeverageCapsulesPerBox(-10);
			fail();
		} catch (BeverageException e) {

		}
	}	
	
	/*
	 *  method: getBeverageCapsules
	 */
	
	@Test
	public void testGetBeverageCapsules1() {
		try {
			int beverageId = data.createBeverage("Coffee", 20, 500);
			assertTrue(beverageId >= 0);
			int capsules = data.getBeverageCapsules(beverageId);
			assertEquals(0, capsules);
		} catch (BeverageException e) {
			fail();
		}
	}
	
	@Test
	public void testGetBeverageCapsules2() {
		try {
			int beverageId = data.createBeverage("Coffee", 20, 500);
			assertTrue(beverageId >= 0);
			
			// create new employee and recharge his account
			// to increase laTazza balance, allowing to buy new boxes
			int id = data.createEmployee("Mario", "Rossi");
			data.rechargeAccount(id, 10000);

			// buy new boxes and check if capsule amount has been updated
			data.buyBoxes(beverageId, 2);
			int capsules = data.getBeverageCapsules(beverageId);
			assertEquals(40, capsules);
		} catch (BeverageException e) {
			fail();
		} catch (NotEnoughBalance e) {
			fail();
		} catch (EmployeeException e) {
			fail();
		}
	}
	
	@Test
	public void testGetBeverageCapsules3() {
		try {
			data.getBeverageCapsules(10);
			fail();
		} catch (BeverageException e) {

		}
	}	
	
	@Test
	public void testGetBeverageCapsules4() {
		try {
			data.getBeverageCapsules(-10);
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
			int id = data.createBeverage("Coffee", 20, 500);
			List<Integer> beverages = data.getBeveragesId();
			assertTrue(beverages.size() == 1);
			assertTrue(beverages.contains(id));
		} catch (BeverageException e) {
			fail();
		}
	}	
	
	@Test
	public void testGetBeveragesId2() {
		List<Integer> beverages = data.getBeveragesId();
		assertTrue(beverages.isEmpty());
	}	
	
	@Test
	// to obtain loop coverage 2+
	public void testGetBeveragesId3() {
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
	
	/*
	 * 	method: getBeverages
	 */
	
	@Test
	public void testGetBeverages1() {
		try {
			int id = data.createBeverage("Coffee", 20, 500);
			Map<Integer, String> beverages = data.getBeverages();
			assertTrue(beverages.size() == 1);
			assertNotNull(beverages.get(id));
			assertTrue(beverages.get(id).equals("Coffee"));
		} catch (BeverageException e) {
			fail();
		}
	}	
	
	@Test
	public void testGetBeverages2() {
		Map<Integer, String> beverages = data.getBeverages();
		assertTrue(beverages.isEmpty());
	}	
	
	@Test
	// to obtain loop coverage 2+
	public void testGetBeverages3() {
		try {
			int id1 = data.createBeverage("Coffee", 20, 500);
			int id2 = data.createBeverage("Tea", 25, 400);
			Map<Integer, String> beverages = data.getBeverages();
			assertTrue(beverages.size() == 2);
			assertNotNull(beverages.get(id1));
			assertNotNull(beverages.get(id2));
			assertTrue(beverages.get(id1).equals("Coffee"));
			assertTrue(beverages.get(id2).equals("Tea"));
		} catch (BeverageException e) {
			fail();
		}
	}
	
	/*
	 * 	method: getEmployeeBalance
	 */
	
	@Test
	public void testGetEmployeeBalance1() {
		try {
			int employeeId = data.createEmployee("Mario", "Rossi");
			int balance = data.getEmployeeBalance(employeeId);
			assertEquals(0, balance);
		} catch (EmployeeException e) {
			fail();
		}
	}	
	
	@Test
	public void testGetEmployeeBalance2() {
		try {
			int employeeId = data.createEmployee("Mario", "Rossi");
			data.rechargeAccount(employeeId, 500);
			int balance = data.getEmployeeBalance(employeeId);
			assertEquals(500, balance);
		} catch (EmployeeException e) {
			fail();
		}
	}	
	
	@Test
	public void testGetEmployeeBalance3() {
		try {
			data.getEmployeeBalance(-10);
			fail();
		} catch (EmployeeException e) {
			
		}
	}	
	
	@Test
	public void testGetEmployeeBalance4() {
		try {
			data.getEmployeeBalance(-10);
			fail();
		} catch (EmployeeException e) {
			
		}
	}	
	
}
