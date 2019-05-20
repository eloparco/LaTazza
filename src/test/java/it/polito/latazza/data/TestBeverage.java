package it.polito.latazza.data;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.polito.latazza.exceptions.BeverageException;
import it.polito.latazza.exceptions.NotEnoughCapsules;

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
	void tc1() {
		// initially -> box price: 5 euro, 20 capsules per box
		try {
			// add 2 new boxes of capsules
			b.increaseAvailableCapsules(2);
			assertEquals(40, b.getAvailableCapsules());
		} catch (BeverageException e) {
			fail();
		}
	}
	
	@Test
	void tc2() {
		// initially -> box price: 5 euro, 20 capsules per box
		try {
			// add 10 new boxes of capsules, now 200 available capsules
			b.increaseAvailableCapsules(10);
			// cause overflow adding too much boxes
			b.increaseAvailableCapsules((Integer.MAX_VALUE - 100) / 20);
			fail();
		} catch (BeverageException e) {
			
		}
	}
	
	@Test
	void tc3() {
		// initially -> box price: 5 euro, 20 capsules per box
		try {
			b.increaseAvailableCapsules(-10);
			fail();
		} catch (BeverageException e) {
			
		}
	}
	
	@Test
	void tc4() {
		// initially -> box price: 5 euro, 20 capsules per box
		try {
			// add 2 new boxes of capsules, now 40 capusles available
			b.increaseAvailableCapsules(2);
			b.decreaseAvailableCapsules(10);
			assertEquals(30, b.getAvailableCapsules());
		} catch (NotEnoughCapsules e) {
			fail();
		} catch (BeverageException e) {
			fail();
		}
	}
	
	@Test
	void tc5() {
		// initially -> box price: 5 euro, 20 capsules per box
		try {
			// add 2 new boxes of capsules, now 40 capusles available
			b.increaseAvailableCapsules(2);
			b.decreaseAvailableCapsules(50);
			fail();
		} catch (NotEnoughCapsules e) {

		} catch (BeverageException e) {
			fail();
		}
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
	void tcSetters() {
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