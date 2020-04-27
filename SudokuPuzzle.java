/*************************************************
 * Authors: Carlos Martinez and Patrick Leishman
 * Date: April 15, 2017
 * Assignment: Team Project
 * Description: Sudoku
 ************************************************/
package sudoku;

import java.util.ArrayList;
import java.util.List;

/**
 * Creates a 2 dimensional ArrayList representing a Sudoku puzzle after reading
 * in the values from a text file. The puzzle has a name and difficulty
 * identifier.
 * 
 * @author Patrick Leishman and Carlos Martinez
 *
 */
public class SudokuPuzzle {
	
	/**
	 *  creates a reader that will take care of reading the .txt file
	 */
	private SudokuFileReader reader;
	
	/**
	 *  used for importing the data from the reader
	 */
	private int[][] puzzleArray;
	
	/**
	 *  main result of the SudokuPuzzleClass
	 */
	private List<List<Integer>> puzzleList;

	/**
	 *  difficulty from 1-3
	 */
	private int difficulty;

	/**
	 * Creates a sudoku puzzle object with a specific file path, name and
	 * difficulty identifier
	 * 
	 * @param path:
	 *            a String pathname
	 * @param name:
	 *            a String name of puzzle
	 * @param difficulty:
	 *            an int level of difficulty
	 */
	public SudokuPuzzle(String path, int difficulty) {
		this.reader = new SudokuFileReader(path);
		this.puzzleArray = reader.getIntArray();
		createArrayList();
		this.difficulty = difficulty;
	}

	/**
	 * changes the path and elements of the sudoku puzzle based on a new .txt
	 * file
	 * 
	 * @param path
	 *            .txt file
	 */
	public void newPath(String path) {
		this.reader.reinitialize(path);
		this.puzzleArray = reader.getIntArray();
		createArrayList();
	}

	/**
	 * Get a single cell
	 * 
	 * @param row
	 * @param col
	 * @return single cell from 2D ArrayList containing sudoku puzzle
	 */
	public int getPuzzleElement(int row, int col) {
		return puzzleList.get(row).get(col);
	}

	public List<List<Integer>> getPuzzleList() {
		return puzzleList;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String elements = this.getClass().getSimpleName() + "\n";
		for (List<Integer> list : puzzleList) {
			elements += list + "\n";
		}
		return elements;
	}

	/**
	 * initializes the ArrayList with the int elements from the reader as
	 * Integers
	 */
	private void createArrayList() {
		this.puzzleList = new ArrayList<List<Integer>>();
		for (int row = 0; row < reader.getLength(); row++) {
			puzzleList.add(new ArrayList<Integer>());
			for (int col = 0; col < reader.getLength(); col++) {
				puzzleList.get(row).add(new Integer(puzzleArray[row][col]));
			}
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((puzzleList == null) ? 0 : puzzleList.hashCode());
		return result;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SudokuPuzzle other = (SudokuPuzzle) obj;
		if (puzzleList == null) {
			if (other.puzzleList != null)
				return false;
		} else if (!puzzleList.equals(other.puzzleList))
			return false;
		return true;
	}

	/**
	 * @return the difficulty
	 */
	public int getDifficulty() {
		return difficulty;
	}

	/**
	 * @param difficulty
	 *            the difficulty to set
	 */
	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}
}