package edu.ncsu.csc216.train_travel.transportation;

public class FirstClassCar extends TrainCar {
	
	//TODO update javadoc method headers
	
	/** Number of rows in a first class car, defined by UC2,S1 */
	private static final int NUM_ROWS = 17;
	/** Number of seats in each row of a first class car, defined by US2,S1 */
	private static final int NUM_SEATS_PER_ROW = 3;
	/** The number of passengers the car can seat, defined by UC3,E3 */
	private static final int CAPACITY = NUM_ROWS*NUM_SEATS_PER_ROW;
	/** Array to store seats for the car */
	private Seat[][] seats;
	
	/**
	 * 
	 * @param carNumber
	 */
	public FirstClassCar(int carNumber) {
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
	 * Getter for the CAPACITY instance variable
	 * @return
	 */
	public static int getCapacity() {
		return CAPACITY;
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
