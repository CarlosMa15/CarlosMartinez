package gps;

import java.util.Random;

public class Gps {
	//fields
	/**
	 * This is the name of the user using the GPS
	 */
	private String name;
	
	/**
	 * This is the position of where the person is located
	 */
	private GpsCoordinates position;
	
	//Constructors
	/**
	 * This is a constructor that creates an object of GPS
	 * @param name This is the name of the user
	 * @param position This is the position of the user
	 */
	public Gps(String name, GpsCoordinates position) {
		this.name = name;
		this.position = position;
	}
	
	//Methods
	/**
	 * This method add a number between -2.5 and 2.5 to the latitude
	 * and the longitude of the position. The random number that is 
	 * added to the longitude is a different number than the number added
	 * to the latitude. It is possible that the two random numbers generate
	 * could be the same. 
	 */
	public void updatePosition(){
		Random rand = new Random();
		
		double ladChange = rand.nextDouble() * 5 - 2.5;
		double longChange = rand.nextDouble() * 5 - 2.5;
		
		position.setLatitude(position.getLatitude() + ladChange);
		position.setLongitude(position.getLongitude() + longChange);		
	}
	
	public GpsCoordinates getPosition() {
		return position;
	}

	/**
	 * This is a toString method that prints the GPS in the form
	 * of (name: position). 
	 */
	@Override
	public String toString() {
		return this.name + ": " + getPosition();
	}
	
}