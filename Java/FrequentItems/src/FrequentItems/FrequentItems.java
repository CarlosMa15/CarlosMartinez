package FrequentItems;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class FrequentItems {

	public static void main(String[] args) {
		FileReader inputStream = null;
		char[] labels = new char[9];
		int[] counters = new int[9];
		Arrays.fill(labels, '\0');
		Arrays.fill(counters, 0);

		try {
			inputStream = new FileReader("/Users/carlosmartinez/Desktop/Java/FrequentItems/src/FrequentItems/S2.txt");

			int asciiNumber;
			while ((asciiNumber = inputStream.read()) != -1) {
				if (asciiNumber > 96 && asciiNumber < 123) {
					char currentCharactor = (char) asciiNumber;
					
					// System.out.println(currentCharactor);

					// if label = currentCharactor then increment counter
					boolean stepChecker = true;
					for (int i = 0; i < 9; i++) {
						if (labels[i] == currentCharactor) {
							counters[i]++;
							stepChecker = false;
							break;
						}
					}

					// if no empty counter then add it in
					if (stepChecker) {
						for (int i = 0; i < 9; i++) {
							if (counters[i] == 0) {
								counters[i] = 1;
								labels[i] = currentCharactor;
								stepChecker = false;
								break;
							}
						}
					}

					// else decrement all counters
					if (stepChecker) {
						for (int i = 0; i < 9; i++) {
							counters[i]--;

//							if (counters[i] == 0) {
//								labels[i] = '#';
//							}
						}
					}

				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 0; i < 9; i++) {
			System.out.println("Charactor: " + labels[i] + " Counter: " + counters[i]);
			System.out.println();
		}

	}
}