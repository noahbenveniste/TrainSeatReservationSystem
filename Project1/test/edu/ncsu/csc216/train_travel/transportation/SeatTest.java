package edu.ncsu.csc216.train_travel.transportation;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Unit tests for the methods of the Seat class
 * @author Noah Benveniste
 */
public class SeatTest {
	
	private static final String LABEL = "1A";
	private static final int CAR_NUMBER = 1;
	private static final String ACTUAL_STRING = "1-1A";
	private static final String ACTUAL_STRING_LIST = "[1-1A,1-1B,1-1C]";
	
	/**
	 * Test method for the Seat constructor
	 */
	@Test
	public void testSeat() {
		//Test the construction of a valid Seat
		Seat s = new Seat(LABEL, CAR_NUMBER);
		assertEquals(LABEL, s.getLabel());
		assertEquals(CAR_NUMBER, s.getTrainCarNumber());
		assertFalse(s.isReserved());
	}

	/**
	 * Test method for reserve()
	 */
	@Test
	public void testReserve() {
		Seat s = new Seat(LABEL, CAR_NUMBER);
		assertFalse(s.isReserved());
		s.reserve();
		assertTrue(s.isReserved());
	}

	/**
	 * Test method for release()
	 */
	@Test
	public void testRelease() {
		Seat s = new Seat(LABEL, CAR_NUMBER);
		assertFalse(s.isReserved());
		s.reserve();
		assertTrue(s.isReserved());
		s.release();
		assertFalse(s.isReserved());
	}

	/**
	 * Test method for isReserved()
	 */
	@Test
	public void testIsReserved() {
		Seat s = new Seat(LABEL, CAR_NUMBER);
		assertFalse(s.isReserved());
		s.reserve();
		assertTrue(s.isReserved());
		s.release();
		assertFalse(s.isReserved());
	}

	/**
	 * Test method for getTrainCarNumber()
	 */
	@Test
	public void testGetTrainCarNumber() {
		Seat s = new Seat(LABEL, CAR_NUMBER);
		assertEquals(CAR_NUMBER, s.getTrainCarNumber());
	}

	/**
	 * Test method for getLabel()
	 */
	@Test
	public void testGetLabel() {
		Seat s = new Seat(LABEL, CAR_NUMBER);
		assertEquals(LABEL, s.getLabel());
	}

	/**
	 * Test method for toString()
	 */
	@Test
	public void testToString() {
		Seat s = new Seat(LABEL, CAR_NUMBER);
		assertEquals(ACTUAL_STRING, s.toString());
	}

	/**
	 * Test method for printListOfSeats()
	 */
	@Test
	public void testPrintListOfSeats() {
		Seat[] seats = new Seat[3];
		seats[0] = new Seat(LABEL, CAR_NUMBER);
		seats[1] = new Seat("1B", 1);
		seats[2] = new Seat("1C", 1);
		assertEquals(ACTUAL_STRING_LIST, Seat.printListOfSeats(seats));
	}

	/**
	 * Test method for compareTo()
	 */
	@Test
	public void testCompareTo() {
		Seat s1 = new Seat(LABEL, CAR_NUMBER);
		Seat s2 = new Seat(LABEL, CAR_NUMBER + 1);
		Seat s3 = new Seat("2A", CAR_NUMBER);
		Seat s4 = new Seat("1B", CAR_NUMBER);
		
		//Compare two seats in different cars
		assertTrue(s1.compareTo(s2) < 0);
		assertTrue(s2.compareTo(s1) > 0);
		//Compare two seats in the same car but different rows
		assertTrue(s1.compareTo(s3) < 0);
		assertTrue(s3.compareTo(s1) > 0);
		//Compare two seats in the same row
		assertTrue(s1.compareTo(s4) < 0);
		assertTrue(s4.compareTo(s1) > 0);
	}

	/**
	 * Test method for hashCode()
	 */
	@Test
	public void testHashCode() {
		Seat s1 = new Seat(LABEL, CAR_NUMBER);
		Seat s2 = new Seat(LABEL, CAR_NUMBER + 1);
		Seat s3 = new Seat("1A", 1);
		
		//Test that two equal objects have the same hash code
		assertEquals(s1.hashCode(), s3.hashCode());
		//Test that two unequal objects have different hash codes
		assertNotEquals(s1.hashCode(), s2.hashCode());
	}
	
	/**
	 * Test method for equals()
	 */
	@Test
	public void testEquals() {
		Seat s1 = new Seat(LABEL, CAR_NUMBER);
		Seat s2 = new Seat(LABEL, CAR_NUMBER + 1);
		Seat s3 = new Seat("2A", CAR_NUMBER);
		Seat s4 = new Seat("1A", 1);
		Seat s5 = new Seat(LABEL, CAR_NUMBER);
		s5.reserve();
		Seat s6 = new Seat("3C", 2);
		s6.reserve();
		
		//Test that a Seat equals itself
		assertTrue(s1.equals(s1));
		//Test that two seats with the same fields are equal
		assertTrue(s1.equals(s4));
		assertTrue(s4.equals(s1));
		//Test that two seats with different car numbers are not equal
		assertFalse(s1.equals(s2));
		assertFalse(s2.equals(s1));
		//Test that two seats with different labels are not equal
		assertFalse(s1.equals(s3));
		assertFalse(s3.equals(s1));
		//Test that two seats with different reserved statuses are not equal
		assertFalse(s1.equals(s5));
		assertFalse(s5.equals(s1));
		//Test that two seats with all differing fields are not equal
		assertFalse(s1.equals(s6));
		assertFalse(s6.equals(s1));
	}
}
