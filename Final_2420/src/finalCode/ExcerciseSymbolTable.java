/********************************************
 * Author: Carlos Martinez
 * Date: May 4, 2015
 * Assignment: Final 2420
 *******************************************/
package finalCode;

import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.StdOut;

/**
 * ExcerciseSymbolTable
 * @author carlosmartinez
 */
public class ExcerciseSymbolTable {

	public static void main(String[] args) {
		Point[] points = { new Point(1, 3), new Point(5, 4), new Point(7, 2), new Point(9, 5), new Point(4, 9),
				new Point(2, 0), new Point(8, 3), new Point(3, 6), new Point(0, 8), new Point(6, 7), new Point(1, 9),
				new Point(5, 0), new Point(7, 3), new Point(9, 4), new Point(4, 5), new Point(2, 6), new Point(0, 7),
				new Point(3, 8), new Point(8, 9), new Point(6, 1) };

		StdOut.println("I chose the symbol table ... because ... ");
		StdOut.println("It garantees log(n) even in worst case senerior and it has the methods required to do assignment");
		System.out.println();
		
		RedBlackBST<Point,Double> pointST = new RedBlackBST<Point,Double>();
		
		Point origin = new Point(0,0);
		Point doubleFive = new Point(5,5);
		Point p = new Point(7,3);
		
		for (Point el: points) {
			pointST.put(el, el.distanceTo(origin));
		}
		
		System.out.println("Smallest Point: " + pointST.min());
		System.out.println("Samllest point Larger than (5,5): " + pointST.ceiling(doubleFive));
		
		if(pointST.contains(p)) {
			System.out.println("The Point " + p + " is part of the pointST");
		} else {
			System.out.println("PointST does not contain that point");
		}
	}
}