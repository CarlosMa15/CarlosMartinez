package finalCode;

/**
 * Represents a point on the cartesian coordinate system.
 * 
 * @author MargretP
 *
 */
public class Point implements Comparable<Point> {
	private int x;
	private int y;
	
	/**
	 * Initializes the fields x and y.
	 * @param x
	 * @param y
	 */
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Calculates the distance between this point and the other point.
	 * 
	 * @param other  the other point
	 * @return the   distance between this point and the other point
	 */
	public double distanceTo(Point other) {
		return Math.hypot(x - other.x, y - other.y);
	}

	/**
	 * Creates a string representation of this Point object.
	 * 
	 * @return a string representation of the following form: (x,y).
	 */
	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}

	/**
	 * Compares this point to the other point.
	 * 
	 * @param   other the other point to compare with
	 * @return  a positive number if this point is greater, a negative number 
	 * 	        if this point is smaller, and 0 if this point is equal to the other point
	 */
	@Override
	public int compareTo(Point other) {
		if (x == other.x) 
			return y - other.y;
		
		return x - other.x;
	}

}
