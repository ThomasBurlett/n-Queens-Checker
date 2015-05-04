package burlette;

import java.awt.EventQueue;
import javax.swing.JFrame;
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

public class project02 {

	private JFrame solutionChecker;
	private JPanel board;
	private JButton checkSolution;
	private JScrollPane scrollPane;
	private JTextArea textArea;
	private JToggleButton[][] gridArray;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					project02 window = new project02();
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
	public project02() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		solutionChecker = new JFrame();
		solutionChecker.getContentPane().setBackground(new Color(255, 228, 181));
		solutionChecker.setTitle("N-Queens Solution Checker");
		solutionChecker.setBounds(100, 100, 806, 500);
		solutionChecker.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		solutionChecker.getContentPane().setLayout(null);

		board = new JPanel();
		board.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		board.setBounds(380, 47, 400, 375);
		solutionChecker.getContentPane().add(board);
		board.setLayout(new GridLayout(8, 8, 0, 0));


		JLabel welcome = new JLabel(
				"Welcome to the N-Queens\r\n Solution Checker!");
		welcome.setFont(new Font("Tahoma", Font.BOLD, 14));
		welcome.setBounds(10, 11, 331, 64);
		solutionChecker.getContentPane().add(welcome);

		JLabel directions = new JLabel("<html><p>"
						+ "Press any of the squares on the board to place a queen. <br><br> "
						+ "The highlighted square acts as a placed queen. <br><br> Press the "
						+ "check solution button at any time to test the board. <br><br> At "
						+ "risk queens will be displayed in the text box below."
						+ "<br><br>If a solution is correct, it will say correct in the text area."
						+ "</p></html>");
		directions.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		directions.setBounds(20, 61, 303, 167);
		solutionChecker.getContentPane().add(directions);

		JLabel rowCloumn = new JLabel("Incorrect solutions will display at risk queens by (Row, Column).");
		rowCloumn.setFont(new Font("Tahoma", Font.PLAIN, 10));
		rowCloumn.setBounds(20, 293, 303, 14);
		solutionChecker.getContentPane().add(rowCloumn);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 318, 303, 104);
		solutionChecker.getContentPane().add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);

		gridArray = new JToggleButton[8][8];

	    ActionListener buttonListener = new ActionListener() {
	          @Override
	          public void actionPerformed(ActionEvent evt) {
	             JToggleButton selectedBtn = (JToggleButton) evt.getSource();
	             for (int row = 0; row < gridArray.length; row++) {
	                for (int col = 0; col < gridArray[row].length; col++) {
	                   if (gridArray[row][col] == selectedBtn) {
	                      System.out.printf("Selected row and column: %d %d%n", row + 1, col + 1);
	                   }
	                }
	             }
	          }
	       };
	       for (int row = 0; row < gridArray.length; row++) {
	           for (int col = 0; col < gridArray[row].length; col++) {
	        	   if ((col % 2 == 1 && row % 2 == 1) || (col % 2 == 0 && row % 2 == 0)) {
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
	     

		int height = 65;
		for (int i = 0; i < 8; i++) {
			String s = Integer.toString(i + 1);
			JLabel lblNewLabel = new JLabel(s);
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblNewLabel.setBounds(352, height, 18, 14);
			solutionChecker.getContentPane().add(lblNewLabel);
			height += 46;
		}

		int width = 398;
		for (int i = 0; i < 8; i++) {
			String s = Integer.toString(i + 1);
			JLabel label = new JLabel(s);
			label.setFont(new Font("Tahoma", Font.BOLD, 14));
			label.setBounds(width, 22, 16, 14);
			solutionChecker.getContentPane().add(label);
			width += 50;
			
		}
		
		checkSolution = new JButton("Check Solution!");
		checkSolution.setFont(new Font("Tahoma", Font.BOLD, 15));
		checkSolution.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.append(Boolean.toString(gridArray[0][0].isSelected()) + "\n");
			}
		});
		checkSolution.setBackground(new Color(100, 149, 237));
		checkSolution.setBounds(82, 239, 177, 52);
		solutionChecker.getContentPane().add(checkSolution);
	}
}
