package edu.ncsu.csc216.train_travel.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.StringTokenizer;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import edu.ncsu.csc216.train_travel.manager.*;
import edu.ncsu.csc216.train_travel.tickets.Reservation;

/**
 * This is the user interface for a train reservation system. It opens by asking the user to specify
 * the size of the train (number of train cars) for this system. Then it enables users to: 1) add new
 * reservations, 2) delete existing reservations, 3) change seating assignments. The left side of the
 * main window shows the train cars' seat maps.
 * 
 * @author Jo Perry
 *
 */
public class TrainTravelGUI extends JFrame implements ActionListener {

	// Window, button, and scrollpane strings
	private final static String TITLE = "Train Reservation System";   // Main window title
	private final static String RESERVATIONS = "Reservations";  // Reservation list title
	private final static String SEAT_MAP = "Seating Map";  // Seating map title
	private final static String DELETE = "Delete Selected Reservation";  // Delete message/label
	private final static String ADD = "Add New Reservation";  // Add message/label
	private final static String QUIT = "Quit";  // Quit message/label
	private final static String LAST = "<--";   // Go back label
	private final static String NEXT = "-->";   // Go forward label
	private final static String FIRST_CLASS = "Comfort Class";  // Choice for Comfort Class tickets
	private final static String SECOND_CLASS = "Economy Class"; // Choice for Economy Class tickets
	private final static String BICYCLE = "Bicycle Class";      // Choice for Bicycle Class tickets
	private final static String PASSENGER_COUNT = "Number of Passengers:"; //Number of Passengers on Reservation: ";
	private final static String PREFERENCE = "Reserved Seats";  // Reserved Seats message/label
	private final static String RESERVATION_KIND = "Kind: ";    // Reservation kind message/label
	private final static String CHANGE = "Change Seats on Selected Reservation";  // Seat change message/label
	private final static String NUM_CARS = "Number of train cars: ";  // Number of train cars message/label
	private final static Object[] HINTS = {"Enter each seat as: <car#>-<seat>", "Separate seats by commas.", 
			"For example: 1-2A,1-2B,2-17A"}; // Seat change dialog message
	private final static String[] RESERVATION_CLASS = {FIRST_CLASS, SECOND_CLASS, BICYCLE};

	// Size constants for the window and scroll panes
	private final static int FRAME_WIDTH = 775;   // Width of main window
	private final static int FRAME_HEIGHT = 650;  // Height of main window
	private static final int RESERVATION_HEIGHT = 100; // Height of reservation display
	private static final int MAP_WIDTH = 27;   // Width of seat map display
	private static final int MAP_HEIGHT = 23;  // Height of seat map display
	private static final int PAD = 10;         // Panel padding

	// Panels, Boxes, and Borders
	private TitledBorder bdrReservations = new TitledBorder(RESERVATIONS); // Reservation panel border
	private TitledBorder bdrSeatMap = new TitledBorder(SEAT_MAP);  // Border around the seat map
	private JPanel pnlLeftSide = new JPanel();     // Holds seating map, entry fields on the left side of the window
	private JPanel pnlReservations = new JPanel(); // Shows reservations
	private JPanel pnlEntry = new JPanel();  // Holds data entry widgets
	private JPanel pnlMap = new JPanel();  // Holds map and data entry widgets
	private GridBagLayout gridbag = new GridBagLayout();  // Applied to pnlEntry
	private GridBagLayout gridbagCar = new GridBagLayout();  // Applied to pnlMap
	private GridBagConstraints gbc = new GridBagConstraints(); // Constraints on pnlEntry
	private GridBagConstraints gbsc = new GridBagConstraints(); // Constraints on pnlMap
	
	// Buttons
	private JButton btnDelete = new JButton(DELETE);  // Remove a reservation
	private JButton btnAdd = new JButton(ADD);  // Add a new reservation
	private JButton btnChange = new JButton(CHANGE); // Change a reservation
	private JButton btnLast = new JButton(LAST); // Show the previous train car seating chart
	private JButton btnNext = new JButton(NEXT); // Show the next train car seating chart
	private JButton btnQuit = new JButton(QUIT); // Quit the application

	// Labels for new reservation/passenger widget display
	private final JLabel lblPassengerCount = new JLabel(PASSENGER_COUNT);  // Label for text field (number of passengers)
	private final JCheckBox cbxReserve = new JCheckBox(PREFERENCE, true);  // Main window checkbox on reserved seats
	private final JLabel lblReservationKind = new JLabel(RESERVATION_KIND); // Label for type of reservation desired
	private final JLabel lblBlank = new JLabel("");   // Blank label (used for widget alignment
	
	// Field, combo box, radio buttons for new passenger reservation entry
	private JTextField txtNumber = new JTextField(); // For entry of #passengers on a new reservation
	private JComboBox<String> cmbTicketType = new JComboBox<String>(RESERVATION_CLASS);  // Choice of reservation types
	private JLabel[] lblPassenger = { lblPassengerCount, lblReservationKind, lblBlank }; // Labels on new passenger widgets
	private Component[] cmpPassenger = { txtNumber, cmbTicketType, cbxReserve}; // New passenger widgets

	// Seating map display 
	private JTextArea txtSeatMap = new JTextArea(MAP_WIDTH, MAP_HEIGHT);  // Text area for seating map display

	// Scrolling list of reservations
	private DefaultListModel<String> dlmReservations = new DefaultListModel<>();  // Default list model for reservation display
	private JList<String> lstReservations = new JList<>(dlmReservations);      // Reservation list to display
	private JScrollPane scrollReservations = new JScrollPane(lstReservations); // Scrollpane to hold the reservation display

	// Backend model
	private TicketMaster myReservations;  // Manages reservations for this application
	private int mapNumber = 0;  // Determines which car map is currently shown

	/**
	 * Constructor. Sets up the user interface and initializes the backend model.
	 */
	public TrainTravelGUI() {
		initBackend();
		initUI();
		this.setVisible(true); 
	}
	

	/**
	 * Main method -- begins program execution.
	 * @param args not used
	 */
	public static void main(String[] args) {
		new TrainTravelGUI();
	}

	// ------ Controller Methods ---------------------------

	/**
	 * Defines actions to be performed on button clicks
	 * @param e button click (user event)
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnQuit))     // Quit the application.
			endExecution();
		if (e.getSource().equals(btnDelete)) { // Delete the selected application.	
			try {
				checkSelection(lstReservations.getSelectedIndex());
				String reservation = lstReservations.getSelectedValue();
				myReservations.cancelReservation(reservation);
				loadReservationList();
				loadSeatMap(mapNumber);
				clearFields();
			} catch (IllegalArgumentException noSelection) {
				JOptionPane.showMessageDialog(this, noSelection.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		if (e.getSource().equals(btnAdd)) { // Add a new reservation.
			String ticketClass = RESERVATION_CLASS[cmbTicketType.getSelectedIndex()];
			try {
				int numPassengers = Integer.parseInt(txtNumber.getText());
				Reservation r = myReservations.makeNewReservation(numPassengers, ticketClass);
				if (cbxReserve.isSelected())
					r.chooseSeats();
			} catch (NumberFormatException badNumber) {
				JOptionPane.showMessageDialog(this, "Number of passengers must be an integer.", "Error", JOptionPane.ERROR_MESSAGE);
			} catch (IllegalArgumentException badData) {
				JOptionPane.showMessageDialog(this, badData.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
			loadSeatMap(mapNumber);
			loadReservationList();
			clearFields();
		}
		if (e.getSource().equals(btnChange)) { // Change the seating assignments on the selected reservation.
			try {
				checkSelection(lstReservations.getSelectedIndex());
				String newSeats = JOptionPane.showInputDialog(null, HINTS, CHANGE, JOptionPane.INFORMATION_MESSAGE);
				if (newSeats == null)  // User cancelled operation
					return;
				myReservations.changeSeats(lstReservations.getSelectedValue(), newSeats);
				loadReservationList();
				loadSeatMap(mapNumber);
			}
			catch (IllegalArgumentException noSelection) {
				JOptionPane.showMessageDialog(this, noSelection.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		if (e.getSource().equals(btnLast))  {  // Scroll to the previous seat map
			if (mapNumber > 0)
				mapNumber--;
			loadSeatMap(mapNumber);
		}
		if (e.getSource().equals(btnNext))  {  // Scroll to the next seat map
			if (mapNumber < myReservations.numberOfSeatMaps() - 1)
				mapNumber++;
			loadSeatMap(mapNumber);
		}
		
	}

	// --------End Controller Methods ---------------------

	// ------ Private Methods -----------------------------
	

	/**
	 * Private method - Initializes the user interface.
	 */
	private void initUI() {
		// Set up the panels and the lists that make the UI
		setUpPanels();
		setUpLists();

		// Construct the main window and add listeners.
		setTitle(TITLE);
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		Container c = getContentPane();
		c.add(pnlLeftSide, BorderLayout.WEST);
		c.add(pnlReservations, BorderLayout.CENTER);
		setVisible(true);
		addListeners();

		// Make sure the application quits when the window is closed.
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				endExecution();
			}
		});
	}

	/**
	 * Private method - Sets up the scrolling list of reservations.
	 */
	private void setUpLists() {
		// Load the data.
		loadReservationList();
		lstReservations.setFont(new Font("Courier", Font.PLAIN, 12));
		lstReservations.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	/**
	 * Private method - Sets up the seating chart.
	 */
	private void setUpSeatingMap() {
		txtSeatMap.setEditable(false);
		txtSeatMap.setFont(new Font("Courier", Font.PLAIN, 12));
		txtSeatMap.setBorder(bdrSeatMap);
		pnlLeftSide.add(pnlEntry);
		loadSeatMap(0);
	}
	
	/**
	 * Private method - Sets up the panels for showing and modifying reservations
	 */
	private void setUpNewReservations() {
		// Set up the right side of the window in pnlReservations.
		// Initialize the reservation list.
		scrollReservations.setBorder(bdrReservations);
		scrollReservations.setPreferredSize(new Dimension(FRAME_WIDTH / 2 - 4
				* (PAD), RESERVATION_HEIGHT));

		pnlReservations.setLayout(new BorderLayout());
		pnlReservations.setBorder((EmptyBorder) BorderFactory
				.createEmptyBorder(PAD, PAD / 2, PAD, PAD));

		// Lay out the buttons and reservation entry fields in a grid.
		pnlEntry.setLayout(gridbag);
		pnlEntry.setBorder((EmptyBorder) BorderFactory.createEmptyBorder( PAD, PAD / 2, PAD, PAD));
		
		// Fill the first row with the seating map
		gbc.anchor = GridBagConstraints.PAGE_START;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.ipady = 0;
		gbc.insets = new Insets(1, 0, 0, 0);
		gbc.fill = GridBagConstraints.NONE;
		pnlEntry.add(pnlMap, gbc);
		
		// Second row is a horizontal separator
		gbc.gridy++;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridwidth = 2;
		pnlEntry.add(new JSeparator(JSeparator.HORIZONTAL), gbc);

		// The next three rows are for user input of new reservation information
		gbc.gridy++;
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridwidth = 1;
		int numLabels = lblPassenger.length;
		gbc.anchor = GridBagConstraints.EAST;

		for (int i = 0; i < numLabels; i++) {
			gbc.gridx = 0;
			gbc.gridy++;
			gbc.fill = GridBagConstraints.NONE;
			gbc.weightx = 0.0;
			pnlEntry.add(lblPassenger[i], gbc);

			gbc.gridx = 1;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.weightx = 1.0;
			pnlEntry.add(cmpPassenger[i], gbc);
			cbxReserve.setSelected(false);
		}

		// Now add the remaining three buttons
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridwidth = 2;
		gbc.gridx = 0;
		gbc.gridy++;		
		pnlEntry.add(btnAdd, gbc);

		gbc.gridy += 1;
		gbc.gridx = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		pnlEntry.add(new JSeparator(JSeparator.HORIZONTAL), gbc);

		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.gridy += 1;
		gbc.fill = GridBagConstraints.NONE;
		pnlEntry.add(btnChange, gbc);
		
		gbc.gridy++;
		gbc.gridwidth = 2;
		gbc.ipady = 0;
		gbc.fill = GridBagConstraints.NONE;
		pnlEntry.add(btnDelete, gbc);
	}

	
	
	/**
	 * Private method - Determines the geometry of the main window.
	 */
	private void setUpPanels() {

		pnlLeftSide.setLayout(new BorderLayout());
		pnlMap.setLayout(gridbagCar);
		
		gbsc.anchor = GridBagConstraints.PAGE_START;
		gbsc.gridx = 0;
		gbsc.gridy = 0;
		gbsc.gridwidth = 2;
		gbsc.insets = new Insets(5, 0, 0, 0);
		gbsc.fill = GridBagConstraints.NONE;
		pnlMap.add(txtSeatMap, gbsc);
		
		gbsc.gridy = 1;
		gbsc.gridwidth = 1;
		pnlMap.add(btnLast, gbsc);
		gbsc.gridx = 1;
		pnlMap.add(btnNext, gbsc);
		
		setUpSeatingMap();
		setUpNewReservations();

		// Put the reservations, quite button on the right side of the window.
		pnlReservations.add(scrollReservations, BorderLayout.CENTER);
		pnlReservations.add(btnQuit, BorderLayout.SOUTH);
		pnlLeftSide.add(pnlEntry, BorderLayout.CENTER);
	}

	/**
	 * Private Method - Adds listeners to the buttons.
	 */
	private void addListeners() {
		btnDelete.addActionListener(this);
		btnAdd.addActionListener(this);
		btnLast.addActionListener(this);
		btnNext.addActionListener(this);
		btnChange.addActionListener(this);
		btnQuit.addActionListener(this);
	}

	/**
	 * Private method - Clears text field sets the seat selection check box to unchecked.
	 */
	private void clearFields() {
		txtNumber.setText("");
		cbxReserve.setSelected(false);
	}

	/**
	 * Private Method - Loads the reservation list model from a string with newline delimiters.
	 */
	private void loadReservationList() {
		dlmReservations.clear();
		StringTokenizer st = new StringTokenizer(myReservations.printReservationList(), "\n");
		while (st.hasMoreTokens()) {
			dlmReservations.addElement(st.nextToken());
		}
	}
	
	/**
	 * Private method - Loads the seating map for the given car.
	 * @param k index of the car map to be loaded
	 */
	private void loadSeatMap(int k) {
		if (k >= 0 && k < myReservations.numberOfSeatMaps()) {
			String seats = "\n" + myReservations.showMap(k);
			txtSeatMap.setText(seats);
		}
	}
	
	/**
	 * Private method - Initializes the backend model.
	 */
	private void initBackend() {
		String aNumber = JOptionPane.showInputDialog(this, "Number of train cars", NUM_CARS, JOptionPane.INFORMATION_MESSAGE);
		if (aNumber == null || aNumber.length() == 0)
			endExecution();
		try {
			myReservations = new ReservationManager(Integer.valueOf(aNumber.trim()));			
		} catch (IllegalArgumentException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			endExecution();
		} 		
	}
	
	/**
	 * Private method - Makes sure an item (reservation) is selected.
	 * @param selectedIndex
	 */
	private void checkSelection(int selectedIndex) {
		if (selectedIndex < 0)
			throw new IllegalArgumentException("No reservation selected.");
	}

	/**
	 * Private Method - Ends execution of the program.
	 */
	private void endExecution() {
		System.exit(0);
	}

	// ------------- End Private Methods -------------------
}