package edu.ncsu.csc216.train_travel.transportation;

/**
 * A concrete class made up of FirstClassCars, SecondClassCars and BicycleTransportCars.
 * Also keeps track of the total number of ComfortClass, EconomyClass and BicycleClass passengers.
 * @author Noah Benveniste
 */
public class Train {
	/** The minimum number of cars that a Train can have, defined by UC1,S1 */
	private static final int MIN_CARS = 4;
	/** The maximum number of cars that a Train can have, defined by UC1,S1 */
	private static final int MAX_CARS = 12;
	/** The array of TrainCar objects that comprise the train*/
	private TrainCar[] car;
	/** The number of FirstClassCars in the Train */
	private int numFirstClassCars;
	/** Number of passengers with ComfortClass Reservations */
	private int comfortClassPassengers;
	/** Number of passengers with EconomyClass Reservations */
	private int econcomyClassPassengers;
	/** Number of passengers with BicycleClass Reservations */
	private int bicyclePassengers;
	
	/** Equal to the sum of the capacities of all FirstClassCars in the Train */
	private final int TOTAL_COMFORT_CAPACITY;
	/** Equal to the sum of all economy car capacities + the bicycle car overflow capacity of 20 */
	private final int TOTAL_ECONOMY_CAPACITY;
	/** Only one bicycle car per train with a capacity of 20 */
	private final int TOTAL_BICYCLE_CAPACITY;
	
	/**
	 * Constructs a Train object of a specified length with no passengers
	 * @param numberOfCars the length of the Train in number of cars
	 * @throws IllegalArgumentException if the length specified is not between 4 and 12
	 */
	public Train(int numberOfCars) {
		if (numberOfCars < MIN_CARS || numberOfCars > MAX_CARS) {
			throw new IllegalArgumentException("Train must have at least " + MIN_CARS + " but no more than " + MAX_CARS + " cars.");
		} else {
			//Initialize the car array
			this.setCar(numberOfCars);
			//Initialize the passenger counters to zero
			this.comfortClassPassengers = 0;
			this.econcomyClassPassengers = 0;
			this.bicyclePassengers = 0;
			
			//Equal to the sum of the capacities of all FirstClassCars in the Train
			this.TOTAL_COMFORT_CAPACITY = FirstClassCar.getCapacity() * this.numFirstClassCars;
			//Equal to the sum of all economy car capacities + the bicycle car overflow capacity of 20
			this.TOTAL_ECONOMY_CAPACITY = SecondClassCar.getCapacity() * (this.numCars() - this.numFirstClassCars - 1) + 20;
			//Only one bicycle car per train with a capacity of 20
			this.TOTAL_BICYCLE_CAPACITY = 20;
		}
	}
	
	/**
	 * Private helper method that initializes that car array with the proper number of
	 * each car type based on the length of the train, as defined by UC1,S2
	 * @param numberOfCars the number of cars the train should have
	 */
	private void setCar(int numberOfCars) {
		this.car = new TrainCar[numberOfCars];
		this.numFirstClassCars = (numberOfCars - 1) / 3;
		//Populate the car array with FirstClassCars
		for (int i = 0; i < this.numFirstClassCars; i++) {
			this.car[i] = new FirstClassCar(i + 1);
		}
		//Add the SecondClassCars
		for (int j = this.numFirstClassCars; j < numberOfCars - 1; j++) {
			this.car[j] = new SecondClassCar(j + 1);
		}
		//The last car is a BicycleTransportCar
		this.car[numberOfCars - 1] = new BicycleTransportCar(numberOfCars);
	}
	
	/**
	 * Gets the number of cars in the train
	 * @return the number of cars in the train
	 */
	public int numCars() {
		return this.car.length;
	}
	
	/**
	 * Increments the number of ComfortClass passengers by a specified amount
	 * @param n the number of new ComfortClass passengers to be added
	 * @return the updated number of ComfortClass passengers
	 */
	public int incComfortClassPassengers(int n) {
		this.comfortClassPassengers += n;
		return this.comfortClassPassengers;
	}
	
	/**
	 * Increments the number of EconomyClass passengers by a specified amount
	 * @param n the number of new EconomyClass passengers to be added
	 * @return the updated number of EconomyClass passengers
	 */
	public int incEconomyClassPassengers(int n) {
		this.econcomyClassPassengers += n;
		return this.econcomyClassPassengers;
	}
	
	/**
	 * Increments the number of BicycleClass passengers by a specified amount
	 * @param n the number of new BicycleClass passengers to be added
	 * @return the updated number of BicycleClass passengers
	 */
	public int incBicyclePassengers(int n) {
		this.bicyclePassengers += n;
		return this.bicyclePassengers;
	}
	
	/**
	 * Gets the seat at the specified zero-based row,column index in the specified car
	 * @param carNum the index of the car to get the seat from
	 * @param row the zero-based row index of the seat
	 * @param col the zero-based column index of the seat
	 * @return the seat located at row,col in the specified car
	 * @throws IllegalArgumentException if row,col is out of bounds or carNum is out of bounds
	 */
	public Seat getSeatFor(int carNum, int row, int col) {
		if (carNum < 0 || carNum >= car.length) { //Check that the carNum index is in range
			throw new IllegalArgumentException();
		}
		Seat s = null;
		try { //Throws IAE if row,col out of bounds for the seating array
			s = this.car[carNum].seatFor(row, col); 
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		return s;
	
	}
	
	/**
	 * Gets the seat specified by a label in the specified car
	 * @param carNum the index of the car to get the seat from
	 * @param label the seat's label in the form <row number><letter>
	 * @return the seat specified by the label in the specified car
	 * @throws IllegalArgumentException if the seat label is invalid for the specified car or
	 * if carNum is out of bounds
	 */
	public Seat getSeatFor(int carNum, String label) {
		if (carNum < 0 || carNum >= car.length) { //Check that the carNum index is in range
			throw new IllegalArgumentException("Invalid car number");
		}
		Seat s = null;
		try { //Throws an IAE if the label is improper for the car
			s = this.car[carNum].seatFor(label);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		return s;
	}
	
	/**
	 * Determines if the Train can hold a specified number more of ComfortClass passengers
	 * @param numPassengers the number of passengers to check if there is room for
	 * @return true if the Train has room for the additional passengers, false otherwise
	 */
	public boolean hasComfortClassRoomFor(int numPassengers) {
		//Check that the sum of the current number of comfort passengers plus the passengers 
		//to be added is less than or equal to the total available capacity for comfort class
		return (this.comfortClassPassengers + numPassengers) <= this.TOTAL_COMFORT_CAPACITY;
	}
	
	/**
	 * Determines if the Train can hold a specified number more of EconomyClass passengers for
	 * unreserved reservations
	 * @param numPassengers the number of passengers to check if there is room for
	 * @return true if the Train has room for the additional passengers, false otherwise
	 */
	public boolean hasEconomyClassRoomFor(int numPassengers) {
		return (this.econcomyClassPassengers + numPassengers) <= this.TOTAL_ECONOMY_CAPACITY;
	}
	
	/**
	 * Determines if the Train can hold a specified number more of BicycleClass passengers
	 * @param numPassengers the number of passengers to check if there is room for
	 * @return true if the Train has room for the additional passengers, false otherwise
	 */
	public boolean hasBicycleCarRoomFor(int numPassengers) {
		return (this.bicyclePassengers + numPassengers) <= this.TOTAL_BICYCLE_CAPACITY;
	}
	
	/**
	 * Gets the number of unreserved second class seats in the Train
	 * @return the number of unreserved second class seats
	 */
	public int openSecondClassSeats() {
		int openSeatCount = 0;
		//Loop through the second class cars
		for (int i = this.numFirstClassCars; i < this.numCars() - 1; i++) {
			openSeatCount += this.car[i].openSeatsLeft();
		}
		return openSeatCount;
	}
	
	/**
	 * Gets the number of unreserved first class seats
	 * @return the number of unreserved first class seats
	 */
	public int openFirstClassSeats() {
		int openSeatCount = 0;
		//Loop through the first class cars
		for (int i = 0; i < this.numFirstClassCars; i++) {
			openSeatCount += this.car[i].openSeatsLeft();
		}
		return openSeatCount;
	}
	
	/**
	 * Determines if the car at the specified ID NUMBER is a BicycleTransportCar
	 * @param carNum the car's ID number
	 * @return true if the car at the index is a BicycleTransportCar, false otherwise
	 */
	public boolean isBicycleCar(int carNum) {
		return this.car[carNum - 1] instanceof BicycleTransportCar;
	}
	
	/**
	 * Determines if the car at the specified ID NUMBER is a SecondClassCar
	 * @param carNum the car's ID number
	 * @return true if the car at the index is a SecondClassCar, false otherwise
	 */
	public boolean isSecondClassCar(int carNum) {
		return this.car[carNum - 1] instanceof SecondClassCar;
	}
	
	/**
	 * Determines if the car at the specified ID NUMBER is a FirstClassCar
	 * @param carNum the car's ID number
	 * @return true if the car at the index is a FirstClassCar, false otherwise
	 */
	public boolean isFirstClassCar(int carNum) {
		return this.car[carNum - 1] instanceof FirstClassCar;
	}
	
	/**
	 * Gets the seat map for the car located at the specified INDEX in the car array
	 * @param the index of the car in the car array
	 * @return the seat map for the car as a single string
	 */
	public String getCarSeatMap(int carNum) {
		return this.car[carNum].getSeatMap();
	}
}
