package edu.ncsu.csc216.train_travel.transportation;

/**
 * @author Noah Benveniste
 */
public abstract class TrainCar {
	/** ASCII value for a */
	private static final int ASCII_A = 65;
	/** The number of the car's order in the Train */
	private int carNumber;
	
	/**
	 * 
	 * @param carNumber
	 */
	public TrainCar(int carNumber) {
		this.carNumber = carNumber;
	}
	
	/**
	 * Getter for the carNumber field
	 * @return the number of the car's order in the Train
	 */
	public int getCarIDNumber() {
		return this.carNumber;
	}
	
	/**
	 * Returns the number of unreserved seats in the car
	 * @return the number of unreserved seats in the car
	 */
	public abstract int openSeatsLeft();
	
	/**
	 * Returns the seat at a specified row,column index in the seating chart
	 * @param row the seat's row
	 * @param col the seat's column (a = 1, b = 2, etc)
	 * @return the seat at the specified row,column index
	 * @throws IllegalArgumentException if the row,column index is out of bounds
	 */
	public abstract Seat seatFor(int row, int col);
	
	/**
	 * Returns the seat at a specified location given a seat label
	 * @param label the label of the seat to return
	 * @return the seat with the given label
	 * @throws IllegalArgumentException if there is no seat with the given label in the car
	 */
	public abstract Seat seatFor(String label);
	
	/**
	 * Returns a string representation of the seating map for the car, including car-specific labels;
	 * Calls drawSeatChart()
	 * @return the string representation of the seating map
	 */
	public abstract String getSeatMap();
	
	/**
	 * Initializes the seats in a 2D array with their labels and car numbers;
	 * @param s the array of seats passed from the child car class
	 */
	protected void initSeats(Seat[][] s) {
		//Seat row loop
		for (int i = 0; i < s.length; i++) {
			//Seat column loop
			for (int j = 0; j < s[i].length; j++) {
				//The number portion of the seat loop is determined by the row number, or the outer loop variable (plus one)
				String label = "" + (i + 1);
				//The letter portion of the label is determined by the seat column, or the inner loop variable
				label += (char) (ASCII_A + j);
				s[i][j] = new Seat(label ,this.getCarIDNumber());
			}
		}
	}
	
	/**
	 * Returns a string representation of the actual seating map based on a 2D seat array
	 * and the location of the aisle; called by the individual implementations of getSeatMap()
	 * in each child TrainCar class
	 * @param seats a 2D array of seats
	 * @param aisleIndex the location of the aisle column in the seating map
	 * @return
	 */
	protected String drawSeatChart(Seat[][] seats, int aisleIndex) {
		//aisleIndex = 1 for first class, 2 for second class
		String s = "";
		for (int i = 0; i < seats.length; i++) {
			for (int j = 0; j < seats[i].length; j++) {
				//Check if the column iteration is on the aisle
				if (j != aisleIndex) {
					//Check if the seat is reserved or not
					if (seats[i][j].isReserved()) {
						//Label consists of a one digit number and a letter
						if (seats[i][j].getLabel().length() == 2) {
							s += "[ " + seats[i][j].getLabel() + "]";
						} else if (seats[i][j].getLabel().length() == 3) { //Label consists of a two digit number and a letter
							s += "[" + seats[i][j].getLabel() + "]";
						}
					} else { //If the seat is not reserved, put in an 'x'
						s += "[ x ]";
					}
				} else { //If the column iteration is on the aisle, print empty space and then decrement the column counter so a seat isn't skipped
					s += "    ";
					j--;
				}
			}
			//When all seats in the row have been added, go down a line
			s += "\n";
		}
		return s;
	}
	
	/**
	 * Returns the seat in an array of seats corresponding to a given label
	 * @param label
	 * @param seats an array of seat objects for a car
	 * @return the seat at the given label in the array of seats
	 * @throws IllegalArgumentException if the label is improper for the car
	 */
	protected Seat seatFor(String label, Seat[][] seats) {
		//Call private helper method to check if label is valid for the train car
		if (parseLabel(label, seats.length, seats[0].length).length == 0) {
			throw new IllegalArgumentException();
		}
		//If the label is valid, use the number corresponding to the row and the letter
		//corresponding to the column to index directly into the Seat array and get the
		//seat
		int row = parseLabel(label, seats.length, seats[0].length)[0];
		int col = parseLabel(label, seats.length, seats[0].length)[1];
		return seats[row][col];
	}
	
	/**
	 * Helper method to parse label strings to check if they are valid for the car
	 * and then return the numerical row,column index corresponding to the label
	 * @param label the seat label to check
	 * @param numRows the number of rows in the seating array passed to seatFor()
	 * @param numCols the number of columns in the seating array passed to seatFor()
	 * @return an empty array if the label is invalid, or a 1x2 integer array containing the
	 * numerical row,column index corresponding to the label if the label is valid
	 */
	private int[] parseLabel(String label, int numRows, int numCols) {
		//Model this method as an FSM
		return new int[0];
	}
}
