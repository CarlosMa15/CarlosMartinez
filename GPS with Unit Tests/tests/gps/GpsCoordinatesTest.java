package gps;

import static org.junit.Assert.*;

import org.junit.Test;

public class GpsCoordinatesTest {

	@Test
	public void testGpsCoordinates() {
		GpsCoordinates one = new GpsCoordinates(40.760671,-111.891122);
		assertEquals(40.760671, one.getLatitude(),0.0001);
		assertEquals(-111.891122, one.getLongitude(),0.0001);
	}

	@Test
	public void testGetLatitude() {
		GpsCoordinates two = new GpsCoordinates(34.284657,48.3748596);
		double expected = 34.284657;
		assertEquals(expected, two.getLatitude(),0.0001);
	}

	@Test
	public void testSetLatitude() {
		GpsCoordinates three = new GpsCoordinates(12.345678,91.234567);
	    double expected = 40.123456;
	    three.setLatitude(40.123456);
	    assertEquals(expected, three.getLatitude(),0.0001);
	}

	@Test
	public void testGetLongitude() {
		GpsCoordinates four = new GpsCoordinates(78.901234,45.678901);
		double expected = 45.678901;
		assertEquals(expected,four.getLongitude(), 0.001);
		
	}

	@Test
	public void testSetLongitude() {
		GpsCoordinates five = new GpsCoordinates(09.876543,98.7654321);
		double expected = 40.123456;
		five.setLongitude(40.123456);
		assertEquals(expected, five.getLongitude(),0.001);
	}

	@Test
	public void testToString() {
		GpsCoordinates six = new GpsCoordinates(40.760671,-111.891122);
		String expected = "40.760671, -111.891122";
	    String actual = six.toString();
	    assertEquals(expected, actual);
	}

}
