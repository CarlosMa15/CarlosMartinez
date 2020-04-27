package hashFunctionsAndPACAlgorithms;

import java.util.Arrays;
import java.util.Random;

public class CouponCollectors {

	public static int numberOfElements(int[] array, int element) {
		int amount = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] == element) {
				amount++;
			}
		}
		return amount;
	}

	/**
	 * This method was provided by a google search
	 * http://javadevnotes.com/check-if-java-array-contains-a-certain-value
	 * 
	 * @param arr  array to search
	 * @param item the item to search for
	 * @return true if it contains that item
	 */
	public static boolean contains(int[] arr, int item) {
		int index = Arrays.binarySearch(arr, item);
		return index >= 0;
	}

	public static void main(String[] args) {
		int n = 300; // 20000; domain
		int m = 400; // 5000; trials
		int possible = 10000;
		int min = 1;
		int[] elementCounter = new int[n];
		double[] sumProbability = new double[possible];
		int[] results = new int[m];
		Random rand = new Random();

		for (int i = 0; i < m; i++) {
			Arrays.fill(elementCounter, 0);
			int counter = 0;
			while (true) {
				int result = rand.nextInt(n - min + 1) + min;
				elementCounter[result - 1] += 1;
				boolean check = true;
				for (int j = 0; j < elementCounter.length; j++) {
					if (elementCounter[j] < 1) {
						check = false;
					}
				}
				counter++;
				if (check)
					break;
			}
			results[i] = counter;
		}

		double expected = 0;
		for (int i = 0; i < possible; i++) {
			double number = i;
			int amount = numberOfElements(results, i);
			double currentProbability = ((double) amount) / m;
			expected += (number * currentProbability);
		}

		for (int i = 0; i < possible; i++) {
			int amount = numberOfElements(results, i);
			double currentProbability = ((double) amount) / m;
			if (i == 0) {
				sumProbability[i] = currentProbability;
			} else {
				sumProbability[i] = currentProbability + sumProbability[i - 1];
			}
			System.out.printf("(%d,%.5f)", i + 1, sumProbability[i]);
		}

		System.out.println("\nExpected: " + expected);

	}
}