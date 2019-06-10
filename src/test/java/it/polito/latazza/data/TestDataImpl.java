package it.polito.latazza.data;

import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.polito.latazza.exceptions.BeverageException;
import it.polito.latazza.exceptions.EmployeeException;
import it.polito.latazza.exceptions.NotEnoughBalance;
import it.polito.latazza.exceptions.NotEnoughCapsules;
import it.polito.latazza.exceptions.DateException;

/**
 * This class includes:
 * - Integration testing
 * - Acceptance testing
 * 
 * Since the used technique for integration is incremental bottom up
 * (i.e. unit tests have been done previously on units this class depends on),
 * no stubs are used.
 * 
 * @author s267563
 * @author s265682
 * @author s265485 
 * @author s261072
 * 
 */
public class TestDataImpl {
	DataInterface data;
	int employeeId, beverageId;
	
	@BeforeEach
	void setUp() throws Exception {
		data = new DataImpl();
		data.reset();
		
		/*
		 * Initial environment:
		 * - 1 employee: Mario Rossi, balance 1000
		 * - 1 beverage: Coffee, 20 capsules per box, 5 euro per box
		 * - Available capsules: 40 coffee
		 * - Balance: 0
		 * 
		 * This is done here because many test cases need an employee, a beverage and/or some capsules.
		 */
		employeeId = data.createEmployee("Mario", "Rossi");
		beverageId = data.createBeverage("Coffee", 20, 500);
		data.rechargeAccount(employeeId, 1000);
		data.buyBoxes(beverageId, 2);
	}
	
	
	/*
	 * Black box
	 */
	
	@Test
	public void testGetBeverageName1() {
		try {
			assertEquals("Coffee", data.getBeverageName(beverageId));
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
			assertTrue(true);
		}
	}	
	
	@Test
	public void testGetBeverageName3() {
		try {
			data.getBeverageName(-10);
			fail();
		} catch (BeverageException e) {
			assertTrue(true);
		}
	}	
	
	@Test
	public void testGetBeverageCapsulesPerBox1() {
		try {
			assertEquals(Integer.valueOf(20), data.getBeverageCapsulesPerBox(beverageId));
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
			assertTrue(true);
		}
	}	
	
	@Test
	public void testGetBeverageCapsulesPerBox3() {
		try {
			data.getBeverageCapsulesPerBox(-10);
			fail();
		} catch (BeverageException e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testGetBeverageCapsules1() {
		try {
			data.reset();
			int idB = data.createBeverage("Coffee", 20, 500);
			assertEquals(Integer.valueOf(0), data.getBeverageCapsules(idB));
		} catch (BeverageException e) {
			fail();
		}
	}
	
	@Test
	public void testGetBeverageCapsules2() {
		try {
			assertEquals(Integer.valueOf(40), data.getBeverageCapsules(beverageId));
		} catch (BeverageException e) {
			fail();
		}
	}
	
	@Test
	public void testGetBeverageCapsules3() {
		try {
			data.getBeverageCapsules(10);
			fail();
		} catch (BeverageException e) {
			assertTrue(true);
		}
	}	
	
	@Test
	public void testGetBeverageCapsules4() {
		try {
			data.getBeverageCapsules(-10);
			fail();
		} catch (BeverageException e) {
			assertTrue(true);
		}
	}	
	
	@Test
	public void testGetBeveragesId1() {
		List<Integer> beverages = data.getBeveragesId();
		assertTrue(beverages.size() == 1);
		assertTrue(beverages.contains(beverageId));
	}	
	
	@Test
	public void testGetBeveragesId2() {
		data.reset();
		List<Integer> beverages = data.getBeveragesId();
		assertTrue(beverages.isEmpty());
	}	
	
	@Test
	public void testGetBeverages1() {
		Map<Integer, String> beverages = data.getBeverages();
		assertTrue(beverages.size() == 1);
		assertNotNull(beverages.get(beverageId));
		assertTrue(beverages.get(beverageId).equals("Coffee"));
	}	
	
	@Test
	public void testGetBeverages2() {
		data.reset();
		Map<Integer, String> beverages = data.getBeverages();
		assertTrue(beverages.isEmpty());
	}	
	
	@Test
	public void testGetEmployeeBalance1() {
		try {
			data.reset();
			int idE = data.createEmployee("Mario", "Rossi");
			int balance = data.getEmployeeBalance(idE);
			assertEquals(0, balance);
		} catch (EmployeeException e) {
			fail();
		}
	}	
	
	@Test
	public void testGetEmployeeBalance2() {
		try {
			assertEquals(Integer.valueOf(1000), data.getEmployeeBalance(employeeId));
			data.rechargeAccount(employeeId, 500);
			assertEquals(Integer.valueOf(1500), data.getEmployeeBalance(employeeId));
		} catch (EmployeeException e) {
			fail();
		}
	}
	
	@Test
	public void testGetEmployeeBalance3() {
		try {
			data.getEmployeeBalance(10);
			fail();
		} catch (EmployeeException e) {
			assertTrue(true);
		}
	}	
	
	@Test
	public void testGetEmployeeBalance4() {
		try {
			data.getEmployeeBalance(-10);
			fail();
		} catch (EmployeeException e) {
			assertTrue(true);
		}
	}	
	
	@Test
	public void testGetEmployeeName1() {
		try {
			assertEquals("Mario", data.getEmployeeName(employeeId));
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
			assertTrue(true);
		}
	}
	
	@Test
	public void testGetEmployeeSurname1() {
		try {
			assertEquals("Rossi", data.getEmployeeSurname(employeeId));
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
			assertTrue(true);
		}
	}
	
	@Test
	public void testGetReport1() {
		data.reset();
		try {
			Date d = new Date();
			assertEquals(0, data.getReport(d, new Date()).size());
			
			employeeId = data.createEmployee("Mario", "Rossi");
			data.rechargeAccount(employeeId, 10000);
			List<String> l = data.getReport(d, new Date());
			assertEquals(1, l.size());
			String s = (String) l.get(0);
			assertEquals(" RECHARGE Mario Rossi " + String.format("%.2f", 100.0) + " €", s.substring(19, s.length()));  

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
			assertTrue(true);
		}
	}
	
	@Test
	public void testGetReport3() {
		try {
			data.getReport( null, new Date());
			fail();
		} catch (DateException e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testGetEmployeeReport1() {
		data.reset();
		try {
			Date d = new Date();
			employeeId = data.createEmployee("Mario", "Rossi");
			assertEquals(0, data.getEmployeeReport(employeeId, d, new Date()).size());

			data.rechargeAccount(employeeId, 10000);
			List<String> l = data.getEmployeeReport(employeeId, d, new Date());
			assertEquals(1, l.size());
			String s = (String) l.get(0);
			assertEquals(" RECHARGE Mario Rossi " + String.format("%.2f", 100.0) + " €", s.substring(19, s.length()));  

			int b = data.createBeverage("tea", 10, 1);
			data.buyBoxes(b, 1);
			data.sellCapsules(employeeId, b, 10, true);
			assertEquals(data.getEmployeeReport(employeeId, d, new Date()).size(), 2);
			s = data.getEmployeeReport(employeeId, d, new Date()).get(1);
			assertEquals(" BALANCE Mario Rossi tea 10", s.substring(19, s.length()));
			
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	public void testGetEmployeeReport2() {
		try {
			data.getEmployeeReport(employeeId, new Date(new Date().getTime() + (1000 * 60 * 60 * 48)), new Date());
			fail();
		} catch (DateException e) {
			assertTrue(true);
		}
		catch (EmployeeException e) {
			fail();
		}
	}
	
	@Test
	public void testGetEmployeeReport3() {
		try {
			data.getEmployeeReport(employeeId, new Date(), null);
			fail();
		} catch (DateException e) {
			assertTrue(true);
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
			assertTrue(true);
		}
	}
	
	@Test
	public void testGetEmployeesId() {
		try {
			assertEquals(1, data.getEmployeesId().size());
			int idE = data.createEmployee("Marco", "Bianchi");
			List<Integer> l = data.getEmployeesId();
			assertEquals(2, l.size());
			assertTrue(l.contains(employeeId));
			assertTrue(l.contains(idE));
		} catch (EmployeeException e){
			fail();
		}
	}
	
	@Test
	public void testGetEmployees() {
		try {
			assertEquals(1, data.getEmployees().size());
			int idE = data.createEmployee("Marco", "Bianchi");
			Map<Integer, String> m = data.getEmployees();
			assertEquals(2, m.size());
			assertEquals("Mario Rossi", m.get(employeeId));
			assertEquals("Marco Bianchi", m.get(idE));
		} catch (EmployeeException e){
			fail();
		}
	}
	
	@Test
	public void testCreateBeverage1() {
		try {
			data.reset();
			int idB = data.createBeverage("Coffee", 20, 500);
			assertEquals("Coffee", data.getBeverageName(idB));
			assertEquals(Integer.valueOf(20), data.getBeverageCapsulesPerBox(idB));
			assertEquals(Integer.valueOf(500), data.getBeverageBoxPrice(idB));
		} catch (BeverageException e) {
			fail();
		}
	}
	
	@Test
	public void testCreateBeverage2() {
		try {
			data.reset();
			data.createBeverage("Coffee", 20, -500);
			fail();
		} catch (BeverageException e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testCreateBeverage3() {
		try {
			data.reset();
			data.createBeverage("Coffee", -20, 500);
			fail();
		} catch (BeverageException e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testCreateBeverage4() {
		try {
			data.createBeverage("Coffee", 25, 600);
			fail();
		} catch (BeverageException e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testGetBeverageBoxPrice1() {
		try {
			assertEquals(Integer.valueOf(500), data.getBeverageBoxPrice(beverageId));
		} catch (BeverageException e) {
			fail();
		}
	}
	
	@Test
	public void testGetBeverageBoxPrice2() {
		try {
			data.reset();
			data.getBeverageBoxPrice(1);
			fail();
		} catch (BeverageException e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testCreateEmployee1() {
		try {
			data.reset();
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
			fail();
		} catch (EmployeeException e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testBuyBoxes1() {
		data.reset();
		try {
			int idE = data.createEmployee("Mario", "Rossi");
			int idB = data.createBeverage("Coffee", 20, 500);
			data.rechargeAccount(idE, 500);
			data.buyBoxes(idB, 1);
			assertEquals(Integer.valueOf(20), data.getBeverageCapsules(idB));
		} catch (BeverageException | NotEnoughBalance | EmployeeException e) {
			fail();
		}
	}
	
	@Test
	public void testBuyBoxes2() {
		try {
			data.buyBoxes(beverageId, 1);
			fail();
		} catch (BeverageException e) {
			fail();
		} catch (NotEnoughBalance e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testBuyBoxes3() {
		try {
			data.rechargeAccount(employeeId, 500);
			data.buyBoxes(beverageId, -1);
			fail();
		} catch (NotEnoughBalance | EmployeeException e) {
			fail();
		} catch (BeverageException e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testBuyBoxes4() {
		try {
			data.rechargeAccount(employeeId, 500);
			data.buyBoxes(10, 1);
		} catch (NotEnoughBalance | EmployeeException e) {
			fail();
		} catch (BeverageException e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testGetBalance1() {
		try {
			data.rechargeAccount(employeeId, 500);
			assertEquals(Integer.valueOf(500), data.getBalance());
			data.buyBoxes(beverageId, 1);
			assertEquals(Integer.valueOf(0), data.getBalance());
			data.sellCapsulesToVisitor(beverageId, 4);
			assertEquals(Integer.valueOf(100), data.getBalance());
		} catch (BeverageException | EmployeeException | NotEnoughBalance | NotEnoughCapsules e) {
			fail();
		}
	}
	
	@Test
	public void testGetBalance2() {
		data.reset();
		assertEquals(Integer.valueOf(0), data.getBalance());
	}
	
	@Test
	public void testRechargeAccount1() {
		try {
			assertEquals(Integer.valueOf(1000), data.getEmployeeBalance(employeeId));
			data.rechargeAccount(employeeId, 500);
			assertEquals(Integer.valueOf(1500), data.getEmployeeBalance(employeeId));
		} catch (EmployeeException e){
			fail();
		}
	}
	
	@Test
	public void testRechargeAccount2() {
		try {
			data.rechargeAccount(-1, 10);
			fail();
		} catch (EmployeeException e){
			assertTrue(true);
		}
	}
	
	@Test
	public void testRechargeAccount3() {
		try {
			assertEquals(Integer.valueOf(1000), data.getEmployeeBalance(employeeId));
			data.rechargeAccount(employeeId, -10);
			fail();
		} catch (EmployeeException e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void tcReset() {
		try {
			data.createEmployee("Luca", "Sassi");
			int b1 = data.createBeverage("tea", 50, 1000);
			data.rechargeAccount(employeeId, 2000);
			data.buyBoxes(b1, 2);
			data.sellCapsules(employeeId, b1, 50, true);
			assertEquals(Integer.valueOf(2000), data.getEmployeeBalance(employeeId));
			data.reset();
			assertEquals(0, data.getEmployees().size());
			assertEquals(0, data.getBeverages().size());
			assertEquals(Integer.valueOf(0), data.getBalance());
		} catch (EmployeeException | BeverageException | NotEnoughBalance | NotEnoughCapsules e) {
			fail();
		}
	}
	
	@Test
	public void testSellCapsules1() {
		data.reset();
		try {
			assertEquals(0, data.getEmployees().size());
			int id1 = data.createEmployee("Mario" ,"Rossi");
			assertEquals(Integer.valueOf(0), data.getEmployeeBalance(id1));
			data.rechargeAccount(id1, 2000);
			assertEquals(Integer.valueOf(2000), data.getEmployeeBalance(id1));
			int b1 = data.createBeverage("tea", 50, 1000);
			assertEquals(1, data.getBeverages().size());
			data.buyBoxes(b1, 2);
			assertEquals(Integer.valueOf(100), data.getBeverageCapsules(b1));
			data.sellCapsules(id1, b1, 50, true);
			assertEquals(Integer.valueOf(1000), data.getEmployeeBalance(id1));
			assertEquals(Integer.valueOf(50), data.getBeverageCapsules(b1));
			assertEquals(Integer.valueOf(0), data.getBalance());
			data.sellCapsules(id1, b1, 50, false);
			assertEquals(Integer.valueOf(1000), data.getEmployeeBalance(id1));
			assertEquals(Integer.valueOf(0), data.getBeverageCapsules(b1));
			assertEquals(Integer.valueOf(1000), data.getBalance());
		} catch (Exception e){
			fail();
		}
	}
	
	@Test
	public void testSellCapsules2() {
		data.reset();
		int id1 = 0, b1 = 0; 
		try {
			assertEquals(0, data.getEmployees().size());
			id1 = data.createEmployee("Mario" ,"Rossi");
			assertEquals(Integer.valueOf(0), data.getEmployeeBalance(id1));
			data.rechargeAccount(id1, 2000);
			assertEquals(Integer.valueOf(2000), data.getEmployeeBalance(id1));
			b1 = data.createBeverage("tea", 50, 1000);
			assertEquals(1, data.getBeverages().size());
			data.buyBoxes(b1, 2);
			assertEquals(Integer.valueOf(100), data.getBeverageCapsules(b1));
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
	
	@Test
	public void testSellCapsules3() {
		data.reset();
		int id1 = 0, b1 = 0; 
		try {
			assertEquals(0, data.getEmployees().size());
			id1 = data.createEmployee("Mario" ,"Rossi");
			assertEquals(Integer.valueOf(0), data.getEmployeeBalance(id1));
			data.rechargeAccount(id1, 2000);
			assertEquals(Integer.valueOf(2000), data.getEmployeeBalance(id1));
			b1 = data.createBeverage("tea", 50, 1000);
			assertEquals(1, data.getBeverages().size());
			data.buyBoxes(b1, 2);
			assertEquals(Integer.valueOf(100), data.getBeverageCapsules(b1));
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
	
	@Test
	public void testSellCapsules4() {
		data.reset();
		int id1 = 0, b1 = 0; 
		try {
			assertEquals(0, data.getEmployees().size());
			id1 = data.createEmployee("Mario" ,"Rossi");
			assertEquals(Integer.valueOf(0), data.getEmployeeBalance(id1));
			data.rechargeAccount(id1, 2000);
			assertEquals(Integer.valueOf(2000), data.getEmployeeBalance(id1));
			b1 = data.createBeverage("tea", 50, 1000);
			assertEquals(1, data.getBeverages().size());
			data.buyBoxes(b1, 2);
			assertEquals(Integer.valueOf(100), data.getBeverageCapsules(b1));
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
	
	@Test
	public void testSellCapsules5() {
		data.reset();
		int id1 = 0, b1 = 0; 
		try {
			assertEquals(0, data.getEmployees().size());
			id1 = data.createEmployee("Mario" ,"Rossi");
			assertEquals(Integer.valueOf(0), data.getEmployeeBalance(id1));
			data.rechargeAccount(id1, 2000);
			assertEquals(Integer.valueOf(2000), data.getEmployeeBalance(id1));
			b1 = data.createBeverage("tea", 50, 1000);
			assertEquals(1, data.getBeverages().size());
			data.buyBoxes(b1, 2);
			assertEquals(Integer.valueOf(100), data.getBeverageCapsules(b1));
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
	
	@Test
	public void testSellCapsules6() {
		data.reset();
		int id1 = 0, b1 = 0; 
		try {
			assertEquals(0, data.getEmployees().size());
			id1 = data.createEmployee("Mario" ,"Rossi");
			assertEquals(Integer.valueOf(0), data.getEmployeeBalance(id1));
			data.rechargeAccount(id1, 2000);
			assertEquals(Integer.valueOf(2000), data.getEmployeeBalance(id1));
			b1 = data.createBeverage("tea", 50, 1000);
			assertEquals(1, data.getBeverages().size());
			data.buyBoxes(b1, 2);
			assertEquals(Integer.valueOf(100), data.getBeverageCapsules(b1));
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
	
	@Test
	public void testSellCapsules7() {
		data.reset();
		int id1 = 0, b1 = 0; 
		try {
			assertEquals(0, data.getEmployees().size());
			id1 = data.createEmployee("Mario" ,"Rossi");
			assertEquals(Integer.valueOf(0), data.getEmployeeBalance(id1));
			data.rechargeAccount(id1, 2000);
			assertEquals(Integer.valueOf(2000), data.getEmployeeBalance(id1));
			b1 = data.createBeverage("tea", 50, 1000);
			assertEquals(1, data.getBeverages().size());
			data.buyBoxes(b1, 2);
			assertEquals(Integer.valueOf(100), data.getBeverageCapsules(b1));
			data.sellCapsules(id1, b1, -5, true);
			fail();
		} catch (NotEnoughCapsules e){
			try {
				data.sellCapsules(id1, b1, -5, false);
				fail();
			} catch (NotEnoughCapsules e1) {
				
			} catch (Exception e2) {
				fail();
			}
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	public void testSellCapsulesToVisitor1() {
		data.reset();
		try {
			assertEquals(0, data.getEmployees().size());
			int id1 = data.createEmployee("Mario" ,"Rossi");
			assertEquals(Integer.valueOf(0), data.getEmployeeBalance(id1));
			data.rechargeAccount(id1, 2000);
			assertEquals(Integer.valueOf(2000), data.getEmployeeBalance(id1));
			int b1 = data.createBeverage("tea", 50, 1000);
			assertEquals(1, data.getBeverages().size());
			data.buyBoxes(b1, 2);
			assertEquals(Integer.valueOf(100), data.getBeverageCapsules(b1));
			data.sellCapsulesToVisitor(b1, 50);
			assertEquals(Integer.valueOf(1000), data.getBalance());
			assertEquals(Integer.valueOf(50), data.getBeverageCapsules(b1));
		} catch (Exception e){
			fail();
		}
	}
	
	@Test
	public void testSellCapsulesToVisitor2() {
		data.reset();
		try {
			assertEquals(0, data.getEmployees().size());
			int id1 = data.createEmployee("Mario" ,"Rossi");
			assertEquals(Integer.valueOf(0), data.getEmployeeBalance(id1));
			data.rechargeAccount(id1, 2000);
			assertEquals(Integer.valueOf(2000), data.getEmployeeBalance(id1));
			int b1 = data.createBeverage("tea", 50, 1000);
			assertEquals(1, data.getBeverages().size());
			data.buyBoxes(b1, 2);
			assertEquals(Integer.valueOf(100), data.getBeverageCapsules(b1));
			data.sellCapsulesToVisitor(b1+1, 50);
			fail();
		} catch (BeverageException e){
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	public void testSellCapsulesToVisitor3() {
		data.reset();
		try {
			assertEquals(0, data.getEmployees().size());
			int id1 = data.createEmployee("Mario" ,"Rossi");
			assertEquals(Integer.valueOf(0), data.getEmployeeBalance(id1));
			data.rechargeAccount(id1, 2000);
			assertEquals(Integer.valueOf(2000), data.getEmployeeBalance(id1));
			int b1 = data.createBeverage("tea", 50, 1000);
			assertEquals(1, data.getBeverages().size());
			data.buyBoxes(b1, 2);
			assertEquals(Integer.valueOf(100), data.getBeverageCapsules(b1));
			data.sellCapsulesToVisitor(-1, 50);
			fail();
		} catch (BeverageException e){
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	public void testSellCapsulesToVisitor4() {
		data.reset();
		try {
			assertEquals(0, data.getEmployees().size());
			int id1 = data.createEmployee("Mario" ,"Rossi");
			assertEquals(Integer.valueOf(0), data.getEmployeeBalance(id1));
			data.rechargeAccount(id1, 2000);
			assertEquals(Integer.valueOf(2000), data.getEmployeeBalance(id1));
			int b1 = data.createBeverage("tea", 50, 1000);
			assertEquals(1, data.getBeverages().size());
			data.buyBoxes(b1, 2);
			assertEquals(Integer.valueOf(100), data.getBeverageCapsules(b1));
			data.sellCapsulesToVisitor(b1, 115);
			fail();
		} catch (NotEnoughCapsules e){
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	public void testSellCapsulesToVisitor5() {
		data.reset();
		try {
			assertEquals(0, data.getEmployees().size());
			int id1 = data.createEmployee("Mario" ,"Rossi");
			assertEquals(Integer.valueOf(0), data.getEmployeeBalance(id1));
			data.rechargeAccount(id1, 2000);
			assertEquals(Integer.valueOf(2000), data.getEmployeeBalance(id1));
			int b1 = data.createBeverage("tea", 50, 1000);
			assertEquals(1, data.getBeverages().size());
			data.buyBoxes(b1, 2);
			assertEquals(Integer.valueOf(100), data.getBeverageCapsules(b1));
			data.sellCapsulesToVisitor(b1, -5);
			fail();
		} catch (NotEnoughCapsules e){
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	public void testUpdateBeverage1() {
		try {
			int b1 = data.createBeverage("tea", 50, 1000);
			data.rechargeAccount(employeeId, 2000);
			data.buyBoxes(b1, 1);
			data.updateBeverage(b1, "lemon tea", 60, 1250);
			assertEquals(2, data.getBeverages().size());
			assertEquals("lemon tea", data.getBeverageName(b1));
			assertEquals(Integer.valueOf(1250), data.getBeverageBoxPrice(b1));
			assertEquals(Integer.valueOf(60), data.getBeverageCapsulesPerBox(b1));
			assertEquals(Integer.valueOf(50), data.getBeverageCapsules(b1));
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	public void testUpdateBeverage2() {
		int b1 = 0;
		try {
			b1 = data.createBeverage("tea", 50, 1000);
			assertEquals(2, data.getBeverages().size());
			data.rechargeAccount(employeeId, 2000);
			data.buyBoxes(b1, 1);
			data.updateBeverage(b1+1, "lemon tea", 60, 1250);
			fail();
		} catch (BeverageException e) {
			assertTrue(true);
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	public void testUpdateBeverage3() {
		int b1 = 0;
		try {
			b1 = data.createBeverage("tea", 50, 1000);
			assertEquals(2, data.getBeverages().size());
			data.rechargeAccount(employeeId, 2000);
			data.buyBoxes(b1, 1);
			data.updateBeverage(-1, "lemon tea", 60, 1250);
			fail();
		} catch (BeverageException e) {
			assertTrue(true);
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	public void testUpdateBeverage4() {
		int b1 = 0;
		try {
			b1 = data.createBeverage("tea", 50, 1000);
			assertEquals(2, data.getBeverages().size());
			data.rechargeAccount(employeeId, 2000);
			data.buyBoxes(b1, 1);
			data.updateBeverage(b1, "lemon tea", -5, 1250);
			fail();
		} catch (BeverageException e) {
			assertTrue(true);
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	public void testUpdateBeverage5() {
		int b1 = 0;
		try {
			b1 = data.createBeverage("tea", 50, 1000);
			assertEquals(2, data.getBeverages().size());
			data.rechargeAccount(employeeId, 2000);
			data.buyBoxes(b1, 1);
			data.updateBeverage(b1+1, "lemon tea", 60, -1000);
			fail();
		} catch (BeverageException e) {
			assertTrue(true);
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	public void testUpdateEmployee1() {
		data.reset();
		try {
			assertEquals(0, data.getEmployees().size());
			int id1 = data.createEmployee("Mario", "Rossi");
			assertEquals(1, data.getEmployees().size());
			data.rechargeAccount(id1, 1000);
			assertEquals(Integer.valueOf(1000), data.getEmployeeBalance(id1));
			data.updateEmployee(id1, "Michele", "Suria");
			assertEquals(1, data.getEmployees().size());
			assertEquals("Michele", data.getEmployeeName(id1));
			assertEquals("Suria", data.getEmployeeSurname(id1));
			assertEquals(Integer.valueOf(1000), data.getEmployeeBalance(id1));
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	public void testUpdateEmployee2() {
		data.reset();
		int id1 = 0;
		try {
			assertEquals(0, data.getEmployees().size());
			id1 = data.createEmployee("Mario", "Rossi");
			assertEquals(1, data.getEmployees().size());
			data.rechargeAccount(id1, 1000);
			assertEquals(Integer.valueOf(1000), data.getEmployeeBalance(id1));
			data.updateEmployee(id1+1, "Michele", "Suria");
			fail();
		} catch (EmployeeException e) {
			assertEquals(1, data.getEmployees().size());
			try {
				assertEquals("Mario", data.getEmployeeName(id1));
				assertEquals("Rossi", data.getEmployeeSurname(id1));
				assertEquals(Integer.valueOf(1000), data.getEmployeeBalance(id1));
			} catch (EmployeeException e1) {
				fail();
			}
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	public void testUpdateEmployee3() {
		data.reset();
		int id1 = 0;
		try {
			assertEquals(0, data.getEmployees().size());
			id1 = data.createEmployee("Mario", "Rossi");
			assertEquals(1, data.getEmployees().size());
			data.rechargeAccount(id1, 1000);
			assertEquals(Integer.valueOf(1000), data.getEmployeeBalance(id1));
			data.updateEmployee(-1, "Michele", "Suria");
			fail();
		} catch (EmployeeException e) {
			assertEquals(1, data.getEmployees().size());
			try {
				assertEquals("Mario", data.getEmployeeName(id1));
				assertEquals("Rossi", data.getEmployeeSurname(id1));
				assertEquals(Integer.valueOf(1000), data.getEmployeeBalance(id1));
			} catch (EmployeeException e1) {
				fail();
			}
		} catch (Exception e) {
			fail();
		}
	}
	
	
	/*
	 * White box
	 */
	
	@Test
	// > 1 iterations for loop coverage
	public void testGetBeveragesId3() {
		try {
			int idB = data.createBeverage("Tea", 25, 400);
			List<Integer> beverages = data.getBeveragesId();
			assertTrue(beverages.size() == 2);
			assertTrue(beverages.contains(beverageId));
			assertTrue(beverages.contains(idB));
		} catch (BeverageException e) {
			fail();
		}
	}
	
	@Test
	// > 1 iterations for loop coverage
	public void testGetBeverages3() {
		try {
			int idB = data.createBeverage("Tea", 25, 400);
			Map<Integer, String> beverages = data.getBeverages();
			assertTrue(beverages.size() == 2);
			assertNotNull(beverages.get(beverageId));
			assertNotNull(beverages.get(idB));
			assertTrue(beverages.get(beverageId).equals("Coffee"));
			assertTrue(beverages.get(idB).equals("Tea"));
		} catch (BeverageException e) {
			fail();
		}
	}
	
	@Test
	public void testDataPersistency() {
		try {
			data.sellCapsules(employeeId, beverageId, 4, true);
			data.sellCapsulesToVisitor(beverageId, 4);
			data = new DataImpl();	// simulate restart of the application
			assertEquals(Integer.valueOf(100), data.getBalance());
			assertEquals(Integer.valueOf(900), data.getEmployeeBalance(employeeId));
			assertEquals(Integer.valueOf(32), data.getBeverageCapsules(beverageId));
			assertEquals("Mario Rossi", data.getEmployeeName(employeeId) + " " + data.getEmployeeSurname(employeeId));
			assertEquals("Coffee", data.getBeverageName(beverageId));
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	public void testExceptionUpdateEmployee() {
		try {
			data.updateEmployee(employeeId, "", "");
			fail();
		} catch (Exception e) {

		}
	}
	
	/*
	 * Acceptance testing
	 * 
	 * Some of the previous ones are also acceptance test cases.
	 * The following ones are added to cover all functional and non-functional requirements.
	 * See acceptance_test_document.md for more info.
	 */
	
	@Test
	public void testScenario2() {
		data.reset();
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
	
	@Test
	public void testScenario6() {
		data.reset();
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
			String expected = format.format(d) + " RECHARGE Mario Rossi " + String.format("%.2f", 5.0) + " €";
			assertEquals(expected, rechargeReport.get(0));
		} catch (EmployeeException e) {
			fail();
		} catch (DateException e) {
			fail();
		}
	}
	
	@Test
	public void testScenario7() {
		data.reset();
		try {
			int id = data.createEmployee("Mario", "Rossi");
			data.rechargeAccount(id, 1);
			int b = data.createBeverage("Coffee", 1, 1);
			assertEquals("Coffee", data.getBeverageName(b));
			data.buyBoxes(b, 1);
			assertEquals(Integer.valueOf(0), data.getBalance());
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testScenario3_1() throws EmployeeException, BeverageException, NotEnoughBalance, NotEnoughCapsules {
		data.reset();
		try {
		employeeId = data.createEmployee("Mario", "Rossi");
		beverageId = data.createBeverage("Coffee", 20, 500);
		data.rechargeAccount(employeeId, 1000);
		data.buyBoxes(beverageId, 1);
		data.updateBeverage(beverageId, "Coffee", 10, 500);
		data.buyBoxes(beverageId, 1);
		data.sellCapsulesToVisitor(beverageId, 25);
		assertEquals(Integer.valueOf(750), data.getBalance());
		assertEquals(Integer.valueOf(5), data.getBeverageCapsules(beverageId));
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	public void testPerformance() {
		data.reset();
		try {
			final int n = 100;
			long begin, end;
			int[] idE = new int[n], idB = new int[n];
			Date startDate=new Date(), endDate;
			
			/* FR7 - create */
			begin = System.currentTimeMillis();  
			for (int i=0; i<n; i++)
				idB[i] = data.createBeverage("Coffe" + i, 2, 50);
			end = System.currentTimeMillis();
			assertTrue((end-begin)/((double) n) < 500);
			
			/* FR7 - update */
			begin = System.currentTimeMillis(); 
			for (int i=0; i<n; i++)
				data.updateBeverage(idB[i], "Coffee"+i, 20, 500);
			end = System.currentTimeMillis();
			assertTrue((end-begin)/((double) n) < 500);
			
			/* FR8 - create */
			begin = System.currentTimeMillis();
			for (int i=0; i<n; i++)
				idE[i] = data.createEmployee("Mari"+i, "Rosi"+i);
			end = System.currentTimeMillis();
			assertTrue((end-begin)/((double) n) < 500);
			
			/* FR8 - update */
			begin = System.currentTimeMillis();  
			for (int i=0; i<n; i++)
				data.updateEmployee(idE[i], "Mario"+i, "Rossi"+i);
			end = System.currentTimeMillis();
			assertTrue((end-begin)/((double) n) < 500);
			
			/* FR3 */
			begin = System.currentTimeMillis();
			for (int i=0; i<n; i++)
				data.rechargeAccount(idE[i], 10000);
			end = System.currentTimeMillis();
			assertTrue((end-begin)/((double) n) < 500);
			
			/* FR4 */
			begin = System.currentTimeMillis();
			for (int i=0; i<n; i++)
				data.buyBoxes(idB[i], 20);
			end = System.currentTimeMillis();
			assertTrue((end-begin)/((double) n) < 500);
			
			/* FR1 */
			begin = System.currentTimeMillis();
			for (int i=0; i<n; i++)
				data.sellCapsules(idE[i], idB[i], 2, true);
			end = System.currentTimeMillis();
			assertTrue((end-begin)/((double) n) < 500);
			
			/* FR2 */
			begin = System.currentTimeMillis();
			for (int i=0; i<n; i++)
				data.sellCapsulesToVisitor(idB[i], 2);
			end = System.currentTimeMillis();
			assertTrue((end-begin)/((double) n) < 500);
			
			/* FR5 */
			endDate = new Date();
			begin = System.currentTimeMillis();
			for (int i=0; i<n; i++)
				data.getEmployeeReport(idE[i], startDate, endDate);
			end = System.currentTimeMillis();
			assertTrue((end-begin)/((double) n) < 500);
			
			/* FR6 */
			begin = System.currentTimeMillis();
			for (int i=0; i<n; i++)
				data.getReport(startDate, endDate);
			end = System.currentTimeMillis();
			assertTrue((end-begin)/((double) n) < 500);
			
		} catch (EmployeeException | BeverageException | NotEnoughCapsules | NotEnoughBalance | DateException e) {
			fail();
		}
	}
	
}
