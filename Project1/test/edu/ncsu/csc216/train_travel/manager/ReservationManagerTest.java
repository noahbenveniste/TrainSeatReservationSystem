package edu.ncsu.csc216.train_travel.manager;

import static org.junit.Assert.*;

import java.lang.reflect.Field;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.train_travel.tickets.EconomyClass;
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
		//Invalid construction
		ReservationManager m1 = null;
		try {
			m1 = new ReservationManager(1);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(m1);
		}
		try {
			m1 = new ReservationManager(13);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(m1);
		}
		//Valid Construction
		ReservationManager m3 = new ReservationManager(4);
		assertEquals(4, m3.numberOfSeatMaps());
	}

	/**
	 * Test method for showMap()
	 */
	@Test
	public void testShowMap() {
		ReservationManager m = new ReservationManager(4);
		String expected1 = "\nFirst Class Car #1"
				+ "\n"
				+ "\n"
				+ "[ 1A]    [ 1B][ 1C]\n"
				+ "[ 2A]    [ 2B][ 2C]\n"
				+ "[ 3A]    [ 3B][ 3C]\n"
				+ "[ 4A]    [ 4B][ 4C]\n"
				+ "[ 5A]    [ 5B][ 5C]\n"
				+ "[ 6A]    [ 6B][ 6C]\n"
				+ "[ 7A]    [ 7B][ 7C]\n"
				+ "[ 8A]    [ 8B][ 8C]\n"
				+ "[ 9A]    [ 9B][ 9C]\n"
				+ "[10A]    [10B][10C]\n"
				+ "[11A]    [11B][11C]\n"
				+ "[12A]    [12B][12C]\n"
				+ "[13A]    [13B][13C]\n"
				+ "[14A]    [14B][14C]\n"
				+ "[15A]    [15B][15C]\n"
				+ "[16A]    [16B][16C]\n"
				+ "[17A]    [17B][17C]\n";
		assertEquals(expected1, m.showMap(0));
	}

	/**
	 * Test method for numberOfSeatMaps()
	 */
	@Test
	public void testNumberOfSeatMaps() {
		ReservationManager m = new ReservationManager(4);
		assertEquals(4, m.numberOfSeatMaps());
	}

	/**
	 * Test method for makeNewReservation()
	 */
	@Test
	public void testMakeNewReservation() {
		ReservationManager m = new ReservationManager(4);
		m.makeNewReservation(1, "Comfort");
		assertEquals("1000 Comfort Class [1-1A]\n", m.printReservationList());
		
		Reservation r1 = m.makeNewReservation(1, "e");
		r1.chooseSeats();
		assertEquals("1000 Comfort Class [1-1A]\n1001 Economy Class [3-19A]\n", m.printReservationList());
		
		try {
			m.makeNewReservation(7, "c");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid number of passengers", e.getMessage());
		}
		
		try {
			m.makeNewReservation(7, "c");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid number of passengers", e.getMessage());
		}
		
		try {
			m.makeNewReservation(7, "e");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid number of passengers", e.getMessage());
		}
		
		try {
			m.makeNewReservation(7, "b");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid number of passengers", e.getMessage());
		}
	}

	/**
	 * Test method for cancelReservation()
	 */
	@Test
	public void testCancelReservation() {
		ReservationManager m = new ReservationManager(4);
		Reservation r1 = m.makeNewReservation(1, "Economy");
		r1.chooseSeats();
		m.makeNewReservation(1, "Bicycle");
		Reservation r2 = m.makeNewReservation(1, "Economy");
		r2.chooseSeats();
		assertEquals("1000 Economy Class [3-19A]\n1001 Bicycle Class (1)\n1002 Economy Class [3-19B]\n", m.printReservationList());
		m.cancelReservation("1001");
		assertEquals("1000 Economy Class [3-19A]\n1002 Economy Class [3-19B]\n", m.printReservationList());
		try {
			m.cancelReservation("1003");
		} catch (IllegalArgumentException e) {
			assertEquals("No reservation with the specified ID exists", e.getMessage());
		}
	}

	/**
	 * Test method for changeSeats()
	 */
	@Test
	public void testChangeSeats() {
		ReservationManager m = new ReservationManager(4);
		m.makeNewReservation(1, "Economy");
		m.makeNewReservation(1, "Bicycle");
		m.makeNewReservation(1, "Economy");
		//Try changing seats for a reservation that doesn't exist
		try {
			m.changeSeats("1003", "1-1A");
		} catch (IllegalArgumentException e) {
			assertEquals("No reservation with the specified ID exists", e.getMessage());
		}
		//Try changing seats with an invalid input
		try {
			m.changeSeats("1000", "fdafdasf");
		} catch (IllegalArgumentException e) {
			assertEquals("Input is improperly formatted", e.getMessage());
		}
		//Valid change
	}

	/**
	 * Test method for printReservationList()
	 */
	@Test
	public void testPrintReservationList() {
		ReservationManager m = new ReservationManager(4);
		Reservation r1 = m.makeNewReservation(1, "Economy");
		r1.chooseSeats();
		m.makeNewReservation(1, "Bicycle");
		Reservation r2 = m.makeNewReservation(1, "Economy");
		r2.chooseSeats();
		assertEquals("1000 Economy Class [3-19A]\n1001 Bicycle Class (1)\n1002 Economy Class [3-19B]\n", m.printReservationList());
	}
}
