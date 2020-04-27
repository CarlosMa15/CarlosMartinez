/*************************************************
 * Author: Carlos Martinez
 * Date: January 27, 2017
 * Assignment: Percolation
 * This class was used to test my PercolationStats
 * class to see that it is working properly
 ************************************************/

package percolation;

public class PercolationStatsTest {

	public static void main(String[] args) {
		PercolationStats per = new PercolationStats(200,100);
		System.out.println("mean()    = " + per.mean());
		System.out.println("stddev()  = " + per.stddev());
		System.out.println("Low()     = " + per.confidenceLow());
		System.out.println("High()    = " + per.confidenceHigh());
	}
}