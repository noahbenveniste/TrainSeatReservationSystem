package edu.ncsu.csc216.train_travel.manager;

import edu.ncsu.csc216.train_travel.tickets.Reservation;

/** 
 * TicketMaster describes the behaviors needed to manage reservations for a train.
 * @author Jo Perry
 */
public interface TicketMaster {

	/**
	 * Create a new reservation.
	 * @param numPassengers number of passengers on the new reservation
	 * @param kind determines which kind of reservation to create
	 * @return the newly created reservation
	 */
	Reservation makeNewReservation(int numPassengers, String kind);
	
	/**
	 * Cancel a reservation.
	 * @param reservationString determines which reservation to cancel
	 */
	void cancelReservation(String reservationString);
	
	/**
	 * Change seats for a reservation.
	 * @param reservationString determines which reservation to change
	 * @param newSeats a string representation of the new seats to assign to the reservation
	 */
	void changeSeats(String reservationString, String newSeats);
	
	/**
	 * Show the list of current reservations as a string.
	 * @return a string representation of the reservation list
	 */	
	String printReservationList();
	
	/**
	 * Show a seating map for a train car.
	 * @param idx index for which train map to show
	 * @return a string representation of the train car map.
	 */
	String showMap(int idx);
	
	/**
	 * How many seat maps are there?
	 * @return the number of seat maps
	 */
	int numberOfSeatMaps();
	
}