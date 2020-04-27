/*************************************************
 * Authors: Carlos Martinez and Patrick Leishman
 * Date: April 15, 2017
 * Assignment: Team Project
 * Description: Sudoku
 ************************************************/
package sudoku;

//Import Statement
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * A GUI for playing sudoku games that are imported from a .txt file
 * 
 * @author Patrick Leishman & Carlos Guadarrama
 *
 */
public class SudokuGUI extends JFrame {
	
	private static final long serialVersionUID = 4407732599510119850L;
	
	/**
	 * This panel holds buttons and comboBoxes 
	 * to provide functionality to the user
	 */
	private JPanel controlPanel;
	
	/**
	 * This Pane holds everything
	 */
	private JPanel contentPane;
	
	/**
	 * This holds the sudokuPanel and helps in formating it.
	 */
	private JPanel displayPanel;
	
	/**
	 * This panel holds the JTextFields that make up most of the program
	 */
	private JPanel sudokuPanel;

	/**
	 *  triggers a new game
	 */
	private JButton btnNewGame;

	/**
	 *  button for checking if what was entered is correct
	 */
	private JButton btnMuteBackGroundMusic;

	/**
	 *  combo box for selecting difficulty
	 */
	private JComboBox<String> diffComboBox;

	/**
	 *  holds a table of SudokuCells which extend JTextField
	 */
	private SudokuCell[][] cells = new SudokuCell[9][9];
	
	/**
	 * holds the DocumentHandlers of each JTextFields
	 */
	private DocumentHandler[][] docHandlers = new DocumentHandler[9][9];

	/**
	 *  imports a list of sudoku puzzles of the indicated difficulty
	 */
	private SudokuPuzzleList blank1 = new SudokuPuzzleList("src/sudoku/puzzlesBlank/difficulty1/", 1);
	
	/**
	 * imports a list of sudoku puzzles solutions of the indicated difficulty
	 */
	private SudokuPuzzleList solution1 = new SudokuPuzzleList("src/sudoku/puzzlesSolutions/difficulty1/", 1);
	
	/**
	 *  imports a list of sudoku puzzles of the indicated difficulty
	 */
	private SudokuPuzzleList blank2 = new SudokuPuzzleList("src/sudoku/puzzlesBlank/difficulty2/", 2);
	
	/**
	 *  imports a list of sudoku puzzles of the indicated difficulty
	 */
	private SudokuPuzzleList solution2 = new SudokuPuzzleList("src/sudoku/puzzlesSolutions/difficulty2/", 2);

	/**
	 *  assigns the currently active puzzle and solution board
	 */
	private SudokuPuzzle activeSolution = solution1.getPuzzle(0);
	
	/**
	 *  assigns the currently active puzzle and solution board
	 */
	private SudokuPuzzle activePuzzle = blank1.getPuzzle(0);

	/**
	 *  index of PuzzleList to be used
	 */
	private int puzzleCount = 1;
	
	/**
	 * This keeps track of the possible correct moves 
	 * the player can make in each board
	 */
	private int totalPossibleMoves;
	
	/**
	 * This keeps track on the number of correct moves the player made
	 */
	private int totalCorrectMoves;
	
	/**
	 * This keeps track of the number of wrong moves the player did
	 */
	private int totalWrongMoves;
	
	/**
	 * This keeps track of the number of moves the player made
	 */
	private int totalMovesMade;

	/**
	 * This plays background music
	 */
	private Clip backgroundMusic;

	/**
	 * This button is used to control sound effects
	 */
	private JButton btnMuteEffects;
	
	/**
	 * This is the numbers of lives the player has left
	 */
	private int numberOfLives = 5;
	
	/**
	 * This displays the number of lives
	 */
	private JLabel btnLives;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SudokuGUI frame = new SudokuGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SudokuGUI() {

		setTitle("Sudoku!");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 0, 800, 750);
		
		createContentPane();
		setContentPane(contentPane);

		createControlPanel();
		contentPane.add(controlPanel, BorderLayout.NORTH);

		createDisplayPanel();
		contentPane.add(displayPanel, BorderLayout.CENTER);
		
		JPanel livesPanel = createLivesPanel();
		
		contentPane.add(livesPanel, BorderLayout.SOUTH);

		createBackgroundMusic();
	}
	
	/**
	 * This creates the lives panel that displays the number of lives the player has left
	 * @return a lives panel
	 */
	private JPanel createLivesPanel() {
		JPanel livesPanel = new JPanel();
		livesPanel.setBackground(new Color(140, 212, 255));
		btnLives = new JLabel("NUMBER OF LIVES: " + numberOfLives);
		btnLives.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 30));
		btnLives.setBorder(new EmptyBorder(0, 0, 30, 0));
		livesPanel.add(btnLives);
		return livesPanel;
	}
	
	/**
	 * This creates the content Pane
	 */
	private void createContentPane() {
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
	}
	
	/**
	 * this creates the Display panel 
	 * the holds the main part of the program
	 */
	private void createDisplayPanel() {
		displayPanel = new JPanel();
		displayPanel.setBackground(new Color(140, 212, 255));
		createSudokuPanel();
		setPuzzleCells();
		displayPanel.add(sudokuPanel);
	}
	
	/**
	 * this creates the sudokuPanel that has the main 
	 * functionality of the program and the JTextFields
	 */
	private void createSudokuPanel() {
		sudokuPanel = new JPanel();
		sudokuPanel.setForeground(new Color(255, 255, 255));
		sudokuPanel.setPreferredSize(new Dimension(550, 550));
		sudokuPanel.setLayout(new GridLayout(9, 9, 0, 0));

		// initialize the sudoku cells
		for (int r = 0; r < cells.length; r++) {
			for (int c = 0; c < cells[r].length; c++) {
				cells[r][c] = new SudokuCell();

				if (r >= 0 && r < 3 && c >= 0 && c < 3) {
					cells[r][c].setBorder(new LineBorder(Color.BLUE));
				}
				if (r >= 0 && r < 3 && c > 5) {
					cells[r][c].setBorder(new LineBorder(Color.BLUE));
				}

				if (r >= 6 && c >= 0 && c < 3) {
					cells[r][c].setBorder(new LineBorder(Color.BLUE));
				}
				if (r >= 6 && c > 5) {
					cells[r][c].setBorder(new LineBorder(Color.BLUE));
				}
				if (r >= 3 && r <= 5 && c >= 3 && c <= 5) {
					cells[r][c].setBorder(new LineBorder(Color.BLUE));
				}
				if (r >= 0 && r <= 2 && c >= 3 && c <= 5) {
					cells[r][c].setBorder(new LineBorder(Color.BLACK));
				}
				if (r >= 6 && r <= 8 && c >= 3 && c <= 5) {
					cells[r][c].setBorder(new LineBorder(Color.BLACK));
				}
				if (r >= 3 && r <= 5 && c >= 0 && c <= 2) {
					cells[r][c].setBorder(new LineBorder(Color.BLACK));
				}
				if (r >= 3 && r <= 5 && c >= 6 && c <= 8) {
					cells[r][c].setBorder(new LineBorder(Color.BLACK));
				}

			}
		}

		for (int r = 0; r < cells.length; r++) {
			for (int c = 0; c < cells[r].length; c++) {
				sudokuPanel.add(cells[r][c]);
			}
		}
	}

	/**
	 * this creates the Clip and start 
	 * playing the background music
	 */
	private void createBackgroundMusic() {
		try {
			AudioInputStream audioInputStream = AudioSystem
					.getAudioInputStream(new File("src/sudoku/Resources/HalseyGasoline.wav"));
			backgroundMusic = AudioSystem.getClip();
			backgroundMusic.open(audioInputStream);
			backgroundMusic.loop((int) Double.POSITIVE_INFINITY);
			backgroundMusic.start();

		} catch (Exception ex) {
			System.out.println("Error with playing sound.");
			ex.printStackTrace();
		}
	}
	
	/**
	 * This sets the the starting value of the TextField
	 */
	private void setPuzzleCells() {
		for (int r = 0; r < cells.length; r++) {
			for (int c = 0; c < cells[r].length; c++) {
				cells[r][c].getDocument().removeDocumentListener(docHandlers[r][c]);
				cells[r][c].setBackground(Color.WHITE);
				docHandlers[r][c] = new DocumentHandler(cells[r][c]);
				cells[r][c].setText("" + activePuzzle.getPuzzleElement(r, c));
				if (!cells[r][c].getText().equals("0")) {
					cells[r][c].setForeground(Color.BLUE);
					cells[r][c].setEditable(false);
				}
				if (cells[r][c].getText().equals("0")) {
					cells[r][c].setEditable(true);
					cells[r][c].setText("");
					cells[r][c].setSolution(activeSolution.getPuzzleElement(r, c));
					cells[r][c].getDocument().addDocumentListener(docHandlers[r][c]);
					totalPossibleMoves++;
				}
			}
		}
	}

	/**
	 * An event handler that dynamically changes a SudokuCell based on correct
	 * or incorrect input.
	 * 
	 * @author Patrick Leishman
	 *
	 */
	private class DocumentHandler implements DocumentListener {
		/**
		 * This is the a JTextField 
		 */
		private SudokuCell cell;
		
		/**
		 * creats a DocumentHandler to each cell
		 * @param cell a JTextField
		 */
		public DocumentHandler(SudokuCell cell) {
			this.cell = cell;
		}
		
		/**
		 * The functionality that the textField will do
		 */
		private void action() {
			if (Integer.parseInt(cell.getText()) == (cell.getSolution())) {
				cell.setForeground(Color.BLUE);
				cell.setBackground(Color.GREEN);
				cell.setEditable(false);
				totalCorrectMoves++;
				totalMovesMade++;
				if (btnMuteEffects.getText() == "MUTE EFFECTS") {
					if (totalPossibleMoves - totalCorrectMoves == 0) {
						changeAllBacksGreen();
						btnLives.setText("YOU WON");
						try {
							AudioInputStream audioInputStream1 = AudioSystem
									.getAudioInputStream(new 
											File("src/sudoku/Resources/Complete.wav"));
							Clip correctSound = AudioSystem.getClip();
							correctSound.open(audioInputStream1);
							correctSound.start();

						} catch (Exception ex) {
							System.out.println("Error with playing sound.");
							ex.printStackTrace();
						}
					} else {
						try {
							AudioInputStream audioInputStream1 = AudioSystem
									.getAudioInputStream(new 
											File("src/sudoku/Resources/CorrectAnswerSoundEffect.wav"));
							Clip correctSound = AudioSystem.getClip();
							correctSound.open(audioInputStream1);
							correctSound.start();

						} catch (Exception ex) {
							System.out.println("Error with playing sound.");
							ex.printStackTrace();
						}
					}
				}
				if (totalPossibleMoves - totalCorrectMoves == 0) {
					changeAllBacksGreen();
					btnLives.setText("YOU WON");
				}
			}
			if (Integer.parseInt(cell.getText()) != (cell.getSolution())) {
				if (btnMuteEffects.getText() == "MUTE EFFECTS") {
					try {
						AudioInputStream audioInputStream2 = AudioSystem
								.getAudioInputStream(new 
										File("src/sudoku/Resources/IncorrectSoundEffect.wav"));
						Clip incorrectSound = AudioSystem.getClip();
						incorrectSound.open(audioInputStream2);
						incorrectSound.start();

					} catch (Exception ex) {
						System.out.println("Error with playing sound.");
						ex.printStackTrace();
					}
				}

				cell.setBackground(Color.RED);
				cell.setForeground(Color.WHITE);
				totalWrongMoves++;
				totalMovesMade++;
				numberOfLives--;
				btnLives.setText("NUMBER OF LIVES: " + numberOfLives);
				
				if (numberOfLives == 0) {
					changeToLoseGame();
				}
			}
		}
		
		/**
		 * Set everything to red because you lost
		 */
		private void changeToLoseGame() {
			for(int row = 0;row < cells.length;row++) {
				for(int col = 0;col < cells.length;col++) {
					cells[row][col].setBackground(Color.RED);
					cells[row][col].setEditable(false);
					cells[row][col].setForeground(Color.BLACK);
				}
			}
			btnLives.setText("YOU LOSE");
		}
		
		/**
		 * This method changes all the back Grounds of cells to Green
		 */
		private void changeAllBacksGreen() {
			for(int row = 0;row < cells.length;row++) {
				for(int col = 0;col < cells.length;col++) {
					cells[row][col].setBackground(Color.GREEN);
				}
			}
		}

		@Override
		public void changedUpdate(DocumentEvent arg0) {
			action();
		}

		@Override
		public void insertUpdate(DocumentEvent arg0) {
			action();
		}

		@Override
		public void removeUpdate(DocumentEvent arg0) {
			cell.setBackground(Color.WHITE);
		}
	}
	
	/**
	 * Creates the control Panel
	 */
	private void createControlPanel() {
		controlPanel = new JPanel();
		controlPanel.setBackground(new Color(140, 212, 255));
		controlPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
		
		createNewButtonAndFunctionality();
		controlPanel.add(btnNewGame);
		
		//Creates a label for the difficulty label
		JLabel lblDifficulty = new JLabel("DIFFICULTY");
		lblDifficulty.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 14));
		controlPanel.add(lblDifficulty);
		
		//creates a combo box to select the difficulty of the board games 
		diffComboBox = new JComboBox<String>();
		diffComboBox.addItem("Easy");
		diffComboBox.addItem("Hard");
		controlPanel.add(diffComboBox);

		createdMuteButtonAndFunctionality();
		controlPanel.add(btnMuteBackGroundMusic);
		
		createMuteEffectsButtonAndFunctionality();
		controlPanel.add(btnMuteEffects);

		JButton btnExit = createExitbuttonAndFunctionality();
		controlPanel.add(btnExit);
	}
	
	/**
	 * create Mute Effects Button And Functionality
	 */
	private void createMuteEffectsButtonAndFunctionality() {
		btnMuteEffects = new JButton("MUTE EFFECTS");
		btnMuteEffects.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnMuteEffects.getText() == "MUTE EFFECTS") {
					btnMuteEffects.setText("PLAY EFFECTS");
				} else {
					btnMuteEffects.setText("MUTE EFFECTS");
				}
			}
		});

		btnMuteEffects.setPreferredSize(new Dimension(150, 40));
		btnMuteEffects.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 14));
	}
	
	/**
	 * create Exit button And Functionality
	 * this also writes to a file when finished
	 * @return an exit button
	 */
	private JButton createExitbuttonAndFunctionality() {
		JButton btnExit = new JButton("EXIT");
		btnExit.setPreferredSize(new Dimension(100, 40));
		btnExit.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 14));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				SudokuWriteFile writer = new SudokuWriteFile(totalPossibleMoves, totalCorrectMoves, totalWrongMoves,
						totalMovesMade);
				writer.writefile();
				System.exit(0);
			}
		});
		return btnExit;
	}
	
	/**
	 * created Mute Button And Functionality
	 */
	private void createdMuteButtonAndFunctionality() {
		btnMuteBackGroundMusic = new JButton("MUTE MUSIC");
		btnMuteBackGroundMusic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (backgroundMusic.isActive()) {
					backgroundMusic.stop();
					btnMuteBackGroundMusic.setText("PLAY MUSIC");
				} else {
					backgroundMusic.loop((int) Double.POSITIVE_INFINITY);
					backgroundMusic.start();
					btnMuteBackGroundMusic.setText("MUTE MUSIC");
				}
			}
		});

		btnMuteBackGroundMusic.setPreferredSize(new Dimension(140, 40));
		btnMuteBackGroundMusic.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 14));
	}
	
	/**
	 * create New Button And Functionality
	 */
	private void createNewButtonAndFunctionality() {
		btnNewGame = new JButton("NEW GAME");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createNewGame();
			}
		});
		btnNewGame.setPreferredSize(new Dimension(125, 40));
		btnNewGame.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 14));
	}
	
	/**
	 * this is the functionality that will be performed when
	 * the new button is clicked, it creates a new game 
	 * reseting the game stats as well
	 */
	private void createNewGame() {
		totalCorrectMoves = 0;
		totalWrongMoves = 0;
		totalMovesMade = 0;
		totalPossibleMoves = 0;

		if (puzzleCount > 2) {
			puzzleCount = 0;
		}

		String diff = (String) diffComboBox.getSelectedItem();
		switch (diff) {
		case "Easy":
			activeSolution = solution1.getPuzzle(puzzleCount);
			activePuzzle = blank1.getPuzzle(puzzleCount);
			numberOfLives = 5;
			break;
		case "Hard":
			activeSolution = solution2.getPuzzle(puzzleCount);
			activePuzzle = blank2.getPuzzle(puzzleCount);
			numberOfLives = 3;
			break;
		}
		btnLives.setText("NUMBER OF LIVES: " + numberOfLives);
		setPuzzleCells();
		puzzleCount++;
	}
}