package it.polito.latazza.data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Locale;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.polito.latazza.exceptions.BeverageException;
import it.polito.latazza.exceptions.EmployeeException;
import it.polito.latazza.exceptions.NotEnoughCapsules;

/**
 * Unit tests for class Beverage
 * 
 * @author s261072
 *
 */
class TestBeverage {

	Beverage b;
	
	@BeforeEach
	void setUp() throws Exception {
		// box price: 5 euro, 20 capsules per box
		b = new Beverage(1, "Coffee", 500, 20);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	/*
	 *		BLACK BOX
	 */
	
	@Test
	void tc1() throws BeverageException {
		// add 2 new boxes of capsules
		b.increaseAvailableCapsules(2); 
		assertEquals(40, b.getAvailableCapsules());
	}
	
	@Test
	void tc2() throws BeverageException {
		// add 10 new boxes of capsules, now 200 available capsules
		b.increaseAvailableCapsules(10); 
		// cause overflow adding too much boxes
		assertThrows(BeverageException.class, () -> b.increaseAvailableCapsules((Integer.MAX_VALUE - 100) / 20));
	}
	
	@Test
	void tc3() {
		assertThrows(BeverageException.class, () -> b.increaseAvailableCapsules(-10));
	}
	
	@Test
	void tc4() throws BeverageException, NotEnoughCapsules {
		// add 2 new boxes of capsules, now 40 capusles available
		b.increaseAvailableCapsules(2);
		b.decreaseAvailableCapsules(10);
		assertEquals(30, b.getAvailableCapsules());
	}
	
	@Test
	void tc5() throws BeverageException {
		// add 2 new boxes of capsules, now 40 capusles available
		b.increaseAvailableCapsules(2);
		assertThrows(NotEnoughCapsules.class, () -> b.decreaseAvailableCapsules(50));
	}
	
	@Test
	void tc6() {
		// initially -> box price: 5 euro, 20 capsules per box
		try {
			// add 2 new boxes of capsules, now 40 capusles available
			b.increaseAvailableCapsules(2);
			b.decreaseAvailableCapsules(-5);
			fail();
		} catch (NotEnoughCapsules e) {
			fail();
		} catch (BeverageException e) {
			
		}
	}
	
	@Test
	void tc7() {
		// initially -> box price: 5 euro, 20 capsules per box
		try {
			// add 2 new boxes of capsules, now 40 capusles available
			b.increaseAvailableCapsules(2);
			b.decreaseAvailableCapsules(- (Integer.MAX_VALUE - 20));
			fail();
		} catch (NotEnoughCapsules e) {
			fail();
		} catch (BeverageException e) {
			
		}
	}
	
	// boundary: availableCapsules + numberOfBoxes * capsulesPerBox = MAXINT
	@Test
	void tc8() {
		try {
			// force available capsules = MAXINT
			// box price: 50 euro, MAXINT capsules per box
			Beverage b = new Beverage(1, "Coffee", 5000, Integer.MAX_VALUE);
			b.increaseAvailableCapsules(1);
			assertEquals(Integer.MAX_VALUE, b.getAvailableCapsules());
		} catch (BeverageException e) {
			fail();
		}
	}
	
	/*
	 *		WHITE BOX
	 */
	
	@Test
	// test constructor
	void tcConstructor() {
		try {
			// negative values not allowed
			new Beverage(-1, "Coffee", -500, -20);
			fail();
		} catch (BeverageException e) {
			
		}
		
	}
	
	@Test
	// test getters
	void tcGetters() {
		// b = new Beverage(1, "Coffee", 500, 20);
		assertEquals(1, b.getId());
		assertEquals("Coffee", b.getName());
		assertEquals(500, b.getBoxPrice());
		assertEquals(20, b.getCapsulesPerBox());
		assertEquals(25, b.getCapsulesPrice());
		assertEquals("Coffee", b.toString());
	}
	
	@Test
	// test setters
	void tcSetters() throws BeverageException {
		b.setName("Black Tea");
		assertEquals("Black Tea", b.getName());
		try {
			b.setBoxPrice(700);
		} catch (Exception e) {
			fail();
		}
		try {
			b.setBoxPrice(-5);
			fail();
		} catch (Exception e) {
		}
		assertEquals(700, b.getBoxPrice());
		try {
			b.setCapsulesPerBox(25);
		} catch (Exception e) {
			fail();
		}
		try {
			b.setCapsulesPerBox(-25);
			fail();
		} catch (Exception e) {
		}
		assertEquals(25, b.getCapsulesPerBox());
	}

}