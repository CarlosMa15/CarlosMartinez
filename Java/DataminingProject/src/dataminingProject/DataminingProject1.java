package dataminingProject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

public class DataminingProject1 {

	public static void main(String[] args) {
		File file = new File(
				"/Users/carlosmartinez/Desktop/Java/DataminingProject/src/dataminingProject/ProjectData.txt");
		HashMap<String, Integer> countries = new HashMap<String, Integer>();
		HashSet<String> diseases = new HashSet<String>();
		HashSet<String> diseases1 = new HashSet<String>();
		HashSet<String> setCountries = new HashSet<String>();
		// country then disease + anti + year
		HashMap<String, HashMap<String, Double>> vectors = new HashMap<String, HashMap<String, Double>>();
		// country year then disease + anti
		HashMap<String, HashMap<String, Double>> yearVectors = new HashMap<String, HashMap<String, Double>>();
		// Years
		HashMap<String, HashMap<String, Double>> vectors2012 = new HashMap<String, HashMap<String, Double>>();
		HashMap<String, HashMap<String, Double>> vectors2013 = new HashMap<String, HashMap<String, Double>>();
		HashMap<String, HashMap<String, Double>> vectors2014 = new HashMap<String, HashMap<String, Double>>();
		HashMap<String, HashMap<String, Double>> vectors2015 = new HashMap<String, HashMap<String, Double>>();

		try {
			@SuppressWarnings("resource")
			BufferedReader bufferReader = new BufferedReader(new FileReader(file));

			while (bufferReader.ready()) {
				String data = bufferReader.readLine();
				String[] parts = data.split("\\s\\|\\s");
				String country = parts[3].trim();
				String illness = parts[0].trim();
				String meds = parts[1].trim();
				String year = parts[2].trim();

				diseases.add(illness + " " + meds + " " + year);
				diseases1.add(illness + " " + meds);

				if (countries.containsKey(country)) {
					countries.put(country, countries.get(country) + 1);
				} else {
					countries.put(country, 1);
				}
			}

		} catch (FileNotFoundException e) {
			System.err.println("File Not Found Exception!");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("IOException!");
			e.printStackTrace();
		}

		for (String key : countries.keySet()) {
			if (countries.get(key) == 108) {
				setCountries.add(key);
			}
		}

		for (String element : setCountries) {
			vectors.put(element, new HashMap<String, Double>());
			yearVectors.put(element + " 2012", new HashMap<String, Double>());
			yearVectors.put(element + " 2013", new HashMap<String, Double>());
			yearVectors.put(element + " 2014", new HashMap<String, Double>());
			yearVectors.put(element + " 2015", new HashMap<String, Double>());
		}

		int a = 0;
		System.out.print("< ");
		for (String element1 : diseases) {
			if (a == 0) {
				System.out.print(element1);
				a = 1;
			} else {
				System.out.print(", " + element1);
			}

		}
		System.out.println(" >");

		try {
			@SuppressWarnings("resource")
			BufferedReader bufferReader = new BufferedReader(new FileReader(file));

			while (bufferReader.ready()) {
				String data = bufferReader.readLine();
				String[] parts = data.split("\\s\\|\\s");
				String country = parts[3].trim();
				String illness = parts[0].trim();
				String meds = parts[1].trim();
				String year = parts[2].trim();
				String resistance = parts[4].trim();

				String key = illness + " " + meds + " " + year;
				String key2 = country + " " + year;
				String key3 = illness + " " + meds;
				if (setCountries.contains(country))
					vectors.get(country).put(key, Double.parseDouble(resistance));

				if (setCountries.contains(country))
					yearVectors.get(key2).put(key3, Double.parseDouble(resistance));

			}

		} catch (FileNotFoundException e) {
			System.err.println("File Not Found Exception!");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("IOException!");
			e.printStackTrace();
		}

		
		for (String country : vectors.keySet()) {
			System.out.print(country + ": < ");
			for (String res : vectors.get(country).keySet()) {
				System.out.print(vectors.get(country).get(res) + " ");
			}
			System.out.println(">");
		}

		System.out.println();
		System.out.println();

		a = 0;
		System.out.print("< ");
		for (String element1 : diseases1) {
			if (a == 0) {
				System.out.print(element1);
				a = 1;
			} else {
				System.out.print(", " + element1);
			}

		}
		System.out.println(" >");

		for (String country : yearVectors.keySet()) {
			for (String res : yearVectors.get(country).keySet()) {
				if (country.contains("2012")) {
					if (!vectors2012.containsKey(country))
						vectors2012.put(country, new HashMap<String, Double>());
					vectors2012.get(country).put(res, yearVectors.get(country).get(res));
				}

				if (country.contains("2013")) {
					if (!vectors2013.containsKey(country))
						vectors2013.put(country, new HashMap<String, Double>());
					vectors2013.get(country).put(res, yearVectors.get(country).get(res));
				}

				if (country.contains("2014")) {
					if (!vectors2014.containsKey(country))
						vectors2014.put(country, new HashMap<String, Double>());
					vectors2014.get(country).put(res, yearVectors.get(country).get(res));
				}

				if (country.contains("2015")) {
					if (!vectors2015.containsKey(country))
						vectors2015.put(country, new HashMap<String, Double>());
					vectors2015.get(country).put(res, yearVectors.get(country).get(res));
				}

			}
		}

		System.out.println();
		System.out.println();

		for (String country : vectors2012.keySet()) {
			System.out.print(country + ": < ");
			for (String res : vectors2012.get(country).keySet()) {
				System.out.print(vectors2012.get(country).get(res) + " ");
			}
			System.out.println(">");
		}

		System.out.println();
		System.out.println();

		for (String country : vectors2013.keySet()) {
			System.out.print(country + ": < ");
			for (String res : vectors2013.get(country).keySet()) {
				System.out.print(vectors2013.get(country).get(res) + " ");
			}
			System.out.println(">");
		}

		System.out.println();
		System.out.println();

		for (String country : vectors2014.keySet()) {
			System.out.print(country + ": < ");
			for (String res : vectors2014.get(country).keySet()) {
				System.out.print(vectors2014.get(country).get(res) + " ");
			}
			System.out.println(">");
		}

		System.out.println();
		System.out.println();

		for (String country : vectors2015.keySet()) {
			System.out.print(country + ": < ");
			for (String res : vectors2015.get(country).keySet()) {
				System.out.print(vectors2015.get(country).get(res) + " ");
			}
			System.out.println(">");
		}
	}
}