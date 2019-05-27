package it.polito.latazza.data;

import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;
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
	 *****************
	 *** Black box ***
	 *****************
	 */
	
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
			assertEquals("Rossi", data.getEmployeeSurname(id));
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
			assertEquals(0, data.getReport(d, new Date()).size());
			
			int id = data.createEmployee("Mario", "Rossi");
			data.rechargeAccount(id, 10000);
			List<String> l = data.getReport(d, new Date());
			assertEquals(1, l.size());
			String s = (String) l.get(0);
			assertEquals(" RECHARGE Mario Rossi " + String.format("%.2f", 100.0) + "€", s.substring(19, s.length()));  

			int b = data.createBeverage("tea", 10, 1);
			data.buyBoxes(b, 1);
			assertEquals(2, data.getReport(d, new Date()).size());
			s = data.getReport(d, new Date()).get(1);
			assertEquals(" BUY tea 1", s.substring(19, s.length()));
			
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
			assertEquals(0, data.getEmployeeReport(id, d, new Date()).size());

			data.rechargeAccount(id, 10000);
			List<String> l = data.getEmployeeReport(id, d, new Date());
			assertEquals(1, l.size());
			String s = (String) l.get(0);
			assertEquals(" RECHARGE Mario Rossi " + String.format("%.2f", 100.0) + "€", s.substring(19, s.length()));  

			int b = data.createBeverage("tea", 10, 1);
			data.buyBoxes(b, 1);
			data.sellCapsules(id, b, 10, true);
			assertEquals(data.getEmployeeReport(id, d, new Date()).size(), 2);
			s = data.getEmployeeReport(id, d, new Date()).get(1);
			assertEquals(" BALANCE Mario Rossi tea 10", s.substring(19, s.length()));
			
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
			assertEquals(0, data.getEmployeesId().size());
			int id1 = data.createEmployee("Mario" ,"Rossi");
			assertEquals(1, data.getEmployeesId().size());
			int id2 = data.createEmployee("Marco", "Bianchi");
			List<Integer> l = data.getEmployeesId();
			assertEquals(2, l.size());
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
			assertEquals(0, data.getEmployees().size());
			int id1 = data.createEmployee("Mario" ,"Rossi");
			assertEquals(data.getEmployees().size(), 1);
			int id2 = data.createEmployee("Marco", "Bianchi");
			Map<Integer, String> m = data.getEmployees();
			assertEquals(2, m.size());
			assertEquals("Mario Rossi", m.get(id1));
			assertEquals("Marco Bianchi", m.get(id2));
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
	
	@Test
	public void testGetBeverageBoxPrice1() {
		try {
			int id = data.createBeverage("Coffee", 20, 500);
			assertEquals(new Integer(500), data.getBeverageBoxPrice(id));
		} catch (BeverageException e) {
			fail();
		}
	}
	
	@Test
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
	
	/*
	 * 	method: rechargeAccount
	 */
	
	@Test
	public void testRechargeAccount1() {
		try {
			assertEquals(data.getEmployees().size(), 0);
			int id1 = data.createEmployee("Mario" ,"Rossi");
			assertEquals(data.getEmployeeBalance(id1), new Integer(0));
			data.rechargeAccount(id1, 10);
			assertEquals(data.getEmployeeBalance(id1), new Integer(10));
		} catch (Exception e){
			fail();
		}
	}
	
	/*
	 * 	method: rechargeAccount
	 */
	
	@Test
	public void testRechargeAccount2() {
		try {
			data.rechargeAccount(-1, 10);
			fail();
		} catch (Exception e){
		}
	}
	
	/*
	 * 	method: rechargeAccount
	 */
	
	@Test
	public void testRechargeAccount3() {
		try {
			assertEquals(data.getEmployees().size(), 0);
			int id1 = data.createEmployee("Mario" ,"Rossi");
			assertEquals(data.getEmployeeBalance(id1), new Integer(0));
			data.rechargeAccount(id1, -10);
			fail();
		} catch (EmployeeException e) {
		} catch (Exception e){
			fail();
		}
	}
	
	@Test
	public void tcReset() {
		try {
			int id1 = data.createEmployee("Mario", "Rossi");
			data.createEmployee("Luca", "Sassi");
			int b1 = data.createBeverage("tea", 50, 1000);
			data.rechargeAccount(id1, 2000);
			data.buyBoxes(b1, 2);
			data.sellCapsules(id1, b1, 50, true);
			assertEquals(data.getEmployeeBalance(id1), new Integer(1000));
			data.reset();
			assertEquals(data.getEmployees().size(), 0);
			assertEquals(data.getBeverages().size(), 0);
			assertEquals(data.getBalance(), new Integer(0));
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	/*
	 * 	method: sellCapsules
	 */
	
	@Test
	public void testSellCapsules1() {
		try {
			assertEquals(data.getEmployees().size(), 0);
			int id1 = data.createEmployee("Mario" ,"Rossi");
			assertEquals(data.getEmployeeBalance(id1), new Integer(0));
			data.rechargeAccount(id1, 2000);
			assertEquals(data.getEmployeeBalance(id1), new Integer(2000));
			int b1 = data.createBeverage("tea", 50, 1000);
			assertEquals(data.getBeverages().size(), 1);
			data.buyBoxes(b1, 2);
			assertEquals(data.getBeverageCapsules(b1), new Integer(100));
			data.sellCapsules(id1, b1, 50, true);
			assertEquals(data.getEmployeeBalance(id1),new Integer(1000));
			assertEquals(data.getBeverageCapsules(b1), new Integer(50));
			assertEquals(data.getBalance(), new Integer(0));
			data.sellCapsules(id1, b1, 50, false);
			assertEquals(data.getEmployeeBalance(id1),new Integer(1000));
			assertEquals(data.getBeverageCapsules(b1), new Integer(0));
			assertEquals(data.getBalance(), new Integer(1000));
		} catch (Exception e){
			fail();
		}
	}
	
	/*
	 * 	method: sellCapsules
	 */
	
	@Test
	public void testSellCapsules2() {
		int id1 = 0, b1 = 0; 
		try {
			assertEquals(data.getEmployees().size(), 0);
			id1 = data.createEmployee("Mario" ,"Rossi");
			assertEquals(data.getEmployeeBalance(id1), new Integer(0));
			data.rechargeAccount(id1, 2000);
			assertEquals(data.getEmployeeBalance(id1), new Integer(2000));
			b1 = data.createBeverage("tea", 50, 1000);
			assertEquals(data.getBeverages().size(), 1);
			data.buyBoxes(b1, 2);
			assertEquals(data.getBeverageCapsules(b1), new Integer(100));
			data.sellCapsules(id1+1, b1, 50, true);
			fail();
		} catch (EmployeeException e){
			try {
				data.sellCapsules(id1+1, b1, 50, false);
				fail();
			} catch (EmployeeException e1) {
			} catch (Exception e2) {
				fail();
			}
		} catch (Exception e) {
			fail();
		}
	}
	
	/*
	 * 	method: sellCapsules
	 */
	
	@Test
	public void testSellCapsules3() {
		int id1 = 0, b1 = 0; 
		try {
			assertEquals(data.getEmployees().size(), 0);
			id1 = data.createEmployee("Mario" ,"Rossi");
			assertEquals(data.getEmployeeBalance(id1), new Integer(0));
			data.rechargeAccount(id1, 2000);
			assertEquals(data.getEmployeeBalance(id1), new Integer(2000));
			b1 = data.createBeverage("tea", 50, 1000);
			assertEquals(data.getBeverages().size(), 1);
			data.buyBoxes(b1, 2);
			assertEquals(data.getBeverageCapsules(b1), new Integer(100));
			data.sellCapsules(-1, b1, 50, true);
			fail();
		} catch (EmployeeException e){
			try {
				data.sellCapsules(-1, b1, 50, false);
				fail();
			} catch (EmployeeException e1) {
			} catch (Exception e2) {
				fail();
			}
		} catch (Exception e) {
			fail();
		}
	}
	
	/*
	 * 	method: sellCapsules
	 */
	
	@Test
	public void testSellCapsules4() {
		int id1 = 0, b1 = 0; 
		try {
			assertEquals(data.getEmployees().size(), 0);
			id1 = data.createEmployee("Mario" ,"Rossi");
			assertEquals(data.getEmployeeBalance(id1), new Integer(0));
			data.rechargeAccount(id1, 2000);
			assertEquals(data.getEmployeeBalance(id1), new Integer(2000));
			b1 = data.createBeverage("tea", 50, 1000);
			assertEquals(data.getBeverages().size(), 1);
			data.buyBoxes(b1, 2);
			assertEquals(data.getBeverageCapsules(b1), new Integer(100));
			data.sellCapsules(id1, b1+1, 50, true);
			fail();
		} catch (BeverageException e){
			try {
				data.sellCapsules(id1, b1+1, 50, false);
				fail();
			} catch (BeverageException e1) {
				
			} catch (Exception e2) {
				fail();
			}
		} catch (Exception e) {
			fail();
		}
	}
	
	/*
	 * 	method: sellCapsules
	 */
	
	@Test
	public void testSellCapsules5() {
		int id1 = 0, b1 = 0; 
		try {
			assertEquals(data.getEmployees().size(), 0);
			id1 = data.createEmployee("Mario" ,"Rossi");
			assertEquals(data.getEmployeeBalance(id1), new Integer(0));
			data.rechargeAccount(id1, 2000);
			assertEquals(data.getEmployeeBalance(id1), new Integer(2000));
			b1 = data.createBeverage("tea", 50, 1000);
			assertEquals(data.getBeverages().size(), 1);
			data.buyBoxes(b1, 2);
			assertEquals(data.getBeverageCapsules(b1), new Integer(100));
			data.sellCapsules(id1, -1, 50, true);
			fail();
		} catch (BeverageException e){
			try {
				data.sellCapsules(id1, -1, 50, false);
				fail();
			} catch (BeverageException e1) {
				
			} catch (Exception e2) {
				fail();
			}
		} catch (Exception e) {
			fail();
		}
	}
	
	/*
	 * 	method: sellCapsules
	 */
	
	@Test
	public void testSellCapsules6() {
		int id1 = 0, b1 = 0; 
		try {
			assertEquals(data.getEmployees().size(), 0);
			id1 = data.createEmployee("Mario" ,"Rossi");
			assertEquals(data.getEmployeeBalance(id1), new Integer(0));
			data.rechargeAccount(id1, 2000);
			assertEquals(data.getEmployeeBalance(id1), new Integer(2000));
			b1 = data.createBeverage("tea", 50, 1000);
			assertEquals(data.getBeverages().size(), 1);
			data.buyBoxes(b1, 2);
			assertEquals(data.getBeverageCapsules(b1), new Integer(100));
			data.sellCapsules(id1, b1, 115, true);
			fail();
		} catch (NotEnoughCapsules e){
			try {
				data.sellCapsules(id1, b1, 115, false);
				fail();
			} catch (NotEnoughCapsules e1) {
				
			} catch (Exception e2) {
				fail();
			}
		} catch (Exception e) {
			fail();
		}
	}
	
	/*
	 * 	method: sellCapsules
	 */
	
	@Test
	public void testSellCapsules7() {
		int id1 = 0, b1 = 0; 
		try {
			assertEquals(data.getEmployees().size(), 0);
			id1 = data.createEmployee("Mario" ,"Rossi");
			assertEquals(data.getEmployeeBalance(id1), new Integer(0));
			data.rechargeAccount(id1, 2000);
			assertEquals(data.getEmployeeBalance(id1), new Integer(2000));
			b1 = data.createBeverage("tea", 50, 1000);
			assertEquals(data.getBeverages().size(), 1);
			data.buyBoxes(b1, 2);
			assertEquals(data.getBeverageCapsules(b1), new Integer(100));
			data.sellCapsules(id1, b1, -5, true);
			fail();
		} catch (BeverageException e){
			try {
				data.sellCapsules(id1, b1, -5, false);
				fail();
			} catch (BeverageException e1) {
				
			} catch (Exception e2) {
				fail();
			}
		} catch (Exception e) {
			fail();
		}
	}
	
	/*
	 * 	method: sellCapsulesToVisitor
	 */
	
	@Test
	public void testSellCapsulesToVisitor1() {
		try {
			assertEquals(data.getEmployees().size(), 0);
			int id1 = data.createEmployee("Mario" ,"Rossi");
			assertEquals(data.getEmployeeBalance(id1), new Integer(0));
			data.rechargeAccount(id1, 2000);
			assertEquals(data.getEmployeeBalance(id1), new Integer(2000));
			int b1 = data.createBeverage("tea", 50, 1000);
			assertEquals(data.getBeverages().size(), 1);
			data.buyBoxes(b1, 2);
			assertEquals(data.getBeverageCapsules(b1), new Integer(100));
			data.sellCapsulesToVisitor(b1, 50);
			assertEquals(data.getBalance(), new Integer(1000));
			assertEquals(data.getBeverageCapsules(b1), new Integer(50));
		} catch (Exception e){
			fail();
		}
	}
	
	/*
	 * 	method: sellCapsulesToVisitor
	 */
	
	@Test
	public void testSellCapsulesToVisitor2() {
		try {
			assertEquals(data.getEmployees().size(), 0);
			int id1 = data.createEmployee("Mario" ,"Rossi");
			assertEquals(data.getEmployeeBalance(id1), new Integer(0));
			data.rechargeAccount(id1, 2000);
			assertEquals(data.getEmployeeBalance(id1), new Integer(2000));
			int b1 = data.createBeverage("tea", 50, 1000);
			assertEquals(data.getBeverages().size(), 1);
			data.buyBoxes(b1, 2);
			assertEquals(data.getBeverageCapsules(b1), new Integer(100));
			data.sellCapsulesToVisitor(b1+1, 50);
			fail();
		} catch (BeverageException e){
		} catch (Exception e) {
			fail();
		}
	}
	
	/*
	 * 	method: sellCapsulesToVisitor
	 */
	
	@Test
	public void testSellCapsulesToVisitor3() {
		try {
			assertEquals(data.getEmployees().size(), 0);
			int id1 = data.createEmployee("Mario" ,"Rossi");
			assertEquals(data.getEmployeeBalance(id1), new Integer(0));
			data.rechargeAccount(id1, 2000);
			assertEquals(data.getEmployeeBalance(id1), new Integer(2000));
			int b1 = data.createBeverage("tea", 50, 1000);
			assertEquals(data.getBeverages().size(), 1);
			data.buyBoxes(b1, 2);
			assertEquals(data.getBeverageCapsules(b1), new Integer(100));
			data.sellCapsulesToVisitor(-1, 50);
			fail();
		} catch (BeverageException e){
		} catch (Exception e) {
			fail();
		}
	}
	
	/*
	 * 	method: sellCapsulesToVisitor
	 */
	
	@Test
	public void testSellCapsulesToVisitor4() {
		try {
			assertEquals(data.getEmployees().size(), 0);
			int id1 = data.createEmployee("Mario" ,"Rossi");
			assertEquals(data.getEmployeeBalance(id1), new Integer(0));
			data.rechargeAccount(id1, 2000);
			assertEquals(data.getEmployeeBalance(id1), new Integer(2000));
			int b1 = data.createBeverage("tea", 50, 1000);
			assertEquals(data.getBeverages().size(), 1);
			data.buyBoxes(b1, 2);
			assertEquals(data.getBeverageCapsules(b1), new Integer(100));
			data.sellCapsulesToVisitor(b1, 115);
			fail();
		} catch (NotEnoughCapsules e){
		} catch (Exception e) {
			fail();
		}
	}
	
	/*
	 * 	method: sellCapsulesToVisitor
	 */
	
	@Test
	public void testSellCapsulesToVisitor5() {
		try {
			assertEquals(data.getEmployees().size(), 0);
			int id1 = data.createEmployee("Mario" ,"Rossi");
			assertEquals(data.getEmployeeBalance(id1), new Integer(0));
			data.rechargeAccount(id1, 2000);
			assertEquals(data.getEmployeeBalance(id1), new Integer(2000));
			int b1 = data.createBeverage("tea", 50, 1000);
			assertEquals(data.getBeverages().size(), 1);
			data.buyBoxes(b1, 2);
			assertEquals(data.getBeverageCapsules(b1), new Integer(100));
			data.sellCapsulesToVisitor(b1, -5);
			fail();
		} catch (BeverageException e){
		} catch (Exception e) {
			fail();
		}
	}
	
	/*
	 * 	method: updateBeverage
	 */
	
	@Test
	public void testUpdateBeverage1() {
		try {
			assertEquals(data.getBeverages().size(), 0);
			int b1 = data.createBeverage("tea", 50, 1000);
			assertEquals(data.getBeverages().size(), 1);
			int id1 = data.createEmployee("Mario" ,"Rossi");
			assertEquals(data.getEmployeeBalance(id1), new Integer(0));
			data.rechargeAccount(id1, 2000);
			data.buyBoxes(b1, 1);
			data.updateBeverage(b1, "lemon tea", 60, 1250);
			assertEquals(data.getBeverages().size(), 1);
			assertEquals(data.getBeverageName(b1), "lemon tea");
			assertEquals(data.getBeverageBoxPrice(b1), new Integer(1250));
			assertEquals(data.getBeverageCapsulesPerBox(b1), new Integer(60));
			assertEquals(data.getBeverageCapsules(b1), new Integer(50));
		} catch (Exception e) {
			fail();
		}
	}
	
	/*
	 * 	method: updateBeverage
	 */
	
	@Test
	public void testUpdateBeverage2() {
		int b1 = 0;
		try {
			assertEquals(data.getBeverages().size(), 0);
			b1 = data.createBeverage("tea", 50, 1000);
			assertEquals(data.getBeverages().size(), 1);
			int id1 = data.createEmployee("Mario" ,"Rossi");
			assertEquals(data.getEmployeeBalance(id1), new Integer(0));
			data.rechargeAccount(id1, 2000);
			data.buyBoxes(b1, 1);
			data.updateBeverage(b1+1, "lemon tea", 60, 1250);
			fail();
		} catch (BeverageException e) {
			assertEquals(data.getBeverages().size(), 1);
			try {
				assertEquals(data.getBeverageName(b1), "tea");
				assertEquals(data.getBeverageBoxPrice(b1), new Integer(1000));
				assertEquals(data.getBeverageCapsulesPerBox(b1), new Integer(50));
				assertEquals(data.getBeverageCapsules(b1), new Integer(50));
			} catch (BeverageException e1) {
				fail();
			}
		} catch (Exception e) {
			fail();
		}
	}
	
	/*
	 * 	method: updateBeverage
	 */
	
	@Test
	public void testUpdateBeverage3() {
		int b1 = 0;
		try {
			assertEquals(data.getBeverages().size(), 0);
			b1 = data.createBeverage("tea", 50, 1000);
			assertEquals(data.getBeverages().size(), 1);
			int id1 = data.createEmployee("Mario" ,"Rossi");
			assertEquals(data.getEmployeeBalance(id1), new Integer(0));
			data.rechargeAccount(id1, 2000);
			data.buyBoxes(b1, 1);
			data.updateBeverage(-1, "lemon tea", 60, 1250);
			fail();
		} catch (BeverageException e) {
			assertEquals(data.getBeverages().size(), 1);
			try {
				assertEquals(data.getBeverageName(b1), "tea");
				assertEquals(data.getBeverageBoxPrice(b1), new Integer(1000));
				assertEquals(data.getBeverageCapsulesPerBox(b1), new Integer(50));
				assertEquals(data.getBeverageCapsules(b1), new Integer(50));
			} catch (BeverageException e1) {
				fail();
			}
		} catch (Exception e) {
			fail();
		}
	}
	
	/*
	 * 	method: updateBeverage
	 */
	
	@Test
	public void testUpdateBeverage4() {
		int b1 = 0;
		try {
			assertEquals(data.getBeverages().size(), 0);
			b1 = data.createBeverage("tea", 50, 1000);
			assertEquals(data.getBeverages().size(), 1);
			int id1 = data.createEmployee("Mario" ,"Rossi");
			assertEquals(data.getEmployeeBalance(id1), new Integer(0));
			data.rechargeAccount(id1, 2000);
			data.buyBoxes(b1, 1);
			data.updateBeverage(b1, "lemon tea", -5, 1250);
			fail();
		} catch (BeverageException e) {
			assertEquals(data.getBeverages().size(), 1);
			try {
				assertEquals(data.getBeverageName(b1), "tea");
				assertEquals(data.getBeverageBoxPrice(b1), new Integer(1000));
				assertEquals(data.getBeverageCapsulesPerBox(b1), new Integer(50));
				assertEquals(data.getBeverageCapsules(b1), new Integer(50));
			} catch (BeverageException e1) {
				fail();
			}
		} catch (Exception e) {
			fail();
		}
	}
	
	/*
	 * 	method: updateBeverage
	 */
	
	@Test
	public void testUpdateBeverage5() {
		int b1 = 0;
		try {
			assertEquals(data.getBeverages().size(), 0);
			b1 = data.createBeverage("tea", 50, 1000);
			assertEquals(data.getBeverages().size(), 1);
			int id1 = data.createEmployee("Mario" ,"Rossi");
			assertEquals(data.getEmployeeBalance(id1), new Integer(0));
			data.rechargeAccount(id1, 2000);
			data.buyBoxes(b1, 1);
			data.updateBeverage(b1+1, "lemon tea", 60, -1000);
			fail();
		} catch (BeverageException e) {
			assertEquals(data.getBeverages().size(), 1);
			try {
				assertEquals(data.getBeverageName(b1), "tea");
				assertEquals(data.getBeverageBoxPrice(b1), new Integer(1000));
				assertEquals(data.getBeverageCapsulesPerBox(b1), new Integer(50));
				assertEquals(data.getBeverageCapsules(b1), new Integer(50));
			} catch (BeverageException e1) {
				fail();
			}
		} catch (Exception e) {
			fail();
		}
	}

	
	/*
	 * 	method: updateEmployee
	 */
	
	@Test
	public void testUpdateEmployee1() {
		try {
			assertEquals(data.getEmployees().size(), 0);
			int id1 = data.createEmployee("Mario", "Rossi");
			assertEquals(data.getEmployees().size(), 1);
			data.rechargeAccount(id1, 1000);
			assertEquals(data.getEmployeeBalance(id1), new Integer(1000));
			data.updateEmployee(id1, "Michele", "Suria");
			assertEquals(data.getEmployees().size(), 1);
			assertEquals(data.getEmployeeName(id1), "Michele");
			assertEquals(data.getEmployeeSurname(id1), "Suria");
			assertEquals(data.getEmployeeBalance(id1), new Integer(1000));
		} catch (Exception e) {
			fail();
		}
	}
	
	/*
	 * 	method: updateEmployee
	 */
	
	@Test
	public void testUpdateEmployee2() {
		int id1 = 0;
		try {
			assertEquals(data.getEmployees().size(), 0);
			id1 = data.createEmployee("Mario", "Rossi");
			assertEquals(data.getEmployees().size(), 1);
			data.rechargeAccount(id1, 1000);
			assertEquals(data.getEmployeeBalance(id1), new Integer(1000));
			data.updateEmployee(id1+1, "Michele", "Suria");
			fail();
		} catch (EmployeeException e) {
			assertEquals(data.getEmployees().size(), 1);
			try {
				assertEquals(data.getEmployeeName(id1), "Mario");
				assertEquals(data.getEmployeeSurname(id1), "Rossi");
				assertEquals(data.getEmployeeBalance(id1), new Integer(1000));
			} catch (EmployeeException e1) {
				fail();
			}
		} catch (Exception e) {
			fail();
		}
	}
	
	/*
	 * 	method: updateEmployee
	 */
	
	@Test
	public void testUpdateEmployee3() {
		int id1 = 0;
		try {
			assertEquals(data.getEmployees().size(), 0);
			id1 = data.createEmployee("Mario", "Rossi");
			assertEquals(data.getEmployees().size(), 1);
			data.rechargeAccount(id1, 1000);
			assertEquals(data.getEmployeeBalance(id1), new Integer(1000));
			data.updateEmployee(-1, "Michele", "Suria");
			fail();
		} catch (EmployeeException e) {
			assertEquals(data.getEmployees().size(), 1);
			try {
				assertEquals(data.getEmployeeName(id1), "Mario");
				assertEquals(data.getEmployeeSurname(id1), "Rossi");
				assertEquals(data.getEmployeeBalance(id1), new Integer(1000));
			} catch (EmployeeException e1) {
				fail();
			}
		} catch (Exception e) {
			fail();
		}
	}
	
	
	/*
	 *****************
	 *** White box ***
	 *****************
	 */
	
	@Test
	public void testDataPersistency() {
		int idB=-1, idE=-1;
		try {
			idB = data.createBeverage("Coffee", 20, 500);
			idE = data.createEmployee("Mario", "Rossi");
			data.rechargeAccount(idE, 700);
			data.buyBoxes(idB, 1);
			data.sellCapsules(idE, idB, 4, true);
		} catch (Exception e) {
			fail("Exception while creating the environment");
		}
		
		try {
			data = new DataImpl();	// simulate restart of the application
			assertEquals(new Integer(200), data.getBalance());
			assertEquals(new Integer(600), data.getEmployeeBalance(idE));
			assertEquals(new Integer(16), data.getBeverageCapsules(idB));
			assertEquals("Mario Rossi", data.getEmployeeName(idE) + " " + data.getEmployeeSurname(idE));
			assertEquals("Coffee", data.getBeverageName(idB));
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	public void testScenario2() {
		try {
			int employeeId = data.createEmployee("Mario", "Rossi");
			int beverageId = data.createBeverage("Coffee", 20, 500);
			
			// recharge balance of another employee to increase the amount of LaTazza, allowing to buy new boxes
			int id = data.createEmployee("John", "Doe");
			data.rechargeAccount(id, 10000);
			data.buyBoxes(beverageId, 1);
			
			data.sellCapsules(employeeId, beverageId, 1, true);
			int balance = data.getEmployeeBalance(employeeId);
			// employee balance (previously 0) is now negative
			assertEquals(-25, balance);
		} catch (EmployeeException e) {
			fail();
		} catch (BeverageException e) {
			fail();
		} catch (NotEnoughCapsules e) {
			fail();
		} catch (NotEnoughBalance e) {
			fail();
		}
	}
	
	
	/*
	 *		FR8 + FR3
	 */
	
	@Test
	public void testScenario6() {
		try {
			int employeeId = data.createEmployee("Mario", "Rossi");
			
			assertEquals("Mario", data.getEmployeeName(employeeId));
			assertEquals("Rossi", data.getEmployeeSurname(employeeId));
			assertEquals(Integer.valueOf(0), data.getEmployeeBalance(employeeId));
			assertEquals(1, data.getEmployees().size());
			assertTrue(data.getEmployees().containsKey(employeeId));
			assertTrue(data.getEmployees().containsValue("Mario Rossi"));
			assertEquals(1, data.getEmployeesId().size());
			assertTrue(data.getEmployeesId().contains(employeeId));
	
			Date d = new Date();
			data.rechargeAccount(employeeId, 500);
			List<String> rechargeReport = data.getEmployeeReport(employeeId, d, new Date());
			assertEquals(1, rechargeReport.size());
			
				
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String expected = format.format(d) + " RECHARGE Mario Rossi " + String.format("%.2f", 5.0) + "€";
			assertEquals(expected, rechargeReport.get(0));
		} catch (EmployeeException e) {
			fail();
		} catch (DateException e) {
			fail();
		}
	}
	
	@Test
	public void testScenario7() {
		try {
			int id = data.createEmployee("Mario", "Rossi");
			data.rechargeAccount(id, 1);
			int b = data.createBeverage("Coffee", 1, 1);
			assertEquals("Coffee", data.getBeverageName(b));
			data.buyBoxes(b, 1);
			assertEquals(new Integer(0), data.getBalance());
		} catch (Exception e) {
			fail();
		}
	}
	
	/*
	 * NFR2
	 */
	@Test
	public void testPerformance() {
		try {
			long begin, end;
			int idE, idB;
			Date startDate=new Date(), endDate;
			
			/* FR7 - create */
			begin = System.currentTimeMillis();  
			idB = data.createBeverage("Coffe", 2, 50);
			end = System.currentTimeMillis();
			assertTrue(end-begin < 500);
			
			/* FR7 - update */
			begin = System.currentTimeMillis();  
			data.updateBeverage(idB, "Coffee", 20, 500);
			end = System.currentTimeMillis();
			assertTrue(end-begin < 500);
			
			/* FR8 - create */
			begin = System.currentTimeMillis();  
			idE = data.createEmployee("Mari", "Rosi");
			end = System.currentTimeMillis();
			assertTrue(end-begin < 500);
			
			/* FR8 - update */
			begin = System.currentTimeMillis();  
			data.updateEmployee(idE, "Mario", "Rossi");
			end = System.currentTimeMillis();
			assertTrue(end-begin < 500);
			
			/* FR3 */
			begin = System.currentTimeMillis();
			data.rechargeAccount(idE, 10000);
			end = System.currentTimeMillis();
			assertTrue(end-begin < 500);
			
			/* FR4 */
			begin = System.currentTimeMillis();
			data.buyBoxes(idB, 20);
			end = System.currentTimeMillis();
			assertTrue(end-begin < 500);
			
			/* FR1 */
			begin = System.currentTimeMillis();
			data.sellCapsules(idE, idB, 2, true);
			end = System.currentTimeMillis();
			assertTrue(end-begin < 500);
			
			/* FR2 */
			begin = System.currentTimeMillis();
			data.sellCapsulesToVisitor(idB, 2);
			end = System.currentTimeMillis();
			assertTrue(end-begin < 500);
			
			// increase transactions for more realistic report production test
			for (int i=0; i<100; i++) {
				data.sellCapsules(idE, idB, 1, true);
				data.sellCapsulesToVisitor(idB, 1);
			}
			endDate = new Date();
			
			/* FR5 */
			begin = System.currentTimeMillis();
			data.getEmployeeReport(idE, startDate, endDate);
			end = System.currentTimeMillis();
			assertTrue(end-begin < 500);
			
			/* FR6 */
			begin = System.currentTimeMillis();
			data.getReport(startDate, endDate);
			end = System.currentTimeMillis();
			assertTrue(end-begin < 500);
			
		} catch (EmployeeException | BeverageException | NotEnoughCapsules | NotEnoughBalance | DateException e) {
			fail();
		}
	}
	
}
