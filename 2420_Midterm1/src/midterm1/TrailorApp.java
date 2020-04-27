package midterm1;

import java.awt.Color;
import java.util.Arrays;
import java.util.Iterator;

import edu.princeton.cs.algs4.Bag;

import edu.princeton.cs.algs4.StdOut;

/**
 * @author Margret Posch and ...........(Carlos Martinez)
 *
 * Class TrailorApp demonstrates the use Java concepts
 * we have covered in CSIS-2420.
 */
public class TrailorApp {
	public static void main(String[] agrs) {
		Trailor[] trailors = {
				new Trailor(12.5, 250, Color.BLACK),
				new Trailor(15.4, 300, Color.GRAY),
				new Trailor(6.5, 215, Color.GRAY),
				new Trailor(14.8, 450, Color.BLACK),
				new Trailor(8.2, 175, Color.RED),
		};
		
		// === Part 1 === 		
		StdOut.println("Trailors: ");
		for(Trailor t : trailors) {
			StdOut.println(t);
		}
		
		
		 Arrays.sort(trailors);
		
		 StdOut.println();
		 StdOut.println("Trailors in aceneding order:");
		 for(Trailor t : trailors) {
			 StdOut.println(t);
		 }
		 
		 Bag<Trailor> bag1 = new Bag<>();
		 
		 for(Trailor el: trailors){
			 bag1.add(el);
			 if(el.getColor() == Color.GRAY){
				 bag1.add(el);
			 }
		 }
		 
		 StdOut.println();
		 StdOut.println("Trailors2:");
		 
		 Iterator<Trailor> it = bag1.iterator();
			
		while(it.hasNext()){
			System.out.println(it.next() + " ");
		}
		
	}
}
