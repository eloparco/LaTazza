package it.polito.latazza.data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.polito.latazza.exceptions.BeverageException;
import it.polito.latazza.exceptions.EmployeeException;
import it.polito.latazza.exceptions.NotEnoughBalance;
import it.polito.latazza.exceptions.NotEnoughCapsules;
import it.polito.latazza.exceptions.DateException;

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
	
	/*
	 * 	method: getEmployeeName
	 */
	
	@Test
	public void testGetEmployeeName1() {
		try {
			int id = data.createEmployee("Mario", "Rossi");
			assertEquals(data.getEmployeeName(id), "Mario");
		}
		catch (EmployeeException e) {
			fail();
		}
	}
	
	@Test
	public void testGetEmployeeName2() {
		try {
			data.getEmployeeName(10);
			fail();
		}
		catch (EmployeeException e) {
		}
	}
	
	/*
	 * 	method: getEmployeeSurname
	 */
	
	@Test
	public void testGetEmployeeSurname1() {
		try {
			int id = data.createEmployee("Mario", "Rossi");
			assertEquals(data.getEmployeeSurname(id), "Rossi");
		}
		catch (EmployeeException e) {
			fail();
		}
	}
	
	@Test
	public void testGetEmployeeSurname2() {
		try {
			data.getEmployeeSurname(10);
			fail();
		}
		catch (EmployeeException e) {
		}
	}
	
	/*
	 * 	method: getReport
	 */
	
	@Test
	public void testGetReport1() {
		try {
			Date d = new Date(); 
			assertEquals(data.getReport(d, new Date()).size(), 0);
			
			int id = data.createEmployee("Mario", "Rossi");
			data.rechargeAccount(id, 10000);
			List<String> l = data.getReport(d, new Date());
			assertEquals(l.size(), 1);
			String s = (String) l.get(0);
			assertEquals(s.substring(19, s.length()), " RECHARGE Mario Rossi 100.00€");  
			
			int b = data.createBeverage("tea", 10, 1);
			data.buyBoxes(b, 1);
			assertEquals(data.getReport(d, new Date()).size(), 2);
			s = data.getReport(d, new Date()).get(1);
			assertEquals(s.substring(19, s.length()), " BUY tea 1");
			
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	public void testGetReport2() {
		try {
			data.getReport( new Date(new Date().getTime() + (1000 * 60 * 60 * 48)), new Date());
			fail();
		} catch (DateException e) {
		}
	}
	
	@Test
	public void testGetReport3() {
		try {
			data.getReport( null, new Date());
			fail();
		} catch (DateException e) {
		}
	}
	
	/*
	 * 	method: getEmployeeReport
	 */
	
	@Test
	public void testGetEmployeeReport1() {
		try {
			Date d = new Date();
			int id = data.createEmployee("Mario" ,"Rossi");
			assertEquals(data.getEmployeeReport(id, d, new Date()).size(), 0);

			data.rechargeAccount(id, 10000);
			List<String> l = data.getEmployeeReport(id, d, new Date());
			assertEquals(l.size(), 1);
			String s = (String) l.get(0);
			assertEquals(s.substring(19, s.length()), " RECHARGE Mario Rossi 100.00€");  
			
			int b = data.createBeverage("tea", 10, 1);
			data.buyBoxes(b, 1);
			data.sellCapsules(id, b, 10, true);
			assertEquals(data.getEmployeeReport(id, d, new Date()).size(), 2);
			s = data.getEmployeeReport(id, d, new Date()).get(1);
			assertEquals(s.substring(19, s.length()), " BALANCE Mario Rossi tea 10");
			
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	public void testGetEmployeeReport2() {
		try {
			data.getEmployeeReport(data.createEmployee("Mario" ,"Rossi"), new Date(new Date().getTime() + (1000 * 60 * 60 * 48)), new Date());
			fail();
		} catch (DateException e) {
		}
		catch (EmployeeException e) {
			fail();
		}
	}
	
	@Test
	public void testGetEmployeeReport3() {
		try {
			data.getEmployeeReport(data.createEmployee("Mario" ,"Rossi"), new Date(), null);
			fail();
		} catch (DateException e) {
		}
		catch (EmployeeException e) {
			fail();
		}
	}
	
	@Test
	public void testGetEmployeeReport4() {
		try {
			data.getEmployeeReport(null, new Date(), new Date());
			fail();
		} catch (DateException e) {
			fail();
		}
		catch (EmployeeException e) {
		}
	}
	
	/*
	 * 	method: getEmployeesId
	 */
	
	@Test
	public void testGetEmployeesId() {
		try {
			assertEquals(data.getEmployeesId().size(), 0);
			int id1 = data.createEmployee("Mario" ,"Rossi");
			assertEquals(data.getEmployeesId().size(), 1);
			int id2 = data.createEmployee("Marco", "Bianchi");
			List<Integer> l = data.getEmployeesId();
			assertEquals(l.size(), 2);
			assertTrue(l.contains(id1));
			assertTrue(l.contains(id2));
		} catch (EmployeeException e){
			fail();
		}
	}
	
	/*
	 * 	method: getEmployees
	 */
	
	@Test
	public void testGetEmployees() {
		try {
			assertEquals(data.getEmployees().size(), 0);
			int id1 = data.createEmployee("Mario" ,"Rossi");
			assertEquals(data.getEmployees().size(), 1);
			int id2 = data.createEmployee("Marco", "Bianchi");
			Map<Integer, String> m = data.getEmployees();
			assertEquals(m.size(), 2);
			assertEquals(m.get(id1), "Mario Rossi");
			assertEquals(m.get(id2), "Marco Bianchi");
		} catch (EmployeeException e){
			fail();
		}
	}
	
	/*
	 * method: createBeverage
	 */
	
	@Test
	public void testCreateBeverage1() {
		int id = -1;
		try {
			id = data.createBeverage("Coffee", 20, 500);
		} catch (BeverageException e) {
			fail();
		}
		
		try {
			assertEquals("Coffee", data.getBeverageName(id));
			assertEquals(new Integer(20), data.getBeverageCapsulesPerBox(id));
			assertEquals(new Integer(500), data.getBeverageBoxPrice(id));
		} catch (BeverageException e) {
			fail();
		}
	}
	
	@Test
	public void testCreateBeverage2() {
		try {
			data.createBeverage("Coffee", 20, -500);
			fail();
		} catch (BeverageException e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testCreateBeverage3() {
		try {
			data.createBeverage("Coffee", -20, 500);
			fail();
		} catch (BeverageException e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testCreateBeverage4() {
		try {
			data.createBeverage("Coffee", 20, 500);
			data.createBeverage("Coffee", 25, 600);
			fail();
		} catch (BeverageException e) {
			assertTrue(true);
		}
	}
	
	/*
	 * method: getBeverageBoxPrice
	 */
	
	public void testGetBeverageBoxPrice1() {
		try {
			int id = data.createBeverage("Coffee", 20, 500);
			assertEquals(new Integer(500), data.getBeverageBoxPrice(id));
		} catch (BeverageException e) {
			fail();
		}
	}
	
	public void testGetBeverageBoxPrice2() {
		try {
			data.getBeverageBoxPrice(1);
			fail();
		} catch (BeverageException e) {
			assertTrue(true);
		}
	}
	
	/*
	 * method: createEmployee
	 */
	
	@Test
	public void testCreateEmployee1() {
		try {
			int id = data.createEmployee("Mario", "Rossi");
			assertEquals("Mario", data.getEmployeeName(id));
			assertEquals("Rossi", data.getEmployeeSurname(id));
		} catch (EmployeeException e) {
			fail();
		}
	}
	
	@Test
	public void testCreateEmployee2() {
		try {
			data.createEmployee("Mario", "Rossi");
			data.createEmployee("Mario", "Rossi");
			fail();
		} catch (EmployeeException e) {
			assertTrue(true);
		}
	}
	
	
	/*
	 * method: buyBoxes
	 */
	
	@Test
	public void testBuyBoxes1() {
		int idB=-1;
		try {
			int idE = data.createEmployee("Mario", "Rossi");
			idB = data.createBeverage("Coffee", 20, 500);
			data.rechargeAccount(idE, 500);
		} catch (Exception e) {
			fail("Exception while creating the environment");
		}
		
		try {
			data.buyBoxes(idB, 1);
			assertEquals(new Integer(20), data.getBeverageCapsules(idB));
		} catch (BeverageException | NotEnoughBalance e) {
			fail();
		}
	}
	
	@Test
	public void testBuyBoxes2() {
		int idB=-1;
		try {
			idB = data.createBeverage("Coffee", 20, 500);
		} catch (Exception e) {
			fail("Exception while creating the environment");
		}
		
		try {
			data.buyBoxes(idB, 1);
			fail();
		} catch (NotEnoughBalance e) {
			assertTrue(true);
		} catch (BeverageException e) {
			fail();
		}
	}
	
	@Test
	public void testBuyBoxes3() {
		int idB=-1;
		try {
			int idE = data.createEmployee("Mario", "Rossi");
			idB = data.createBeverage("Coffee", 20, 500);
			data.rechargeAccount(idE, 500);
		} catch (Exception e) {
			fail("Exception while creating the environment");
		}
		
		try {
			data.buyBoxes(idB, -1);
			fail();
		} catch (NotEnoughBalance e) {
			fail();
		} catch (BeverageException e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testBuyBoxes4() {
		try {
			int idE = data.createEmployee("Mario", "Rossi");
			data.rechargeAccount(idE, 500);
		} catch (Exception e) {
			fail("Exception while creating the environment");
		}
		
		try {
			data.buyBoxes(1, 1);
			fail();
		} catch (NotEnoughBalance e) {
			fail();
		} catch (BeverageException e) {
			assertTrue(true);
		}
	}
	
	/*
	 * method: getBalance
	 */
	
	@Test
	public void testGetBalance1() {
		try {
			int idE = data.createEmployee("Mario", "Rossi");
			int idB = data.createBeverage("Coffee", 20, 500);
			data.rechargeAccount(idE, 500);
			assertEquals(new Integer(500), data.getBalance());
			data.buyBoxes(idB, 1);
			assertEquals(new Integer(0), data.getBalance());
			data.sellCapsulesToVisitor(idB, 4);
			assertEquals(new Integer(100), data.getBalance());
		} catch (BeverageException | EmployeeException | NotEnoughBalance | NotEnoughCapsules e) {
			fail();
		}
	}
	
	@Test
	public void testGetBalance2() {
		assertEquals(new Integer(0), data.getBalance());
	}
}
