/*************************************************
 * Author: Carlos Martinez
 * Date: January 27, 2017
 * Assignment: Percolation
 ************************************************/
package percolation;

//Import Statements
import java.util.Random;
import edu.princeton.cs.algs4.StdStats;

/**
 * This class is used to find statistics from
 * running a whole bunch of percolation methods
 * @author Carlos Martinez
 */
public class PercolationStats {
	//Fields
	/**
	 * This object just keep track of how many
	 * sites it took before 
	 */
	private int[] data;
	
	/**
	 * The number of Percolation ran
	 */
	private int T;
	
	/**
	 * This is the size of the percolation / Number of sites
	 */
	private int size;
	
	//Constructors
	/**
	 * This constructor runs many Percolation and gathers data
	 * @param N Rows and Columns in each Percolation
	 * @param T Number of percolation that are going to be run
	 */
	public PercolationStats(int N, int T){
		if (N <= 0 || T <= 0) {
			throw new IllegalArgumentException();
		}
		
		this.T = T;
		this.size = N * N;
		data = new int[T];
		int index = 0;
		Random rand =  new Random();
		int counter = 0;
		
		for(int a = 0;a < T; a++){
			Percolation myPercolation = new Percolation(N);
			counter = 0;
			do{
				int i = rand.nextInt(N);
				int j = rand.nextInt(N);
				if(!myPercolation.isOpen(i, j)){
					myPercolation.open(i, j);
					counter++;
				}
			}while(!myPercolation.percolates());
			data[index++] = counter;
		}
	}
	
	//Methods
	/**
	 * This method calculates the mean of the data
	 * @return average of the data
	 */
	public double mean(){ //u
		double mean = StdStats.mean(data);
		return mean / size;
	}
	
	/**
	 * this method calculates sample 
	 * standard deviation of percolation threshold
	 * @return sample standard deviation of percolation threshold
	 */
	public double stddev(){ //O
		double st = StdStats.stddev(data);
		return st/size;
	}
	
	/**
	 * This method calculates low end point of 95% confidence interval
	 * @return low end point of 95% confidence interval
	 */
	public double confidenceLow(){
		double top = 1.96 * stddev();
		double bottom = (Math.sqrt(T));
		double fraction = top/bottom;
		double low = mean() - fraction;
		return low;
	}
	
	/**
	 * This method calculates high end point of 95% confidence interval
	 * @return high end point of 95% confidence interval
	 */
	public double confidenceHigh(){
		double top = 1.96 * stddev();
		double bottom = (Math.sqrt(T));
		double fraction = top/bottom;
		double high = mean() + fraction;
		return high;
	}
}