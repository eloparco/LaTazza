package it.polito.latazza.data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Locale;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.polito.latazza.exceptions.BeverageException;

/**
 * Integration tests for class BoxPurchase + Beverage
 * 
 * Since the used technique for integration is incremental bottom up
 * (i.e. unit tests have been done previously on units this class depends on),
 * no stubs are used.
 * 
 * @author s267563
 *
 */
class TestBoxPurchase {
	Beverage b;
	BoxPurchase p;
	String s;
	
	@BeforeEach
	void setUp() throws Exception {
		b = new Beverage(1, "tea", 10, 10); 
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	/*
	 *		BLACK BOX
	 */
	
	@Test
	void tc1() {
		try {
			new BoxPurchase(b, 10);
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	void tc2() {
		try {
			new BoxPurchase(b, -1);
			fail();
		} catch (BeverageException e) {
		}
	}
	
	@Test
	void tc3() {
		try {
			new BoxPurchase(null, 10);
			fail();
		} catch (BeverageException e) {
		}
	}

	/*
	 *		WHITE BOX
	 */
	
	@Test
	void tcToStringFormat() {
		try {
			p = new BoxPurchase(b, 10);
			s = p.toString();
			assertEquals(" BUY tea 10", s.substring(19, s.length()));
		} catch (BeverageException e) {
			fail();
		}
	}

}