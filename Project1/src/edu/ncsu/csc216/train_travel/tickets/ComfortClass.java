package edu.ncsu.csc216.train_travel.tickets;

import java.util.Arrays;

import edu.ncsu.csc216.train_travel.transportation.FirstClassCar;
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
	/** */
	private boolean seatsAlreadyChosen;

	/**
	 * Constructs a new ComfortClass Reservation
	 * @param numPassengers the number of passengers the Reservation is for
	 * @param myTrain the Train that the Reservation is for
	 */
	private ComfortClass(int numPassengers, Train myTrain) {
		super(numPassengers, myTrain);
		seatsAlreadyChosen = false;
		this.chooseSeats();
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
		if (!this.seatsAlreadyChosen) {
			//First, check that there is enough FirstClassCar capacity for this Reservation on myTrain. If not, throw an IAE
			if (myTrain.openFirstClassSeats() < this.getNumPassengers()) {
				throw new IllegalArgumentException("Not enough open seats");
			}
			//If the number of passengers is 1, try the preferred single passenger seating
			if (this.getNumPassengers() == 1) {
				if (this.tryOnePassengerPreferredSeating()) {
					this.onePassengerPreferredSeating();
				} else {
					this.standardAssignmentMethod();
				}
			} else if (this.getNumPassengers() == 2) { //If the number of passengers is 2, try the preferred two passenger seating
				if (this.tryTwoPassengerPreferredSeating()) {
					this.twoPassengerPreferredSeating();
				} else {
					this.standardAssignmentMethod();
				}
			} else {
				this.standardAssignmentMethod();
			}
			seatsAlreadyChosen = true;
		} else {
			return;
		}
	}
	
	/**
	 * Standard indexing method for assigning seats for Reservations with 3 or more passengers, or used if preferred
	 * seating assignments are unavailable
	 */
	private void standardAssignmentMethod() {
		int numFirstClassCars = (myTrain.numCars() - 1) / 3;
		this.theSeats = new Seat[this.getNumPassengers()];
		int idx = 0;
		
		//Start from seat 17A in the last FirstClassCar, indexing left to right, back to front of each car until all seats are assigned
		for (int i = numFirstClassCars - 1; i >= 0; i--) {
			for (int j = FirstClassCar.getNumRows() - 1; j >= 0; j--) { //Seat row loop, start in the last row
				for (int k = 0; k < FirstClassCar.getNumSeatsPerRow(); k++) { //Seat loop, start in the A seat
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
	 * Method that handles the preferred seating assignment method for one passenger ComfortClass Reservations
	 */
	private void onePassengerPreferredSeating() {
		int numFirstClassCars = (myTrain.numCars() - 1) / 3;
		for (int i = 0; i < numFirstClassCars; i++) { //Start in the first FirstClassCar
			for (int j = 0; j < FirstClassCar.getNumRows(); j++) { //For each row, check if both the B and C seats are available
				if (!(myTrain.getSeatFor(i, j, 0).isReserved())) { //If the current A seat is not reserved
					myTrain.getSeatFor(i, j, 0).reserve(); //Reserve it
					this.theSeats = new Seat[1]; //Add it to an array for theSeats
					this.theSeats[0] = myTrain.getSeatFor(i, j, 0);
					return;
				}
			}
		}
	}
	
	/**
	 * Method that handles the preferred seating assignment method for one passenger ComfortClass Reservations
	 */
	private void twoPassengerPreferredSeating() {
		int numFirstClassCars = (myTrain.numCars() - 1) / 3;
		for (int i = 0; i < numFirstClassCars; i++) { //Start in the first FirstClassCar
			for (int j = 0; j < FirstClassCar.getNumRows(); j++) { //For each row, check if both the B and C seats are available
				if (!(myTrain.getSeatFor(i, j, 1).isReserved()) && !(myTrain.getSeatFor(i, j, 2).isReserved())) { //If the B and C seat in this row are not reserved
					//Reserve them
					myTrain.getSeatFor(i, j, 1).reserve();
					myTrain.getSeatFor(i, j, 2).reserve();
					//Add them to theSeats array
					this.theSeats = new Seat[2];
					this.theSeats[0] = myTrain.getSeatFor(i, j, 1);
					this.theSeats[1] = myTrain.getSeatFor(i, j, 2);
					return;
				}
			}
		}
	}
	
	/**
	 * Helper method used to check if the preferred seating assignment for a one passenger Reservation is available
	 * Start from seat 1A in the first FirstClassCar, indexing front to back all A seats. Repeat for all FirstClassCars 
	 * if necessary. 
	 * @return true if there is an unreserved A seat in some FirstClassCar, false otherwise
	 */
	private boolean tryOnePassengerPreferredSeating() {
		int numFirstClassCars = (myTrain.numCars() - 1) / 3;
		for (int i = 0; i < numFirstClassCars; i++) { //Start in the first FirstClassCar
			for (int j = 0; j < FirstClassCar.getNumRows(); j++) { //For each row, check if both the B and C seats are available
				if (!(myTrain.getSeatFor(i, j, 0).isReserved())) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Helper method use to check if the preferred seating assignment for a two passenger Reservation is available
	 * Start with seats 1B and 1C in the first FirstClassCar. Index all B and C seats, front to back, until two free seats are found
	 * in the same row. Repeat for all FirstClassCars if necessary.
	 * @return true if there are a unreserved B and C seats in the same row in some row in some FirstClassCar, false otherwise
	 */
	private boolean tryTwoPassengerPreferredSeating() {
		int numFirstClassCars = (myTrain.numCars() - 1) / 3;
		for (int i = 0; i < numFirstClassCars; i++) { //Start in the first FirstClassCar
			for (int j = 0; j < FirstClassCar.getNumRows(); j++) { //For each row, check if both the B and C seats are available
				if (!(myTrain.getSeatFor(i, j, 1).isReserved()) && !(myTrain.getSeatFor(i, j, 2).isReserved())) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Releases the seats reserved by this Reservation and reserves the seats 
	 * @param seatString specifies which seat(s) will now be reserved for this Reservation, in the form dictated by UC7,S2-3
	 * @throws IllegalArgumentException if seatString represents invalid Seats for this Reservation
	 */
	@Override
	public void changeSeats(String seatString) {
		Seat[] newSeats = null;
		//Throw an IAE if the string cannot be parsed
		try {
			newSeats = this.parseSeats(seatString);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		//Check that the new seats have valid car numbers for FirstClassCars
		for (int i = 0; i < newSeats.length; i++) {
			if (!(myTrain.isFirstClassCar(newSeats[i].getTrainCarNumber()))) {
				throw new IllegalArgumentException("Non-first class seat entered");
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
		//Release reserved seats
		for (int i = 0; i < theSeats.length; i++) {
			theSeats[i].release();
		}
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
		//Sort array first, send to print list of seats
		Arrays.sort(theSeats);
		return "" + this.getID() + " Comfort Class " + Seat.printListOfSeats(this.theSeats);
	}
}
