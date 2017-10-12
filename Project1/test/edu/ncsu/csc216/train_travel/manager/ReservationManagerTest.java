package edu.ncsu.csc216.train_travel.manager;

import static org.junit.Assert.*;

import java.lang.reflect.Field;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.train_travel.tickets.Reservation;

/**
 * Unit tests for the ReservationManager class
 * @author Noah Benveniste
 */
public class ReservationManagerTest {

	/**
	 * Resets the Reservation ID number to 1000 for the beginning of each test so this value
	 * will increment in a predictable fashion
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	@Before
	public void setup() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		//Source for this code: https://stackoverflow.com/questions/40515021/resetting-a-private-static-int-from-a-junit-test-class
		Field number = Reservation.class.getDeclaredField("number");
	    number.setAccessible(true); //to overcome the visibility issue
	    number.setInt(null, 1000); //null since it's static, reset back to 1000 for each test so Reservation IDs increment in a predictable manner
	}
	
	/**
	 * Test method for constructor
	 */
	@Test
	public void testReservationManager() {
		//fail();
	}

	/**
	 * Test method for showMap()
	 */
	@Test
	public void testShowMap() {
		//fail("Not yet implemented");
	}

	/**
	 * Test method for numberOfSeatMaps()
	 */
	@Test
	public void testNumberOfSeatMaps() {
		//fail("Not yet implemented");
	}

	/**
	 * Test method for makeNewReservation()
	 */
	@Test
	public void testMakeNewReservation() {
		//fail("Not yet implemented");
	}

	/**
	 * Test method for cancelReservation()
	 */
	@Test
	public void testCancelReservation() {
		//fail("Not yet implemented");
	}

	/**
	 * Test method for changeSeats()
	 */
	@Test
	public void testChangeSeats() {
		//fail("Not yet implemented");
	}

	/**
	 * Test method for printReservationList()
	 */
	@Test
	public void testPrintReservationList() {
		ReservationManager m = new ReservationManager(4);
		m.makeNewReservation(1, "Comfort");
		m.makeNewReservation(2, "Comfort");
		System.out.println(m.printReservationList());
	}
}
