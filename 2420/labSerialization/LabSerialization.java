package labSerialization;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class LabSerialization {
    public static void main(String[] args) {
        ListVsSetDemo demo = new ListVsSetDemo(
                new ColoredSquare(4, Color.RED),
                new ColoredSquare(6, Color.BLUE),
                new ColoredSquare(4, Color.RED),
                new ColoredSquare(8, Color.YELLOW));

        //testDemo(demo);
        
        String filename = "src/labSerialization/Demo.ser";
        serialize(demo,filename);
        System.out.println("Done Serializing");
        System.out.println();
        
        ListVsSetDemo newDemo = deserialize(filename);
        testDemo(newDemo);

    };

    private static void testDemo(ListVsSetDemo demo) {
        System.out.println("List:");
        System.out.println(demo.getListElements());
        System.out.println("Set:");
        System.out.println(demo.getSetElements());
    }
    
    private static void serialize(ListVsSetDemo demo, String filename) {
    	try(ObjectOutputStream serializer = new ObjectOutputStream(new FileOutputStream(filename))) {
    		serializer.writeObject(demo);
    	} catch (IOException e) {
			System.err.println(e);
			e.printStackTrace();
		}
    }
    
    private static ListVsSetDemo deserialize(String filename) {
    	ListVsSetDemo demo = null;
    	try(ObjectInputStream deserializer = new ObjectInputStream(new FileInputStream(filename))) {
    		demo = (ListVsSetDemo) deserializer.readObject();
    	} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
    	return demo;
    }
}