package edu.ncsu.csc216.train_travel.tickets;

import edu.ncsu.csc216.train_travel.transportation.Train;

public class EconomyClass extends Reservation{
	/** */
	private boolean reservedSeats;

	private EconomyClass(int numPassengers, Train train) {
		super(numPassengers, train);
		// TODO Auto-generated constructor stub
	}
	
	public static EconomyClass newReservation(int numPassengers, Train train) {
		return null;
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
