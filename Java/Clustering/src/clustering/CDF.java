package clustering;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class CDF {
	
	
	public static int numberOfElements(double[] array, double element) {
		int amount = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] == element) {
				amount++;
			}
		}
		return amount;
	}

	public static void main(String[] args) {
		File file = new File("/Users/carlosmartinez/Desktop/Java/Clustering/src/clustering/20Trials.txt");
		double[] trails = new double[20];
		
		try {
			@SuppressWarnings("resource")
			BufferedReader bufferReader = new BufferedReader(new FileReader(file));

			int count =0;
			while (bufferReader.ready()) {
				String data = bufferReader.readLine();
				trails[count++] = Math.sqrt(Double.parseDouble(data));
			}

		} catch (FileNotFoundException e) {
			System.err.println("File Not Found Exception!");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("IOException!");
			e.printStackTrace();
		}
		
		Arrays.sort(trails);
		double[] sumProbability = new double[20];
		for (int i = 0; i < 20; i++) {
			int amount = numberOfElements(trails, trails[i]);
			double currentProbability = ((double) amount) / 20;
			if (i == 0) {
				sumProbability[i] = currentProbability;
			} else {
				sumProbability[i] = currentProbability + sumProbability[i - 1];
			}
			System.out.printf("%.5f %.5f\n", trails[i], sumProbability[i]);
		}
		
	}

}
