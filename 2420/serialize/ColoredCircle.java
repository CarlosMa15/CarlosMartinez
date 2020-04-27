package serialize;

import java.io.Serializable;

public class ColoredCircle implements Comparable<ColoredCircle>,Serializable{
	
	private static final long serialVersionUID = 1441071111698445100L;
	private double diameter;
	private String color;
	
	public ColoredCircle(double radius, String color) {
		this.diameter = 2 * radius;
		this.color = color;
	}

	@Override
	public String toString() {
		return "C(" + diameter + ")..." + color;
	}

	@Override
	public int compareTo(ColoredCircle other) {
		return Double.compare(this.diameter, other.diameter);
	}
}