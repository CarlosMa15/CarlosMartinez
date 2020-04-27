package clustering;

public class PointPair implements Comparable<PointPair>{

	private double x1;
	private double y1;
	private double x2;
	private double y2;
	private int ID1;
	private int ID2;
	private double distance;
	
	public PointPair(int ID1,int ID2,double x1, double y1, double x2, double y2) {
		this.ID1 = ID1;
		this.ID2 = ID2;
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.distance = Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
	}

	public double getX1() {
		return x1;
	}

	public void setX1(double x1) {
		this.x1 = x1;
	}

	public double getY1() {
		return y1;
	}

	public void setY1(double y1) {
		this.y1 = y1;
	}

	public double getX2() {
		return x2;
	}

	public void setX2(double x2) {
		this.x2 = x2;
	}

	public double getY2() {
		return y2;
	}

	public void setY2(double y2) {
		this.y2 = y2;
	}

	public int getLeftID() {
		return ID1;
	}

	public void setLeftID(int iD1) {
		this.ID1 = iD1;
	}
	
	public int getRightID() {
		return ID2;
	}

	public void setRightID(int iD2) {
		this.ID2 = iD2;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	@Override
	public int compareTo(PointPair other) {
		if (this.distance == other.distance) return 0;
		return this.distance > other.distance ? 1 : -1;
	}
}