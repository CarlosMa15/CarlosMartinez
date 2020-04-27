/*************************************************
 * Authors: Carlos Martinez and Patrick Leishman
 * Date: April 15, 2017
 * Assignment: Team Project
 * Description: Sudoku
 ************************************************/
package sudoku;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * A class for reading a single sudoku puzzle text file into a character array
 * 
 * @author Patrick Leishman and Carlos Martinez
 *
 */
public class SudokuFileReader {
	private static int length = 9;
	// path for a single sudoku text file
	private String filePath;
	// 2D char array for storing sudoku puzzle
	private char[][] charArray;
	private int[][] intArray;

	/**
	 * Creates a Sudoku File Reader with specified path and dimension length
	 * 
	 * @param filePathName
	 * @param length
	 */
	public SudokuFileReader(String filePathName) {
		this.filePath = filePathName;
		charArray = new char[length][length];
		intArray = new int[length][length];
		this.readFile();
		this.toIntArray();
	}

	/**
	 * reinitialize current fileReader with new path and array
	 * 
	 * @param filePathName
	 */
	public void reinitialize(String filePathName) {
		this.filePath = filePathName;
		charArray = new char[length][length];
		intArray = new int[length][length];
		this.readFile();
		this.toIntArray();
	}

	/**
	 * Reads each character from a sudoku.txt file and stores them in a double
	 * array
	 */
	public void readFile() {
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			for (int i = 0; i < length; i++) {
				String line = reader.readLine();
				charArray[i] = line.toCharArray();
			}
		} catch (IOException e) {
			System.err.println("The file could not be found and/or read");
		}
	}

	/**
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * @return 2D int array converted from char array
	 */
	public int[][] getIntArray() {
		return intArray;
	}

	/**
	 * @return convert char array to 2D int array
	 */
	private int[][] toIntArray() {
		for (int r = 0; r < length; r++) {
			for (int c = 0; c < length; c++) {
				intArray[r][c] = Character.getNumericValue(charArray[r][c]);
			}
		}
		return intArray;
	}

	/**
	 * @return int length of dimensions of puzzle being read
	 */
	public int getLength() {
		return length;
	}
}