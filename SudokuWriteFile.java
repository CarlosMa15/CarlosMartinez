/*************************************************
 * Authors: Carlos Martinez and Patrick Leishman
 * Date: April 15, 2017
 * Assignment: Team Project
 * Description: Sudoku
 ************************************************/
package sudoku;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * This class creates a writer to a file
 * @author Carlos Martinez and Patrick Leishman
 */
public class SudokuWriteFile {
	
	/**
	 * This keeps track od the number of wrong moves
	 */
	private int numberOfWrongMoves;
	
	 /** This keeps track of the number of
	 * moves that the player has made to solve the board
	 */
	private int numberOfMoves;
	
	/**
	 * This keeps track of the number of correct moves
	 * that the player did to solve the board.
	 */
	private int numberOfCorrectMoves;
	
	/**
	 * this keeps track of the possible correct moves
	 * that the player can possibly make
	 */
	private int numberOfPossibleCorrectMoves;
	
	/**
	 * This is used to know if the program needs to 
	 * display the number of wrong moves or not
	 */
	private boolean wrongMovesDisplay;
	
	/**
	 * This creates a new class that writes to a file 
	 * depending from the information
	 * @param pm number Of Possible Correct Moves
	 * @param cm number Of Correct Moves
	 * @param wm number Of Wrong Moves
	 * @param tm number Of Moves Made
	 */
	public SudokuWriteFile(int pm,int cm,int wm,int tm) {
		this.numberOfCorrectMoves = cm;
		this.numberOfPossibleCorrectMoves = pm;
		this.numberOfMoves = tm;
		this.numberOfWrongMoves = wm;
		this.wrongMovesDisplay = true;
	}
	
	
	
	/**
	 * This methods writes to a file
	 */
	public void writefile() {
		try (PrintWriter writer = new PrintWriter("src/sudoku/Resources/SudokuGuiStats.txt")) {
			writer.printf("%40s : %-1s%n","SUDOKU GAME","STATS");
			writer.printf("%40s : %-1d%n","Number Of Moves Made",numberOfMoves);
			if (this.wrongMovesDisplay) {
				writer.printf("%40s : %-1d%n","Number Of Wrong Moves Made",numberOfWrongMoves);
			}
			writer.printf("%40s : %-1d%n","Number Of Correct Moves Made",numberOfCorrectMoves);
			writer.printf("%40s : %-1d%n","Number Of Possible Correct Moves",numberOfPossibleCorrectMoves);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}