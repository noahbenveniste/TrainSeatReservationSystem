package edu.ncsu.csc216.train_travel.tickets;

import edu.ncsu.csc216.train_travel.transportation.Train;

/**
 * Concrete child class of Reservation. Represents a Reservation for a BicycleTransportCar.
 * Cannot have reserved seating.
 * @author Noah Benveniste
 */
public class BicycleClass extends Reservation {

	/**
	 * Constructs a new BicycleClass Reservation
	 * @param numPassengers the number of passengers the Reservation is for
	 * @param myTrain the Train that the Reservation is for
	 */
	private BicycleClass(int numPassengers, Train myTrain) {
		super(numPassengers, myTrain);
	}
	
	/**
	 * Method that is used by clients to construct a new BicycleClass Reservation
	 * @param numPassengers the number of passengers the Reservation is for
	 * @param myTrain the Train that the Reservation is for
	 * @return the newly created Reservation
	 */
	public static BicycleClass newReservation(int numPassengers, Train myTrain) {
		return new BicycleClass(numPassengers, myTrain);
	}

	/**
	 * Non-applicable method for BicycleClass Reservations
	 * @throws IllegalArgumentException if called on a BicycleClass Reservation
	 */
	@Override
	public void chooseSeats() throws IllegalArgumentException {
		throw new IllegalArgumentException();
	}

	/**
	 * Non-applicable method for BicycleClass Reservations
	 * @throws IllegalArgumentException if called on a BicycleClass Reservation
	 */
	@Override
	public void changeSeats(String s) throws IllegalArgumentException {
		throw new IllegalArgumentException();
	}

	/**
	 * Cancels this Reservation by decrementing the proper passenger counts
	 */
	@Override
	public void cancel() {
		int n = -1*this.getNumPassengers();
		myTrain.incBicyclePassengers(n);
	}

	/**
	 * Returns a string representation of this Reservation of the format dictated by UC9,S2
	 * @return the string representation of this Reservation
	 */
	@Override
	public String toPrint() {
		return "(" + this.getNumPassengers() + ")";
	}

}
