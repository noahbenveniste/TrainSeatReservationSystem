package edu.ncsu.csc216.train_travel.transportation;

/**
 * Abstract parent class for FirstClassCar, SecondClassCar and BicycleTransportCar.
 * Stores state for the car number.
 * @author Noah Benveniste
 */
public abstract class TrainCar {
	/** ASCII value for a */
	private static final int ASCII_A = 65;
	/** The number of the car's order in the Train */
	private int carNumber;
	
	/**
	 * Constructor for a TrainCar parent object. Called by the 
	 * constructors of FirstClassCar, SecondClassCar and BicycleTransportCar
	 * @param carNumber the number corresponding to the car's order in the train
	 */
	public TrainCar(int carNumber) {
		if (carNumber < 1 || carNumber > 12) {
			throw new IllegalArgumentException("Invalid car number");
		} else {
			this.carNumber = carNumber;
		}
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
				s[i][j] = new Seat(label , this.getCarIDNumber());
			}
		}
	}
	
	/**
	 * Returns a string representation of the actual seating map based on a 2D seat array
	 * and the location of the aisle; called by the individual implementations of getSeatMap()
	 * in each child TrainCar class
	 * @param seats a 2D array of seats
	 * @param aisleIndex the location of the aisle column in the seating map
	 * @return the seating map as a single concatenated string to be printed
	 */
	protected String drawSeatChart(Seat[][] seats, int aisleIndex) {
		//aisleIndex = 1 for first class, 2 for second class
		String s = "";
		for (int i = 0; i < seats.length; i++) {
			for (int j = 0; j < seats[i].length; j++) {
				//Check if the column iteration is on the aisle
				if (j != aisleIndex) {
					//Check if the seat is reserved or not
					if (!seats[i][j].isReserved()) {
						//Label consists of a one digit number and a letter
						if (seats[i][j].getLabel().length() == 2) {
							s += "[ " + seats[i][j].getLabel() + "]";
						} else if (seats[i][j].getLabel().length() == 3) { //Label consists of a two digit number and a letter
							s += "[" + seats[i][j].getLabel() + "]";
						}
					} else { //If the seat is reserved, put in an 'x'
						s += "[ x ]";
					}
				} else { //If the column iteration is on the aisle, print empty space and then the seat at the index in the seat array
					s += "   ";
					//Check if the seat is reserved or not
					if (!seats[i][j].isReserved()) {
						//Label consists of a one digit number and a letter
						if (seats[i][j].getLabel().length() == 2) {
							s += "[ " + seats[i][j].getLabel() + "]";
						} else if (seats[i][j].getLabel().length() == 3) { //Label consists of a two digit number and a letter
							s += "[" + seats[i][j].getLabel() + "]";
						}
					} else { //If the seat is reserved, put in an 'x'
						s += "[ x ]";
					}
				}
			}
			//When all seats in the row have been added, go down a line
			s += "\n";
		}
		return s;
	}
	
	/**
	 * Returns the seat in an array of seats corresponding to a given label
	 * Called by the public seatFor() methods implemented by FirstClassCar and
	 * SecondClassCar
	 * @param label the label corresponding to the Seat object to be returned
	 * @param seats an array of seat objects for a car
	 * @return the seat at the given label in the array of seats
	 * @throws IllegalArgumentException if the label is improper for the car
	 */
	protected Seat seatFor(String label, Seat[][] seats) {
		if (this.seatLabelValidator(label, seats.length, seats[0].length)) {
			int[] idx = this.parseLabel(label);
			return seats[idx[0]][idx[1]];
		} else {
			throw new IllegalArgumentException("Invalid seat label for car");
		}
	}
	
	/**
	 * Method used to parse a seat label and return a zero-based row,column index
	 * @param label
	 * @return
	 */
	private int[] parseLabel(String label) {
		int row = 0;
		int col = 0;
		int[] idx = new int[2];
		if (label.length() == 2) {
			row = Integer.parseInt("" + label.charAt(0)) - 1;
			col = (int) label.charAt(1) - ASCII_A;
		} else if (label.length() == 3) {
			row = Integer.parseInt("" + label.charAt(0) + label.charAt(1)) - 1;
			col = (int) label.charAt(2) - ASCII_A;
		}
		idx[0] = row;
		idx[1] = col;
		return idx;
	}
		
	/**
	 * Used to check labels for valid form. The label must contain a one or two digit integer
	 * followed directly by a letter. The integer must be between 1 and the number of rows for
	 * the car. The letter must be between A and C if the car has three seats per row and between
	 * A and D if the car has four seats per row.
	 * @param label the label to validate
	 * @param numRow the number of rows in the car
	 * @param numSeatsPerRow the number of seats per row in the car
	 * @return true if the label is valid
	 */
	private boolean seatLabelValidator(String label, int numRow, int numSeatsPerRow) {
		if (label.length() != 2 && label.length() != 3) {
			return false;
		}
		//For labels consisting of a one digit integer and a letter
		if (label.length() == 2) {
			if (!Character.isDigit(label.charAt(0)) || !Character.isLetter(label.charAt(1))) {
				return false;
			}
			if (Integer.parseInt("" + label.charAt(0)) < 1 || Integer.parseInt("" + label.charAt(0)) > numRow) {
				return false;
			}
			if (numSeatsPerRow == 3 && label.charAt(1) != 'A' && label.charAt(1) != 'B' && label.charAt(1) != 'C') {
				return false;
			} else if (numSeatsPerRow == 4 && label.charAt(1) != 'A' && label.charAt(1) != 'B' && label.charAt(1) != 'C' && label.charAt(1) != 'D') {
				return false;
			}
		//For labels consisting of a two digit integer and a letter
		} else if (label.length() == 3) {
			if (!Character.isDigit(label.charAt(0)) || !Character.isDigit(label.charAt(1)) || !Character.isLetter(label.charAt(2))) {
				return false;
			}
			if (Integer.parseInt("" + label.charAt(0) + label.charAt(1)) < 1 || Integer.parseInt("" + label.charAt(0) + label.charAt(1)) > numRow) {
				return false;
			}
			if (numSeatsPerRow == 3 && label.charAt(2) != 'A' && label.charAt(2) != 'B' && label.charAt(2) != 'C') {
				return false;
			} else if (numSeatsPerRow == 4 && label.charAt(2) != 'A' && label.charAt(2) != 'B' && label.charAt(2) != 'C'&& label.charAt(2) != 'D') {
				return false;
			}
		}
		return true;
	}
}
