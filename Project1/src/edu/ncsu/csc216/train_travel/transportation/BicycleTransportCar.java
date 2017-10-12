package edu.ncsu.csc216.train_travel.transportation;

/**
 * Concrete child of TrainCar. A BicycleTransportCar has unreserved seating capacity
 * for EconomyClass and BicycleClass reservations.
 * @author Noah Benveniste
 */
public class BicycleTransportCar extends TrainCar {
	/** Capacity of a bicycle car for BicycleClass passengers, defined by UC6,S4 */
	private static final int CAPACITY = 20;
	/** Error message */
	private static final String SEAT_ERROR_MESSAGE = "Cannot reserve seats in a bicycle transport car";
	
	/**
	 * Constructs a BicycleTransportCar. Calls the abstract parent's constructor
	 * with the carNumber
	 * @param carNumber the number corresponding to the car's order in the train
	 */
	public BicycleTransportCar(int carNumber) {
		super(carNumber);
	}

	/**
	 * Getter for the car's capacity
	 * @return the BicycleTransportCar's capacity i.e. the number of BicycleClass passengers 
	 * the car can hold
	 */
	public static int getCapacity() {
		return CAPACITY;
	}
	
	/**
	 * Non-applicable method for BicycleTransportCar methods because these objects
	 * do not store Seat arrays
	 */
	@Override
	public int openSeatsLeft() {
		return 0;
	}

	/**
	 * Non-applicable method for BicycleTransportCar methods because these objects
	 * do not store Seat arrays
	 * @throws IllegalArgumentException if called on a BicycleTransportCar object
	 */
	@Override
	public Seat seatFor(int row, int col) {
		throw new IllegalArgumentException(SEAT_ERROR_MESSAGE);

	}

	/**
	 * Non-applicable method for BicycleTransportCar methods because these objects
	 * do not store Seat arrays
	 * @throws IllegalArgumentException if called on a BicycleTransportCar object
	 */
	@Override
	public Seat seatFor(String label) {
		throw new IllegalArgumentException(SEAT_ERROR_MESSAGE);
	}

	/**
	 * Non-applicable method for BicycleTransportCar methods because these objects
	 * do not store Seat arrays
	 */
	@Override
	public String getSeatMap() {
		return "Bicycle Transport Car #" + this.getCarIDNumber() + "\n" + " Seats cannot be reserved.";
	}
}
