package edu.ncsu.csc216.train_travel.transportation;

public class BicycleTransportCar extends TrainCar {
	/** Capacity of a bicycle car for bicycle passengers, defined by UC6,S4 */
	private static final int CAPACITY = 20;
	/** Error message */
	private static final String SEAT_ERROR_MESSAGE = "Cannot reserve seats in a bicycle transport car";
	
	/**
	 * 
	 * @param carNumber
	 */
	public BicycleTransportCar(int carNumber) {
		super(carNumber);
	}

	/**
	 * Getter for the CAPACITY instance variable
	 * @return
	 */
	public static int getCapacity() {
		return CAPACITY;
	}
	
	@Override
	public int openSeatsLeft() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Seat seatFor(int row, int col) {
		throw new IllegalArgumentException();
	}

	@Override
	public Seat seatFor(String label) {
		throw new IllegalArgumentException();
	}

	@Override
	public String getSeatMap() {
		// TODO Auto-generated method stub
		return null;
	}
}
