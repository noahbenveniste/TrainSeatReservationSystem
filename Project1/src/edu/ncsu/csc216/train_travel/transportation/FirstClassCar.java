package edu.ncsu.csc216.train_travel.transportation;

/**
 * Concrete child of TrainCar. A FirstClassCar only has capacity for reserved seats.
 * In addition to the state inherited from TrainCar, also stores an array of Seat objects 
 * that can be reserved.
 * @author Noah Benveniste
 */
public class FirstClassCar extends TrainCar {
	
	//TODO update javadoc method headers
	
	/** Number of rows in a first class car, defined by UC2,S1 */
	private static final int NUM_ROWS = 17;
	/** Number of seats in each row of a first class car, defined by US2,S1 */
	private static final int NUM_SEATS_PER_ROW = 3;
	/** The number of passengers the car can seat, defined by UC3,E3 */
	private static final int CAPACITY = NUM_ROWS * NUM_SEATS_PER_ROW;
	/** Zero-based index of the car's aisle, used by the seat map */
	private static final int AISLE_INDEX = 1;
	/** Array to store seats for the car */
	private Seat[][] seats;
	
	/**
	 * Constructs a FirstClassCar. In addition to calling the abstract parent's constructor,
	 * initializes the car's array of Seat objects
	 * @param carNumber the number corresponding to the car's order in the train
	 */
	public FirstClassCar(int carNumber) {
		super(carNumber);
		//Create an empty seat array with dimensions based on the number of rows and number of seats per row
		this.seats = new Seat[NUM_ROWS][NUM_SEATS_PER_ROW];
		//Populate the array with Seat objects
		initSeats(seats);
	}
	
	/**
	 * Getter for the number of rows
	 * @return the number of rows of seats in the car
	 */
	public static int getNumRows() {
		return NUM_ROWS;
	}
	
	/**
	 * Getter for the number of seats per row
	 * @return the number of seat per row in the car
	 */
	public static int getNumSeatsPerRow() {
		return NUM_SEATS_PER_ROW;
	}
	
	/**
	 * Getter for the car's capacity
	 * @return the FirstClassCar's capacity i.e. the number of seats
	 */
	public static int getCapacity() {
		return CAPACITY;
	}

	/**
	 * Returns the number of unreserved seats in the car
	 * @return the number of unreserved seats in the car
	 */
	@Override
	public int openSeatsLeft() {
		int openSeatCount = 0;
		for (int i = 0; i < this.seats.length; i++) {
			for (int j = 0; j < this.seats[i].length; j++) {
				if (!seats[i][j].isReserved()) {
					openSeatCount++;
				}
			}
		}
		return openSeatCount;
	}

	/**
	 * Returns the seat at a specified ZERO-BASED row,column index in the seating chart
	 * @param row the seat's row
	 * @param col the seat's column (a = 0, b = 1, etc)
	 * @return the seat at the specified row,column index
	 * @throws IllegalArgumentException if the row,column index is out of bounds
	 */
	@Override
	public Seat seatFor(int row, int col) {
		if (row < 0 || row >= NUM_ROWS || col < 0 || col >= NUM_SEATS_PER_ROW) {
			throw new IllegalArgumentException("Row or column out of bounds");
		} else {
			return this.seats[row][col];
		}
	}

	/**
	 * Returns the seat at a specified location given a seat label
	 * @param label the label of the seat to return
	 * @return the seat with the given label
	 * @throws IllegalArgumentException if there is no seat with the given label in the car
	 */
	@Override
	public Seat seatFor(String label) {
		Seat s = null;
		try {
			s = seatFor(label, this.seats);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		return s;
	}

	/**
	 * Returns a string representation of the seating map for the car, including car-specific labels;
	 * Calls drawSeatChart()
	 * @return the string representation of the seating map
	 */
	@Override
	public String getSeatMap() {
		return "First Class Car #" + this.getCarIDNumber() + "\n" + "\n" + drawSeatChart(this.seats, AISLE_INDEX);
	}
}
