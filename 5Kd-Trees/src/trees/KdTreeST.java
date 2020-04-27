/*****************************************************
 * Author Carlos Humberto Martinez Guadarrama
 * Date: March 28, 2017
 * Assignment 5: KD- Tree
 * Helper: Sara Chadwick
 ****************************************************/
package trees;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;

public class KdTreeST<Value> {

	/**
	 * This is the number of items that are in the KdTree
	 */
	private int size;

	/**
	 * This is the root, top node of the binary tree
	 */
	private Node root;

	/**
	 * This is a node that is used to structures the binary tree
	 * 
	 * @author Carlos Martinez
	 */
	private class Node {

		/**
		 * This is the point that the node stores
		 */
		private Point2D point;

		/**
		 * this is the value that the node stores
		 */
		private Value value;

		/**
		 * this is the Rectangle that the point in the KdTree has
		 */
		private RectHV rec;

		/**
		 * this is the left child of the node in the KdTree, also referred as
		 * bottom child
		 */
		private Node left;

		/**
		 * this is the right child of the node in the KdTree, also referred as
		 * top child
		 */
		private Node right;

		/**
		 * This constructs a new node with two null children
		 * 
		 * @param point
		 *            the point that the node will store
		 * @param value
		 *            the Value that the node will store
		 * @param rectangle
		 *            the point's rectangle representing in the KdTree
		 */
		public Node(Point2D point, Value value, RectHV rectangle) {
			this.point = point;
			this.value = value;
			this.rec = rectangle;
			this.left = null;
			this.right = null;
		}
	}

	/**
	 * Construct an empty KD-tree of points
	 */
	public KdTreeST() {
		this.size = 0;
		this.root = null;
	}

	/**
	 * Is the KD-Tree empty?
	 * 
	 * @return returns true if the KD-Tree is empty returns false if the KD-Tree
	 *         is not empty
	 */
	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * This methods returns the number of points in the KD-Tree
	 * 
	 * @return The number of points in the KD-Tree
	 */
	public int size() {
		return this.size;
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

		this.root = put(root, p, val, 0, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY,
				Double.POSITIVE_INFINITY);
	}

	/**
	 * this is a helper node to put method that does most the work recursively
	 * 
	 * @param node
	 *            the node that we are at checking
	 * @param point
	 *            the point we are adding or looking for if already here
	 * @param value
	 *            the value that the point is associated with
	 * @param level
	 *            the level or height that the node is at in the KdTree
	 * @param xmin
	 * @param xmax
	 * @param ymin
	 * @param ymax
	 * @return a new node created or an existing node with an updated value
	 */
	private Node put(Node node, Point2D point, Value value, int level, Double xmin, Double xmax, Double ymin,
			Double ymax) {
		if (node == null) {

			size++;
			return new Node(point, value, new RectHV(xmin, ymin, xmax, ymax));
		}

		int compareValue = compareValue(point, node.point, level);

		if (compareValue < 0) {
			if (level % 2 == 0) {
				node.left = put(node.left, point, value, level + 1, xmin, node.point.x(), ymin, ymax);
			} else {
				node.left = put(node.left, point, value, level + 1, xmin, xmax, ymin, node.point.y());
			}
		} else if (compareValue > 0) {
			if (level % 2 == 0) {
				node.right = put(node.right, point, value, level + 1, node.point.x(), xmax, ymin, ymax);
			} else {
				node.right = put(node.right, point, value, level + 1, xmin, xmax, node.point.y(), ymax);
			}
		} else if (compareValue == 0) {
			node.value = value;
		}
		return node;
	}

	/**
	 * This method is a type of comparable that finds which point is bigger or
	 * smaller
	 * 
	 * @param point
	 *            the point being added
	 * @param otherPoint
	 *            the point of the node that we compare it against
	 * @param level
	 *            the height of the tree that we are at
	 * @return an integer that works like a comparable telling you which point
	 *         is bigger
	 */
	private int compareValue(Point2D point, Point2D otherPoint, int level) {
		if (level % 2 == 0) {
			// Compare x-coordinates
			int cmpResult = new Double(point.x()).compareTo(new Double(otherPoint.x()));

			if (cmpResult == 0) {
				return new Double(point.y()).compareTo(new Double(otherPoint.y()));
			} else {
				return cmpResult;
			}
		} else {
			// Compare y-coordinates
			int cmpResult = new Double(point.y()).compareTo(new Double(otherPoint.y()));

			if (cmpResult == 0) {
				return new Double(point.x()).compareTo(new Double(otherPoint.x()));
			} else {
				return cmpResult;
			}
		}
	}

	/**
	 * This method returns the value associated with the given point
	 * 
	 * @param p
	 *            the point that we are looking for
	 * @return null if the point is not in the KdTree, returns the value of the
	 *         point
	 */
	public Value get(Point2D p) {
		if (p == null) {
			throw new NullPointerException();
		}

		return get(root, p, 0);
	}

	/**
	 * This is a method is a helper method for the get method, it does most of
	 * the work for the get method
	 * 
	 * @param node
	 *            the node that we are at searching
	 * @param point
	 *            the point that we are searching for
	 * @param level
	 *            the level that we are at in the KdTree
	 * @return null if the point does not exist in the KdTree or the value of
	 *         the point
	 */
	private Value get(Node node, Point2D point, int level) {
		while (node != null) {
			int compareValue = compareValue(point, node.point, level++);
			if (compareValue < 0) {
				node = node.left;
			} else if (compareValue > 0) {
				node = node.right;
			} else {
				return node.value;
			}

		}
		return null;
	}

	/**
	 * This method tells you if the point exist in the KdTree or not
	 * 
	 * @param p
	 *            the point that we are searching for
	 * @return true if the point exist in the KdTree or false if the point does
	 *         not exist in the KdTree
	 */
	public boolean contains(Point2D p) {
		if (p == null) {
			throw new NullPointerException();
		}

		return (get(root, p, 0) != null);
	}

	/**
	 * This method returns an queue of points that represent the KdTree in level
	 * order
	 * 
	 * @return an queue of points that represent the KdTree in level order
	 */
	public Iterable<Point2D> points() {
		Queue<Point2D> points = new Queue<>();
		Queue<Node> queue = new Queue<>();
		queue.enqueue(root);

		while (!queue.isEmpty()) {
			Node node = queue.dequeue();
			if (node != null) {
				points.enqueue(node.point);

				queue.enqueue(node.left);
				queue.enqueue(node.right);
			}
		}

		return points;
	}

	/**
	 * This method returns all values in the given Rectangle
	 * 
	 * @param rect
	 *            that rectangle that we are using in the method to find points
	 *            inside the rectangle
	 * @return all points inside the rectangle
	 */
	public Iterable<Point2D> range(RectHV rect) {
		if (rect == null) {
			throw new NullPointerException();
		}

		Queue<Point2D> rangePoints = new Queue<>();
		rangeAddPoints(root, rect, rangePoints);
		return rangePoints;
	}

	/**
	 * This is a helper method to range method which adds points that are inside
	 * the rectangle
	 * 
	 * @param node
	 *            the node that we are using to acess the rectangle and point to
	 *            test if it is in the rectangle
	 * @param rect
	 *            the rectangle that we are searching
	 * @param rangePoints
	 *            the Queue that holds the points that are inside the queue
	 */
	private void rangeAddPoints(Node node, RectHV rect, Queue<Point2D> rangePoints) {
		if (node != null && rect.intersects(node.rec)) {
			if (rect.contains(node.point)) {
				rangePoints.enqueue(node.point);
			}
		}

		if (node.left != null && rect.intersects(node.left.rec)) {
			rangeAddPoints(node.left, rect, rangePoints);
		}
		if (node.right != null && rect.intersects(node.right.rec)) {
			rangeAddPoints(node.right, rect, rangePoints);
		}
	}

	/**
	 * This method finds the nearest Point to a given point in the KdTree
	 * 
	 * @param p
	 *            the given point
	 * @return thr closes point in the kdTree to the given point
	 */
	public Point2D nearest(Point2D p) {
		if (p == null) {
			throw new NullPointerException();
		}

		if (isEmpty()) {
			return null;
		}

		Point2D result = nearest(root, p, root.point);

		return result;
	}

	/**
	 * This is a helper method for the nearest method
	 * 
	 * @param node
	 *            the node that holds the info of the point we are checking
	 * @param point
	 *            the point that we are checking
	 * @param min
	 *            the point that we have found so far to be the nearest
	 * @return the nearest Point to the given point
	 */
	private Point2D nearest(Node node, Point2D point, Point2D min) {

		if (min.distanceSquaredTo(point) >= node.rec.distanceSquaredTo(point)) {
			if (node.point.distanceSquaredTo(point) < min.distanceSquaredTo(point)) {
				min = node.point;
			}
		}

		int selection = 0; // Zero equal right side
		if (node.left != null && node.right != null
				&& node.left.rec.distanceSquaredTo(point) < node.right.rec.distanceSquaredTo(point)) {
			selection = 1; // one equal left side
		}

		switch (selection) {
		case 0:
			if (node.right != null) {
				min = nearest(node.right, point, min);
			}
			if (node.left != null && min.distanceSquaredTo(point) > node.left.rec.distanceSquaredTo(point)) {
				min = nearest(node.left, point, min);
			}
			break;
		case 1:
			if (node.left != null) {
				min = nearest(node.left, point, min);
			}
			if (node.right != null && min.distanceSquaredTo(point) > node.right.rec.distanceSquaredTo(point)) {
				min = nearest(node.right, point, min);
			}
			break;
		}

		return min;
	}

	/**
	 * Unit testing of the methods (not graded)
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		KdTreeST<Integer> st = new KdTreeST<>();
		System.out.println("Is the PointST<Point2D> empty: " + st.isEmpty());

		st.put(new Point2D(56, 50), 10);
		st.put(new Point2D(10, 28), 40);
		st.put(new Point2D(70, 41), 90);
		st.put(new Point2D(20, 80), 160);
		st.put(new Point2D(30, 45), 250);

		System.out.println("Is the PointST<Point2D> empty: " + st.isEmpty());
		System.out.println("Number of Elements: " + st.size());
		System.out.println("Get the Value of point(20,80): " + st.get(new Point2D(30, 45)));
		System.out.println("Does st contain point(40,40): " + st.contains(new Point2D(70, 41)));
		System.out.println("Closes point to point(100,100): " + st.nearest(new Point2D(100, 100)));
		System.out.println();

		System.out.println("Iterable Points:");
		for (Point2D el : st.points()) {
			System.out.println(el);
		}
		System.out.println();

		System.out.println("Iterable Range:");
		for (Point2D el : st.range(new RectHV(0, 50, 100, 100))) {
			System.out.println(el);
		}
		System.out.println();

		KdTreeST<Integer> st1 = new KdTreeST<>();
		st1.put(new Point2D(83, 95), 1);
		st1.put(new Point2D(58, 29), 2);
		st1.put(new Point2D(38, 56), 3);
		st1.put(new Point2D(23, 32), 4);
		st1.put(new Point2D(98, 15), 5);

		System.out.println("Iterable Points:");
		for (Point2D el : st1.points()) {
			System.out.println(el);
		}
		System.out.println();

		KdTreeST<Integer> st2 = new KdTreeST<>();
		st2.put(new Point2D(30, 20), 1);
		st2.put(new Point2D(70, 20), 2);
		st2.put(new Point2D(30, 80), 3);
		st2.put(new Point2D(70, 80), 4);
		st2.put(new Point2D(55, 52), 5);

		System.out.println("Nearest point to point(50,50): " + st2.nearest(new Point2D(50, 50)));
		System.out.println();

		KdTreeST<Integer> st3 = new KdTreeST<>();
		st3.put(new Point2D(94, 29), 1);
		st3.put(new Point2D(75, 25), 2);
		st3.put(new Point2D(39, 92), 3);
		st3.put(new Point2D(75, 62), 4);
		st3.put(new Point2D(19, 4), 5);

		System.out.println("Nearest point to point(0,0): " + st3.nearest(new Point2D(0, 0)));
	}
}