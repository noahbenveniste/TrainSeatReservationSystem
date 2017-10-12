package edu.ncsu.csc216.train_travel.tickets;

import static org.junit.Assert.*;

import java.lang.reflect.Field;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.train_travel.transportation.Seat;
import edu.ncsu.csc216.train_travel.transportation.Train;

/**
 * Unit tests for the ComfortClass class of Reservation
 * @author Noah Benveniste
 */
public class ComfortClassTest {
	
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
	 * Test method for cancel()
	 */
	@Test
	public void testCancel() {
		Train t = new Train(4);
		
		ComfortClass c1 = ComfortClass.newReservation(1, t);
		assertEquals(1000, c1.getID());
		c1.chooseSeats();
		assertEquals(50, t.openFirstClassSeats());
		c1.cancel();
		assertEquals(51, t.openFirstClassSeats());
		
		ComfortClass c2 = ComfortClass.newReservation(6, t);
		assertEquals(1001, c2.getID());
		c2.chooseSeats();
		assertEquals(45, t.openFirstClassSeats());
		c2.cancel();
		assertEquals(51, t.openFirstClassSeats());
	}

	/**
	 * Test method for chooseSeats()
	 */
	@Test
	public void testChooseSeats() {
		Train t = new Train(7); //Has two FirstClassCars
		ComfortClass c1 = ComfortClass.newReservation(1, t);
		assertEquals(1000, c1.getID());
		c1.chooseSeats();
		assertEquals("[1-1A]", c1.toPrint());
		
		ComfortClass c2 = ComfortClass.newReservation(2, t);
		assertEquals(1001, c2.getID());
		c2.chooseSeats();
		assertEquals("[1-1B,1-1C]", c2.toPrint());
		
		ComfortClass c3 = ComfortClass.newReservation(6, t);
		assertEquals(1002, c3.getID());
		c3.chooseSeats();
		assertEquals("[2-16A,2-16B,2-16C,2-17A,2-17B,2-17C]", c3.toPrint());
		
		assertTrue(t.hasComfortClassRoomFor(93));
		assertFalse(t.hasComfortClassRoomFor(94));
		
		ComfortClass c4 = ComfortClass.newReservation(1, t);
		assertEquals(1003, c4.getID());
		c4.chooseSeats();
		assertEquals("[1-2A]", c4.toPrint());
		
		ComfortClass c5 = ComfortClass.newReservation(2, t);
		assertEquals(1004, c5.getID());
		c5.chooseSeats();
		assertEquals("[1-2B,1-2C]", c5.toPrint());
		
		//Reserve all A seats in first FirstClassCar, first A seat in the next car should be reserved.
		t.getSeatFor(0, 2, 0).reserve();
		t.getSeatFor(0, 3, 0).reserve();
		t.getSeatFor(0, 4, 0).reserve();
		t.getSeatFor(0, 5, 0).reserve();
		t.getSeatFor(0, 6, 0).reserve();
		t.getSeatFor(0, 7, 0).reserve();
		t.getSeatFor(0, 8, 0).reserve();
		t.getSeatFor(0, 9, 0).reserve();
		t.getSeatFor(0, 10, 0).reserve();
		t.getSeatFor(0, 11, 0).reserve();
		t.getSeatFor(0, 12, 0).reserve();
		t.getSeatFor(0, 13, 0).reserve();
		t.getSeatFor(0, 14, 0).reserve();
		t.getSeatFor(0, 15, 0).reserve();
		t.getSeatFor(0, 16, 0).reserve();
		
		ComfortClass c6 = ComfortClass.newReservation(1, t);
		assertEquals(1005, c6.getID());
		c6.chooseSeats();
		assertEquals("[2-1A]", c6.toPrint());
		
		//Reserve all A seats in both FCCs, default seating method should execute.
		//2-15B should be the next available seat
		t.getSeatFor(1, 1, 0).reserve();
		t.getSeatFor(1, 2, 0).reserve();
		t.getSeatFor(1, 3, 0).reserve();
		t.getSeatFor(1, 4, 0).reserve();
		t.getSeatFor(1, 5, 0).reserve();
		t.getSeatFor(1, 6, 0).reserve();
		t.getSeatFor(1, 7, 0).reserve();
		t.getSeatFor(1, 8, 0).reserve();
		t.getSeatFor(1, 9, 0).reserve();
		t.getSeatFor(1, 10, 0).reserve();
		t.getSeatFor(1, 11, 0).reserve();
		t.getSeatFor(1, 12, 0).reserve();
		t.getSeatFor(1, 13, 0).reserve();
		t.getSeatFor(1, 14, 0).reserve();
		t.getSeatFor(1, 15, 0).reserve();
		t.getSeatFor(1, 16, 0).reserve();
		
		ComfortClass c7 = ComfortClass.newReservation(1, t);
		assertEquals(1006, c7.getID());
		c7.chooseSeats();
		assertEquals("[2-15B]", c7.toPrint());
		
		//Assign seats so that no B/C row has two free seats in the first FCC
		t.getSeatFor(0, 2, 1).reserve(); //Row 3 has its B reserved
		t.getSeatFor(0, 3, 2).reserve(); //Row 4 has its C reserved
		t.getSeatFor(0, 4, 1).reserve();
		t.getSeatFor(0, 5, 1).reserve();
		t.getSeatFor(0, 6, 1).reserve();
		t.getSeatFor(0, 7, 2).reserve();
		t.getSeatFor(0, 8, 1).reserve();
		t.getSeatFor(0, 9, 1).reserve();
		t.getSeatFor(0, 10, 2).reserve();
		t.getSeatFor(0, 11, 1).reserve();
		t.getSeatFor(0, 11, 2).reserve();
		t.getSeatFor(0, 12, 2).reserve();
		t.getSeatFor(0, 13, 1).reserve();
		t.getSeatFor(0, 14, 2).reserve();
		t.getSeatFor(0, 15, 1).reserve();
		t.getSeatFor(0, 16, 1).reserve();
		t.getSeatFor(0, 16, 2).reserve();
		
		//System.out.print(t.getCarSeatMap(0) + "\n\n" + t.getCarSeatMap(1));
		
		ComfortClass c8 = ComfortClass.newReservation(2, t);
		c8.chooseSeats();
		assertEquals("[2-1B,2-1C]", c8.toPrint());
		
		ComfortClass c9 = ComfortClass.newReservation(2, t);
		c9.chooseSeats();
		assertEquals("[2-2B,2-2C]", c9.toPrint());
		
		//System.out.print(t.getCarSeatMap(0) + "\n\n" + t.getCarSeatMap(1));
		
		//Assign randomly B and C seats in the second FCC so 2 passenger preferred seating is not possible
		t.getSeatFor(1, 2, 1).reserve(); //Row 3 has its B reserved
		t.getSeatFor(1, 3, 2).reserve(); //Row 4 has its C reserved
		t.getSeatFor(1, 4, 1).reserve();
		t.getSeatFor(1, 5, 1).reserve();
		t.getSeatFor(1, 6, 1).reserve();
		t.getSeatFor(1, 7, 2).reserve();
		t.getSeatFor(1, 8, 1).reserve();
		t.getSeatFor(1, 9, 1).reserve();
		t.getSeatFor(1, 10, 2).reserve();
		t.getSeatFor(1, 11, 1).reserve();
		t.getSeatFor(1, 11, 2).reserve();
		t.getSeatFor(1, 12, 2).reserve();
		t.getSeatFor(1, 13, 1).reserve();
		t.getSeatFor(1, 14, 2).reserve();
		
		ComfortClass c10 = ComfortClass.newReservation(2, t);
		c10.chooseSeats();
		assertEquals("[2-13B,2-14C]", c10.toPrint());
		
		
		//Reserve more seats
		ComfortClass c11 = ComfortClass.newReservation(6, t);
		c11.chooseSeats();
		c11.toPrint();
		assertEquals("[2-6C,2-7C,2-8B,2-9C,2-10C,2-11B]", c11.toPrint());
		assertEquals(16, t.openFirstClassSeats());
		
		ComfortClass c12 = ComfortClass.newReservation(4, t);
		c12.chooseSeats();
		assertEquals("[1-16C,2-3C,2-4B,2-5C]", c12.toPrint());
		assertEquals(12, t.openFirstClassSeats());
		
		ComfortClass c13 = ComfortClass.newReservation(6, t);
		c13.chooseSeats();
		assertEquals("[1-9C,1-10C,1-11B,1-13B,1-14C,1-15B]", c13.toPrint());
		assertEquals(6, t.openFirstClassSeats());
		
		ComfortClass c14 = ComfortClass.newReservation(1, t);
		c14.chooseSeats();
		assertEquals("[1-8B]", c14.toPrint());
		assertEquals(5, t.openFirstClassSeats());
		
		//Attempt to add 6 passengers when there are only 5 seats left
		try {
			ComfortClass c15 = ComfortClass.newReservation(6, t);
			c15.chooseSeats();
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Not enough open seats", e.getMessage());
		}
		
		//Add 3 more passengers
		ComfortClass c16 = ComfortClass.newReservation(3, t);
		c16.chooseSeats();
		
		//Attempt to add 3 passengers when there are only 2 seats left
		try {
			ComfortClass c17 = ComfortClass.newReservation(3, t);
			c17.chooseSeats();
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Not enough open seats", e.getMessage());
		}
		
		//Add 2 more passengers
		ComfortClass c18 = ComfortClass.newReservation(2, t);
		c18.chooseSeats();
		
		//Attempt to add 1 more passenger when there are no seats left
		try {
			ComfortClass c19 = ComfortClass.newReservation(1, t);
			c19.chooseSeats();
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Not enough open seats", e.getMessage());
		}
	}

	/**
	 * Test method for changeSeats()
	 */
	@Test
	public void testChangeSeats() {
		Train t = new Train(4);
		ComfortClass c = ComfortClass.newReservation(1, t);
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

		invalid = "2-1A";
		try {
			c.changeSeats(invalid);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Non-first class seat entered", e.getMessage());
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
		
		invalid = "1-1D";
		try {
			c.changeSeats(invalid);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid seat label for car", e.getMessage());
		}
		
		invalid = "1-2A,1-1A";
		try {
			c.changeSeats(invalid);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("There are 1 passengers but 2 seats were entered", e.getMessage());
		}
		
		Train t1 = new Train(7);
		ComfortClass c1 = ComfortClass.newReservation(2, t1);
		c1.chooseSeats();
		
		invalid = "1-2A,3-2A";
		try {
			c1.changeSeats(invalid);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Non-first class seat entered", e.getMessage());
		}
		
		invalid = "1-1A,99-99A";
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
		
		t1.getSeatFor(0, "1A").reserve();
		invalid = "1-1A,1-2A";
		try {
			c1.changeSeats(invalid);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("One or more of the seats entered are already reserved", e.getMessage());
		}
		
		t1.getSeatFor(0, "1A").release();
		t1.getSeatFor(0, "2A").reserve();
		invalid = "1-1A,1-2A";
		try {
			c1.changeSeats(invalid);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("One or more of the seats entered are already reserved", e.getMessage());
		}
		
		//Valid reassignment
		c1.changeSeats("2-2B,1-17A");
		assertEquals("[1-17A,2-2B]", c1.toPrint());
		
	}

	/**
	 * Test method for toPrint()
	 */
	@Test
	public void testToPrint() {
		Train t = new Train(4);
		ComfortClass c = null;
		
		c = ComfortClass.newReservation(1, t);
		c.chooseSeats();
		assertEquals("[1-1A]", c.toPrint());
		
		c = ComfortClass.newReservation(4, t);
		c.chooseSeats();
		assertEquals("[1-16A,1-17A,1-17B,1-17C]", c.toPrint());
	}
}
