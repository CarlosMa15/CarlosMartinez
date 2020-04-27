/*******************************************************
 * Author: Carlos Martinez
 * Date: February 11, 2017
 * Assignment 2:  Randomized Queues and Deques
 ******************************************************/
package randomizedQueue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import edu.princeton.cs.algs4.StdIn;

public class Subset {
	
	private static final String[] letter = {"A","B","C","D","E","F","G"};

	public static void main(String[] args) {
		
		// Reading from a text File implementation of RandomQueue
		System.out.print("Queue: ");
		
		try (Scanner input = new Scanner(new File("Subset.txt"))) {
			RandomizedQueue<String> num = new RandomizedQueue();
			String line;
			int checker = 0;
			int numberOfSamples = 0;
			
			while (input.hasNext()) {
				line = input.next();
				if(checker == 0) {
					numberOfSamples = Integer.parseInt(line);
				}
				if(checker != 0) {
				num.enqueue(line);
				System.out.print(line + " ");
				}
				checker++;
			}
			
			System.out.println();
			for ( int i = 0; i < numberOfSamples; i++) {
				System.out.println("Sample: " + num.sample());
			}
			System.out.println();
		} catch (FileNotFoundException e) {
			System.out.println("File " + "Subset.txt" + " could not be found");
		}
		
		//Array implementation of RandomQueue
		RandomizedQueue<String> letterQueue = new RandomizedQueue<>();
		for(int i = 0; i < letter.length; i++){
			letterQueue.enqueue(letter[i]);
		}
		
		System.out.println("Queue: " + Arrays.toString(letter) + " Java Subset 4");
		
		System.out.println(letterQueue.sample());
		System.out.println(letterQueue.sample());
		System.out.println(letterQueue.sample());
		
		System.out.println("Queue: " + Arrays.toString(letter) + " Java Subset 3");
		
		System.out.println(letterQueue.sample());
		System.out.println(letterQueue.sample());
		System.out.println(letterQueue.sample());
		
	}
}