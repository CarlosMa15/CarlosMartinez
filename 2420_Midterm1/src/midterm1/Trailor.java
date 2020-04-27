package midterm1;

import java.awt.Color;

/**
 * @author Margret Posch and ...........(Carlos Martinez)
 *
 * Class Trailor represents a trailer that is defined by its
 * length, weight, and color.
 */
public class Trailor implements Comparable<Trailor>{
	private final double length; // length in feet
	private final int weight;    // weight in pound
	private final Color color;
	
	/**
	 * Initializes a new Trailor object. 
	 * The length is measured in feet and the weight is measured in pounds.
	 * 
	 * @param length represents the length of the trailer in feet
	 * @param weight represents the weight of the trailer in pound
	 * @param color specifies the color of the trailer
	 */
	public Trailor(double length, int weight, Color color) {
		this.length = length;
		this.weight = weight;
		this.color = color;
	}
	
	/**
	 * @return the color of the trailer
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * @return the length of the trailer
	 */
	public double getLength() {
		return length;
	}

	/**
	 * @return the weight of the trailer
	 */
	public int getWeight() {
		return weight;
	}

	@Override
	public String toString() {
		return String.format("trailor: %4.1fft %dlb color:#%02X%02X%02X",
			length, weight, color.getRed(), color.getGreen(), color.getBlue());
	}
	
	/**
	 * Compares the Trailors be its length
	 */
	@Override
	public int compareTo(Trailor o) {
		//return Double.toString(length).compareTo(Double.toString(o.length));
		//return (int)(length - o.length);
		if(length < o.length){
			return -1;
		}
		if(length > o.length){
			return 1;
		}
		else return 0;
	}
}
