package clustering;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LloydsAlgorithm {

	public static int numberOfElements(double[] array, double element) {
		int amount = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] == element) {
				amount++;
			}
		}
		return amount;
	}

	public static double[] newCenter(int[] centers, Map<Integer, double[]> vectors, int center) {
		double x = 0;
		double y = 0;
		int counter = 0;

		for (int el : centers) {
			if (el == center) {
				x += vectors.get(el)[0];
				y += vectors.get(el)[1];
				counter++;
			}
		}

		double answer[] = new double[2];
		answer[0] = x / counter;
		answer[1] = y / counter;
		return answer;
	}

	public static void main(String[] args) {
		File file = new File("/Users/carlosmartinez/Desktop/Java/Clustering/src/clustering/C2.txt");
		Map<Integer, double[]> vectors = new HashMap<Integer, double[]>();

		try {
			@SuppressWarnings("resource")
			BufferedReader bufferReader = new BufferedReader(new FileReader(file));

			while (bufferReader.ready()) {
				String data = bufferReader.readLine();
				String[] parts = data.split("	");
				double[] vector = { Double.parseDouble(parts[1]), Double.parseDouble(parts[2]) };
				vectors.put(Integer.parseInt(parts[0]), vector);
			}

		} catch (FileNotFoundException e) {
			System.err.println("File Not Found Exception!");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("IOException!");
			e.printStackTrace();
		}

		int trialNumber = 1;
		int[] second = { 1003, 485, 922, 522, 1040, 803, 793, 846, 1000, 433, 904, 828, 749, 887, 598, 721, 739, 941,
				403, 912 };
		int[] third = { 545, 449, 591, 13, 1010, 662, 550, 503, 438, 460, 458, 515, 489, 526, 549, 374, 661, 519, 608,
				490 };
		double[] trails = new double[trialNumber];

		for (int a = 0; a < trialNumber; a++) {

			int startIndex1 = 1;
			int startIndex2 = 1010;  // second[a];
			int startIndex3 = 1040;  // third[a];

			double[] location1 = vectors.get(startIndex1);
			double[] location2 = vectors.get(startIndex2);
			double[] location3 = vectors.get(startIndex3);
			double distance1 = 2;
			double distance2 = 2;
			double distance3 = 2;
			double diffence = 0;
			double[] distances = new double[1041];
			Arrays.fill(distances, Double.POSITIVE_INFINITY);
			int[] center = new int[1041];
			Arrays.fill(center, 4);
			distances[0] = -1;
			center[0] = -1;

			while (distance1 > diffence && distance2 > diffence && distance3 > diffence) {

				System.out.println("(" + location1[0] + "," + location1[1] + ")");
				System.out.println("(" + location2[0] + "," + location2[1] + ")");
				System.out.println("(" + location3[0] + "," + location3[1] + ")\n");

				for (int i = 1; i <= 1040; i++) {
					double x2 = location1[0];
					double y2 = location1[1];
					double x1 = vectors.get(i)[0];
					double y1 = vectors.get(i)[1];
					double dis = Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));

					distances[i] = dis;
					center[i] = 1;

					x2 = location2[0];
					y2 = location2[1];
					dis = Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));

					if (dis < distances[i]) {
						distances[i] = dis;
						center[i] = 2;
					}

					x2 = location3[0];
					y2 = location3[1];
					dis = Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));

					if (dis < distances[i]) {
						distances[i] = dis;
						center[i] = 3;
					}
				}

				double[] newLocation1 = newCenter(center, vectors, 1);
				double x2 = location1[0];
				double y2 = location1[1];
				double x1 = newLocation1[0];
				double y1 = newLocation1[1];
				double dis = Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
				distance1 = dis;
				location1 = newLocation1;

				double[] newLocation2 = newCenter(center, vectors, 2);
				x2 = location2[0];
				y2 = location2[1];
				x1 = newLocation2[0];
				y1 = newLocation2[1];
				dis = Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
				distance2 = dis;
				location2 = newLocation2;

				double[] newLocation3 = newCenter(center, vectors, 3);
				x2 = location3[0];
				y2 = location3[1];
				x1 = newLocation3[0];
				y1 = newLocation3[1];
				dis = Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
				distance3 = dis;
				location3 = newLocation3;

			}

			System.out.println("SET:::::::::::::::::::::::::::::::::::::::::::");
			for (int i = 1; i <= 1040; i++) {
				if (center[i] == 1) {
					// System.out.print("(" + vectors.get(i)[0] + "," + vectors.get(i)[1] + ")");
					System.out.println(vectors.get(i)[0] + "	" + vectors.get(i)[1] + "	a");
					//System.out.print(i + " , ");
				}
			}

			//System.out.println("\nSET:::::::::::::::::::::::::::::::::::::::::::");
			for (int i = 1; i <= 1040; i++) {
				if (center[i] == 2) {
					// System.out.print("(" + vectors.get(i)[0] + "," + vectors.get(i)[1] + ")");
					// System.out.print(i + " , ");
					System.out.println(vectors.get(i)[0] + "	" + vectors.get(i)[1] + "	b");
				}
			}

			//System.out.println("\nSET:::::::::::::::::::::::::::::::::::::::::::");
			for (int i = 1; i <= 1040; i++) {
				if (center[i] == 3) {
					// System.out.print("(" + vectors.get(i)[0] + "," + vectors.get(i)[1] + ")");
					System.out.println(vectors.get(i)[0] + "	" + vectors.get(i)[1] + "	c");
				}
			}

			double distanceSum = 0;
			for (int i = 1; i <= 1040; i++) {
				double dis = distances[i] * distances[i];
				distanceSum += dis;
			}
			trails[a] = Math.sqrt(distanceSum / 1040);
		}

		System.out.println("\n" + trails[0]);

//		Arrays.sort(trails);
//		System.out.println();
//		double[] sumProbability = new double[trialNumber];
//		for (int i = 0; i < trialNumber; i++) {
//			int amount = numberOfElements(trails, trails[i]);
//			double currentProbability = ((double) amount) / trialNumber;
//			if (i == 0) {
//				sumProbability[i] = currentProbability;
//			} else {
//				sumProbability[i] = currentProbability + sumProbability[i - 1];
//			}
//			System.out.printf("%.5f %.5f\n", trails[i], sumProbability[i]);
//		}

	}
}