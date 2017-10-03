package edu.ncsu.csc216.train_travel.transportation;

public class BicycleTransportCar extends TrainCar {
	/** Capacity of a bicycle car, defined by UC6, S4 (20 eco passengers, 20 bicycle passengers) */
	private static final int CAPACITY = 40;
	
	/**
	 * 
	 * @param carNumber
	 */
	public BicycleTransportCar(int carNumber) {
		super(carNumber);
	}

	@Override
	public int openSeatsLeft() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Seat seatFor(int row, int col) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Seat seatFor(String label) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSeatMap() {
		// TODO Auto-generated method stub
		return null;
	}
}
