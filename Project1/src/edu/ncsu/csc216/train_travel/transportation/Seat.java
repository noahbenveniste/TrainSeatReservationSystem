package edu.ncsu.csc216.train_travel.transportation;

/**
 * POJO class that represents a Seat object. Stores state for a label in the form of <row number><letter>, 
 * the number of the train car that the seat exists in, as well as the seat's reserved status.
 * The Seat object's state is immutable after construction except for its reserved status.
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
		return this.getTrainCarNumber() + "-" + this.getLabel();
	}
	
	/**
	 * Creates a comma-separated list of seats in a Seat array in ascending sorted order
	 * e.g. if the car number is 3, the form would be "[3-1A, 3-1B, 3-1C...]"
	 * @param seats the array of seats
	 * @return the list of seats as a string
	 */
	public static String printListOfSeats(Seat[] seats) {
		//Fence-post
		String out = "[" + seats[0].toString();
		for (int i = 1; i < seats.length; i++) {
			out += "," + seats[i].toString();
		}
		//Close the string
		out += "]";
		return out;
	}
	
	/**
	 * Compares this Seat to another Seat first by car number and then by label.
	 * This Seat is greater than the other if it exists in a car further up in the
	 * train, if it exists in a row further up in the car, or if it is to the left
	 * of the other if they are in the same row. Used for sorting arrays of seats.
	 * @param o the other seat to compare to
	 * @return a negative int if this Seat comes before the other Seat, a positive
	 * int if this Seat comes after the other Seat, or zero if the Seats are equal.
	 */
	public int compareTo(Seat o) {
		String olabel = o.getLabel();
		int orowNum = 0;
		char orowLetter;
		if (olabel.length() == 2) {
			orowNum = Integer.parseInt("" + olabel.charAt(0));
		} else if (olabel.length() == 3) {
			orowNum = Integer.parseInt("" + olabel.charAt(0) + olabel.charAt(1));
		}
		orowLetter = olabel.charAt(olabel.length() - 1);
		
		String myLabel = this.getLabel();
		int rowNum = 0;
		char rowLetter;
		if (myLabel.length() == 2) {
			rowNum = Integer.parseInt("" + myLabel.charAt(0));
		} else if (myLabel.length() == 3) {
			rowNum = Integer.parseInt("" + myLabel.charAt(0) + myLabel.charAt(1));
		}
		rowLetter = myLabel.charAt(myLabel.length() - 1);
		
		//First, compare by car number
		if (this.getTrainCarNumber() != o.getTrainCarNumber()) {
			return this.getTrainCarNumber() - o.getTrainCarNumber();
		} else if (rowNum != orowNum) {//Compare by row number
			return rowNum - orowNum;
		} else if (rowLetter != orowLetter) {//Compare by row letter
			return rowLetter - orowLetter;
		} else { //If the seats are in the same car and have the same label, they are the same
			return 0;
		}
	}

	/**
	 * Generates a hash code for this Seat across all fields
	 * @return the hash code for this Seat
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		result = prime * result + (reserved ? 1231 : 1237);
		result = prime * result + trainCarID;
		return result;
	}

	/**
	 * Compares two Seat objects for equality across all fields.
	 * @param obj The other object to compare to
	 * @return true if this Seat and the input seat are equal across all fields,
	 * false if the input object is null, not of type Seat, or if this Seat
	 * and the input Seat have unequal trainCarID, label or reserved status.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Seat other = (Seat) obj;
		if (label == null) {
			if (other.label != null)
				return false;
		} else if (!label.equals(other.label))
			return false;
		if (reserved != other.reserved)
			return false;
		if (trainCarID != other.trainCarID)
			return false;
		return true;
	} 
}
