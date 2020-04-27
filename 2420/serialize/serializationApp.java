package serialize;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;

public class serializationApp {

	public static void main(String[] args) {
		String filePath = "src/serialize/files/Circles.ser";
		
		ColoredCircle[] circles = { 
				new ColoredCircle(4.5,"RED"),
				new ColoredCircle(2.5,"BLUE"),
				new ColoredCircle(5.5,"GREEN"),
				new ColoredCircle(3.5,"ORANGE") };
		
		System.out.println(Arrays.toString(circles));
		Arrays.sort(circles);
		System.out.println(Arrays.toString(circles));
		System.out.println();
		
		serializeColoredCircle(circles,filePath);
		System.out.println("Done Serializing");
		System.out.println();
		
		ColoredCircle[] circles2 = deserializeColoredCircle(filePath);
		System.out.println("Circles2:");
		System.out.println(Arrays.toString(circles2));
		
	}
	
	private static ColoredCircle[] deserializeColoredCircle(String file) {
		ColoredCircle[] circles = null;
		try(ObjectInputStream deserializer = new ObjectInputStream(new FileInputStream(file))) {
			
			int numberOfCircles = deserializer.readInt();
			System.out.println("Just for Demo: Number of Circles: "  + numberOfCircles);
			circles = (ColoredCircle[]) deserializer.readObject();
		
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return circles;
	}

	private static void serializeColoredCircle(ColoredCircle[] circleArray, String file) {
		try(ObjectOutputStream serializer = new ObjectOutputStream(new FileOutputStream(file))) {
			serializer.writeInt(circleArray.length);
			serializer.writeObject(circleArray);	
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}