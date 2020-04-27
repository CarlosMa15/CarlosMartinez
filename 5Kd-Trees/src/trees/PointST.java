/*****************************************************
 * Author Carlos Humberto Martinez Guadarrama
 * Date: March 28, 2017
 * Assignment 5: KD- Tree
 ****************************************************/
package trees;

//Import Statement
import java.util.ArrayList;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.RedBlackBST;

/**
 * Assignment 5: KD tree
 * 
 * @author Carlos Martinez
 * @param <Value>
 */
public class PointST<Value> {

	/**
	 * This is the symbol Table represented by a BST that store everything
	 */
	private RedBlackBST<Point2D, Value> symbolTable;

	/**
	 * Construct an empty symbol table of points
	 */
	public PointST() {
		symbolTable = new RedBlackBST<Point2D, Value>();
	}

	/**
	 * Is the symbol table empty?
	 * 
	 * @return returns true if the symbol table is empty returns false if the
	 *         symbol table is not empty
	 */
	public boolean isEmpty() {
		return symbolTable.isEmpty();
	}

	/**
	 * This methods returns the number of points in the symbol table
	 * 
	 * @return The number of points in the symbol table
	 */
	public int size() {
		return symbolTable.size();
	}

	/**
	 * associate the value value with point p
	 * 
	 * @param p
	 *            the Point2D
	 * @param val
	 *            the Value
	 */
	public void put(Point2D p, Value val) {
		if (p == null || val == null) {
			throw new NullPointerException();
		}

		symbolTable.put(p, val);
	}

	/**
	 * This method returns the value associated with the point p
	 * 
	 * @param p
	 *            the point2D
	 * @return The value associated with the point p
	 */
	public Value get(Point2D p) {
		if (p == null) {
			throw new NullPointerException();
		}

		return symbolTable.get(p);
	}

	/**
	 * Does the symbol table contain point p?
	 * 
	 * @param p
	 *            the point we are looking for
	 * @return false if the symbol table does not contain the point returns true
	 *         if the symbol table contains the point
	 */
	public boolean contains(Point2D p) {
		if (p == null) {
			throw new NullPointerException();
		}

		return symbolTable.contains(p);
	}

	/**
	 * This method return an iterator of all the Point2D from the symbol table
	 * 
	 * @return an iterator of all the Point2D from the symbol table
	 */
	public Iterable<Point2D> points() {
		return symbolTable.keys();
	}

	/**
	 * This method returns an arrayList of all the points inside the Rectangle
	 * 
	 * @param rect
	 *            the Rectangle
	 * @return an arrayList of all the points inside the Rectangle
	 */
	public Iterable<Point2D> range(RectHV rect) {
		if (rect == null) {
			throw new NullPointerException();
		}

		ArrayList<Point2D> points = new ArrayList<Point2D>();
		
		for (Point2D el : symbolTable.keys()) {
			if (rect.contains(el)) {
				points.add(el);
			}
		}

		return points;
	}

	/**
	 * This method finds the nearest point from the symbol Table to the given
	 * point
	 * 
	 * @param p
	 *            the point
	 * @return returns the nearest point to the given point in the symbol Table
	 */
	public Point2D nearest(Point2D p) {
		if (p == null) {
			throw new NullPointerException();
		}

		if (symbolTable.isEmpty()) {
			return null;
		}

		Point2D nearestPoint = symbolTable.min();
		double distance = p.distanceSquaredTo(nearestPoint);

		// Checks all points to find the nearest
		for (Point2D key : points()) {
			if (p.distanceSquaredTo(key) < distance) { //&& p.compareTo(key) != 0) { causes error
				nearestPoint = key;
				distance = p.distanceSquaredTo(key);
			}
		}

		return nearestPoint;
	}

	/**
	 * Unit testing of the methods (not graded)
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		PointST<Integer> st = new PointST<>();
		System.out.println("Is the PointST<Point2D> empty: " + st.isEmpty());

		st.put(new Point2D(10,10),  10);
		st.put(new Point2D(20,20),  40);
		st.put(new Point2D(30,30),  90);
		st.put(new Point2D(40,40), 160);
		st.put(new Point2D(50,50), 250);
		
		System.out.println("Is the PointST<Point2D> empty: " + st.isEmpty());
		System.out.println("Number of Elements: " + st.size());
		System.out.println("Does PointST<Point2D> contain Point2D(3,3): " + st.contains(new Point2D(3,3)));
		System.out.println();
		
		System.out.println("Iterator of points:");
		for(Point2D el: st.points()) {
			System.out.println(el);
		}
		System.out.println();
		
		System.out.println("Nearest point from PointST<Point2D> to point(60,60): " + st.nearest(new Point2D(60,60)));
		System.out.println();
		
		System.out.println("Range Iterator:");
		for(Point2D el: st.range(new RectHV(0,0,50,100))) {
			System.out.println(el);
		}
	}
}