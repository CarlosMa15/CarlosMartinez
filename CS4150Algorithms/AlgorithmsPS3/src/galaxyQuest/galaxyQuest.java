/*************************************************************
 * Author: Carlos Martinez
 * Date: September 8, 2018
 * Assignment: Galaxy Quest
 ************************************************************/
package galaxyQuest;

import java.util.Scanner;

public class galaxyQuest {

	public static void main(String[] args) {
		// Objects
		Scanner input = new Scanner(System.in);
		String line = input.nextLine();
		String[] dimensions = line.split(" ");
		long d = Integer.parseInt(dimensions[0]);
		int k = Integer.parseInt(dimensions[1]);
		Coordinate[] arr = new Coordinate[k];

		// Gets all the coordinates that exist
		for (int i = 0; i < k; i++) {
			String coor = input.nextLine();
			String[] numbers = coor.split(" ");
			long left = Long.parseLong(numbers[0]);
			long right = Long.parseLong(numbers[1]);
			arr[i] = new Coordinate(left, right);
		}

		// Finds a coordinate with at least half of all stars being close to it by d.
		Coordinate majority = findMajority(arr, d);
		
		// If null no star galaxy meeting requirements exists
		if (majority == null) {
			System.out.print("NO");
			return;
		}

		// finds the number of stars that are close enough by d
		long count = 0;
		for (int h = 0; h < k; h++) {
			long diff = (long) (Math.pow(arr[h].x - majority.x, 2) + Math.pow(arr[h].y - majority.y, 2));
			if (diff < Math.pow(d, 2)) {
				count++;
			}
		}

		System.out.print(count);
	}

	/**
	 * This returns the number of stars close to it by d
	 * @param arr
	 * @param target
	 * @param d
	 * @return
	 */
	public static long countOccurrences(Coordinate[] arr, Coordinate target, long d) {
		int count = 0;
		for (int i = 0; i < arr.length; i++) {
			// (x1-x2)2 + (y1-y2)2 ≤ d2
			long diff = (long) (Math.pow((arr[i].x - target.x), 2) + Math.pow((arr[i].y - target.y), 2));
			;

			if (diff < (Math.pow(d, 2))) {
				count++;
			}
		}
		return count;

	}

	/**
	 * This find the coordinate with more than half of the stars, the star that with most stars near it
	 * forming a galaxy. binary search of all stars to find a star more than half in the galaxy.
	 * @param arr The array with all the coordinates
	 * @param d The distance diameter of the galaxy
	 * @return the start with more than half the stars close to it.
	 */
	public static Coordinate findMajority(Coordinate[] arr, long d) {
		if (arr.length == 1) { //Base case of the recursive
			return arr[0];
		}
		else {
			int mid = arr.length / 2;
			Coordinate[] a1 = new Coordinate[mid];
			Coordinate[] a2 = new Coordinate[arr.length - mid];
			System.arraycopy(arr, 0, a1, 0, mid);
			System.arraycopy(arr, mid, a2, 0, a2.length);
			
			//If null, no star with more than half the stars in universe exist in galaxy
			Coordinate x = findMajority(a1, d);
			Coordinate y = findMajority(a2, d);

			if (x == null && y == null) {
				return null;
			} else if (x == null) {
				long count = countOccurrences(arr, y, d);
				if (count > arr.length / 2) {
					return y;
				}
				return null;
			} else if (y == null) {
				long count = countOccurrences(arr, x, d);
				if (count > arr.length / 2) {
					return x;
				}
				return null;
			} else {
				long countX = countOccurrences(arr, x, d);
				long countY = countOccurrences(arr, y, d);
				if (countX > arr.length / 2) {
					return x;
				} else if (countY > arr.length / 2) {
					return y;
				} else {
					return null;
				}
			}
		}
	}
}