package edu.ncsu.csc216.train_travel.transportation;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Unit tests for the TrainCar class. Note that because most of the methods in TrainCar
 * are either abstract or protected, FirstClassCarTest, SecondClassCarTest and BicycleTransportCarTest
 * extensively test the individual implementations of the abstract methods, as well as the
 * protected methods that are called by these implementations.
 * @author Noah Benveniste
 */
public class TrainCarTest {

	/**
	 * Test method for constructor
	 */
	@Test
	public void testTrainCar() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for getCarIDNumber()
	 */
	@Test
	public void testGetCarIDNumber() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for initSeats()
	 */
	@Test
	public void testInitSeats() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for drawSeatChart()
	 */
	@Test
	public void testDrawSeatChart() {
		FirstClassCar car = new FirstClassCar(1);
		//Get the seats from the car
		Seat[][] seats = new Seat[17][3];
		for (int i = 0; i < 17; i++) {
			for (int j = 0; j < 3; j++) {
				seats[i][j] = car.seatFor(i, j);
			}
		}
		fail("Not yet implemented");
	}

	/**
	 * Test method for seatFor()
	 */
	@Test
	public void testSeatForStringSeatArrayArray() {
		fail("Not yet implemented");
	}
}
