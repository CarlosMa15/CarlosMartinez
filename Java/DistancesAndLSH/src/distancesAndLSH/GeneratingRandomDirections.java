package distancesAndLSH;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class GeneratingRandomDirections {
	
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

	public static void main(String[] args) {
		Random rand = new Random();
		int d = 10;
		double vector[] = new double[d];

		for (int i = 1; i <= (d / 2); i++) {
			double u1 = rand.nextDouble();
			double ln = Math.log(u1);
			double root = -2 * ln;
			double squareRoot = Math.sqrt(root);
			double u2 = rand.nextDouble();
			double in = 2 * Math.PI * u2;
			double cos = Math.cos(in);
			double sin = Math.sin(in);
			double y1 = squareRoot * cos;
			double y2 = squareRoot * sin;
			vector[(i * 2) - 2] = y1;
			vector[(i * 2) - 1] = y2;
		}

		printArray(vector);
		System.out.println();

		d = 100;
		ArrayList<double[]> vectors = new ArrayList<double[]>();
		int domain = 100;
		double sumProbability[] = new double[100];

		for (int j = 0; j < 160; j++) {
			
			vector = new double[d];

			for (int i = 1; i <= (d / 2); i++) {
				double u1 = rand.nextDouble();
				double ln = Math.log(u1);
				double root = -2 * ln;
				double squareRoot = Math.sqrt(root);
				double u2 = rand.nextDouble();
				double in = 2 * Math.PI * u2;
				double cos = Math.cos(in);
				double sin = Math.sin(in);
				double y1 = squareRoot * cos;
				double y2 = squareRoot * sin;
				vector[(i * 2) - 2] = y1;
				vector[(i * 2) - 1] = y2;
			}
			
			vectors.add(vector);
			
		}
		
		ArrayList<Double> dotProducts = new ArrayList<Double>();
		ArrayList<Double> normDotProducts = new ArrayList<Double>();
		
		int j = 1;
		for (int i = 0; i < 100; i++) {
			for (int k = j; k < 100; k++) {
				dotProducts.add(dotProduct(vectors.get(i), vectors.get(k)));
				normDotProducts.add(dotProduct(normalize(vectors.get(i)), normalize(vectors.get(k))));
			}
			j++;
		}
		
		Collections.sort(dotProducts);
		
		int results[] = new int[dotProducts.size()];
		for(int i = 0; i < dotProducts.size(); i++) {
			double x = dotProducts.get(i);
			int y = (int)x;
			results[i] = y;
		}
		
		for (int i = -50; i < domain / 2; i++) {
			int amount = numberOfElements(results, i);
			double currentProbability = ((double) amount) / 4950;
			if (i == - 50) {
				sumProbability[i + 50] = currentProbability;
			} else {
				sumProbability[i + 50] = currentProbability + sumProbability[i - 1 + 50];
			}
		}
		
//		for (int i = 0; i < sumProbability.length; i++) {
//			System.out.printf("(%d,%.5f)\n", i - 50, sumProbability[i]);
//		}
		
		int[] angularSimularity = new int[normDotProducts.size()];
		for(int i = 0; i < dotProducts.size(); i++) {
			double halfPI = 1 / Math.PI;
			double arccos = Math.acos(normDotProducts.get(i));
			double mult = halfPI * arccos;
			double answer = 1 - mult;
			if(answer > .85) System.out.println(answer);
			angularSimularity[i] = (int) (answer * 1000);
		}
		
		Arrays.sort(angularSimularity);
		
		sumProbability = new double[angularSimularity[angularSimularity.length - 1] - angularSimularity[0] + 1];
		for (int i = angularSimularity[0]; i <= angularSimularity[angularSimularity.length - 1]; i++) {
			int amount = numberOfElements(angularSimularity, i);
			double currentProbability = ((double) amount) / angularSimularity.length;
			if (i == angularSimularity[0]) {
				sumProbability[i - angularSimularity[0]] = currentProbability;
			} else {
				sumProbability[i - angularSimularity[0]] = currentProbability + sumProbability[i - 1 - angularSimularity[0]];
			}
		}
		
		for (int i = 0; i < sumProbability.length; i++) {
			double number = i + angularSimularity[0];
			number = number / 1000;
			System.out.printf("(%.5f,%.5f)\n", number, sumProbability[i]);
		}
	}
}