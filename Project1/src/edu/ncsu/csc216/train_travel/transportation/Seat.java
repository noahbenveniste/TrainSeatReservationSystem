package edu.ncsu.csc216.train_travel.transportation;

/**
 * @author Noah Benveniste
 */
public class Seat implements Comparable<Seat> {
	
	/** The label for the seat (<row number><letter>) */
	private String label;
	/** The number of the train car that the seat exists in */
	private int trainCarID;
	/** The seat's reserved status */
	private boolean reserved;
	
	/**
	 * Constructs a Seat object by initializing the seat label and car number, as
	 * well as initializing the seat to unreserved
	 * @param label the seat's label in the form <row number><letter>
	 * @param trainCarID the number of the train car that the seat exists in
	 */
	public Seat(String label, int trainCarID) {
		this.label = label;
		this.trainCarID = trainCarID;
		this.reserved = false;
	}
	
	/**
	 * Method that reserves a seat
	 */
	public void reserve() {
		this.reserved = true;
	}
	
	/**
	 * Method that makes a seat unreserved
	 */
	public void release() {
		this.reserved = false;
	}
	
	/**
	 * Returns the seat's reserved status
	 * @return true if the seat is reserved, false if not
	 */
	public boolean isReserved() {
		return this.reserved;
	}
	
	/**
	 * Getter for the number of the train car that the seat exists in
	 * @return the train car number
	 */
	public int getTrainCarNumber() {
		return this.trainCarID;
	}
	
	/**
	 * Getter for the seat's label string
	 * @return the seat's label string (<row number><letter>)
	 */
	public String getLabel() {
		return this.label;
	}
	
	/**
	 * Returns a string representation of the seat in the form <car#>-<label>
	 * @return the string representation of the seat
	 */
	@Override
	public String toString() {
		//TODO implement
		return null;
	}
	
	/**
	 * Creates a comma-separated list of seats in a Seat array
	 * e.g. if the car number is 3, the form would be "[3-1A, 3-1B, 3-1C...]"
	 * @param seats the array of seats
	 * @return the list of seats as a string
	 */
	
	//Possibly used to print seats in a reservation?
	public static String printListOfSeats(Seat[] seats) { //TODO make input param more descriptive
		//TODO implement
		return null;
	}
	
	/**
	 * 
	 */
	public int compareTo(Seat o) {
		//TODO implement
		return 0;
	} 
	
	//TODO generate hashCode() and equals()
}
