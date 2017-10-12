package edu.ncsu.csc216.train_travel.tickets;

import static org.junit.Assert.*;

import java.lang.reflect.Field;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.train_travel.transportation.Train;

/**
 * Unit tests for the EconomyClass class of Reservation
 * @author Noah Benveniste
 */
public class EconomyClassTest {

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
		assertTrue(t.hasEconomyClassRoomFor(186));
		assertFalse(t.hasEconomyClassRoomFor(187));
		
		//Try creating a reservation with too few passengers
		EconomyClass c = null;
		try {
			c = EconomyClass.newReservation(0, t);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(c);
		}
		
		//Try creating a reservation with too many passengers
		c = null;
		try {
			c = EconomyClass.newReservation(7, t);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(c);
		}
		
		//Try to create a new Reservation when the Train cannot accommodate anymore passengers
		//Lower boundary
		t.incEconomyClassPassengers(187);
		c = null;
		try {
			c = EconomyClass.newReservation(1, t);
		} catch (IllegalArgumentException e) {
			assertNull(c);
		}
		t.incEconomyClassPassengers(-187);
		
		//Middle value
		t.incEconomyClassPassengers(184);
		c = null;
		try {
			c = EconomyClass.newReservation(3, t);
		} catch (IllegalArgumentException e) {
			assertNull(c);
		}
		t.incEconomyClassPassengers(-184);
		
		//Upper boundary
		t.incEconomyClassPassengers(181);
		c = null;
		try {
			c = EconomyClass.newReservation(6, t);
		} catch (IllegalArgumentException e) {
			assertNull(c);
		}
		t.incEconomyClassPassengers(-181);
		
		//Create a reservation with min number of passengers
		c = EconomyClass.newReservation(1, t);
		assertEquals(1000, c.getID());
		assertEquals(1, c.getNumPassengers());
		assertTrue(t.hasEconomyClassRoomFor(185));
		assertFalse(t.hasEconomyClassRoomFor(186));
		
		//Create a reservation with middle number of passengers
		c = EconomyClass.newReservation(3, t);
		assertEquals(1001, c.getID());
		assertEquals(3, c.getNumPassengers());
		assertTrue(t.hasEconomyClassRoomFor(182));
		assertFalse(t.hasEconomyClassRoomFor(183));
		
		//Create a reservation with max number of passengers
		c = EconomyClass.newReservation(6, t);
		assertEquals(1002, c.getID());
		assertEquals(6, c.getNumPassengers());
		assertTrue(t.hasEconomyClassRoomFor(176));
		assertFalse(t.hasEconomyClassRoomFor(177));
	}

	/**
	 * Test method for cancel()
	 */
	@Test
	public void testCancel() {
		Train t = new Train(4);
		
		EconomyClass c1 = EconomyClass.newReservation(1, t);
		c1.chooseSeats();
		assertEquals(1000, c1.getID());
		assertEquals(151, t.openSecondClassSeats());
		assertTrue(t.hasEconomyClassRoomFor(185));
		assertFalse(t.hasEconomyClassRoomFor(186));
		c1.cancel();
		assertEquals(152, t.openSecondClassSeats());
		assertTrue(t.hasEconomyClassRoomFor(186));
		assertFalse(t.hasEconomyClassRoomFor(187));
		
		
		EconomyClass c2 = EconomyClass.newReservation(6, t);
		c2.chooseSeats();
		assertEquals(1001, c2.getID());
		assertEquals(146, t.openSecondClassSeats());
		assertTrue(t.hasEconomyClassRoomFor(180));
		assertFalse(t.hasEconomyClassRoomFor(181));
		c2.cancel();
		assertEquals(152, t.openSecondClassSeats());
		assertTrue(t.hasEconomyClassRoomFor(186));
		assertFalse(t.hasEconomyClassRoomFor(187));
	}

	/**
	 * Test method for chooseSeats()
	 */
	@Test
	public void testChooseSeats() {
		Train t = new Train(4); //Has two second class cars
		EconomyClass c = EconomyClass.newReservation(1, t);
		c.chooseSeats();
		assertEquals(1000, c.getID());
		assertEquals("1000 Economy Class [3-19A]", c.toPrint());
		assertEquals(151, t.openSecondClassSeats());
		
		EconomyClass c1 = EconomyClass.newReservation(6, t);
		c1.chooseSeats();
		assertEquals(1001, c1.getID());
		assertEquals("1001 Economy Class [3-18A,3-18B,3-18C,3-19B,3-19C,3-19D]", c1.toPrint());
	}

	/**
	 * Test method for changeSeats()
	 */
	@Test
	public void testChangeSeats() {
		Train t = new Train(4);
		EconomyClass c = EconomyClass.newReservation(1, t);
		c.chooseSeats();
		String invalid;
		
		invalid = "";
		try {
			c.changeSeats(invalid);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("There are 1 passengers but 0 seats were entered", e.getMessage());
		}
		
		invalid = "fsdafdasfsa";
		try {
			c.changeSeats(invalid);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Input is improperly formatted", e.getMessage());
		}
		
		invalid = "999-999A";
		try {
			c.changeSeats(invalid);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Input is improperly formatted", e.getMessage());
		}
		
		invalid = "999-99A";
		try {
			c.changeSeats(invalid);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Input is improperly formatted", e.getMessage());
		}
		
		invalid = "99-999A";
		try {
			c.changeSeats(invalid);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Input is improperly formatted", e.getMessage());
		}
		
		invalid = "99-99A";
		try {
			c.changeSeats(invalid);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid car number", e.getMessage());
		}

		invalid = "1-1A";
		try {
			c.changeSeats(invalid);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Non-second class seat entered", e.getMessage());
		}
		
		invalid = "2-99A";
		try {
			c.changeSeats(invalid);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid seat label for car", e.getMessage());
		}
		
		invalid = "1-99A";
		try {
			c.changeSeats(invalid);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid seat label for car", e.getMessage());
		}
		
		invalid = "2-2A,2-1A";
		try {
			c.changeSeats(invalid);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("There are 1 passengers but 2 seats were entered", e.getMessage());
		}
		
		Train t1 = new Train(7);
		EconomyClass c1 = EconomyClass.newReservation(2, t1);
		c1.chooseSeats();
		
		invalid = "1-2A,3-2A";
		try {
			c1.changeSeats(invalid);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Non-second class seat entered", e.getMessage());
		}
		
		invalid = "2-1A,99-99A";
		try {
			c1.changeSeats(invalid);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid car number", e.getMessage());
		}
		
		invalid = "1-1A,1-99A";
		try {
			c1.changeSeats(invalid);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid seat label for car", e.getMessage());
		}
		
		t1.getSeatFor(2, "1A").reserve();
		invalid = "3-1A,3-2A";
		try {
			c1.changeSeats(invalid);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("One or more of the seats entered are already reserved", e.getMessage());
		}
		
		t1.getSeatFor(2, "1A").release();
		t1.getSeatFor(2, "2A").reserve();
		invalid = "3-1A,3-2A";
		try {
			c1.changeSeats(invalid);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("One or more of the seats entered are already reserved", e.getMessage());
		}
		
		//Valid reassignment
		c1.changeSeats("4-2B,3-17A");
		assertEquals("1001 Economy Class [3-17A,4-2B]", c1.toPrint());
	}

	/**
	 * Test method for toPrint()
	 */
	@Test
	public void testToPrint() {
		Train t = new Train(4);
		EconomyClass c = null;
		
		c = EconomyClass.newReservation(1, t);
		c.chooseSeats();
		assertEquals("1000 Economy Class [3-19A]", c.toPrint());
		
		c = EconomyClass.newReservation(4, t);
		c.chooseSeats();
		assertEquals("1001 Economy Class [3-18A,3-19B,3-19C,3-19D]", c.toPrint());
	}
}
