package midterm2;
/****************************************************************************
 * @author  Carlos Humberto Martinez Guadarrama
 * 
 * CSIS-2420 Midterm2 Spring 2017
 ****************************************************************************/
import java.util.Random;

import edu.princeton.cs.algs4.BinarySearchST;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * @author  
 *
 * Compares the performance of Sedgewick's symbol table implementations
 * BinarySearchST and ST
 */
public class PerformanceTestSpring2017
{
	private static final Random rand = new Random();

	public static void main(String[] args) {
		StdOut.printf("%-12s  %-17s  %s%n", "number of", "time to create", "time to create");
		StdOut.printf("%-12s  %-17s  %s%n", "integers", "BinarySearchST", "ST");
		StdOut.println("---------------------------------------------------");

		// TODO 3 change the update of the control variable i in the for loop header.
		//      Rather than adding 100,000 it should double its value 
		for (int i = 10000; i < 500000; i *= 2) {
			Integer[] numbers = getRandomIntArray(i);
			
			Stopwatch stopwatch = new Stopwatch();
			createBinarySearchST(numbers);
			double timeToCreateBST = stopwatch.elapsedTime();
			
			stopwatch = new Stopwatch();
			createST(numbers);
			double timeToCreateST = stopwatch.elapsedTime();
			
			StdOut.printf("%-12d  %-17.3f  %.3f%n", i, timeToCreateBST, timeToCreateST);
		}
		
		// TODO 4: Use one or more println statements to describe the difference between
		//       BinarySearchST and ST
		
		System.out.println("Both are rerepresents an ordered symbol table,but the BinarySearchST uses\n"
				+ "a sorted array and the other (ST) a balanced binary tree, The BinarySearchST is not balnced\n"
				+ "so hieght of the tree can grow bigger effecting performance, while (ST) is more balnce,\n"
				+ "keeping the height of the tree to a minimal\n");
		
		// TODO 5: Use multiple println statements to explain the difference in performance.
		
		System.out.println("The BinarySearchST is unbalnce which means the hieght can grow much bigger/n"
				+ "than the ST");
		System.out.println("The ST is balnced binary tree that keeps the performance done due to the fact\n"
				+ "that the tree's hieght is kept to a minimal.");
		System.out.println("ST uses a TreeMap that is a type of Red-Black binary tree");
		System.out.println("ST time seams to about just double due to it being more balnce");
		System.out.println("BinarySearchST are less balance so time close to doubles at first,but");
		System.out.println("The time performance gets bigger as time goes on, from what I saw\n"
				+ "from about twice to six time at the end, different data differs a little");
		
	}

	/**
	 * Creates a symbol table of type BinarySearchST based on the keys 
	 * provided in the argument.
	 * Each key should be associated with its hash code. 
	 * In other words: If the key is 6 then the value it is associated with 
	 * should be the hash code of 6.
	 * 
	 * @param keys the keys of the symbol table 
	 */
	private static BinarySearchST<Integer, Integer> createBinarySearchST(Integer[] keys) {
		// TODO 1: change the method implementation so that it creates a
		//         symbol table as described in the doc comment above 
		
		BinarySearchST<Integer, Integer> st = new BinarySearchST<Integer,Integer>();
		
		for(Integer key: keys) {
			st.put(key, key.hashCode());
		}
		return st; 
	}

	/**
	 * Creates a symbol table of type ST based on the keys provided in the argument.
	 * Each key should be associated with its hash code. 
	 * In other words: If the key is 6 then the value it is associated with 
	 * should be the hash code of 6.	 * 
	 * @param keys the keys of the symbol table 
	 */
	private static ST<Integer, Integer> createST(Integer[] keys) {
		// TODO 2: change the method implementation so that it creates a
		//         symbol table as described in the doc comment above 
		
		ST<Integer,Integer> st = new ST<Integer,Integer>();
		for(Integer key: keys) {
			st.put(key, key.hashCode());
		}
		return st;
	}

	/**
	 * Creates an array of the specified size and fills it 
	 * with random integers from the range [0, size]
	 * @param size Size of the array
	 * @return array of random integers
	 */
	private static Integer[] getRandomIntArray(int size) {
		Integer[] array = new Integer[size];
		for (int i = 0; i < size; i++) {
			array[i] = rand.nextInt(size);
		}
		return array;
	}
}