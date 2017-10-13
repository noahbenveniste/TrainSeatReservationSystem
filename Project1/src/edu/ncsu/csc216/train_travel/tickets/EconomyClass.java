package edu.ncsu.csc216.train_travel.tickets;

import java.util.Arrays;

import edu.ncsu.csc216.train_travel.transportation.Seat;
import edu.ncsu.csc216.train_travel.transportation.SecondClassCar;
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
		train.incEconomyClassPassengers(numPassengers);
	}
	
	/**
	 * Method that is used by clients to construct a new EconomyClass Reservation
	 * @param numPassengers the number of passengers the Reservation is for
	 * @param myTrain the Train that the Reservation is for
	 * @return the newly created Reservation
	 * @throws IllegalArgumentException if the number of passengers is invalid or if myTrain
	 * cannot accommodate them
	 */
	public static EconomyClass newReservation(int numPassengers, Train myTrain) {
		if (!Reservation.numPassengersInRange(numPassengers)) {
			throw new IllegalArgumentException();
		}
		if (!myTrain.hasEconomyClassRoomFor(numPassengers)) {
			throw new IllegalArgumentException("Not enough room in Economy/Second Class");
		}
		return new EconomyClass(numPassengers, myTrain);
	}

	/**
	 * Reserves seats in myTrain for this Reservation based on the rules in [UC3,S4-6], [UC5,S7]
	 * @throws IllegalArgumentException if there are not enough seats
	 */
	@Override
	public void chooseSeats() {
		//Check that there is enough unreserved seats in all SecondClassCars in myTrain. Throw an IAE if not.
		if (myTrain.openSecondClassSeats() < this.getNumPassengers()) {
			throw new IllegalArgumentException("Not enough open seats in Economy/Second Class");
		}
		this.reservedSeats = true;
		//Start in seat 19A in the last SecondClassCar, indexing right across the row until all seats are assigned. Proceed up through
		//all rows in the car until all seats are found, or into the next car if the previous one is full
		int numFirstClassCars = (myTrain.numCars() - 1) / 3;
		int numSecondClassCars = myTrain.numCars() - numFirstClassCars - 1;
		this.theSeats = new Seat[this.getNumPassengers()];
		int idx = 0;
		
		//Start from seat 17A in the last SecondClassCar, indexing left to right, back to front of each car until all seats are assigned
		for (int i = (numSecondClassCars + numFirstClassCars) - 1; i > (numFirstClassCars - 1); i--) {
			for (int j = SecondClassCar.getNumRows() - 1; j >= 0; j--) { //Seat row loop, start in the last row
				for (int k = 0; k < SecondClassCar.getNumSeatsPerRow(); k++) { //Seat loop, start in the A seat
					//If the currently indexed seat is unreserved, reserve it, add it to arr, increment the index counter
					if (!(myTrain.getSeatFor(i, j, k).isReserved())) {
						myTrain.getSeatFor(i, j, k).reserve();
						theSeats[idx] = myTrain.getSeatFor(i, j, k);
						idx++;
					}
					//If all seats have been found for the number of passengers in the reservation, break out
					if (idx == this.getNumPassengers()) {
						return;
					}
				}
			}
		}
	}

	/**
	 * Releases the seats reserved by this Reservation and reserves the seats 
	 * @param seatString specifies which seat(s) will now be reserved for this Reservation, in the form dictated by UC7,S2-3
	 * @throws IllegalArgumentException if seatString represents invalid Seats for this Reservation
	 */
	@Override
	public void changeSeats(String seatString) {
		//Add a check that throws an IAE if this reservation did not originally have reserved seats
		if (!this.reservedSeats) {
			throw new IllegalArgumentException("Cannot change seats on an Economy/Second Class reservation that has no assigned seats");
		}
		Seat[] newSeats = null;
		//Throw an IAE if the string cannot be parsed
		try {
			newSeats = this.parseSeats(seatString);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		//Check that the new seats have valid car numbers for SecondClassCars
		for (int i = 0; i < newSeats.length; i++) {
			if (!(myTrain.isSecondClassCar(newSeats[i].getTrainCarNumber()))) {
				throw new IllegalArgumentException("Non-second class seat entered");
			}
		}
		//Pass the array of new seats to reserve along with the old seats to reassignSeats()
		try {
			newSeats = this.reassignSeats(newSeats, this.theSeats);
		} catch (IllegalArgumentException e) { //Throws an IAE if any of the seats are already reserved or if there are not enough or too many seats
			throw new IllegalArgumentException(e.getMessage());
		}
		//Set theSeats to the newly reserved seats
		this.theSeats = newSeats;
	}

	/**
	 * Cancels this Reservation by releasing any reserved Seats associated with this Reservation and decrementing the
	 * proper passenger counts
	 */
	@Override
	public void cancel() {
		for (int i = 0; i < this.theSeats.length; i++) { //If there are reserved seats, loop through theSeats releasing all seats
			this.theSeats[i].release();
		}
		//Decrement the number of economy class passengers
		int n = -1 * this.getNumPassengers();
		myTrain.incEconomyClassPassengers(n);
	}

	/**
	 * Returns a string representation of this Reservation of the format dictated by UC9,S2
	 * @return the string representation of this Reservation
	 */
	@Override
	public String toPrint() {
		if (this.reservedSeats) { 
			//Sort the seats first, then pass to printListOfSeats()
			Arrays.sort(theSeats);
			return "" + this.getID() + " Economy Class " + Seat.printListOfSeats(theSeats); //Print format for reserved EconomyClass Reservation
		} else {
			return "" + this.getID() + " Economy Class " + "(" + this.getNumPassengers() + ")"; //Print format for unreseved EconomyClass Reservation
		}
	}
}
