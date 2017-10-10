package edu.ncsu.csc216.train_travel.tickets;

import edu.ncsu.csc216.train_travel.transportation.Seat;
import edu.ncsu.csc216.train_travel.transportation.Train;

/**
 * Concrete child class of Reservation. Represents a Reservation for a FirstClassCar. Can only have
 * reserved seats.
 * @author Noah Benveniste
 */
public class ComfortClass extends Reservation {
	/** Array of seats for reserved seating assignments */
	private Seat[] theSeats;

	/**
	 * Constructs a new ComfortClass Reservation
	 * @param numPassengers the number of passengers the Reservation is for
	 * @param myTrain the Train that the Reservation is for
	 */
	private ComfortClass(int numPassengers, Train myTrain) {
		super(numPassengers, myTrain);
		myTrain.incComfortClassPassengers(numPassengers);
	}
	
	/**
	 * Method that is used by clients to construct a new ComfortClassReservation
	 * @param numPassengers the number of passengers the Reservation is for
	 * @param myTrain the Train that the Reservation is for
	 * @return the newly created Reservation
	 * @throws IllegalArgumentException if the number of passengers is invalid or if myTrain
	 * cannot accommodate them
	 */
	public static ComfortClass newReservation(int numPassengers, Train myTrain) {
		if (!Reservation.numPassengersInRange(numPassengers) || !myTrain.hasComfortClassRoomFor(numPassengers)) {
			throw new IllegalArgumentException();
		}
		return new ComfortClass(numPassengers, myTrain);
	}

	/**
	 * Reserves seats in myTrain for this Reservation based on the rules in [UC3,S4-6], [UC5,S7]
	 * @throws IllegalArgumentException if there are not enough seats
	 */
	@Override
	public void chooseSeats() {
		//First, check that there is enough FirstClassCar capacity for this Reservation on myTrain. If not, throw an IAE
		
		/** Standard car indexing: 3 or more passengers for this Reservation */
		//Start from seat 17A in the last FirstClassCar, indexing left to right, back to front of each car until all seats are assigned
		
		/** 2 Passenger Reservation */
		//Start with seats 1B and 1C in the first FirstClassCar. Index all B and C seats, front to back, until two free seats are found
		//in the same row. Repeat for all FirstClassCars if necessary. If there are not two free B and C seats in the same row for all
		//FirstClassCars on the train, proceed with standard car indexing to find the rest of the seats.
		
		/** 1 Passenger Reservation */
		//Start from seat 1A in the first FirstClassCar, indexing front to back all A seats. Repeat for all FirstClassCars if necessary.
		//If no free A seats are found, proceed with standard
	}

	/**
	 * Releases the seats reserved by this Reservation and reserves the seats 
	 * @param seatString specifies which seat(s) will now be reserved for this Reservation, in the form dictated by UC7,S2-3
	 * @throws IllegalArgumentException if seatString represents invalid Seats for this Reservation
	 */
	@Override
	public void changeSeats(String seatString) throws IllegalArgumentException {
		//Parse the seat string to get an array of seat objects
		Seat[] newSeats = this.parseSeats(seatString);
		//Pass the array of new seats to reserve along with the old seats to reassignSeats()
		newSeats = this.reassignSeats(this.theSeats, newSeats);
		//Set theSeats to the newly reserved seats
		this.theSeats = newSeats;
	}

	/**
	 * Cancels this Reservation by releasing any reserved Seats associated with this Reservation and decrementing the
	 * proper passenger counts
	 */
	@Override
	public void cancel() {
		//Release reserved seats
		//Decrement the number of comfort class passengers on the train
		int n = -1*this.getNumPassengers();
		myTrain.incComfortClassPassengers(n);
	}

	/**
	 * Returns a string representation of this Reservation of the format dictated by UC9,S2
	 * @return the string representation of this Reservation
	 */
	@Override
	public String toPrint() {
		return Seat.printListOfSeats(theSeats);
	}

}
