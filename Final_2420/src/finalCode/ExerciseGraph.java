/********************************************
 * Author: Carlos Martinez
 * Date: May 4, 2015
 * Assignment: Final 2420
 *******************************************/
package finalCode;

import java.util.Scanner;

import edu.princeton.cs.algs4.BreadthFirstPaths;
import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.In;

/**
 * ExerciseGraph
 * @author carlosmartinez
 */
public class ExerciseGraph {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		In in = new In("src/finalCode/FinalGraph.txt");
		Graph graph = new Graph(in);
		
		System.out.print("Starting vertex (0 - 8): ");
		int start = input.nextInt();
		System.out.print("Destination vertex (0 - 8): ");
		int end = input.nextInt();
		
		BreadthFirstPaths breadthFirstPaths = new BreadthFirstPaths(graph,start);
		boolean first = true;
		for(Integer el: breadthFirstPaths.pathTo(end)) {
			if (first) {
				System.out.print(el);
				first = false;
			} else {
				System.out.print(" " + el);
			}
		}
	}
}