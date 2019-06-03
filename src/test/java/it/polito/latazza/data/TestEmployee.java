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
	private Employee employee;
	
	private static final int ID = 1;
	private static final String MARIO = "Mario";
	private static final String ROSSI = "Rossi";
	private static final String ANDREA = "Andrea";
	private static final String BIANCHI = "Bianchi";
	
	@BeforeEach
	void setUp() throws Exception {
		employee = new Employee(ID, MARIO, ROSSI);
	}
	
	
	/*
	 * Black box
	 */
	
	@Test
	void testIncreaseBalance1() throws EmployeeException {
		employee.increaseBalance(10);
		assertEquals(10, employee.getBalance());
	}

	@Test
	void testIncreaseBalance2() throws EmployeeException {
		employee.increaseBalance(10);
		assertThrows(EmployeeException.class, () -> employee.increaseBalance(Integer.MAX_VALUE));
	}
	
	@Test
	void testIncreaseBalance3() throws EmployeeException {
		assertThrows(EmployeeException.class, () -> employee.increaseBalance(-10));
	}
	
	@Test
	void testDecreaseBalance1() throws EmployeeException {
		employee.decreaseBalance(10);
		assertEquals(-10, employee.getBalance());
	}
	
	@Test
	void testDecreaseBalance2() throws EmployeeException {
		employee.decreaseBalance(10);
		assertThrows(EmployeeException.class, () -> employee.decreaseBalance(Integer.MAX_VALUE));
	}
	
	@Test
	void testDecreaseBalance3() {
		assertThrows(EmployeeException.class, () -> employee.decreaseBalance(-10));
	}
	
	
	/*
	 * White box
	 */
	
	@Test
	void testGettersAndSetters() throws EmployeeException {
		assertEquals(ID, employee.getId());
		assertEquals(MARIO, employee.getName());
		assertEquals(ROSSI, employee.getSurname());
		assertEquals(MARIO + " " + ROSSI, employee.toString());
		employee.setName(ANDREA);
		employee.setSurname(BIANCHI);
		assertEquals(ID, employee.getId());
		assertEquals(ANDREA, employee.getName());
		assertEquals(BIANCHI, employee.getSurname());
		assertEquals(ANDREA + " " + BIANCHI, employee.toString());
	}

}
