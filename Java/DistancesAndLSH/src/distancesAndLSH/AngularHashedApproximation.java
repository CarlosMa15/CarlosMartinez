package distancesAndLSH;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.NoRouteToHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;

public class AngularHashedApproximation {
	
	/**
	 * This method finds out how many elements exist in a given array
	 * @param dotProducts The array to check
	 * @param element The element to look for
	 * @return The amount of times the element is in the array
	 */
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
	 * This method calculates the dot product of 2 vectors
	 * @param vect_A The first vector
	 * @param vect_B The second vector
	 * @return The dot product between vect_A and vect_B
	 */
	static double dotProduct(double vect_A[], double vect_B[]) 
    { 
      
        double product = 0; 
      
        // Loop for calculate cot product 
        for (int i = 0; i < vect_A.length; i++) 
            product = product + vect_A[i] * vect_B[i]; 
        return product; 
    } 
	
	/**
	 * This method prints the elements of an array
	 * @param vector The method who's elements we wish to print
	 */
	private static void printArray(double[] vector) {
		System.out.print("<");
		for (int i = 0; i < vector.length; i++) {
			if (i > 0) {
				System.out.print(", ");
			}
			System.out.print(vector[i]);
		}
		System.out.println(">");
	}
	
	/**
	 * This method finds the magnitude of a vector
	 * @param vector The vector to find the magnitude of
	 * @return The magnitude of the vector
	 */
	private static double magnitude(double[] vector) {
		double result = 0;
		
		for(int i = 0; i < vector.length; i ++) {
			double mult = vector[i] * vector[i];
			result += mult;
		}
		
		return Math.sqrt(result);
	}
	
	/**
	 * This method returns the normalization of the vector
	 * @param vector The vector to normalize
	 * @return The normalization of the vector
	 */
	private static double[] normalize(double[] vector) {
		double[] result = new double[vector.length];
		double magnitude = magnitude(vector);
		for(int i = 0; i < vector.length; i ++) {
			result[i] = vector[i] / magnitude;
		}
		
		return result;
	}

	public static void main(String[] args) {
		File file1 = new File("/Users/carlosmartinez/Desktop/Java/DistancesAndLSH/src/distancesAndLSH/R.txt");
		ArrayList<double[]> vectors = new ArrayList<double[]>();
		double[][] normalizeVectors = new double[500][];
		ArrayList<Double> dotProducts = new ArrayList<Double>();
		ArrayList<Double> normDotProducts = new ArrayList<Double>();
		double sumProbability[] = new double[308];
		int counter1 = 0;
		try {
			@SuppressWarnings("resource")
			BufferedReader bufferReader = new BufferedReader(new FileReader(file1));
			while(bufferReader.ready()) {
				counter1++;
				String data = bufferReader.readLine();
				String[] elements = data.split(" ");
				System.out.println(elements.length);
				double[] vector = new double[100];
				for(int i = 0; i < 100; i++) {
					vector[i] = Double.parseDouble(elements[i]);
				}
				vectors.add(vector);
			}
		} catch (FileNotFoundException e) {
			System.err.println("File Not Found Exception!");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("IOException!");
			e.printStackTrace();
		}
		
		for(int i = 0; i < 500; i++) {
			normalizeVectors[i] = normalize(vectors.get(i));
		}
		
		int j = 1;
		for (int i = 0; i < 500; i++) {
			for (int k = j; k < 500; k++) {
				dotProducts.add(dotProduct(vectors.get(i), vectors.get(k)));
				normDotProducts.add(dotProduct(normalizeVectors[i], normalizeVectors[k]));
			}
			j++;
		}
		
		int counter = 0;
		int[] angularSimularity = new int[normDotProducts.size()];
		for(int i = 0; i < normDotProducts.size(); i++) {
			double halfPI = 1 / Math.PI;
			double arccos = Math.acos(normDotProducts.get(i));
			double mult = halfPI * arccos;
			double answer = 1 - mult;
			if(answer > .85) counter++;
			angularSimularity[i] = (int) (answer * 1000);
		}
		
		Arrays.sort(angularSimularity);
		
		System.out.println("MIN: " + angularSimularity[0]);
		System.out.println("MIN: " + angularSimularity[angularSimularity.length - 1]);
		
		for (int i = 687; i <= 994; i++) {
			int amount = numberOfElements(angularSimularity, i);
			double currentProbability = ((double) amount) / angularSimularity.length;
			if (i == 687) {
				sumProbability[i - 687] = currentProbability;
			} else {
				sumProbability[i - 687] = currentProbability + sumProbability[i - 1 - 687];
			}
		}
		
		for (int i = 0; i < sumProbability.length; i++) {
			double number = i + 687;
			number = number / 1000;
			System.out.printf("(%.5f,%.5f)", number, sumProbability[i]);
		}
		System.out.println("\n" + counter1 );
		System.out.println("\n" + counter + " " + normDotProducts.size());
	}
}