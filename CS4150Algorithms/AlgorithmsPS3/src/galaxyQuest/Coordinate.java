/*************************************************************
 * Author: Carlos Martinez
 * Date: September 8, 2018
 * Assignment: Galaxy Quest
 ************************************************************/
package galaxyQuest;

/**
 * This is a helper class for the Galaxy Quest Class
 * @author Carlos Martinez
 */
public class Coordinate {
	public long x;
	public long y;

	/**
	 * This creates a new coordinate object
	 * @param x This is the x part of the coordinates
	 * @param y This is the y part of the coordinates
	 */
	public Coordinate(long x, long y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * This is the toString method of the object, this is useless.
	 * This was only implemented for debugging purpose.
	 */
	@Override
	public String toString() {
		return "Coordinate [x=" + x + ", y=" + y + "]";
	}
}