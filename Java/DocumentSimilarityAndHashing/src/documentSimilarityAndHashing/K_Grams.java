package documentSimilarityAndHashing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class K_Grams {

	static Set<String> set1_2 = new HashSet<String>();
	static Set<String> set1_3 = new HashSet<String>();
	static Set<String> setWord1_2 = new HashSet<String>();
	static Set<String> set2_2 = new HashSet<String>();
	static Set<String> set2_3 = new HashSet<String>();
	static Set<String> setWord2_2 = new HashSet<String>();
	static Set<String> set3_2 = new HashSet<String>();
	static Set<String> set3_3 = new HashSet<String>();
	static Set<String> setWord3_2 = new HashSet<String>();
	static Set<String> set4_2 = new HashSet<String>();
	static Set<String> set4_3 = new HashSet<String>();
	static Set<String> setWord4_2 = new HashSet<String>();

	/**
	 * Method checks if the array contains element
	 * 
	 * @param array Array to check
	 * @param key   the key we are looking for
	 * @return true if contains false otherwise
	 */
	public static boolean Arraycontains(final int[] array, final int key) {
		for (final int i : array) {
	        if (i == key) {
	            return true;
	        }
	    }
	    return false;
	}

	/**
	 * This create a list of list permutations of the list
	 * 
	 * @param numbers The list to permute
	 * @param amount  how many permutes to create
	 * @return A list of list of permutations
	 */
	public static List<List<Integer>> permute(int[] numbers, int amount) {
		List<List<Integer>> permutations = new ArrayList<List<Integer>>();
		permutations.add(new ArrayList<Integer>());
		for (int i = 0; i < numbers.length; i++) {
			List<List<Integer>> current = new ArrayList<List<Integer>>();
			int counter = 0;
			for (List<Integer> permutation : permutations) {
				for (int j = 0, n = permutation.size() + 1; j < n; j++) {
					if (counter++ >= amount)
						break;
					List<Integer> temp = new ArrayList<Integer>(permutation);
					temp.add(j, numbers[i]);
					current.add(temp);
				}
			}
			permutations = new ArrayList<List<Integer>>(current);
		}

		return permutations;
	}

	/**
	 * This method prints the elements of the set
	 * 
	 * @param set the set we wish to print
	 */
	public static void printSet(Set<String> set) {
		Iterator<String> it = set.iterator();
		while (it.hasNext()) {
			String el = it.next();
			System.out.println("Element: \"" + el + "\"");
		}
	}

	/**
	 * This creates the intersection of the set
	 * 
	 * @param set1 Set 1
	 * @param set2 Set 2
	 * @return returns the intersection of 2 sets
	 */
	public static Set<String> SetIntersection(Set<String> set1, Set<String> set2) {
		Set<String> set = new HashSet<String>();
		Iterator<String> it = set1.iterator();
		while (it.hasNext()) {
			String element = it.next();
			if (set2.contains(element)) {
				set.add(element);
			}
		}
		return set;
	}

	/**
	 * Return a set of the elements in set2 that are not present to set1
	 * 
	 * @param set1 The union set
	 * @param set2 The set to check
	 * @return a set to return with elements elements not present
	 */
	public static Set<String> SetNoN(Set<String> set1, Set<String> set2) {
		Set<String> set = new HashSet<String>();
		Iterator<String> it = set1.iterator();
		while (it.hasNext()) {
			String element = it.next();
			if (!set2.contains(element)) {
				set.add(element);
			}
		}
		return set;
	}

	/**
	 * This creates the union of the set
	 * 
	 * @param set1 Set 1
	 * @param set2 Set 2
	 * @return returns the union of the 2 sets
	 */
	public static Set<String> SetUnion(Set<String> set1, Set<String> set2) {
		Set<String> set = new HashSet<String>();
		Iterator<String> it = set1.iterator();
		while (it.hasNext()) {
			set.add(it.next());
		}
		it = set2.iterator();
		while (it.hasNext()) {
			set.add(it.next());
		}
		return set;
	}

	/**
	 * This creates the char k-gram set
	 * 
	 * @param k    The length of the char element
	 * @param data The information to break down
	 * @return The set containing the elements
	 */
	public static Set<String> charSet(int k, String data) {
		Set<String> set = new HashSet<String>();
		int i = 0;
		while (i + k <= data.length()) {
			String section = data.substring(i, i + k);
			set.add(section);
			i++;
		}
		return set;
	}

	/**
	 * This creates the work k_gram of two words
	 * 
	 * @param data The information to break down
	 * @return The set containing the elements
	 */
	public static Set<String> wordSet(String data) {
		Set<String> set = new HashSet<String>();
		String[] words = data.split(" ");
		for (int i = 0; i + 1 < words.length; i++) {
			String k_gram = words[i] + words[i + 1];
			set.add(k_gram);
		}
		return set;
	}

	public static void main(String[] args) {
		File file1 = new File("/Users/carlosmartinez/Desktop/Java/DocumentSimilarity"
				+ "AndHashing/src/documentSimilarityAndHashing/D1.txt");
		File file2 = new File("/Users/carlosmartinez/Desktop/Java/DocumentSimilarity"
				+ "AndHashing/src/documentSimilarityAndHashing/D2.txt");
		File file3 = new File("/Users/carlosmartinez/Desktop/Java/DocumentSimilarity"
				+ "AndHashing/src/documentSimilarityAndHashing/D3.txt");
		File file4 = new File("/Users/carlosmartinez/Desktop/Java/DocumentSimilarity"
				+ "AndHashing/src/documentSimilarityAndHashing/D4.txt");

		BufferedReader bufferReader;

		try {
			bufferReader = new BufferedReader(new FileReader(file1));
			String data = bufferReader.readLine();

			set1_2 = charSet(2, data);
			set1_3 = charSet(3, data);
			setWord1_2 = wordSet(data);

			bufferReader = new BufferedReader(new FileReader(file2));
			data = bufferReader.readLine();

			set2_2 = charSet(2, data);
			set2_3 = charSet(3, data);
			setWord2_2 = wordSet(data);

			bufferReader = new BufferedReader(new FileReader(file3));
			data = bufferReader.readLine();

			set3_2 = charSet(2, data);
			set3_3 = charSet(3, data);
			setWord3_2 = wordSet(data);

			bufferReader = new BufferedReader(new FileReader(file4));
			data = bufferReader.readLine();

			set4_2 = charSet(2, data);
			set4_3 = charSet(3, data);
			setWord4_2 = wordSet(data);

			System.out.println("****** Creating k-Grams ******");
			System.out.println("******     Part 1       ******");
			System.out.println("Set1_2:     " + set1_2.size());
			System.out.println("Set1_3:     " + set1_3.size());
			System.out.println("SetWord1_2: " + setWord1_2.size());
			System.out.println("Set2_2:     " + set2_2.size());
			System.out.println("Set2_3:     " + set2_3.size());
			System.out.println("SetWord2_2: " + setWord2_2.size());
			System.out.println("Set3_2:     " + set3_2.size());
			System.out.println("Set3_3:     " + set3_3.size());
			System.out.println("SetWord3_2: " + setWord3_2.size());
			System.out.println("Set4_2:     " + set4_2.size());
			System.out.println("Set4_3:     " + set4_3.size());
			System.out.println("SetWord4_2: " + setWord4_2.size());

			System.out.println();
			System.out.println("****** Part 2 ******");

			Set<String> setI = SetIntersection(set1_2, set2_2);
			Set<String> setU = SetUnion(set1_2, set2_2);
			double intersection = setI.size();
			double union = setU.size();
			double A = intersection / union;
			System.out.println("Set1_2 + Set2_2:         " + A);

			setI = SetIntersection(set1_2, set3_2);
			setU = SetUnion(set1_2, set3_2);
			intersection = setI.size();
			union = setU.size();
			double B = intersection / union;
			System.out.println("Set1_2 + Set3_2:         " + B);

			setI = SetIntersection(set1_2, set4_2);
			setU = SetUnion(set1_2, set4_2);
			intersection = setI.size();
			union = setU.size();
			double C = intersection / union;
			System.out.println("Set1_2 + Set4_2:         " + C);

			setI = SetIntersection(set2_2, set3_2);
			setU = SetUnion(set2_2, set3_2);
			intersection = setI.size();
			union = setU.size();
			double D = intersection / union;
			System.out.println("Set2_2 + Set3_2:         " + D);

			setI = SetIntersection(set2_2, set4_2);
			setU = SetUnion(set2_2, set4_2);
			intersection = setI.size();
			union = setU.size();
			double E = intersection / union;
			System.out.println("Set2_2 + Set4_2:         " + E);

			setI = SetIntersection(set3_2, set4_2);
			setU = SetUnion(set3_2, set4_2);
			intersection = setI.size();
			union = setU.size();
			double F = intersection / union;
			System.out.println("Set3_2 + Set4_2:         " + F);

			System.out.println();

			setI = SetIntersection(set1_3, set2_3);
			setU = SetUnion(set1_3, set2_3);
			intersection = setI.size();
			union = setU.size();
			double A1 = intersection / union;
			System.out.println("Set1_3 + Set2_3:         " + A1);

			setI = SetIntersection(set1_3, set3_3);
			setU = SetUnion(set1_3, set3_3);
			intersection = setI.size();
			union = setU.size();
			double B1 = intersection / union;
			System.out.println("Set1_3 + Set3_3:         " + B1);

			setI = SetIntersection(set1_3, set4_3);
			setU = SetUnion(set1_3, set4_3);
			intersection = setI.size();
			union = setU.size();
			double C1 = intersection / union;
			System.out.println("Set1_3 + Set4_3:         " + C1);

			setI = SetIntersection(set2_3, set3_3);
			setU = SetUnion(set2_3, set3_3);
			intersection = setI.size();
			union = setU.size();
			double D1 = intersection / union;
			System.out.println("Set2_3 + Set3_3:         " + D1);

			setI = SetIntersection(set2_3, set4_3);
			setU = SetUnion(set2_3, set4_3);
			intersection = setI.size();
			union = setU.size();
			double E1 = intersection / union;
			System.out.println("Set2_3 + Set4_3:         " + E1);

			setI = SetIntersection(set3_3, set4_3);
			setU = SetUnion(set3_3, set4_3);
			intersection = setI.size();
			union = setU.size();
			double F1 = intersection / union;
			System.out.println("Set3_3 + Set4_3:         " + F1);

			System.out.println();

			setI = SetIntersection(setWord1_2, setWord2_2);
			setU = SetUnion(setWord1_2, setWord2_2);
			intersection = setI.size();
			union = setU.size();
			double A2 = intersection / union;
			System.out.println("SetWord1_2 + SetWord2_2: " + A2);

			setI = SetIntersection(setWord1_2, setWord3_2);
			setU = SetUnion(setWord1_2, setWord3_2);
			intersection = setI.size();
			union = setU.size();
			double B2 = intersection / union;
			System.out.println("SetWord1_2 + SetWord3_2: " + B2);

			setI = SetIntersection(setWord1_2, setWord4_2);
			setU = SetUnion(setWord1_2, setWord4_2);
			intersection = setI.size();
			union = setU.size();
			double C2 = intersection / union;
			System.out.println("SetWord1_2 + SetWord4_2: " + C2);

			setI = SetIntersection(setWord2_2, setWord3_2);
			setU = SetUnion(setWord2_2, setWord3_2);
			intersection = setI.size();
			union = setU.size();
			double D2 = intersection / union;
			System.out.println("SetWord2_2 + SetWord3_2: " + D2);

			setI = SetIntersection(setWord2_2, setWord4_2);
			setU = SetUnion(setWord2_2, setWord4_2);
			intersection = setI.size();
			union = setU.size();
			double E2 = intersection / union;
			System.out.println("SetWord2_2 + SetWord4_2: " + E2);

			setI = SetIntersection(setWord3_2, setWord4_2);
			setU = SetUnion(setWord3_2, setWord4_2);
			intersection = setI.size();
			union = setU.size();
			double F2 = intersection / union;
			System.out.println("SetWord3_2 + SetWord4_2: " + F2);

		} catch (FileNotFoundException e) {
			System.err.println("File Not Found Exception!");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("IOException!");
			e.printStackTrace();
		}

		System.out.println();
		System.out.println("****** Min Hashing ******");
		int[] t = {5, 20, 60, 100, 125,  150, 300, 600};
		Set<String> setU = SetUnion(set1_3, set2_3);
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		Iterator<String> it = setU.iterator();
		int i = 0;
		while (it.hasNext()) {
			String el  = it.next();
			map.put(el, i);
			i++;
		}

		List<Integer> list = new LinkedList<Integer>();
		for (int j = 0; j < 824; j++) {
			list.add(j);
		}

		Set<String> setNON1_3 = SetNoN(setU, set1_3);
		Set<String> setNON2_3 = SetNoN(setU, set2_3);

		for (i = 0; i < t.length; i++) { // iterating through the 5 t
			int currentT = t[i];
			int[] locations = new int[setNON1_3.size()];
			int[] locations1 = new int[setNON2_3.size()];
			int[] resultA = new int[currentT];
			int[] resultB = new int[currentT];
			for (int j = 0; j < currentT; j++) { // iterating through the t # of permutations
				int  a = 0;
				Collections.shuffle(list);
				it = setNON1_3.iterator();
				while (it.hasNext()) {
					String el = it.next();
					locations[a++] = (int) list.get(map.get(el));
				}
				a = 0;
				it = setNON2_3.iterator();
				while (it.hasNext()) {
					String el = it.next();
					locations1[a++] = (int) list.get(map.get(el));
				}

				for (int b = 0; b < 30; b++) {
					if (!Arraycontains(locations, b)) {
						resultA[j] = b;
						break;
					}
				}

				for (int b = 0; b < 30; b++) {
					if (!Arraycontains(locations1, b)) {
						resultB[j] = b;
						break;
					}
				}
			}
			double total = 0;
			for (int a = 0; a < resultA.length; a++) {
				if (resultA[a] == resultB[a])
					total++;
			}
			total = total / currentT;
			double percent = total * 100;
			System.out.println("t: " + currentT + " Percentage: " + percent);
		}
	}
}