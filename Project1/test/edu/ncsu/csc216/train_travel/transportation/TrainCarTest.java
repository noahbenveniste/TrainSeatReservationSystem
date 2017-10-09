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
		
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.train_travel.transportation.TrainCar#getCarIDNumber()}.
	 */
	@Test
	public void testGetCarIDNumber() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.train_travel.transportation.TrainCar#initSeats(edu.ncsu.csc216.train_travel.transportation.Seat[][])}.
	 */
	@Test
	public void testInitSeats() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.train_travel.transportation.TrainCar#drawSeatChart(edu.ncsu.csc216.train_travel.transportation.Seat[][], int)}.
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
		
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.train_travel.transportation.TrainCar#seatFor(java.lang.String, edu.ncsu.csc216.train_travel.transportation.Seat[][])}.
	 */
	@Test
	public void testSeatForStringSeatArrayArray() {
		fail("Not yet implemented");
	}

}
