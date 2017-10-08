package edu.ncsu.csc216.train_travel.transportation;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Unit tests for the FirstClassCar class
 * @author Noah Benveniste
 */
public class FirstClassCarTest {
	/** ASCII value for a */
	private static final int ASCII_A = 65;

	/**
	 * Test method for constructor
	 */
	@Test
	public void testFirstClassCar() {
		//Test valid middle value construction
		FirstClassCar car1 = new FirstClassCar(6);
		assertNotNull(car1);
		
		//Test valid lower boundary value construction
		FirstClassCar car2 = new FirstClassCar(1);
		assertNotNull(car2);
		
		//Test valid upper boundary value construction 
		FirstClassCar car3 = new FirstClassCar(12);
		assertNotNull(car3);
		
		//Test invalid lower boundary value construction
		FirstClassCar car4 = null;
		try {
			car4 = new FirstClassCar(0);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid car number", e.getMessage());
			assertNull(car4);
		}
		
		//Test invalid lower boundary value construction
		FirstClassCar car5 = null;
		try {
			car5 = new FirstClassCar(13);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid car number", e.getMessage());
			assertNull(car5);
		}
		
		//Check that the seat array was initialized correctly in the valid objects
		for (int i = 0; i < FirstClassCar.getNumRows(); i++) {
			for (int j = 0; j < FirstClassCar.getNumSeatsPerRow(); j++) {
				//Create a seat object for comparison
				String label = "" + (i + 1);
				label += (char) (ASCII_A + j);
				Seat s = new Seat(label , 6);
				//Compare the newly created seat object to the seat in the array for equality
				assertEquals(s, car1.seatFor(i, j));
			}
		}
	}
	
	/**
	 * Test method for getNumRows()
	 */
	@Test
	public void testGetNumRows() {
		assertEquals(17, FirstClassCar.getNumRows());
	}

	/**
	 * Test method for getNumSeatsPerRow()
	 */
	@Test
	public void testGetNumSeatsPerRow() {
		assertEquals(3, FirstClassCar.getNumSeatsPerRow());
	}

	/**
	 * Test method for getCapacity()
	 */
	@Test
	public void testGetCapacity() {
		assertEquals(51, FirstClassCar.getCapacity());
	}

	/**
	 * Test method for openSeatsLeft()
	 */
	@Test
	public void testOpenSeatsLeft() {
		//Initialize object
		FirstClassCar car = new FirstClassCar(1);
		
		//Test that all seats start out unreserved
		assertEquals(51, car.openSeatsLeft());
		
		//Reserve a seat
		car.seatFor(0, 0).reserve();
		
		//Check that the open seats left decreased by 1
		assertEquals(50, car.openSeatsLeft());
		
		//Reserve some more seats
		car.seatFor(0, 1).reserve();
		car.seatFor(0, 2).reserve();
		car.seatFor(1, 2).reserve();
		
		//Check that the open seats left decreased by 3
		assertEquals(47, car.openSeatsLeft());
		
		//Release some seats
		car.seatFor(0, 1).release();
		car.seatFor(0, 2).release();
		
		//Check that the open seats left increased by 2
		assertEquals(49, car.openSeatsLeft());
	}

	/**
	 * Test method for seatFor(int, int)
	 */
	@Test
	public void testSeatForIntInt() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for seatFor(String)
	 */
	@Test
	public void testSeatForString() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for getSeatMap()
	 */
	@Test
	public void testGetSeatMap() {
		fail("Not yet implemented");
	}

}
