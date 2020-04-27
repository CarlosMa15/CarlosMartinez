package hashFunctionsAndPACAlgorithms;

import java.util.Arrays;
import java.util.Random;

public class BirthdayParadox {

	public static int numberOfElements(int[] array, int element) {
		int amount = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] == element) {
				amount++;
			}
		}
		return amount;
	}

	public static void main(String[] args) {
		int n = 5000; // 1000000;
		int m = 300; // 10000;
		int[] elementCounter = new int[n + 1];
		int[] results = new int[m];
		double[] sumProbability = new double[m];
		int trialCounter = 0;
		int min = 1;
		Random rand = new Random();

		Arrays.fill(results, 0);
		for (int i = 0; i < m; i++) {
			Arrays.fill(elementCounter, 0);
			trialCounter = 0;
			while (true) {
				int result = rand.nextInt(n - min + 1) + min;
				elementCounter[result]++;
				trialCounter++;
				if (elementCounter[result] > 1)
					break;
			}
			results[i] = trialCounter;
		}

		Arrays.sort(results);

		for (int i = 0; i < m; i++) {
			int amount = numberOfElements(results, i);
			double currentProbability = ((double) amount) / m;
			if (i == 0) {
				sumProbability[i] = currentProbability;
			} else {
				sumProbability[i] = currentProbability + sumProbability[i - 1];
			}
		}
		
		double expected = 0;
		for(int i = 0; i < m; i++) {
			double number = i;
			int amount = numberOfElements(results, i);
			double currentProbability = ((double) amount) / m;
			expected += (number * currentProbability);
		}

		for (int i = 0; i < results.length; i++) {
			System.out.printf("(%d,%.5f)\n", i + 1, sumProbability[i]);
		}

		System.out.println("\nAverage: " + expected);
	}
}