package it.polito.latazza.data;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.polito.latazza.exceptions.EmployeeException;

class TestEmployee {
	Employee e;
	
	@BeforeEach
	void setUp() throws Exception {
		e = new Employee(1, "Mario", "Rossi");
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void tc1() {
		try {
			e.increaseBalance(10);
			assertEquals(10, e.getBalance());
		} catch (EmployeeException e1) {
			fail("Exception generated");
		}
	}

	@Test
	void tc2() {
		try {
			e.increaseBalance(10);
			e.increaseBalance(Integer.MAX_VALUE);
			fail("Exception not generated");
		} catch (EmployeeException e1) {
			assertTrue(true);
		}
	}
	
	@Test
	void tc3() {
		try {
			e.increaseBalance(-10);
			fail("Exception not generated");
		} catch (EmployeeException e) {
			assertTrue(true);
		}
	}
	
	@Test
	void tc4() {
		try {
			e.decreaseBalance(10);
			assertEquals(-10, e.getBalance());
		} catch (EmployeeException e1) {
			fail("Exception generated");
		}
	}
	
	@Test
	void tc5() {
		try {
			e.decreaseBalance(10);
			e.decreaseBalance(Integer.MAX_VALUE);
			fail("Exception not generated");
		} catch (EmployeeException e1) {
			assertTrue(true);
		}
	}
	
	@Test
	void tc6() {
		try {
			e.decreaseBalance(-10);
			fail("Exception not generated");
		} catch (EmployeeException e) {
			assertTrue(true);
		}
	}

}
