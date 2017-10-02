package edu.ncsu.csc216.train_travel.transportation;

/**
 * @author Noah Benveniste
 */
public abstract class TrainCar {
	/** Constant for the column index for seating column A */
	private static final int ASCII_A = 1;
	/** The number of the car's order in the Train */
	private int carNumber;
	
	/**
	 * 
	 * @param n
	 */
	public TrainCar(int n) {
		//TODO implement
	}
	
	/**
	 * Getter for the carNumber field
	 * @return the number of the car's order in the Train
	 */
	public int getCarIDNumber() {
		return this.carNumber;
	}
	
	/**
	 * Returns the number of unreserved seats in the car
	 * @return the number of unreserved seats in the car
	 */
	public abstract int openSeatsLeft();
	
	/**
	 * Returns the seat at a specified row,column index in the seating chart
	 * @param row the seat's row
	 * @param col the seat's column (a = 1, b = 2, etc)
	 * @return the seat at the specified row,column index
	 * @throws IllegalArgumentException if the row,column index is out of bounds
	 */
	public abstract Seat seatFor(int row, int col);
	
	/**
	 * Returns the seat at a specified location given a seat label
	 * @param label the label of the seat to return
	 * @return the seat with the given label
	 * @throws IllegalArgumentException if there is no seat with the given label in the car
	 */
	public abstract Seat seatFor(String label);
	
	/**
	 * Returns a string representation of the seating map for the car
	 * @return the string representation of the seating map
	 */
	public abstract String getSeatMap();
	
	/**
	 * Initializes the seats in a 2D array with their labels and car numbers
	 * @param s
	 */
	protected void initSeats(Seat[][] s) { //TODO make input param more descriptive
		//TODO implement
	}
	
	/**
	 * Returns a string representation of the actual seating map based on a 2D seat array
	 * and the location of the aisle
	 * @param seats a 2D array of seats
	 * @param aisleIndex the location of the aisle column in the seating map
	 * @return
	 */
	protected String drawSeatChart(Seat[][] seats, int aisleIndex) {
		//TODO implement
		return null;
	}
	
	/**
	 * Returns the seat in an array of seats corresponding to a given label
	 * @param label
	 * @param seats an array of seat objects for a car
	 * @return the seat at the given label in the array of seats
	 */
	protected Seat seatFor(String label, Seat[][] seats) {
		//TODO implement
		return null;
	}
}
