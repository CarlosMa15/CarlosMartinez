/*************************************************
 * Authors: Carlos Martinez and Patrick Leishman
 * Date: April 15, 2017
 * Assignment: Team Project
 * Description: Sudoku
 ************************************************/
package sudoku;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * An object for generating an array list of SudokuPuzzles from a specified path
 * and difficulty level. Can be used to store a list of problems or solutions
 * based on directory.
 * 
 * @author Patrick Leishman and Carlos Martinez
 *
 */
public class SudokuPuzzleList {
	private List<SudokuPuzzle> puzzleList = new ArrayList<>();
	private List<File> fileList;
	private File directory;
	private int difficulty;

	/**
	 * Creates an object that stores the location, difficulty and an Array List
	 * of Sudoku Puzzles objects.
	 * 
	 * @param directory
	 * @param difficulty
	 */
	public SudokuPuzzleList(String directory, int difficulty) {
		this.directory = new File(directory);
		this.difficulty = difficulty;
		fileList = new ArrayList<File>(Arrays.asList(this.directory.listFiles()));
		for (File path : fileList) {
			puzzleList.add(new SudokuPuzzle(path.toString(), this.difficulty));
		}
	}

	/**
	 * 
	 * @return Puzzle List
	 */
	public List<SudokuPuzzle> getArrayList() {
		return puzzleList;
	}

	/**
	 * 
	 * @param index
	 * @return puzzle at index from list
	 */
	public SudokuPuzzle getPuzzle(int index) {
		return puzzleList.get(index);
	}
}