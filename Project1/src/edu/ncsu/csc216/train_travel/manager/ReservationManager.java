package edu.ncsu.csc216.train_travel.manager;

import java.util.ArrayList;

import edu.ncsu.csc216.train_travel.tickets.EconomyClass;
import edu.ncsu.csc216.train_travel.tickets.Reservation;
import edu.ncsu.csc216.train_travel.transportation.Train;

/**
 * Class that handles interaction between the model and GUI. Used to initialize a single
 * Train object that all Reservations will be made for and maintain a list of those Reservation
 * objects.
 * @author Noah Benveniste
 */
public class ReservationManager implements TicketMaster {
	/** The maximum number of passengers for any reservation, defined by UC3,S1 */
	private static final int MAX_PASSENGERS_PER_RESERVATION = 6;
	/** The Train object used for managing Reservations */
	private Train theTrain;
	/** ArrayList to store all Reservation objects for the Train, sorted by ID number */
	private ArrayList<Reservation> theReservations;
	
	/**
	 * Constructs a ReservationManager object
	 * @param numCars the number of cars that the Train object associated with this ReservationManager will have
	 * @throws IllegalArgumentException if the number of cars is out of range
	 */
	public ReservationManager(int numCars) {
		this.theTrain = new Train(numCars);
		this.theReservations = new ArrayList<Reservation>();
		
	}
	
	/**
	 * Returns the seating map for a specified car
	 * @param idx the index of the car in the Train's TrainCar array
	 * @return the seating map of the specified car as a string
	 */
	public String showMap(int idx) { //TODO make input parameter more descriptive
		//TODO implement
		return null;
	}
	
	/**
	 * Used to determine how many seat maps there are for the Train
	 * @return the number of seat maps for the Train
	 */
	public int numberOfSeatMaps() {
		//TODO implement
		return 0;
	}
	
	/**
	 * Method used to create a new Reservation
	 * @param numPassengers the number of passengers the Reservation is for
	 * @param kind the type of Reservation or type of train car the Reservation is for
	 * @return the newly created Reservation
	 * @throws IllegalArgumentException if the input parameters are improper or the Reservation cannot
	 * be accommodated
	 */
	public Reservation makeNewReservation(int numPassengers, String kind) {
		//Placeholder code to remove checkstyle errors saying unused fields
		if (numPassengers < 0 || numPassengers > MAX_PASSENGERS_PER_RESERVATION) {
			return null;
		} else {
			this.theReservations.add(EconomyClass.newReservation(numPassengers, this.theTrain));
		}
		return null;
	}
	
	/**
	 * Cancels an existing Reservation
	 * @param reservationString a string corresponding to the Reservation to be cancelled
	 */
	public void cancelReservation(String reservationString) {
		//TODO implement
	}
	
	/**
	 * Changes seats for a Reservation
	 * @param reservationString the Reservation to be changed
	 * @param newSeats the new seats for the indicated Reservation
	 */
	public void changeSeats(String reservationString, String newSeats) {
		//TODO implement
	}
	
	/**
	 * Returns the current list of Reservations as a string
	 * @return the list of Reservations as a string
	 */
	public String printReservationList() {
		//TODO implement
		return null;
	}
}
