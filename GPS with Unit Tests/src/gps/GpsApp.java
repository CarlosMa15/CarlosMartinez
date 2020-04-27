package gps;

public class GpsApp {

	public static void main(String[] args) {
		GpsCoordinates slc = new GpsCoordinates(40.760671,-111.891122);
		
		System.out.println("Gps Coordinates of SLC: " + slc);
		System.out.println();
		
		Gps myGps = new Gps("Garmin", slc);
		
		System.out.println("myGps: " + myGps);
		System.out.println();
		
		for(int i = 0; i <3; i++){
			myGps.updatePosition();
			System.out.println("Position updated" + (i+1) + ": " + myGps);
		}

	}

}
