package edu.ncsu.csc216.train_travel.transportation;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Unit tests for Train
 * @author Noah Benveniste
 */
public class TrainTest {
	/** Error message for bad train length */
	private static final String ERROR_MSG = "Train must have at least 4 but no more than 12 cars.";
	/** Error message for trying to accessing seats in a bicycle car */
	private static final String SEAT_ERROR_MESSAGE = "Cannot reserve seats in a bicycle transport car";

	/**
	 * Test method for constructor
	 */
	@Test
	public void testTrain() {
		//Test valid construction of Train at lower boundary length
		Train t1 = new Train(4);
		assertEquals(4, t1.numCars());
		assertTrue(t1.isFirstClassCar(1));
		assertTrue(t1.isSecondClassCar(2));
		assertTrue(t1.isSecondClassCar(3));
		assertTrue(t1.isBicycleCar(4));
		
		//Test valid construction of Train at middle value length
		Train t2 = new Train(8);
		assertEquals(8, t2.numCars());
		assertTrue(t2.isFirstClassCar(1));
		assertTrue(t2.isFirstClassCar(2));
		assertTrue(t2.isSecondClassCar(3));
		assertTrue(t2.isSecondClassCar(4));
		assertTrue(t2.isSecondClassCar(5));
		assertTrue(t2.isSecondClassCar(6));
		assertTrue(t2.isSecondClassCar(7));
		assertTrue(t2.isBicycleCar(8));
		
		Train t6 = new Train(7);
		assertEquals(7, t6.numCars());
		assertTrue(t6.isFirstClassCar(1));
		assertTrue(t6.isFirstClassCar(2));
		assertTrue(t6.isSecondClassCar(3));
		assertTrue(t6.isSecondClassCar(4));
		assertTrue(t6.isSecondClassCar(5));
		assertTrue(t6.isSecondClassCar(6));
		assertTrue(t6.isBicycleCar(7));
		
		//Test valid construction of Train at upper boundary length
		Train t3 = new Train(12);
		assertEquals(12, t3.numCars());
		assertTrue(t3.isFirstClassCar(1));
		assertTrue(t3.isFirstClassCar(2));
		assertTrue(t3.isFirstClassCar(3));
		assertTrue(t3.isSecondClassCar(4));
		assertTrue(t3.isSecondClassCar(5));
		assertTrue(t3.isSecondClassCar(6));
		assertTrue(t3.isSecondClassCar(7));
		assertTrue(t3.isSecondClassCar(8));
		assertTrue(t3.isSecondClassCar(9));
		assertTrue(t3.isSecondClassCar(10));
		assertTrue(t3.isSecondClassCar(11));
		assertTrue(t3.isBicycleCar(12));
		
		//Test invalid construction of Train at lower boundary
		Train t4 = null;
		try {
			t4 = new Train(3);
		} catch (IllegalArgumentException e) {
			assertNull(t4);
			assertEquals(ERROR_MSG, e.getMessage());
		}
		
		//Test invalid construction of Train at upper boundary
		Train t5 = null;
		try {
			t5 = new Train(13);
		} catch (IllegalArgumentException e) {
			assertNull(t5);
			assertEquals(ERROR_MSG, e.getMessage());
		}
	}

	/**
	 * Test method for numCars()
	 */
	@Test
	public void testNumCars() {
		Train t1 = new Train(4);
		assertEquals(4, t1.numCars());
		
		Train t2 = new Train(8);
		assertEquals(8, t2.numCars());
		
		Train t3 = new Train(12);
		assertEquals(12, t3.numCars());
	}

	/**
	 * Test method for isFirstClassCar()
	 */
	@Test
	public void testIsFirstClassCar() {
		Train t = new Train(4);
		assertTrue(t.isFirstClassCar(1));
		assertFalse(t.isFirstClassCar(2));
		assertFalse(t.isFirstClassCar(3));
		assertFalse(t.isFirstClassCar(4));
	}

	/**
	 * Test method for isSecondClassCar()
	 */
	@Test
	public void testIsSecondClassCar() {
		Train t = new Train(4);
		assertFalse(t.isSecondClassCar(1));
		assertTrue(t.isSecondClassCar(2));
		assertTrue(t.isSecondClassCar(3));
		assertFalse(t.isSecondClassCar(4));
	}

	/**
	 * Test method for isBicycleCar()
	 */
	@Test
	public void testIsBicycleCar() {
		Train t = new Train(4);
		assertFalse(t.isBicycleCar(1));
		assertFalse(t.isBicycleCar(2));
		assertFalse(t.isBicycleCar(3));
		assertTrue(t.isBicycleCar(4));
	}

	/**
	 * Test method for incComfortClassPassengers()
	 */
	@Test
	public void testIncComfortClassPassengers() {
		Train t = new Train(4);
		int n1 = t.incComfortClassPassengers(0);
		assertEquals(0, n1);
		int n2 = t.incComfortClassPassengers(10);
		assertEquals(10, n2);
		int n3 = t.incComfortClassPassengers(-5);
		assertEquals(5, n3);
	}

	/**
	 * Test method for incEconomyClassPassengers()
	 */
	@Test
	public void testIncEconomyClassPassengers() {
		Train t = new Train(4);
		int n1 = t.incEconomyClassPassengers(0);
		assertEquals(0, n1);
		int n2 = t.incEconomyClassPassengers(10);
		assertEquals(10, n2);
		int n3 = t.incEconomyClassPassengers(-5);
		assertEquals(5, n3);
	}

	/**
	 * Test method for incBicyclePassengers()
	 */
	@Test
	public void testIncBicyclePassengers() {
		Train t = new Train(4);
		int n1 = t.incBicyclePassengers(0);
		assertEquals(0, n1);
		int n2 = t.incBicyclePassengers(10);
		assertEquals(10, n2);
		int n3 = t.incBicyclePassengers(-5);
		assertEquals(5, n3);
	}

	/**
	 * Test method for hasComfortClassRoomFor()
	 */
	@Test
	public void testHasComfortClassRoomFor() {
		Train t = new Train(4);
		assertTrue(t.hasComfortClassRoomFor(1));
		assertTrue(t.hasComfortClassRoomFor(6));
		
		t.incComfortClassPassengers(51);
		assertFalse(t.hasComfortClassRoomFor(1));
		
		t.incComfortClassPassengers(-1);
		assertTrue(t.hasComfortClassRoomFor(1));
		
		t.incComfortClassPassengers(-5);
		assertTrue(t.hasComfortClassRoomFor(6));
	}

	/**
	 * Test method for hasEconomyClassRoomFor()
	 */
	@Test
	public void testHasEconomyClassRoomFor() {
		Train t = new Train(4);
		assertTrue(t.hasEconomyClassRoomFor(1));
		assertTrue(t.hasEconomyClassRoomFor(6));
		
		t.incEconomyClassPassengers(83);
		assertTrue(t.hasEconomyClassRoomFor(1));
		assertTrue(t.hasEconomyClassRoomFor(6));
		
		t.incEconomyClassPassengers(83);
		assertTrue(t.hasEconomyClassRoomFor(1));
		assertTrue(t.hasEconomyClassRoomFor(6));
		
		t.incEconomyClassPassengers(20);
		assertFalse(t.hasEconomyClassRoomFor(1));
		
		t.incEconomyClassPassengers(-1);
		assertTrue(t.hasEconomyClassRoomFor(1));
		
		t.incEconomyClassPassengers(-5);
		assertTrue(t.hasEconomyClassRoomFor(6));
	}

	/**
	 * Test method for hasBicycleCarRoomFor()
	 */
	@Test
	public void testHasBicycleCarRoomFor() {
		Train t = new Train(4);
		assertTrue(t.hasBicycleCarRoomFor(1));
		assertTrue(t.hasBicycleCarRoomFor(6));
		
		t.incBicyclePassengers(14);
		assertTrue(t.hasBicycleCarRoomFor(1));
		assertTrue(t.hasBicycleCarRoomFor(6));
		
		t.incBicyclePassengers(6);
		assertFalse(t.hasBicycleCarRoomFor(1));
		
		t.incBicyclePassengers(-6);
		assertTrue(t.hasBicycleCarRoomFor(1));
		assertTrue(t.hasBicycleCarRoomFor(6));
	}

	/**
	 * Test method for getSeatFor(int, int, int)
	 */
	@Test
	public void testGetSeatForIntIntInt() {
		Train t = new Train(4);
		//Invalid lower bound car index
		Seat s = null;
		try {
			s = t.getSeatFor(-1, 0, 0);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		//Invalid upper bound car index
		s = null;
		try {
			s = t.getSeatFor(4, 0, 0);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
		/** Testing on accessing a first class car*/
		//Invalid seat row index, lower bound
		s = null;
		try {
			s = t.getSeatFor(0, -1, 0);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
		//Invalid seat row index, upper bound
		s = null;
		try {
			s = t.getSeatFor(0, 17, 0);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
		//Invalid seat column index, lower bound
		s = null;
		try {
			s = t.getSeatFor(0, 0, -1);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
		//Invalid seat column index, upper bound
		s = null;
		try {
			s = t.getSeatFor(0, 0, 3);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
		/** Testing on accessing a second class car */
		//Invalid seat row index, lower bound
		s = null;
		try {
			s = t.getSeatFor(1, -1, 0);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
		//Invalid seat row index, upper bound
		s = null;
		try {
			s = t.getSeatFor(1, 19, 0);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
		//Invalid seat column index, lower bound
		s = null;
		try {
			s = t.getSeatFor(1, 0, -1);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
		//Invalid seat column index, upper bound
		s = null;
		try {
			s = t.getSeatFor(1, 0, 4);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
		/** Trying to access seats in the bicycle car throws an exception */
		s = null;
		try {
			s = t.getSeatFor(3, 0, 0);
		} catch (IllegalArgumentException e) {
			assertNull(s);
			assertEquals(SEAT_ERROR_MESSAGE, e.getMessage());
		}
		
		/** Access valid seats in first class car */
		assertEquals(new Seat("1A", 1), t.getSeatFor(0, 0, 0));
		assertEquals(new Seat("1C", 1), t.getSeatFor(0, 0, 2));
		assertEquals(new Seat("10A", 1), t.getSeatFor(0, 9, 0));
		assertEquals(new Seat("10C", 1), t.getSeatFor(0, 9, 2));
		assertEquals(new Seat("17A", 1), t.getSeatFor(0, 16, 0));
		assertEquals(new Seat("17C", 1), t.getSeatFor(0, 16, 2));
		
		/** Access valid seats in second class car */
		assertEquals(new Seat("1A", 2), t.getSeatFor(1, 0, 0));
		assertEquals(new Seat("1D", 2), t.getSeatFor(1, 0, 3));
		assertEquals(new Seat("10A", 2), t.getSeatFor(1, 9, 0));
		assertEquals(new Seat("10D", 2), t.getSeatFor(1, 9, 3));
		assertEquals(new Seat("19A", 2), t.getSeatFor(1, 18, 0));
		assertEquals(new Seat("19D", 2), t.getSeatFor(1, 18, 3));
	}

	/**
	 * Test method for getSeatFor(int, String)
	 */
	@Test
	public void testGetSeatForIntString() {
		Train t = new Train(4);
		//Invalid lower bound car index
		Seat s = null;
		try {
			s = t.getSeatFor(-1, "1A");
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		//Invalid upper bound car index
		s = null;
		try {
			s = t.getSeatFor(4, "1A");
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
		/** Testing on accessing a first class car*/
		//Invalid seat row index, upper bound
		s = null;
		try {
			s = t.getSeatFor(0, "18A");
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
		//Invalid seat column index, upper bound
		s = null;
		try {
			s = t.getSeatFor(0, "1D");
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
		/** Testing on accessing a second class car */
		//Invalid seat row index, upper bound
		s = null;
		try {
			s = t.getSeatFor(1, "20A");
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
		//Invalid seat column index, upper bound
		s = null;
		try {
			s = t.getSeatFor(1, "1E");
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
		/** Trying to access seats in the bicycle car throws an exception */
		s = null;
		try {
			s = t.getSeatFor(3, "1A");
		} catch (IllegalArgumentException e) {
			assertNull(s);
			assertEquals(SEAT_ERROR_MESSAGE, e.getMessage());
		}
		
		/** Access valid seats in first class car */
		assertEquals(new Seat("1A", 1), t.getSeatFor(0, "1A"));
		assertEquals(new Seat("1C", 1), t.getSeatFor(0, "1C"));
		assertEquals(new Seat("10A", 1), t.getSeatFor(0, "10A"));
		assertEquals(new Seat("10C", 1), t.getSeatFor(0, "10C"));
		assertEquals(new Seat("17A", 1), t.getSeatFor(0, "17A"));
		assertEquals(new Seat("17C", 1), t.getSeatFor(0, "17C"));
		
		/** Access valid seats in second class car */
		assertEquals(new Seat("1A", 2), t.getSeatFor(1, "1A"));
		assertEquals(new Seat("1D", 2), t.getSeatFor(1, "1D"));
		assertEquals(new Seat("10A", 2), t.getSeatFor(1, "10A"));
		assertEquals(new Seat("10D", 2), t.getSeatFor(1, "10D"));
		assertEquals(new Seat("19A", 2), t.getSeatFor(1, "19A"));
		assertEquals(new Seat("19D", 2), t.getSeatFor(1, "19D"));
	}

	/**
	 * Test method for openSecondClassSeats()
	 */
	@Test
	public void testOpenSecondClassSeats() {
		Train t = new Train(4);
		//Two second class cars with 76 seats available
		assertEquals(152, t.openSecondClassSeats());
		//Reserve some seats
		t.getSeatFor(1, "1A").reserve();
		t.getSeatFor(2, "1A").reserve();
		t.getSeatFor(2, "19D").reserve();
		t.getSeatFor(2, "10C").reserve();
		t.getSeatFor(1, "19A").reserve();
		//Check that the open second class seats has decreased by 5
		assertEquals(147, t.openSecondClassSeats());
		//Release the seats, checking that the openSecondClassSeats() increases
		t.getSeatFor(2, "10C").release();
		assertEquals(148, t.openSecondClassSeats());
		t.getSeatFor(2, "1A").release();
		assertEquals(149, t.openSecondClassSeats());
		t.getSeatFor(1, "19A").release();
		t.getSeatFor(1, "1A").release();
		assertEquals(151, t.openSecondClassSeats());
		t.getSeatFor(2, "19D").release();
		assertEquals(152, t.openSecondClassSeats());
	}

	/**
	 * Test method for openFirstClassSeats()
	 */
	@Test
	public void testOpenFirstClassSeats() {
		Train t = new Train(4);
		//One first class car with 51 seats available
		assertEquals(51, t.openFirstClassSeats());
		//Reserve some seats
		t.getSeatFor(0, "1A").reserve();
		t.getSeatFor(0, "1C").reserve();
		t.getSeatFor(0, "10B").reserve();
		t.getSeatFor(0, "17C").reserve();
		//Check that the available seats decreased by 4
		assertEquals(47, t.openFirstClassSeats());
		//Release the seats, checking that the available seats increases
		t.getSeatFor(0, "1C").release();
		assertEquals(48, t.openFirstClassSeats());
		t.getSeatFor(0, "10B").release();
		t.getSeatFor(0, "17C").release();
		assertEquals(50, t.openFirstClassSeats());
		t.getSeatFor(0, "1A").release();
		assertEquals(51, t.openFirstClassSeats());
	}

	/**
	 * Test method for getCarSeatMap()
	 */
	@Test
	public void testGetCarSeatMap() {
		Train t = new Train(4);
		//Get the seat map for car 1
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
		assertEquals(expected1, t.getCarSeatMap(0));
		
		//Get the seat map for car 2
		String expected2 = "\nSecond Class Car #2"
							+ "\n"
							+ "\n"
							+ "[ 1A][ 1B]    [ 1C][ 1D]\n"
							+ "[ 2A][ 2B]    [ 2C][ 2D]\n"
							+ "[ 3A][ 3B]    [ 3C][ 3D]\n"
							+ "[ 4A][ 4B]    [ 4C][ 4D]\n"
							+ "[ 5A][ 5B]    [ 5C][ 5D]\n"
							+ "[ 6A][ 6B]    [ 6C][ 6D]\n"
							+ "[ 7A][ 7B]    [ 7C][ 7D]\n"
							+ "[ 8A][ 8B]    [ 8C][ 8D]\n"
							+ "[ 9A][ 9B]    [ 9C][ 9D]\n"
							+ "[10A][10B]    [10C][10D]\n"
							+ "[11A][11B]    [11C][11D]\n"
							+ "[12A][12B]    [12C][12D]\n"
							+ "[13A][13B]    [13C][13D]\n"
							+ "[14A][14B]    [14C][14D]\n"
							+ "[15A][15B]    [15C][15D]\n"
							+ "[16A][16B]    [16C][16D]\n"
							+ "[17A][17B]    [17C][17D]\n"
							+ "[18A][18B]    [18C][18D]\n"
							+ "[19A][19B]    [19C][19D]\n";
		assertEquals(expected2, t.getCarSeatMap(1));
		
		//Get the seat map for car 3
		String expected3 = "\nSecond Class Car #3"
							+ "\n"
							+ "\n"
							+ "[ 1A][ 1B]    [ 1C][ 1D]\n"
							+ "[ 2A][ 2B]    [ 2C][ 2D]\n"
							+ "[ 3A][ 3B]    [ 3C][ 3D]\n"
							+ "[ 4A][ 4B]    [ 4C][ 4D]\n"
							+ "[ 5A][ 5B]    [ 5C][ 5D]\n"
							+ "[ 6A][ 6B]    [ 6C][ 6D]\n"
							+ "[ 7A][ 7B]    [ 7C][ 7D]\n"
							+ "[ 8A][ 8B]    [ 8C][ 8D]\n"
							+ "[ 9A][ 9B]    [ 9C][ 9D]\n"
							+ "[10A][10B]    [10C][10D]\n"
							+ "[11A][11B]    [11C][11D]\n"
							+ "[12A][12B]    [12C][12D]\n"
							+ "[13A][13B]    [13C][13D]\n"
							+ "[14A][14B]    [14C][14D]\n"
							+ "[15A][15B]    [15C][15D]\n"
							+ "[16A][16B]    [16C][16D]\n"
							+ "[17A][17B]    [17C][17D]\n"
							+ "[18A][18B]    [18C][18D]\n"
							+ "[19A][19B]    [19C][19D]\n";
		assertEquals(expected3, t.getCarSeatMap(2));
		
		//Get the seat map for car 4
		String expected4 = "\nBicycle Transport Car #4\n Seats cannot be reserved.";
		assertEquals(expected4, t.getCarSeatMap(3));
	}
}
