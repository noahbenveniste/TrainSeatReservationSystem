package edu.ncsu.csc216.train_travel.transportation;

public class SecondClassCar extends TrainCar {
	/** Number of rows of seats for a second class car, defined by UC2,S1 */
	private static final int NUM_ROWS = 19;
	/** Number of seats in a row for a second class car, defined by UC2,S1 */
	private static final int NUM_SEATS_PER_ROW = 4;
	/** The number of seats in the car */
	private static final int CAPACITY = NUM_ROWS*NUM_SEATS_PER_ROW;
	/** The total number of seats and standing passengers the car can hold, defined by UC4,S4 */
	private static final int RESERVE_LIMIT = (int) ((int) CAPACITY*1.1);
	/** Array to store seats for the car */
	private Seat[][] seats;
	
	/**
	 * 
	 * @param carNumber
	 */
	public SecondClassCar(int carNumber) {
		super(carNumber);
	}
	
	/**
	 * Getter for the NUM_ROWS instance variable
	 * @return
	 */
	public static int getNumRows() {
		return NUM_ROWS;
	}
	
	/**
	 * Getter for the NUM_SEATS_PER_ROW instance variable
	 * @return
	 */
	public static int getNumSeatsPerRow() {
		return NUM_SEATS_PER_ROW;
	}
	
	/**
	 * Getter for 
	 * @return
	 */
	public static int getCapacity() {
		return RESERVE_LIMIT;
	}

	@Override
	public int openSeatsLeft() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Seat seatFor(int row, int col) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Seat seatFor(String label) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSeatMap() {
		// TODO Auto-generated method stub
		return null;
	}
}
