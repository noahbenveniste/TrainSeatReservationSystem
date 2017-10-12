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
		//Initialize object
		FirstClassCar car = new FirstClassCar(1);
		
		//Valid upper boundary row, upper boundary column
		Seat seat1 = car.seatFor(0, 0);
		assertEquals(new Seat("1A", 1), seat1);
		
		//Valid upper boundary row, middle value column
		Seat seat2 = car.seatFor(0, 1);
		assertEquals(new Seat("1B", 1), seat2);
		
		//Valid upper boundary row, lower boundary column
		Seat seat3 = car.seatFor(0, 2);
		assertEquals(new Seat("1C", 1), seat3);
		
		//Valid middle value row, upper boundary column
		Seat seat4 = car.seatFor(9, 0);
		assertEquals(new Seat("10A", 1), seat4);
		
		//Valid middle value row, middle value column
		Seat seat5 = car.seatFor(9, 1);
		assertEquals(new Seat("10B", 1), seat5);
		
		//Valid middle value row, lower value column
		Seat seat6 = car.seatFor(9, 2);
		assertEquals(new Seat("10C", 1), seat6);
		
		//Valid lower boundary row, upper boundary column
		Seat seat7 = car.seatFor(16, 0);
		assertEquals(new Seat("17A", 1), seat7);
		
		//Valid lower boundary row, middle value column
		Seat seat8 = car.seatFor(16, 1);
		assertEquals(new Seat("17B", 1), seat8);
		
		//Valid lower boundary row, lower boundary column
		Seat seat9 = car.seatFor(16, 2);
		assertEquals(new Seat("17C", 1), seat9);
		
		//Invalid upper boundary row, upper boundary column
		Seat seat10 = null;
		try {
			seat10 = car.seatFor(-1, -1);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(seat10);
		}
		
		//Invalid upper boundary row, lower boundary column
		Seat seat11 = null;
		try {
			seat11 = car.seatFor(-1, 3);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(seat11);
		}
		
		//Invalid lower boundary row, upper boundary column
		Seat seat12 = null;
		try {
			seat12 = car.seatFor(17, -1);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(seat12);
		}
		
		//Invalid lower boundary row, lower boundary column
		Seat seat13 = null;
		try {
			seat13 = car.seatFor(17, 3);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(seat13);
		}
		
		//Invalid row only, upper boundary
		Seat seat14 = null;
		try {
			seat14 = car.seatFor(-1, 2);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(seat14);
		}
		
		//Invalid row only, lower bound
		Seat seat15 = null;
		try {
			seat15 = car.seatFor(17, 2);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(seat15);
		}
		
		//Invalid column only, upper bound
		Seat seat16 = null;
		try {
			seat16 = car.seatFor(16, -1);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(seat16);
		}
		
		//Invalid column only, lower bound
		Seat seat17 = null;
		try {
			seat17 = car.seatFor(16, 3);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(seat17);
		}
	}

	/**
	 * Test method for seatFor(String)
	 */
	@Test
	public void testSeatForString() {
		//Initialize object
		FirstClassCar car = new FirstClassCar(1);
		
		//Valid upper boundary row, upper boundary column
		Seat seat1 = car.seatFor("1A");
		assertEquals(new Seat("1A", 1), seat1);
		
		//Valid upper boundary row, middle value column
		Seat seat2 = car.seatFor("1B");
		assertEquals(new Seat("1B", 1), seat2);
		
		//Valid upper boundary row, lower boundary column
		Seat seat3 = car.seatFor("1C");
		assertEquals(new Seat("1C", 1), seat3);
		
		//Valid middle value row, upper boundary column
		Seat seat4 = car.seatFor("10A");
		assertEquals(new Seat("10A", 1), seat4);
		
		//Valid middle value row, middle value column
		Seat seat5 = car.seatFor("10B");
		assertEquals(new Seat("10B", 1), seat5);
		
		//Valid middle value row, lower value column
		Seat seat6 = car.seatFor("10C");
		assertEquals(new Seat("10C", 1), seat6);
		
		//Valid lower boundary row, upper boundary column
		Seat seat7 = car.seatFor("17A");
		assertEquals(new Seat("17A", 1), seat7);
		
		//Valid lower boundary row, middle value column
		Seat seat8 = car.seatFor("17B");
		assertEquals(new Seat("17B", 1), seat8);
		
		//Valid lower boundary row, lower boundary column
		Seat seat9 = car.seatFor("17C");
		assertEquals(new Seat("17C", 1), seat9);
		
		//Test invalid seat labels
		//Empty label
		Seat seat10 = null;
		try {
			seat10 = car.seatFor("");
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(seat10);
		}
		
		//Label with no letter
		Seat seat11 = null;
		try {
			seat11 = car.seatFor("1");
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(seat11);
		}
		
		//Two digit label with no letter
		Seat seat12 = null;
		try {
			seat12 = car.seatFor("17");
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(seat12);
		}
		
		//Label with no number
		Seat seat13 = null;
		try {
			seat13 = car.seatFor("A");
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(seat13);
		}
		
		//Label with invalid row number, lower bound
		Seat seat14 = null;
		try {
			seat14 = car.seatFor("0A");
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(seat14);
		}
		
		//Label with invalid row number, upper bound
		Seat seat15 = null;
		try {
			seat15 = car.seatFor("18A");
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(seat15);
		}
		
		//Label with invalid letter for first class car
		Seat seat16 = null;
		try {
			seat16 = car.seatFor("10D");
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(seat16);
		}
		
		Seat seat25 = null;
		try {
			seat25 = car.seatFor("9D");
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(seat25);
		}
		
		//Label of the correct length but all letters
		Seat seat17 = null;
		try {
			seat17 = car.seatFor("AB");
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(seat17);
		}
		
		Seat seat18 = null;
		try {
			seat18 = car.seatFor("ABC");
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(seat18);
		}
		
		//Label of the correct length but all numbers
		Seat seat19 = null;
		try {
			seat19 = car.seatFor("12");
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(seat19);
		}
		
		Seat seat20 = null;
		try {
			seat20 = car.seatFor("123");
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(seat20);
		}
		
		//Label of the correct length but letters/numbers out of order
		Seat seat21 = null;
		try {
			seat21 = car.seatFor("A2");
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(seat21);
		}
		
		Seat seat22 = null;
		try {
			seat22 = car.seatFor("1A1");
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(seat22);
		}
		
		Seat seat23 = null;
		try {
			seat23 = car.seatFor("A11");
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(seat23);
		}
		
		//Label of the correct length but contains non-alphanumeric characters
		Seat seat24 = null;
		try {
			seat24 = car.seatFor("11!");
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(seat24);
		}
	}

	/**
	 * Test method for getSeatMap
	 */
	@Test
	public void testGetSeatMap() {
		//Initialize object
		FirstClassCar car = new FirstClassCar(1);
		String expected1 = "First Class Car #1"
						+ "\n"
						+ "\n"
						+ "[ 1A]   [ 1B][ 1C]\n"
						+ "[ 2A]   [ 2B][ 2C]\n"
						+ "[ 3A]   [ 3B][ 3C]\n"
						+ "[ 4A]   [ 4B][ 4C]\n"
						+ "[ 5A]   [ 5B][ 5C]\n"
						+ "[ 6A]   [ 6B][ 6C]\n"
						+ "[ 7A]   [ 7B][ 7C]\n"
						+ "[ 8A]   [ 8B][ 8C]\n"
						+ "[ 9A]   [ 9B][ 9C]\n"
						+ "[10A]   [10B][10C]\n"
						+ "[11A]   [11B][11C]\n"
						+ "[12A]   [12B][12C]\n"
						+ "[13A]   [13B][13C]\n"
						+ "[14A]   [14B][14C]\n"
						+ "[15A]   [15B][15C]\n"
						+ "[16A]   [16B][16C]\n"
						+ "[17A]   [17B][17C]\n";
		assertEquals(expected1, car.getSeatMap());
		
		//Assign seats and check that the map updates properly
		car.seatFor("17C").reserve();
		car.seatFor("1A").reserve();
		car.seatFor("10B").reserve();
		String expected2 = "First Class Car #1"
						+ "\n"
						+ "\n"
						+ "[ x ]   [ 1B][ 1C]\n"
						+ "[ 2A]   [ 2B][ 2C]\n"
						+ "[ 3A]   [ 3B][ 3C]\n"
						+ "[ 4A]   [ 4B][ 4C]\n"
						+ "[ 5A]   [ 5B][ 5C]\n"
						+ "[ 6A]   [ 6B][ 6C]\n"
						+ "[ 7A]   [ 7B][ 7C]\n"
						+ "[ 8A]   [ 8B][ 8C]\n"
						+ "[ 9A]   [ 9B][ 9C]\n"
						+ "[10A]   [ x ][10C]\n"
						+ "[11A]   [11B][11C]\n"
						+ "[12A]   [12B][12C]\n"
						+ "[13A]   [13B][13C]\n"
						+ "[14A]   [14B][14C]\n"
						+ "[15A]   [15B][15C]\n"
						+ "[16A]   [16B][16C]\n"
						+ "[17A]   [17B][ x ]\n";
		assertEquals(expected2, car.getSeatMap());
		
		//Release seats and check that the map updates properly
		car.seatFor("1A").release();
		String expected3 = "First Class Car #1"
						+ "\n"
						+ "\n"
						+ "[ 1A]   [ 1B][ 1C]\n"
						+ "[ 2A]   [ 2B][ 2C]\n"
						+ "[ 3A]   [ 3B][ 3C]\n"
						+ "[ 4A]   [ 4B][ 4C]\n"
						+ "[ 5A]   [ 5B][ 5C]\n"
						+ "[ 6A]   [ 6B][ 6C]\n"
						+ "[ 7A]   [ 7B][ 7C]\n"
						+ "[ 8A]   [ 8B][ 8C]\n"
						+ "[ 9A]   [ 9B][ 9C]\n"
						+ "[10A]   [ x ][10C]\n"
						+ "[11A]   [11B][11C]\n"
						+ "[12A]   [12B][12C]\n"
						+ "[13A]   [13B][13C]\n"
						+ "[14A]   [14B][14C]\n"
						+ "[15A]   [15B][15C]\n"
						+ "[16A]   [16B][16C]\n"
						+ "[17A]   [17B][ x ]\n";
		assertEquals(expected3, car.getSeatMap());
		
		car.seatFor("17C").release();
		car.seatFor("10B").release();
		String expected4 = "First Class Car #1"
						+ "\n"
						+ "\n"
						+ "[ 1A]   [ 1B][ 1C]\n"
						+ "[ 2A]   [ 2B][ 2C]\n"
						+ "[ 3A]   [ 3B][ 3C]\n"
						+ "[ 4A]   [ 4B][ 4C]\n"
						+ "[ 5A]   [ 5B][ 5C]\n"
						+ "[ 6A]   [ 6B][ 6C]\n"
						+ "[ 7A]   [ 7B][ 7C]\n"
						+ "[ 8A]   [ 8B][ 8C]\n"
						+ "[ 9A]   [ 9B][ 9C]\n"
						+ "[10A]   [10B][10C]\n"
						+ "[11A]   [11B][11C]\n"
						+ "[12A]   [12B][12C]\n"
						+ "[13A]   [13B][13C]\n"
						+ "[14A]   [14B][14C]\n"
						+ "[15A]   [15B][15C]\n"
						+ "[16A]   [16B][16C]\n"
						+ "[17A]   [17B][17C]\n";
		assertEquals(expected4, car.getSeatMap());
	}
}
