package edu.ncsu.csc216.train_travel.tickets;

import static org.junit.Assert.*;

import java.lang.reflect.Field;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.train_travel.transportation.Train;

/**
 * Unit tests for the BicycleClass class of Reservation
 * @author Noah
 */
public class BicycleClassTest {

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
	 * Test method for newReservation()
	 */
	@Test
	public void testNewReservation() {
		Train t = new Train(4);
		
		//Try creating a reservation with too few passengers
		BicycleClass c = null;
		try {
			c = BicycleClass.newReservation(0, t);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(c);
		}
		
		//Try creating a reservation with too many passengers
		c = null;
		try {
			c = BicycleClass.newReservation(7, t);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(c);
		}
		
		//Try to create a new Reservation when the Train cannot accommodate anymore passengers
		//Lower boundary
		t.incBicyclePassengers(20);
		c = null;
		try {
			c = BicycleClass.newReservation(1, t);
		} catch (IllegalArgumentException e) {
			assertNull(c);
		}
		t.incBicyclePassengers(-20);
		
		//Middle value
		t.incBicyclePassengers(18);
		c = null;
		try {
			c = BicycleClass.newReservation(3, t);
		} catch (IllegalArgumentException e) {
			assertNull(c);
		}
		t.incBicyclePassengers(-18);
		
		//Upper boundary
		t.incBicyclePassengers(15);
		c = null;
		try {
			c = BicycleClass.newReservation(6, t);
		} catch (IllegalArgumentException e) {
			assertNull(c);
		}
		t.incBicyclePassengers(-15);
		
		//Create a reservation with min number of passengers
		c = BicycleClass.newReservation(1, t);
		assertEquals(1000, c.getID());
		assertEquals(1, c.getNumPassengers());
		assertTrue(t.hasBicycleCarRoomFor(19));
		assertFalse(t.hasBicycleCarRoomFor(20));
		
		//Create a reservation with middle number of passengers
		c = BicycleClass.newReservation(3, t);
		assertEquals(1001, c.getID());
		assertEquals(3, c.getNumPassengers());
		assertTrue(t.hasBicycleCarRoomFor(16));
		assertFalse(t.hasBicycleCarRoomFor(17));
		
		//Create a reservation with max number of passengers
		c = BicycleClass.newReservation(6, t);
		assertEquals(1002, c.getID());
		assertEquals(6, c.getNumPassengers());
		assertTrue(t.hasBicycleCarRoomFor(10));
		assertFalse(t.hasBicycleCarRoomFor(11));
	}

	/**
	 * Test method for chooseSeats()
	 */
	@Test
	public void testChooseSeats() {
		Train t = new Train(4);
		BicycleClass c = BicycleClass.newReservation(1, t);
		try {
			c.chooseSeats();
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Cannot reserve seats in a Bicycle Car", e.getMessage());
		}
	}

	/**
	 * Test method for changeSeats()
	 */
	@Test
	public void testChangeSeats() {
		Train t = new Train(4);
		BicycleClass c = BicycleClass.newReservation(1, t);
		try {
			c.changeSeats("1-1A");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Cannot reserve seats in a Bicycle Car", e.getMessage());
		}
	}

	/**
	 * Test method for cancel()
	 */
	@Test
	public void testCancel() {
		Train t = new Train(4);
		BicycleClass c = BicycleClass.newReservation(5, t);
		assertTrue(t.hasBicycleCarRoomFor(15));
		assertFalse(t.hasBicycleCarRoomFor(16));
		assertEquals(1000, c.getID());
		c.cancel();
		assertTrue(t.hasBicycleCarRoomFor(20));
		assertFalse(t.hasBicycleCarRoomFor(21));
		BicycleClass d = BicycleClass.newReservation(5, t);
		assertEquals(1001, d.getID());
	}

	/**
	 * Test method for toPrint()
	 */
	@Test
	public void testToPrint() {
		Train t = new Train(4);
		BicycleClass c = BicycleClass.newReservation(1, t);
		assertEquals("1000 Bicycle Class (1)", c.toPrint());
	}
}
