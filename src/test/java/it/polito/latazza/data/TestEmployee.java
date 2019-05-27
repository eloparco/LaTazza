package it.polito.latazza.data;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.polito.latazza.exceptions.EmployeeException;

/**
 * Unit tests for class Employee
 * 
 * @author s265682
 * 
 */
class TestEmployee {
	Employee employee;
	
	@BeforeEach
	void setUp() throws Exception {
		employee = new Employee(1, "Mario", "Rossi");
	}
	
	
	/*
	 * Black box
	 */
	
	@Test
	void testIncreaseBalance1() {
		try {
			employee.increaseBalance(10);
			assertEquals(10, employee.getBalance());
		} catch (EmployeeException e) {
			fail();
		}
	}

	@Test
	void testIncreaseBalance2() {
		try {
			employee.increaseBalance(10);
			employee.increaseBalance(Integer.MAX_VALUE);
			fail();
		} catch (EmployeeException e) {
			assertTrue(true);
		}
	}
	
	@Test
	void testIncreaseBalance3() {
		try {
			employee.increaseBalance(-10);
			fail();
		} catch (EmployeeException e) {
			assertTrue(true);
		}
	}
	
	@Test
	void testDecreaseBalance1() {
		try {
			employee.decreaseBalance(10);
			assertEquals(-10, employee.getBalance());
		} catch (EmployeeException e) {
			fail();
		}
	}
	
	@Test
	void testDecreaseBalance2() {
		try {
			employee.decreaseBalance(10);
			employee.decreaseBalance(Integer.MAX_VALUE);
			fail();
		} catch (EmployeeException e) {
			assertTrue(true);
		}
	}
	
	@Test
	void testDecreaseBalance3() {
		try {
			employee.decreaseBalance(-10);
			fail();
		} catch (EmployeeException e) {
			assertTrue(true);
		}
	}
	
	
	/*
	 * White box
	 */
	
	@Test
	void testGettersAndSetters() {
		employee.setName("Andrea");
		employee.setSurname("Bianchi");
		assertEquals(1, employee.getId());
		assertEquals("Andrea", employee.getName());
		assertEquals("Bianchi", employee.getSurname());
		assertEquals("Andrea Bianchi", employee.toString());
	}

}
