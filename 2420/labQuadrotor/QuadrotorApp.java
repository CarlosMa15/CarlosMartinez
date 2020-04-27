package labQuadrotor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuadrotorApp {

	public static void main(String[] args) {
		List<Quadrotor> rotors = new ArrayList<>(Arrays.asList( new Quadrotor(2, 4, 2),
				new Quadrotor(3, 4, 4), new Quadrotor(4, 4, 6), new Quadrotor(5, 4, 2),
				new Quadrotor(6, 4, 4), new Quadrotor(7, 4, 6)));
		
		System.out.println(rotors);
		changeOrientation(rotors);
		System.out.println(rotors);
		
		Quadrotor searchItem = new Quadrotor(4,6,4);
		
		if (rotors.contains(searchItem)) {
			System.out.println("rotors does contain " + searchItem);
		} else {
			System.out.println("rotor does not contain " + searchItem);
		}
		
		System.out.println("Number of rotors: " + rotors.size());
		
		rotors.remove(searchItem);
		System.out.println(rotors);
		
		rotors.remove(0);
		System.out.println(rotors);
		
	}
	
	/**
	 * this method switches the values in the x an y position of the Quadrotor
	 * @param list
	 */
	private static void changeOrientation(List<Quadrotor> list) {
			for (int i = 0; i < list.size(); i++) {
				int x = list.get(i).getX();
				
				list.get(i).setX(list.get(i).getY());
				list.get(i).setY(x);
			}
	}
}