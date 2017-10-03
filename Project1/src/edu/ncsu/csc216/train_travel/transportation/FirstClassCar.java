package edu.ncsu.csc216.train_travel.transportation;

public class FirstClassCar extends TrainCar {
	
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
}
