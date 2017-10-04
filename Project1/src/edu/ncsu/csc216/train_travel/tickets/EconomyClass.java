package edu.ncsu.csc216.train_travel.tickets;

import edu.ncsu.csc216.train_travel.transportation.Train;

public class EconomyClass extends Reservation{
	/** */
	private boolean reservedSeats;

	/**
	 * 
	 * @param numPassengers
	 * @param train
	 */
	private EconomyClass(int numPassengers, Train train) {
		super(numPassengers, train);
	}
	
	/**
	 * 
	 * @param numPassengers
	 * @param train
	 * @return
	 */
	public static EconomyClass newReservation(int numPassengers, Train train) {
		EconomyClass eco = new EconomyClass(numPassengers, train);
		return eco;
	}

	@Override
	public void chooseSeats() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeSeats(String s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cancel() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toPrint() {
		// TODO Auto-generated method stub
		return null;
	}

}
