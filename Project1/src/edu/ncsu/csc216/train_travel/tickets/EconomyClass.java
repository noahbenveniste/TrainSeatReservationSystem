package edu.ncsu.csc216.train_travel.tickets;

import edu.ncsu.csc216.train_travel.transportation.Seat;
import edu.ncsu.csc216.train_travel.transportation.Train;

/**
 * Concrete child class of Reservation. Represents a Reservation for a SecondClassCar. Can have reserved
 * or unreserved seats. Can also use capacity from BicycleTransportCar for an unreserved Reservation if
 * there is no room left in the SecondClassCars in the Train.
 * @author Noah Benveniste
 */
public class EconomyClass extends Reservation {
	/** Whether or not there are reserved seats for this Economy reservation */
	private boolean reservedSeats;
	/** Array of seats for reserved seating assignments */
	private Seat[] theSeats;

	/**
	 * Constructs a new EconomyClass Reservation
	 * @param numPassengers the number of passengers the Reservation is for
	 * @param myTrain the Train that the Reservation is for
	 */
	private EconomyClass(int numPassengers, Train train) {
		super(numPassengers, train);
	}
	
	/**
	 * Method that is used by clients to construct a new EconomyClass Reservation
	 * @param numPassengers the number of passengers the Reservation is for
	 * @param myTrain the Train that the Reservation is for
	 * @return the newly created Reservation
	 */
	public static EconomyClass newReservation(int numPassengers, Train myTrain) {
		return new EconomyClass(numPassengers, myTrain);
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
		if (this.reservedSeats) {
			for (int i = 0; i < this.theSeats.length; i++) { //If there are reserved seats, loop through theSeats releasing all seats
				this.theSeats[i].release();
			}
		}
		//Decrement the number of economy class passengers
		int n = -1*this.getNumPassengers();
		myTrain.incEconomyClassPassengers(n);
	}

	/**
	 * Returns a string representation of this Reservation of the format dictated by UC9,S2
	 * @return the string representation of this Reservation
	 */
	@Override
	public String toPrint() {
		if (this.reservedSeats) { 
			return Seat.printListOfSeats(theSeats); //Print format for reserved EconomyClass Reservation
		} else {
			return "(" + this.getNumPassengers() + ")"; //Print format for unreseved EconomyClass Reservation
		}
	}
}
