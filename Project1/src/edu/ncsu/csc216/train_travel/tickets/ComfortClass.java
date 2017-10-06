package edu.ncsu.csc216.train_travel.tickets;

import edu.ncsu.csc216.train_travel.transportation.Train;

/**
 * Concrete child class of Reservation. Represents a Reservation for a FirstClassCar. Can only have
 * reserved seats.
 * @author Noah Benveniste
 */
public class ComfortClass extends Reservation {

	/**
	 * Constructs a new ComfortClass Reservation
	 * @param numPassengers the number of passengers the Reservation is for
	 * @param myTrain the Train that the Reservation is for
	 */
	private ComfortClass(int numPassengers, Train myTrain) {
		super(numPassengers, myTrain);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Method that is used by clients to construct a new ComfortClassReservation
	 * @param numPassengers the number of passengers the Reservation is for
	 * @param myTrain the Train that the Reservation is for
	 * @return the newly created Reservation
	 */
	public static ComfortClass newReservation(int numPassengers, Train myTrain) {
		return null;
	}

	/**
	 * Reserves seats in myTrain for this Reservation based on the rules in [UC3,S4-6], [UC5,S7]
	 * @throws IllegalArgumentException if there are not enough seats
	 */
	@Override
	public void chooseSeats() {
		// TODO Auto-generated method stub
	}

	/**
	 * Releases the seats reserved by this Reservation and reserves the seats 
	 * @param seatString specifies which seat(s) will now be reserved for this Reservation, in the form dictated by UC7,S2-3
	 * @throws IllegalArgumentException if seatString represents invalid Seats for this Reservation
	 */
	@Override
	public void changeSeats(String seatString) {
		// TODO Auto-generated method stub
	}

	/**
	 * Cancels this Reservation by releasing any reserved Seats associated with this Reservation and decrementing the
	 * proper passenger counts
	 */
	@Override
	public void cancel() {
		// TODO Auto-generated method stub
	}

	/**
	 * Returns a string representation of this Reservation of the format dictated by UC9,S2
	 * @return the string representation of this Reservation
	 */
	@Override
	public String toPrint() {
		// TODO Auto-generated method stub
		return null;
	}

}
