package edu.ncsu.csc216.train_travel.tickets;

import java.util.ArrayList;
import java.util.Scanner;

import edu.ncsu.csc216.train_travel.transportation.Seat;
import edu.ncsu.csc216.train_travel.transportation.Train;

/**
 * Abstract parent class for ComfortClass, EconomyClass and BicycleClass Reservations.
 * Stores state for the Train object that the Reservation is made for, the number of passengers
 * the Reservation is for, and the Reservation's unique ID number.
 * @author Noah Benveniste
 */
public abstract class Reservation {
	/** Max number of passengers for any reservation */
	private static final int MAX_PASSENGERS = 6;
	/** Used to keep track of reservation IDs, initialized to 1000; Gets incremented each time a new Reservation is created */
	private static int number = 1000;
	/** The ID number of the reservation */
	private int reservationID;
	/** The number of passengers in the reservation */
	private int numPassengers;
	/** The Train object that the Reservation is associated with */
	protected Train myTrain;
	
	/**
	 * Constructs a Reservation object. Called by the child classes, sets the state for myTrain,
	 * numPassengers and reservationID
	 * @param numPassengers the number of passengers the Reservation is for
	 * @param myTrain the Train object the Reservation is associated with
	 */
	public Reservation(int numPassengers, Train myTrain) {
		this.numPassengers = numPassengers;
		this.myTrain = myTrain;
		//Set the reservationID to the current number
		this.reservationID = number;
		//Increment the number so the next Reservation that is created has the correct ID
		number++;
	}
	
	/**
	 * Getter method for the number of passengers
	 * @return numPassengers
	 */
	public int getNumPassengers() {
		return this.numPassengers;
	}
	
	/**
	 * Getter method for the reservation ID
	 * @return reservationID
	 */
	public int getID() {
		return this.reservationID;
	}
	
	/**
	 * Method that determines if the number of passengers to be added to a Reservation is valid
	 * @param numPassengers the number of passengers to be added
	 * @return true if the number of passengers is greater than zero and less than or equal to 6,
	 * false otherwise
	 */
	public static boolean numPassengersInRange(int numPassengers) {
		return (numPassengers > 0 && numPassengers <= MAX_PASSENGERS);
	}
	
	/**
	 * Reserves seats in myTrain for this Reservation based on the rules in [UC3,S4-6], [UC5,S7]
	 * @throws IllegalArgumentException if there are not enough seats
	 */
	public abstract void chooseSeats();
	
	/**
	 * Releases the seats reserved by this Reservation and reserves the seats 
	 * @param seatString specifies which seat(s) will now be reserved for this Reservation, in the form dictated by UC7,S2-3
	 * @throws IllegalArgumentException if seatString represents invalid Seats for this Reservation
	 */
	public abstract void changeSeats(String seatString);
	
	/**
	 * Cancels this Reservation by releasing any reserved Seats associated with this Reservation and decrementing the
	 * proper passenger counts
	 */
	public abstract void cancel();
	
	/**
	 * Returns a string representation of this Reservation of the format dictated by UC9,S2
	 * @return the string representation of this Reservation
	 */
	public abstract String toPrint();
	
	/**
	 * Returns an array of seats based on an input string representing a list of seat labels of the format
	 * dictated by UC7,S2-3
	 * @param seatString a string representing a list of seats
	 * @return an array of Seats corresponding to the input string
	 * @throws IllegalArgumentException if the seat string is not formatted properly 
	 */
	protected Seat[] parseSeats(String seatString) {
		//if (!this.seatStringValidator(seatString)) {
		//	throw new IllegalArgumentException("Seat string is invalid");
		//}
		//Construct a scanner for the input string, tokenizing on commas
		Scanner lineReader = new Scanner(seatString);
		lineReader.useDelimiter(",");
		
		ArrayList<Seat> tempArray = new ArrayList<Seat>();
		String regex = "\\d{1,2}-\\d{1,2}[A-D]";
		
		while(lineReader.hasNext()) {
			String current = lineReader.next();
			current = current.trim(); //Get rid of excess whitespace
			
			if (!current.matches(regex)) {
				lineReader.close();
				throw new IllegalArgumentException("Input is improperly formatted");
			}
			
			String[] s = current.split("-");
			int carNum = Integer.parseInt(s[0]);
			String label = s[1];
			
			Seat seat = null;
			try {
				seat = myTrain.getSeatFor(carNum-1, label);
			} catch (IllegalArgumentException e) {
				lineReader.close();
				throw new IllegalArgumentException(e.getMessage());
			}
			
			tempArray.add(seat);
		}
		lineReader.close();
		
		Seat[] outArray = new Seat[tempArray.size()];
		for (int i = 0; i < tempArray.size(); i++) {
			outArray[i] = tempArray.get(i);
		}
		return outArray;
	}
	
	/**
	 * Reserves new seats for this Reservation and releases any that are currently reserved
	 * @param seatsToReserve an array containing the Seat objects to be reserved to this Reservation
	 * @param currentSeatArray the current array of seats
	 * @return the updated array of seats from theSeats
	 * @throws IllegalArgumentException if the seats to reserve are already reserve or there aren't enough seats
	 */
	protected Seat[] reassignSeats(Seat[] seatsToReserve, Seat[] currentSeatArray) {
		//Check that the new seats are valid
		//Check if there are enough seats passed for all the passengers included in the reservation or if there are too many
		if (seatsToReserve.length != this.getNumPassengers()) {
			throw new IllegalArgumentException("There are " + this.getNumPassengers() + 
					" passengers but " + seatsToReserve.length + " seats were entered");
		}
		//Check that all of the seats are unreserved
		for (int i = 0; i < seatsToReserve.length; i++) {
			if (seatsToReserve[i].isReserved()) { //Check that the seat is not reserved
				boolean seatMatch = false;
				//If it is reserved, first check that it is not already a part of this reservation
				for (int j = 0; j < currentSeatArray.length; j++) {
					if (seatsToReserve[i].equals(currentSeatArray[j])) { //If a seat matches, flag it
						seatMatch = true;
					} 
				}
				//If no matching seats were found, throw the IAE
				if (!seatMatch) {
					throw new IllegalArgumentException("One or more of the seats entered are already reserved");
				}
			}
		}
		//Loop through the current seating array, releasing all reserved seats
		for (int i = 0; i < currentSeatArray.length; i++) {
			currentSeatArray[i].release();
		}
		//Loop through the seats to reserve array, reserving all seats and then returning the array
		for (int i = 0; i < seatsToReserve.length; i++) {
			seatsToReserve[i].reserve();
		}
		return seatsToReserve;
	}
}
