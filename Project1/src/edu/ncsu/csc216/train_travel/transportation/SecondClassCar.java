package edu.ncsu.csc216.train_travel.transportation;

/**
 * Concrete child of TrainCar. A SecondClassCar has capacity for reserved and unreserved seats.
 * In addition to the state inherited from TrainCar, also stores an array of Seat objects 
 * that can be reserved.
 * @author Noah Benveniste
 */
public class SecondClassCar extends TrainCar {
	/** Number of rows of seats for a second class car, defined by UC2,S1 */
	private static final int NUM_ROWS = 19;
	/** Number of seats in a row for a second class car, defined by UC2,S1 */
	private static final int NUM_SEATS_PER_ROW = 4;
	/** The number of seats in the car */
	private static final int CAPACITY = NUM_ROWS * NUM_SEATS_PER_ROW;
	/** The total number of seats and standing passengers the car can hold, defined by UC4,S4 */
	private static final int RESERVE_LIMIT = (int) ((int) CAPACITY * 1.1);
	/** Array to store seats for the car */
	private Seat[][] seats;
	
	/**
	 * Constructs a SecondClassCar. In addition to calling the abstract parent's constructor,
	 * initializes the car's array of Seat objects
	 * @param carNumber the number corresponding to the car's order in the train
	 */
	public SecondClassCar(int carNumber) {
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
	 * @return the SecondClassCar's capacity i.e. the number of seats plus the number of
	 * standing passengers the car can accommodate
	 */
	public static int getCapacity() {
		return RESERVE_LIMIT;
	}

	/**
	 * Returns the number of unreserved seats in the car
	 * @return the number of unreserved seats in the car
	 */
	@Override
	public int openSeatsLeft() {
		// TODO Auto-generated method stub
		return 0;
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
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Returns the seat at a specified location given a seat label
	 * @param label the label of the seat to return
	 * @return the seat with the given label
	 * @throws IllegalArgumentException if there is no seat with the given label in the car
	 */
	@Override
	public Seat seatFor(String label) {
		return seatFor(label, this.seats);
	}

	/**
	 * Returns a string representation of the seating map for the car, including car-specific labels;
	 * Calls drawSeatChart()
	 * @return the string representation of the seating map
	 */
	@Override
	public String getSeatMap() {
		// TODO Auto-generated method stub
		return null;
	}
}
