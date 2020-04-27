package FrequentItems;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CountMinSketch {

	private static String[] hashFunctions = { "SHA-384", "SHA-512", "SHA-1", "SHA-224"};
	
	static int[][] CountMinSketch = new int[5][10];
	
	static int[] a = {4,6,4,9,7};
	
	static int[] b = {0,7,8,9,8};
	
	static int[] c = {7,1,3,8,9};
	
	/**
	 * This method returns the hash code depending to the hashFunction
	 * @param hashFunction the hash to which to use
	 * @param input the object to get the hash code
	 * @return the hash code depending to the hashFunction
	 */
	public static int getHashCode(String hashFunction, String input) {

		try {

			MessageDigest md = MessageDigest.getInstance(hashFunction);

			byte[] messageDigest = md.digest(input.getBytes());

			BigInteger no = new BigInteger(1, messageDigest);

			String hashtext = no.toString(16);

			// Add preceding 0s to make it 32 bit
			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}

			input = hashtext;

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Math.abs(input.hashCode() % 10);

	}

	public static void main(String[] args) {
		
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 10; j++) {
				CountMinSketch[i][j] = 0;
			}
		}

		FileReader inputStream = null;

		try {
			inputStream = new FileReader("/Users/carlosmartinez/Desktop/Java/FrequentItems/src/FrequentItems/S2.txt");

			int asciiNumber;
			while ((asciiNumber = inputStream.read()) != -1) {
				if (asciiNumber != 10) { // 10 is the ascii for \n or new line
					char currentCharactor = (char) asciiNumber;					
					for(int i = 0; i < 4; i++) {
						CountMinSketch[i][getHashCode(hashFunctions[i], Character.toString(currentCharactor))]++;
//						if (currentCharactor == 'a') System.out.println("A " + i + " " + getHashCode(hashFunctions[i], Character.toString(currentCharactor)));
//						if (currentCharactor == 'b') System.out.println("B " + i + " " + getHashCode(hashFunctions[i], Character.toString(currentCharactor)));
//						if (currentCharactor == 'c') System.out.println("C " + i + " " + getHashCode(hashFunctions[i], Character.toString(currentCharactor)));
					}
					CountMinSketch[4][Character.toString(currentCharactor).hashCode() % 10]++;
//					if (currentCharactor == 'a') System.out.println("A " + 4 + " " + Character.toString(currentCharactor).hashCode() % 10);
//					if (currentCharactor == 'b') System.out.println("B " + 4 + " " + Character.toString(currentCharactor).hashCode() % 10);
//					if (currentCharactor == 'c') System.out.println("C " + 4 + " " + Character.toString(currentCharactor).hashCode() % 10);
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		for(int i = 0; i < 5; i++) {
//			for(int j = 0; j < 10; j++) {
//				System.out.printf("CountMinSketch(%d)(%d) = %d\n\n",i,j,CountMinSketch[i][j]);
//			}
//		}
		
		double aC = Double.POSITIVE_INFINITY;
		double bC = Double.POSITIVE_INFINITY;
		double cC = Double.POSITIVE_INFINITY;
		for(int i = 0; i < 5; i++) {
			aC = Math.min(CountMinSketch[i][a[i]],aC);
			bC = Math.min(CountMinSketch[i][b[i]],bC);
			cC = Math.min(CountMinSketch[i][c[i]],cC);
		}
		
		System.out.println(aC);
		System.out.println(bC);
		System.out.println(cC);
	}
}