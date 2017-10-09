package edu.ncsu.csc216.train_travel.transportation;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Unit tests for the SecondClassCar class
 * @author Noah Benveniste
 */
public class SecondClassCarTest {
	/** ASCII value for a */
	private static final int ASCII_A = 65;
	
	/**
	 * Test method for constructor
	 */
	@Test
	public void testSecondClassCar() {
		//Test valid middle value construction
		SecondClassCar car1 = new SecondClassCar(6);
		assertNotNull(car1);
		
		//Test valid lower boundary value construction
		SecondClassCar car2 = new SecondClassCar(1);
		assertNotNull(car2);
		
		//Test valid upper boundary value construction 
		SecondClassCar car3 = new SecondClassCar(12);
		assertNotNull(car3);
		
		//Test invalid lower boundary value construction
		SecondClassCar car4 = null;
		try {
			car4 = new SecondClassCar(0);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid car number", e.getMessage());
			assertNull(car4);
		}
		
		//Test invalid lower boundary value construction
		SecondClassCar car5 = null;
		try {
			car5 = new SecondClassCar(13);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid car number", e.getMessage());
			assertNull(car5);
		}
		
		//Check that the seat array was initialized correctly in the valid objects
		for (int i = 0; i < SecondClassCar.getNumRows(); i++) {
			for (int j = 0; j < SecondClassCar.getNumSeatsPerRow(); j++) {
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
		assertEquals(19, SecondClassCar.getNumRows());
	}

	/**
	 * Test method for getNumSeatsPerRow()
	 */
	@Test
	public void testGetNumSeatsPerRow() {
		assertEquals(4, SecondClassCar.getNumSeatsPerRow());
	}

	/**
	 * Test method for getCapacity()
	 */
	@Test
	public void testGetCapacity() {
		assertEquals(83, SecondClassCar.getCapacity());
	}

	/**
	 * Test method for openSeats()
	 */
	@Test
	public void testOpenSeatsLeft() {
		//Initialize object
		SecondClassCar car = new SecondClassCar(1);
		
		//Test that all seats start out unreserved
		assertEquals(76, car.openSeatsLeft());
		
		//Reserve a seat
		car.seatFor(0, 0).reserve();
		
		//Check that the open seats left decreased by 1
		assertEquals(75, car.openSeatsLeft());
		
		//Reserve some more seats
		car.seatFor(0, 1).reserve();
		car.seatFor(0, 2).reserve();
		car.seatFor(1, 2).reserve();
		
		//Check that the open seats left decreased by 3
		assertEquals(72, car.openSeatsLeft());
		
		//Release some seats
		car.seatFor(0, 1).release();
		car.seatFor(0, 2).release();
		
		//Check that the open seats left increased by 2
		assertEquals(74, car.openSeatsLeft());
	}

	/**
	 * Test method for seatFor(int, int)
	 */
	@Test
	public void testSeatForIntInt() {
		//Initialize object
		SecondClassCar car = new SecondClassCar(1);
		
		//Valid upper boundary row, upper boundary column
		Seat seat1 = car.seatFor(0, 0);
		assertEquals(new Seat("1A", 1), seat1);
		
		//Valid upper boundary row, middle value column
		Seat seat2 = car.seatFor(0, 1);
		assertEquals(new Seat("1B", 1), seat2);
		
		//Valid upper boundary row, lower boundary column
		Seat seat3 = car.seatFor(0, 3);
		assertEquals(new Seat("1D", 1), seat3);
		
		//Valid middle value row, upper boundary column
		Seat seat4 = car.seatFor(9, 0);
		assertEquals(new Seat("10A", 1), seat4);
		
		//Valid middle value row, middle value column
		Seat seat5 = car.seatFor(9, 1);
		assertEquals(new Seat("10B", 1), seat5);
		
		//Valid middle value row, lower value column
		Seat seat6 = car.seatFor(9, 3);
		assertEquals(new Seat("10D", 1), seat6);
		
		//Valid lower boundary row, upper boundary column
		Seat seat7 = car.seatFor(18, 0);
		assertEquals(new Seat("19A", 1), seat7);
		
		//Valid lower boundary row, middle value column
		Seat seat8 = car.seatFor(18, 1);
		assertEquals(new Seat("19B", 1), seat8);
		
		//Valid lower boundary row, lower boundary column
		Seat seat9 = car.seatFor(18, 3);
		assertEquals(new Seat("19D", 1), seat9);
		
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
			seat11 = car.seatFor(-1, 4);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(seat11);
		}
		
		//Invalid lower boundary row, upper boundary column
		Seat seat12 = null;
		try {
			seat12 = car.seatFor(19, -1);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(seat12);
		}
		
		//Invalid lower boundary row, lower boundary column
		Seat seat13 = null;
		try {
			seat13 = car.seatFor(19, 4);
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
			seat15 = car.seatFor(19, 2);
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
			seat17 = car.seatFor(16, 4);
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
		SecondClassCar car = new SecondClassCar(1);
		
		//Valid upper boundary row, upper boundary column
		Seat seat1 = car.seatFor("1A");
		assertEquals(new Seat("1A", 1), seat1);
		
		//Valid upper boundary row, middle value column
		Seat seat2 = car.seatFor("1B");
		assertEquals(new Seat("1B", 1), seat2);
		
		//Valid upper boundary row, lower boundary column
		Seat seat3 = car.seatFor("1D");
		assertEquals(new Seat("1D", 1), seat3);
		
		//Valid middle value row, upper boundary column
		Seat seat4 = car.seatFor("10A");
		assertEquals(new Seat("10A", 1), seat4);
		
		//Valid middle value row, middle value column
		Seat seat5 = car.seatFor("10B");
		assertEquals(new Seat("10B", 1), seat5);
		
		//Valid middle value row, lower value column
		Seat seat6 = car.seatFor("10D");
		assertEquals(new Seat("10D", 1), seat6);
		
		//Valid lower boundary row, upper boundary column
		Seat seat7 = car.seatFor("19A");
		assertEquals(new Seat("19A", 1), seat7);
		
		//Valid lower boundary row, middle value column
		Seat seat8 = car.seatFor("19B");
		assertEquals(new Seat("19B", 1), seat8);
		
		//Valid lower boundary row, lower boundary column
		Seat seat9 = car.seatFor("19D");
		assertEquals(new Seat("19D", 1), seat9);
		
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
			seat15 = car.seatFor("20A");
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(seat15);
		}
		
		//Label with invalid letter for second class car
		Seat seat16 = null;
		try {
			seat16 = car.seatFor("10E");
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(seat16);
		}
		
		Seat seat25 = null;
		try {
			seat25 = car.seatFor("9E");
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
	 * Test method for getSeatMap()
	 */
	@Test
	public void testGetSeatMap() {
		SecondClassCar car = new SecondClassCar(2);
		String expected1 = "\nSecond Class Car #2"
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
		assertEquals(expected1, car.getSeatMap());
		
		//Assign seats and check that the map updates properly
		car.seatFor("19D").reserve();
		car.seatFor("1A").reserve();
		car.seatFor("10B").reserve();
		String expected2 = "\nSecond Class Car #2"
						+ "\n"
						+ "\n"
						+ "[ x ][ 1B]    [ 1C][ 1D]\n"
						+ "[ 2A][ 2B]    [ 2C][ 2D]\n"
						+ "[ 3A][ 3B]    [ 3C][ 3D]\n"
						+ "[ 4A][ 4B]    [ 4C][ 4D]\n"
						+ "[ 5A][ 5B]    [ 5C][ 5D]\n"
						+ "[ 6A][ 6B]    [ 6C][ 6D]\n"
						+ "[ 7A][ 7B]    [ 7C][ 7D]\n"
						+ "[ 8A][ 8B]    [ 8C][ 8D]\n"
						+ "[ 9A][ 9B]    [ 9C][ 9D]\n"
						+ "[10A][ x ]    [10C][10D]\n"
						+ "[11A][11B]    [11C][11D]\n"
						+ "[12A][12B]    [12C][12D]\n"
						+ "[13A][13B]    [13C][13D]\n"
						+ "[14A][14B]    [14C][14D]\n"
						+ "[15A][15B]    [15C][15D]\n"
						+ "[16A][16B]    [16C][16D]\n"
						+ "[17A][17B]    [17C][17D]\n"
						+ "[18A][18B]    [18C][18D]\n"
						+ "[19A][19B]    [19C][ x ]\n";
		assertEquals(expected2, car.getSeatMap());
		
		//Release seats and check that the map updates properly
		car.seatFor("1A").release();
		String expected3 = "\nSecond Class Car #2"
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
						+ "[10A][ x ]    [10C][10D]\n"
						+ "[11A][11B]    [11C][11D]\n"
						+ "[12A][12B]    [12C][12D]\n"
						+ "[13A][13B]    [13C][13D]\n"
						+ "[14A][14B]    [14C][14D]\n"
						+ "[15A][15B]    [15C][15D]\n"
						+ "[16A][16B]    [16C][16D]\n"
						+ "[17A][17B]    [17C][17D]\n"
						+ "[18A][18B]    [18C][18D]\n"
						+ "[19A][19B]    [19C][ x ]\n";
		assertEquals(expected3, car.getSeatMap());
		
		car.seatFor("19D").release();
		car.seatFor("10B").release();
		String expected4 = "\nSecond Class Car #2"
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
		assertEquals(expected4, car.getSeatMap());
	}
}
