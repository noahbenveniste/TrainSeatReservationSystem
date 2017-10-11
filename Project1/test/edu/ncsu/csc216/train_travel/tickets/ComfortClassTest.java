package edu.ncsu.csc216.train_travel.tickets;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.train_travel.transportation.Train;

/**
 * Unit tests for the ComfortClass class of Reservation
 * @author Noah Benveniste
 */
public class ComfortClassTest {

	/**
	 * Test method for newReservation()
	 */
	@Test
	public void testNewReservation() {
		Train t = new Train(4);
		
		//Try creating a reservation with too few passengers
		ComfortClass c = null;
		try {
			c = ComfortClass.newReservation(0, t);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(c);
		}
		
		//Try creating a reservation with too many passengers
		c = null;
		try {
			c = ComfortClass.newReservation(7, t);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(c);
		}
		
		//Try to create a new Reservation when the Train cannot accommodate anymore passengers
		//Lower boundary
		t.incComfortClassPassengers(51);
		c = null;
		try {
			c = ComfortClass.newReservation(1, t);
		} catch (IllegalArgumentException e) {
			assertNull(c);
		}
		t.incComfortClassPassengers(-51);
		
		//Middle value
		t.incComfortClassPassengers(49);
		c = null;
		try {
			c = ComfortClass.newReservation(3, t);
		} catch (IllegalArgumentException e) {
			assertNull(c);
		}
		t.incComfortClassPassengers(-49);
		
		//Upper boundary
		t.incComfortClassPassengers(46);
		c = null;
		try {
			c = ComfortClass.newReservation(6, t);
		} catch (IllegalArgumentException e) {
			assertNull(c);
		}
		t.incComfortClassPassengers(-46);
		
		//Create a reservation with min number of passengers
		c = ComfortClass.newReservation(1, t);
		assertEquals(1000, c.getID());
		assertEquals(1, c.getNumPassengers());
		assertTrue(t.hasComfortClassRoomFor(50));
		assertFalse(t.hasComfortClassRoomFor(51));
		
		//Create a reservation with middle number of passengers
		c = ComfortClass.newReservation(3, t);
		assertEquals(1001, c.getID());
		assertEquals(3, c.getNumPassengers());
		assertTrue(t.hasComfortClassRoomFor(47));
		assertFalse(t.hasComfortClassRoomFor(48));
		
		//Create a reservation with max number of passengers
		c = ComfortClass.newReservation(6, t);
		assertEquals(1002, c.getID());
		assertEquals(6, c.getNumPassengers());
		assertTrue(t.hasComfortClassRoomFor(41));
		assertFalse(t.hasComfortClassRoomFor(42));
	}

	/**
	 * Test method for toPrint()
	 */
	@Test
	public void testToPrint() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for cancel()
	 */
	@Test
	public void testCancel() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for chooseSeats()
	 */
	@Test
	public void testChooseSeats() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for changeSeats()
	 */
	@Test
	public void testChangeSeats() {
		fail();
	}
}
