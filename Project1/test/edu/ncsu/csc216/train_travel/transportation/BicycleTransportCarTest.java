package edu.ncsu.csc216.train_travel.transportation;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Unit tests for the BicycleTransportCar class
 * @author Noah Benveniste
 */
public class BicycleTransportCarTest {
	/** Error message */
	private static final String SEAT_ERROR_MESSAGE = "Cannot reserve seats in a bicycle transport car";

	/**
	 * Test method for constructor
	 */
	@Test
	public void testBicycleTransportCar() {
		//Test valid middle value construction
		BicycleTransportCar car1 = new BicycleTransportCar(6);
		assertNotNull(car1);
		
		//Test valid lower boundary value construction
		BicycleTransportCar car2 = new BicycleTransportCar(1);
		assertNotNull(car2);
		
		//Test valid upper boundary value construction 
		BicycleTransportCar car3 = new BicycleTransportCar(12);
		assertNotNull(car3);
		
		//Test invalid lower boundary value construction
		BicycleTransportCar car4 = null;
		try {
			car4 = new BicycleTransportCar(0);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid car number", e.getMessage());
			assertNull(car4);
		}
		
		//Test invalid lower boundary value construction
		BicycleTransportCar car5 = null;
		try {
			car5 = new BicycleTransportCar(13);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid car number", e.getMessage());
			assertNull(car5);
		}
	}

	/**
	 * Test method for getCapacity()
	 */
	@Test
	public void testGetCapacity() {
		assertEquals(20, BicycleTransportCar.getCapacity());
	}

	/**
	 * Test method for openSeatsLeft()
	 */
	@Test
	public void testOpenSeatsLeft() {
		//This method is not applicable to BicycleTransportCars, should always return 0
		BicycleTransportCar car = new BicycleTransportCar(1);
		assertEquals(0, car.openSeatsLeft());
	}

	/**
	 * Test method for seatFor(int, int)
	 */
	@Test
	public void testSeatForIntInt() {
		//This method is not applicable to BicycleTransportCars, should throw an IAE
		BicycleTransportCar car = new BicycleTransportCar(1);
		Seat seat1 = null;
		try {
			seat1 = car.seatFor(0, 0);
		} catch (IllegalArgumentException e) {
			assertNull(seat1);
			assertEquals(SEAT_ERROR_MESSAGE, e.getMessage());
		}
		
	}

	/**
	 * Test method for seatFor(String)
	 */
	@Test
	public void testSeatForString() {
		//This method is not applicable to BicycleTransportCars, should throw an IAE
		BicycleTransportCar car = new BicycleTransportCar(1);
		Seat seat1 = null;
		try {
			seat1 = car.seatFor("1A");
		} catch (IllegalArgumentException e) {
			assertNull(seat1);
			assertEquals(SEAT_ERROR_MESSAGE, e.getMessage());
		}
	}

	/**
	 * Test method for getSeatMap()
	 */
	@Test
	public void testGetSeatMap() {
		BicycleTransportCar car = new BicycleTransportCar(1);
		String expected = "Bicycle Transport Car #1\n Seats cannot be reserved.";
		assertEquals(expected, car.getSeatMap());
	}
}
