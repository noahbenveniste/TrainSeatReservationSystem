package edu.ncsu.csc216.train_travel.tickets;

import edu.ncsu.csc216.train_travel.transportation.Seat;
import edu.ncsu.csc216.train_travel.transportation.Train;

/**
 * @author Noah Benveniste
 */
public abstract class Reservation {
	
	/** Max number of passengers for any reservation */
	private static final int MAX_PASSENGERS = 6;
	/** Used to keep track of reservation IDs, initialized to 1000 */
	private static int number;
	/** The ID number of the reservation */
	private int reservationID;
	/** The number of passengers in the reservation */
	private int numPassengers;
	
	/**
	 * 
	 * @param numPassengers
	 * @param train
	 */
	public Reservation(int numPassengers, Train train) {
		this.numPassengers = numPassengers;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getNumPassengers() {
		return 0;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getID() {
		return 0;
	}
	
	/**
	 * 
	 * @param numPassengers
	 * @return
	 */
	public static boolean numPassengersInRange(int numPassengers) {
		return false;
	}
	
	/**
	 * 
	 */
	public abstract void chooseSeats();
	
	/**
	 * 
	 * @param s
	 */
	public abstract void changeSeats(String s); //TODO make input param more descriptive
	
	/**
	 * 
	 */
	public abstract void cancel();
	
	/**
	 * 
	 * @return
	 */
	public abstract String toPrint();
	
	/**
	 * 
	 * @param s
	 * @return
	 */
	protected Seat[] parseSeats(String s) { //TODO make input param more descriptive
		return null;
	}
	
	/**
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	protected Seat[] reassignSeats(Seat[] s1, Seat[] s2) { //TODO make input param more descriptive
		return null;
	}
}
