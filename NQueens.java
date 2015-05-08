package burlette;

/**
 * Thomas Burlett
 * Dr. Stephan
 * CSE 271
 * Project 2
 * N-Queens Solver
 * 5/08/2015
 */

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import java.awt.GridLayout;
import javax.swing.JToggleButton;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.util.ArrayList;
import javax.swing.JTextField;

public class NQueens {

	private JFrame solutionChecker;
	private JPanel board;
	private JButton checkSolution;
	private JScrollPane scrollPane;
	private JTextArea textArea;
	private JToggleButton[][] gridArray;
	private int queens = 0;
	private JTextField currentQueens;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NQueens window = new NQueens();
					window.solutionChecker.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public NQueens() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		// Frame setup. Absolute Layout
		solutionChecker = new JFrame();
		solutionChecker.setResizable(false);
		solutionChecker.getContentPane()
				.setBackground(new Color(255, 228, 181));
		solutionChecker.setTitle("N-Queens Solution Checker");
		solutionChecker.setBounds(100, 100, 805, 503);
		solutionChecker.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		solutionChecker.getContentPane().setLayout(null);

		// Panel with grid layout for the chess board
		board = new JPanel();
		board.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		board.setBounds(380, 47, 400, 400);
		solutionChecker.getContentPane().add(board);
		board.setLayout(new GridLayout(8, 8, 0, 0));

		// Welcome text for top of GUI
		JLabel welcome = new JLabel(
				"Welcome to the N-Queens\r\n Solution Checker!");
		welcome.setFont(new Font("Tahoma", Font.BOLD, 14));
		welcome.setBounds(10, 11, 331, 64);
		solutionChecker.getContentPane().add(welcome);

		// Displays directions for the user
		JLabel directions = new JLabel(
				"<html><p>"
						+ "- Press any of the squares on the board to place a queen. <br> "
						+ "- Select no more than 8 queens. <br> "
						+ "- The highlighted square acts as a placed queen. <br>- Press the "
						+ "check solution button at any time to test the board. <br>- At "
						+ "risk queens will be displayed in the text box below."
						+ "<br>- If a solution is correct, it will say correct in the text area."
						+ "</p></html>");
		directions.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		directions.setBounds(20, 47, 303, 133);
		solutionChecker.getContentPane().add(directions);

		// Label to show the format of the text area
		JLabel rowColumn = new JLabel(
				"Incorrect solutions will display at risk queens by (Row, Column).");
		rowColumn.setFont(new Font("Tahoma", Font.PLAIN, 10));
		rowColumn.setBounds(20, 237, 303, 14);
		solutionChecker.getContentPane().add(rowColumn);

		// Scroll pane for text area
		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 252, 303, 195);
		solutionChecker.getContentPane().add(scrollPane);

		// Text area where all solutions are displayed as correct or incorrect
		textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);

		// Creates a 2D array for the toggle buttons locations to be stored and
		// easily accessed
		gridArray = new JToggleButton[8][8];

		// Action Listener for the button
		ActionListener buttonListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				// Finds which button the event came from
				JToggleButton selectedBtn = (JToggleButton) evt.getSource();
				for (int row = 0; row < gridArray.length; row++) {
					for (int col = 0; col < gridArray[row].length; col++) {
						// Just helpful console output---User won't see it
						if (gridArray[row][col] == selectedBtn) {
							if (selectedBtn.isSelected()) {
								System.out.printf(
										"Selected row and column: (%d, %d)%n",
										row + 1, col + 1);
								queens++;
								currentQueens.setText(Integer.toString(queens));
							} else {
								System.out
										.printf("Deselected row and column: (%d, %d)%n",
												row + 1, col + 1);
								queens--;
								currentQueens.setText(Integer.toString(queens));
							}
						}
					}
				}
			}
		};

		// These for loops populate the grid panel with toggle buttons of either
		// black or white to make the board
		for (int row = 0; row < gridArray.length; row++) {
			for (int col = 0; col < gridArray[row].length; col++) {
				if ((col % 2 == 1 && row % 2 == 1)
						|| (col % 2 == 0 && row % 2 == 0)) {
					gridArray[row][col] = new JToggleButton();
					gridArray[row][col].setBackground(Color.BLACK);

				} else {
					gridArray[row][col] = new JToggleButton();
					gridArray[row][col].setBackground(Color.WHITE);
				}
				gridArray[row][col].addActionListener(buttonListener);
				board.add(gridArray[row][col]);
			}
		}

		// This creates labels on the left side of the board to show the row
		// numbers
		int height = 65;
		for (int i = 0; i < 8; i++) {
			String s = Integer.toString(i + 1);
			JLabel lblNewLabel = new JLabel(s);
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblNewLabel.setBounds(352, height, 18, 14);
			solutionChecker.getContentPane().add(lblNewLabel);
			height += 49;
		}

		// This creates a label on the top of the board to show column numbers
		int width = 398;
		for (int i = 0; i < 8; i++) {
			String s = Integer.toString(i + 1);
			JLabel label = new JLabel(s);
			label.setFont(new Font("Tahoma", Font.BOLD, 14));
			label.setBounds(width, 22, 16, 14);
			solutionChecker.getContentPane().add(label);
			width += 50;

		}

		// The magic check solution button
		checkSolution = new JButton("Check Solution!");
		checkSolution.setFont(new Font("Tahoma", Font.BOLD, 15));
		checkSolution.addActionListener(new ActionListener() {
			// Here is what to do when the button is pressed
			public void actionPerformed(ActionEvent arg0) {
				// First it checks how queens are on the board(should be <= 8
				// queens)
				if (queens <= 8) {
					textArea.setText("");
					// Create two arraylists. That way row numbers and column
					// numbers can be found easily
					ArrayList<Integer> row = new ArrayList<Integer>();
					ArrayList<Integer> column = new ArrayList<Integer>();
					// Used later
					boolean r = true;
					boolean c = true;
					boolean d = true;
					// This populates the lists by pulling apart the (row,
					// column) format. Helpful later.
					for (int i = 0; i < gridArray.length; i++) {
						for (int j = 0; j < gridArray[i].length; j++) {
							if (gridArray[i][j].isSelected()) {
								row.add(i);
								column.add(j);
							}
						}
					}
					// Here is where the actual checking begins
					for (int k = 0; k < row.size(); k++) {
						for (int k2 = 0; k2 < row.size(); k2++) {
							// This checks whether any row numbers are repeated
							// and will append a message if queens are attacking
							if ((row.get(k).compareTo(row.get(k2)) == 0)
									&& (k != k2)) {
								r = false;
								textArea.append("(" + (row.get(k) + 1) + ","
										+ (column.get(k) + 1) + ")"
										+ " is attacking " + "("
										+ (row.get(k2) + 1) + ","
										+ (column.get(k2) + 1) + ")\n");
							}
							// This checks whether any column numbers are
							// repeated and will append a message if queens are
							// attacking
							if ((column.get(k).compareTo(column.get(k2)) == 0)
									&& (k != k2)) {
								c = false;
								textArea.append("(" + (row.get(k) + 1) + ","
										+ (column.get(k) + 1) + ")"
										+ " is attacking " + "("
										+ (row.get(k2) + 1) + ","
										+ (column.get(k2) + 1) + ")\n");
							}

							// This checks the diagonals of all the queens to
							// see if any are attacking
							// Similar to the in class powerpoint diagonal check
							if ((Math.abs(row.get(k) - row.get(k2)) == Math
									.abs(column.get(k) - column.get(k2)))
									&& (k != k2)) {
								d = false;
								textArea.append("(" + (row.get(k) + 1) + ","
										+ (column.get(k) + 1) + ")"
										+ " is attacking " + "("
										+ (row.get(k2) + 1) + ","
										+ (column.get(k2) + 1) + ")\n");
							}

						}
					}
					// If all 3 conditions are met, the board displays true.
					if (r && c && d) {
						textArea.append("The board is correct.\n");
					}
					// If there are >8 queens, it will display a message.
				} else {
					textArea.setText("");
					JOptionPane.showMessageDialog(null,
							"Please select no more than 8 queens.");
				}
			}
		});
		checkSolution.setBackground(new Color(100, 149, 237));
		checkSolution.setBounds(83, 174, 177, 52);
		solutionChecker.getContentPane().add(checkSolution);
		
		//Panel for bottom right to hold a count of placed queens
		JPanel countPanel = new JPanel();
		countPanel.setBounds(380, 445, 409, 32);
		countPanel.setBackground(new Color(255, 228, 181));
		solutionChecker.getContentPane().add(countPanel);
		
		//Label
		JLabel lblNumberOfQueens = new JLabel("Current Number of Placed Queens: ");
		countPanel.add(lblNumberOfQueens);
		
		//TextField that displays current number of queens placed
		currentQueens = new JTextField();
		currentQueens.setEditable(false);
		currentQueens.setText("0");
		countPanel.add(currentQueens);
		currentQueens.setColumns(2);
	}
}
