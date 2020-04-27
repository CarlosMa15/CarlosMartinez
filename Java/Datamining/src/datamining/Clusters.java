package datamining;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Clusters {

	public static void main(String[] args) {
		
		File file = new File("/Users/carlosmartinez/Desktop/Java/Datamining/src/datamining/data.txt");
		
		try {
			@SuppressWarnings("resource")
			BufferedReader bufferReader = new BufferedReader(new FileReader(file));
			while (bufferReader.ready()) {
				String line = bufferReader.readLine();
				if(line.contains(":Ceftazidime:")) {
					System.out.println(line);
				}
			}

		} catch (FileNotFoundException e) {
			System.err.println("File Not Found Exception!");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("IOException!");
			e.printStackTrace();
		}

//		File file = new File("/Users/carlosmartinez/Desktop/Java/Datamining/src/datamining/EntireData.txt");
//		Set<String> countries = new HashSet<String>();
//		Set<String> antibiotic = new HashSet<String>();
//		Set<String> bacterium_strain = new HashSet<String>();
//		Map<String, HashMap<String, ArrayList<Double>>> countryAnti = new HashMap<String, HashMap<String, ArrayList<Double>>>();
//
//		try {
//			@SuppressWarnings("resource")
//			BufferedReader bufferReader = new BufferedReader(new FileReader(file));
//			bufferReader.readLine();
//			while (bufferReader.ready()) {
//				String[] line = bufferReader.readLine().split("\\s\\|\\s");
//				bacterium_strain.add(line[0].trim());
//				antibiotic.add(line[1].trim());
//				countries.add(line[3].trim());
//			}
//
//		} catch (FileNotFoundException e) {
//			System.err.println("File Not Found Exception!");
//			e.printStackTrace();
//		} catch (IOException e) {
//			System.err.println("IOException!");
//			e.printStackTrace();
//		}
//
//		try {
//			@SuppressWarnings("resource")
//			BufferedReader bufferReader = new BufferedReader(new FileReader(file));
//			bufferReader.readLine();
//			while (bufferReader.ready()) {
//				String[] line = bufferReader.readLine().split("\\s\\|\\s");
//				String bacteriumStrain = line[0].trim();
//				String antiBiotic = line[1].trim();
//				String country = line[3].trim();
//				String number = line[4].trim();
//
//				if (!countryAnti.containsKey(country)) {
//					countryAnti.put(country, new HashMap<String, ArrayList<Double>>());
//				}
//
//				if (!countryAnti.get(country).containsKey(antiBiotic)) {
//					countryAnti.get(country).put(antiBiotic, new ArrayList<Double>());
//				}
//
//				countryAnti.get(country).get(antiBiotic).add(Double.parseDouble(number));
//
//			}
//
//		} catch (FileNotFoundException e) {
//			System.err.println("File Not Found Exception!");
//			e.printStackTrace();
//		} catch (IOException e) {
//			System.err.println("IOException!");
//			e.printStackTrace();
//		}
//
//		for (String country : countries) {
//			for (String dis : countryAnti.get(country).keySet()) {
//				System.out.print(country + ":" + dis + ":<");
//				boolean first = true;
//				for (Double number : countryAnti.get(country).get(dis)) {
//					if (first) {
//						System.out.print(number);
//						first = false;
//					} else {
//						System.out.print("," + number);
//					}
//				}
//				System.out.println(">");
//			}
//		}

	}
}