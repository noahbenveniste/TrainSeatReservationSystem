package edu.ncsu.csc216.train_travel.manager;

import java.util.ArrayList;

import edu.ncsu.csc216.train_travel.tickets.BicycleClass;
import edu.ncsu.csc216.train_travel.tickets.ComfortClass;
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
	/** ArrayList used to store the Reservation type at the index corresponding to the Reservation in theReservations */
	private ArrayList<String> reservationTypes;
	
	/**
	 * Constructs a ReservationManager object
	 * @param numCars the number of cars that the Train object associated with this ReservationManager will have
	 * @throws IllegalArgumentException if the number of cars is out of range
	 */
	public ReservationManager(int numCars) {
		try {
			this.theTrain = new Train(numCars);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		this.theReservations = new ArrayList<Reservation>();
	}
	
	/**
	 * Returns the seating map for a specified car
	 * @param idx the index of the car in the Train's TrainCar array
	 * @return the seating map of the specified car as a string
	 */
	public String showMap(int idx) {
		return this.theTrain.getCarSeatMap(idx);
	}
	
	/**
	 * Used to determine how many seat maps there are for the Train
	 * @return the number of seat maps for the Train
	 */
	public int numberOfSeatMaps() {
		return this.theTrain.numCars();
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
		if (numPassengers < 1 || numPassengers > MAX_PASSENGERS_PER_RESERVATION) {
			throw new IllegalArgumentException("Invalid number of passengers");
		}
		Reservation r = null;
		String type = null;
		if (kind.toLowerCase().charAt(0) == 'f' || kind.toLowerCase().charAt(0) == 'c') {
			try {
				r = ComfortClass.newReservation(numPassengers, this.theTrain);
			} catch (IllegalArgumentException e) {
				throw new IllegalArgumentException(e.getMessage());
			}
			type = "Comfort Class";
		} else if (kind.toLowerCase().charAt(0) == 's' || kind.toLowerCase().charAt(0) == 'e') {
			try {
				r = EconomyClass.newReservation(numPassengers, this.theTrain);
			} catch (IllegalArgumentException e) {
				throw new IllegalArgumentException(e.getMessage());
			}
			type = "Economy Class";
		} else if (kind.toLowerCase().charAt(0) == 'b') {
			try {
				r = BicycleClass.newReservation(numPassengers, this.theTrain);
			} catch (IllegalArgumentException e) {
				throw new IllegalArgumentException(e.getMessage());
			}
			type = "Bicycle Class";
		} else {
			throw new IllegalArgumentException("Invalid reservation type");
		}
		if (this.theReservations.size() == 0) {
			this.theReservations.add(r);
		} else {
			for (int i = 0; i < theReservations.size(); i++) {
				if (this.theReservations.get(i).getID() > r.getID()) {
					this.theReservations.add(i, r);
					this.reservationTypes.add(i, type);
				}
			}
		}
		return null;
	}
	
	/**
	 * Cancels an existing Reservation
	 * @param reservationString a string corresponding to the Reservation to be cancelled
	 */
	public void cancelReservation(String reservationIDString) {
		int reservationID = Integer.parseInt("" + reservationIDString.charAt(0) + reservationIDString.charAt(1) + 
				reservationIDString.charAt(2) + reservationIDString.charAt(3));
		for (int i = 0; i < this.theReservations.size(); i++) {
			if (this.theReservations.get(i).getID() == reservationID) {
				try {
					this.theReservations.get(i).cancel();
					this.theReservations.remove(i);
					this.reservationTypes.remove(i);
				} catch (IllegalArgumentException e) {
					throw new IllegalArgumentException(e.getMessage());
				}
			}
		}
		throw new IllegalArgumentException("No reservation with the specified ID exists");
	}
	
	/**
	 * Changes seats for a Reservation
	 * @param reservationString the Reservation to be changed
	 * @param newSeats the new seats for the indicated Reservation
	 */
	public void changeSeats(String reservationIDString, String newSeats) {
		int reservationID = Integer.parseInt("" + reservationIDString.charAt(0) + reservationIDString.charAt(1) + 
				reservationIDString.charAt(2) + reservationIDString.charAt(3));
		for (int i = 0; i < this.theReservations.size(); i++) {
			if (this.theReservations.get(i).getID() == reservationID) {
				this.theReservations.get(i).changeSeats(newSeats);
			}
		}
		throw new IllegalArgumentException("No reservation with the specified ID exists");
	}
	
	/**
	 * Returns the current list of Reservations as a string
	 * @return the list of Reservations as a string
	 */
	public String printReservationList() {
		//Fencepost
		String out = "" + theReservations.get(0).getID() + " " + reservationTypes.get(0) + " " + theReservations.get(0).toPrint();
		for (int i = 1; i < theReservations.size(); i++) {
			out += "\n" + theReservations.get(i).getID() + " " + reservationTypes.get(i) + " " + theReservations.get(i).toPrint();
		}
		return out;
	}
}
