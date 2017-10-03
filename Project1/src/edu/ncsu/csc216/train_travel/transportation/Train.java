package edu.ncsu.csc216.train_travel.transportation;

public class Train {
	/** */
	private static final int MIN_CARS = 0;
	/** */
	private static final int MAX_CARS = 0;
	/** */
	private int numFirstClassCars;
	/** */
	private int comfortClassPassengers;
	/** */
	private int econcomyClassPassengers;
	/** */
	private int bicyclePassengers;
	
	/**
	 * 
	 * @param n
	 */
	public Train(int n) {
		
	}
	
	/**
	 * 
	 * @return
	 */
	public int numCars() {
		return 0;
	}
	
	/**
	 * 
	 * @param n
	 * @return
	 */
	public int incComfortClassPassengers(int n) {
		return 0;
	}
	
	/**
	 * 
	 * @param n
	 * @return
	 */
	public int incEconomyClassPassengers(int n) {
		return 0;
	}
	
	/**
	 * 
	 * @param n
	 * @return
	 */
	public int incBicyclePassengers(int n) {
		return 0;
	}
	
	/**
	 * 
	 * @param n1
	 * @param n2
	 * @param n3
	 * @return
	 */
	public Seat getSeatFor(int n1, int n2, int n3) {
		return null;
	}
	
	/**
	 * 
	 * @param n
	 * @param s
	 * @return
	 */
	public Seat getSeatFor(int n, String s) {
		return null;
	}
	
	/**
	 * 
	 * @param n
	 * @return
	 */
	public boolean hasComfortClassRoomFor(int n) {
		return false;
	}
	
	/**
	 * 
	 * @param n
	 * @return
	 */
	public boolean hasEconomyClassRoomFor(int n) {
		return false;
	}
	
	/**
	 * 
	 * @param n
	 * @return
	 */
	public boolean hasBicycleCarRoomFor(int n) {
		return false;
	}
	
	/**
	 * 
	 * @return
	 */
	public int openSecondClassSeats() {
		return 0;
	}
	
	/**
	 * 
	 * @return
	 */
	public int openFirstClassSeats() {
		return 0;
	}
	
	/**
	 * 
	 * @param n
	 * @return
	 */
	public boolean isBicycleCar(int n) {
		return false;
	}
	
	/**
	 * 
	 * @param n
	 * @return
	 */
	public boolean isSecondClassCar(int n) {
		return false;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isFirstClassCar(int n) {
		return false;
	}
	
	/**
	 * 
	 * @param n
	 * @return
	 */
	public String getCarSeatMap(int n) {
		return null;
	}
}
