package gps;

public class GpsCoordinates {
	
	//fields
	/**
	 * This is the latitude of the GPA Coordinate
	 */
	private double latitude;
	
	/**
	 * This is the longitude of the GPA Coordinate
	 */
	private double longitude;
	
	//Constructors
	/**
	 * This is a constructor that creates an object of GPA
	 * Coordinate
	 * @param latitude The latitude direction of the position
	 * @param longitude The longitude direction of the position
	 */
	public GpsCoordinates(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	//Methods
	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	/**
	 * This is the toString method the prints the GPA Coordinate in the
	 * form of latitude, longitude. The latitude and longitude only have
	 * six decimal places after the decimal place.
	 */
	@Override
	public String toString() {
		return String.format("%.6f, %.6f", getLatitude(),getLongitude());
	}
}
