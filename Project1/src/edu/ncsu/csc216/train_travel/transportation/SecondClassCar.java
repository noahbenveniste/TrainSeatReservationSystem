package edu.ncsu.csc216.train_travel.transportation;

public class SecondClassCar extends TrainCar {
	/** Number of rows of seats for a second class car, defined by UC2,S1 */
	private static final int NUM_ROWS = 19;
	/** Number of seats in a row for a second class car, defined by UC2,S1 */
	private static final int NUM_SEATS_PER_ROW = 4;
	/** The number of seats in the car */
	private static final int CAPACITY = NUM_ROWS*NUM_SEATS_PER_ROW;
	/** The total number of seats and standing passengers the car can hold, defined by UC4,S4 */
	private static final int RESERVE_LIMIT = CAPACITY*1.1;
	
	/**
	 * 
	 * @param carNumber
	 */
	public SecondClassCar(int carNumber) {
		super(carNumber);
	}
}
