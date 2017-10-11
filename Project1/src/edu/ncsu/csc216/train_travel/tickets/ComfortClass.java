package edu.ncsu.csc216.train_travel.tickets;

import java.util.stream.Stream;

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
	}
	
	/**
	 * Standard indexing method for assigning seats for Reservations with 3 or more passengers, or used if preferred
	 * seating assignments are unavailable
	 */
	private void standardAssignmentMethod() {
		int numFirstClassCars = (myTrain.numCars() - 1) / 3;
		Seat[] arr = new Seat[this.getNumPassengers()];
		int idx = 0;
		
		//Start from seat 17A in the last FirstClassCar, indexing left to right, back to front of each car until all seats are assigned
		for (int i = numFirstClassCars - 1; i >= 0; i--) {
			for (int j = FirstClassCar.getNumRows() - 1; j >= 0; j--) { //Seat row loop, start in the last row
				for (int k = 0; k < FirstClassCar.getNumSeatsPerRow(); k++) { //Seat loop, start in the A seat
					//If the currently indexed seat is unreserved, reserve it, add it to arr, increment the index counter
					if (!myTrain.getSeatFor(i, j, k).isReserved()) {
						myTrain.getSeatFor(i, j, k).reserve();
						arr[idx] = myTrain.getSeatFor(i, j, k);
						idx++;
					}
					//If all seats have been found for the number of passengers in the reservation, break out
					if (idx == this.getNumPassengers() - 1) {
						break;
					}
				}
			}
		}
		this.theSeats = arr;
	}
	
	/**
	 * Method that handles the preferred seating assignment method for one passenger ComfortClass Reservations
	 */
	private void onePassengerPreferredSeating() {
		int numFirstClassCars = (myTrain.numCars() - 1) / 3;
		for (int i = 0; i < numFirstClassCars; i++) { //Start in the first FirstClassCar
			for (int j = 0; j < FirstClassCar.getNumRows(); j++) { //For each row, check if both the B and C seats are available
				if (!myTrain.getSeatFor(i, j, 0).isReserved()) { //If the current A seat is not reserved
					myTrain.getSeatFor(i, j, 0).reserve(); //Reserve it
					this.theSeats = new Seat[1]; //Add it to an array for theSeats
					this.theSeats[0] = myTrain.getSeatFor(i, j, 0);
					break;
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
				if (!myTrain.getSeatFor(i, j, 1).isReserved() && !myTrain.getSeatFor(i, j, 2).isReserved()) { //If the B and C seat in this row are not reserved
					//Reserve them
					myTrain.getSeatFor(i, j, 1).reserve();
					myTrain.getSeatFor(i, j, 2).reserve();
					//Add them to theSeats array
					this.theSeats = new Seat[2];
					this.theSeats[0] = myTrain.getSeatFor(i, j, 1);
					this.theSeats[1] = myTrain.getSeatFor(i, j, 2);
					break;
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
				if (!myTrain.getSeatFor(i, j, 0).isReserved()) {
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
				if (!myTrain.getSeatFor(i, j, 1).isReserved() && !myTrain.getSeatFor(i, j, 2).isReserved()) {
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
		if (!this.seatStringValidator(seatString)) {
			throw new IllegalArgumentException("Seat string is invalid");
		}
		Seat[] newSeats = this.parseSeats(seatString);
		//Pass the array of new seats to reserve along with the old seats to reassignSeats()
		newSeats = this.reassignSeats(this.theSeats, newSeats);
		//Set theSeats to the newly reserved seats
		this.theSeats = newSeats;
	}

	/**
	 * 
	 * @param seatString
	 * @return
	 */
	private boolean seatStringValidator(String seatString) {
		int numFirstClassCars = (myTrain.numCars() - 1) / 3;
		int numPassengers = getNumPassengers();
		//Must contain a one or two digit integer followed by a dash followed by a seat label. The initial integer 
		//(the car number) must refer to a FirstClassCar in myTrain. The label must be valid for a FirstClassCar i.e.
		//it has an integer from 1-17 followed by a letter A, B or C
		String format = "\\d{1, 2}-([1-9]|1[0-7])[A-C](,\\d{1, 2}-([1-9]|1[0-7])[A-C]){0, %d}";
		String regex = String.format(format, numPassengers - 1);
	
		if (!seatString.matches(regex)) {
			return false;
		}
		
		return Stream.of(seatString.split(",")).map((string) -> string.split("-")[0]).map(Integer::parseInt).allMatch((carNum) -> (carNum >= 1 && carNum <= numFirstClassCars));
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
