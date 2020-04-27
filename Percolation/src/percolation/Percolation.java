/*************************************************
 * Author: Carlos Martinez
 * Date: January 27, 2017
 * Assignment: Percolation
 ************************************************/
package percolation;

//Import Statements
import java.util.Arrays;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Class Percolation for Assignment Percolation used
 * to run the Percolation Visualizer.
 * @author Carlos Martinez
 */
public class Percolation {
	//Fields
	/**
	 * This is the number of objects in each row and
	 *  the number of objects in each column. These
	 *  have the same number of objects in both.
	 */
	private int N;
	
	/**
	 * This is the object that keeps track if the 
	 * object's sites are opened or closed.
	 */
	private boolean[] sites;
	
	/**
	 * This is the object that checks if the object
	 * percolates or not
	 */
	private WeightedQuickUnionUF unionUF;
	
	/**
	 * The object that is used to check if the object
	 * is full while not causing back wash problem
	 */
	private WeightedQuickUnionUF unionUF1;
	
	/**
	 * This is the number of sites that are created.
	 */
	private int size;
	
	//Constructors
	/**
	 * This constructor creates an object of Percolation.
	 * @param n The number of Rows and columns in each row.
	 * It is a square so it uses the same variable.
	 */
	public Percolation(int n) {
		if (n <= 0) {
			throw new IllegalArgumentException();
		}
		
		this.N = n;
		this.size = n * n;
		sites = new boolean[size];
		Arrays.fill(sites, false);
		unionUF = new WeightedQuickUnionUF(size + 2);
		unionUF1 = new WeightedQuickUnionUF(size + 1);
		
		//Connecting the Top
		for (int i = 0; i < N - 1; i++) {
			unionUF.union(i, i + 1);
		}
		unionUF.union(size, 0);
		
		//Connects the Bottom
		for (int i = 0; i < N - 1; i++) {
			int sub = i + 1;
			unionUF.union(size - sub, size - sub - 1);
		}
		unionUF.union(size - 1, size + 1);
		
		//connects the other top
		for (int i = 0; i < N - 1; i++) {
			unionUF1.union(i, i + 1);
		}
		unionUF1.union(size, 0);
	}
	
	//Methods
	/**
	 * This Method opens the site of a given index. Top left
	 * Corner equals (0,0), The method is also responsible 
	 * for connecting the unions connections that gives the 
	 * functionality to the rest of the program.
	 * @param i The row location
	 * @param j The column location
	 */
	public void open(int i, int j) {
		if (i < 0 || i > N - 1 || j < 0 || j > N - 1) {
			throw new IndexOutOfBoundsException();
		}
		int index = (i * N) + j;
		if (sites[index] == false) {
			sites[index] = true;

			// Checking in row Zero
			if (i == 0) {
				// Checking in column Zero
				if (j == 0) {
					if (sites[index + 1]) {
						unionUF.union(index, index + 1);
					}
					if (sites[index + N]) {
						unionUF.union(index, index + N);
					}
					if (sites[index + 1]) {
						unionUF1.union(index, index + 1);
					}
					if (sites[index + N]) {
						unionUF1.union(index, index + N);
					}
				}

				// Check column N
				else if (j == N - 1) {
					if (sites[index - 1]) {
						unionUF.union(index, index - 1);
					}
					if (sites[index + N]) {
						unionUF.union(index, index + N);
					}
					if (sites[index - 1]) {
						unionUF1.union(index, index - 1);
					}
					if (sites[index + N]) {
						unionUF1.union(index, index + N);
					}
				}
				// Just in row Zero
				else {
					if (sites[index + N]) {
						unionUF.union(index, index + N);
					}
					if (sites[index - 1]) {
						unionUF.union(index, index - 1);
					}
					if (sites[index + 1]) {
						unionUF.union(index, index + 1);
					}
					if (sites[index + N]) {
						unionUF1.union(index, index + N);
					}
					if (sites[index - 1]) {
						unionUF1.union(index, index - 1);
					}
					if (sites[index + 1]) {
						unionUF1.union(index, index + 1);
					}
				}
			}

			// Check if you are in the last Row
			else if (i == N - 1) {
				// Check if you are in the first column
				if (j == 0) {
					if (sites[index + 1]) {
						unionUF.union(index, index + 1);
					}
					if (sites[index - N]) {
						unionUF.union(index, index - N);
					}
					if (sites[index + 1]) {
						unionUF1.union(index, index + 1);
					}
					if (sites[index - N]) {
						unionUF1.union(index, index - N);
					}
				}
				// Check if last column
				else if (j == N - 1) {
					if (sites[index - 1]) {
						unionUF.union(index, index - 1);
					}
					if (sites[index - N]) {
						unionUF.union(index, index - N);
					}
					if (sites[index - 1]) {
						unionUF1.union(index, index - 1);
					}
					if (sites[index - N]) {
						unionUF1.union(index, index - N);
					}
				}

				else {
					if (sites[index - 1]) {
						unionUF.union(index, index - 1);
					}
					if (sites[index - N]) {
						unionUF.union(index, index - N);
					}
					if (sites[index + 1]) {
						unionUF.union(index, index + 1);
					}
					if (sites[index - 1]) {
						unionUF1.union(index, index - 1);
					}
					if (sites[index - N]) {
						unionUF1.union(index, index - N);
					}
					if (sites[index + 1]) {
						unionUF1.union(index, index + 1);
					}
				}
			}

			// Check if in the first Column
			else if (j == 0) {
				if (sites[index + 1]) {
					unionUF.union(index, index + 1);
				}
				if (sites[index + N]) {
					unionUF.union(index, index + N);
				}
				if (sites[index - N]) {
					unionUF.union(index, index - N);
				}
				if (sites[index + 1]) {
					unionUF1.union(index, index + 1);
				}
				if (sites[index + N]) {
					unionUF1.union(index, index + N);
				}
				if (sites[index - N]) {
					unionUF1.union(index, index - N);
				}
			}
			// Check if in the last column
			else if (j == N - 1) {
				if (sites[index - 1]) {
					unionUF.union(index, index - 1);
				}
				if (sites[index + N]) {
					unionUF.union(index, index + N);
				}
				if (sites[index - N]) {
					unionUF.union(index, index - N);
				}
				if (sites[index - 1]) {
					unionUF1.union(index, index - 1);
				}
				if (sites[index + N]) {
					unionUF1.union(index, index + N);
				}
				if (sites[index - N]) {
					unionUF1.union(index, index - N);
				}
			}

			else if (i > 0 && i < N - 1 && j > 0 && j < N - 1) {
				if (sites[index - 1]) {
					unionUF.union(index, index - 1);
				}
				if (sites[index + N]) {
					unionUF.union(index, index + N);
				}
				if (sites[index - N]) {
					unionUF.union(index, index - N);
				}
				if (sites[index + 1]) {
					unionUF.union(index, index + 1);
				}
				if (sites[index - 1]) {
					unionUF1.union(index, index - 1);
				}
				if (sites[index + N]) {
					unionUF1.union(index, index + N);
				}
				if (sites[index - N]) {
					unionUF1.union(index, index - N);
				}
				if (sites[index + 1]) {
					unionUF1.union(index, index + 1);
				}
			}
		}
	}
	
	/**
	 * This Method just Checks if the site of a certain index
	 * is open
	 * @param i The row location
	 * @param j The column location
	 * @return boolean, returns true id the site is open,
	 * returns false if the site is not open.
	 */
	public boolean isOpen(int i, int j) {
		if (i < 0 || i > N - 1 || j < 0 || j > N - 1) {
			throw new IndexOutOfBoundsException();
		}
		int index = (i * N) + j;
		return sites[index];
	}
	
	/**
	 * This method Just Checks if the site is connected to
	 * the top given their location. Top left corner (0,0)
	 * @param i The Row location
	 * @param j The column location
	 * @return boolean, true if site is connected to the top
	 * returns false if the site is not connected to the top
	 */
	public boolean isFull(int i, int j) {
		if (i < 0 || i > N - 1 || j < 0 || j > N - 1) {
			throw new IndexOutOfBoundsException();
		}
		int index = (i * N) + j;
		if (unionUF1.connected(index, size) && sites[index]) {
			return true;
		}
		return false;
	}
	
	/**
	 * This Method just checks if the program percolates,
	 * if the method is connected from top to bottom.
	 * @return	boolean, true if top and bottom are connecting
	 * false if top and bottom are not connected.
	 */
	public boolean percolates() {

		return unionUF.connected(size, size + 1);
	}
	
	//This Method is not Part of the Assignment, 
	//Just used to test the Class and run the Visualizer
	/**
	 * This Method Checks how many Sites are Opened
	 * This Method is Not Required in the Assignment,
	 * it is just used to test the code.
	 * @return Number of Sites that are Opened
	 */
	public int numberOfOpenSites(){
		int counter = 0;
		for(boolean el : sites){
			if (el){
				counter ++;
			}
		}
		return counter;
	}
}