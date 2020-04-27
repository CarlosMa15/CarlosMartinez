package clustering;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class kMean {

	static int getMaxIndex(double[] list) {
		int index = 1;
		double distance = 0;
		for(int i = 1; i < list.length; i++) {
			if(list[i] > distance) {
				distance = list[i];
				index = i;
			}
		}
		
		return index;
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

//		for (int name : vectors.keySet()) {
//			int key = name;
//			double[] value = vectors.get(name);
//			System.out.println(key + " (" + value[0] + "," + value[1] + ")");
//		}
		
		int[] clusters = new int[vectors.size() + 1];
		Arrays.fill(clusters, 1);
		clusters[0] = (int) Double.NEGATIVE_INFINITY;
		double[] distance = new double[vectors.size() + 1];
		distance[0] = (int) Double.NEGATIVE_INFINITY;
		distance[1] = 0;
		for(int i = 2; i <= vectors.size(); i++) {
			double x2 = vectors.get(1)[0];
			double y2 = vectors.get(1)[1];
			double x1 = vectors.get(i)[0];
			double y1 = vectors.get(i)[1];
			
			distance[i] = Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
		}
		
//		for(double el: distance) {
//			System.out.println("Distance: " + el);
//		}
//		System.out.println();
		
//		System.out.println("Max Distance: " + distance[getMaxIndex(distance)]);
//		System.out.println("Index: " + getMaxIndex(distance));
//		System.out.println("(" + vectors.get(1040)[0] + "," + vectors.get(1040)[1] + ")");
		
//		int index = getMaxIndex(distance);
//		for(int i = 1; i < distance.length; i++) {
//			double x2 = vectors.get(index)[0];
//			double y2 = vectors.get(index)[1];
//			double x1 = vectors.get(i)[0];
//			double y1 = vectors.get(i)[1];
//			double distance2 = Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
//			
//			if(distance2 < distance[i]) {
//				distance[i] = distance2;
//				clusters[i] = 1040;
//			}
//		}
		
		double[] W = new double[vectors.size() + 1];
		W[0] = (int) Double.NEGATIVE_INFINITY;
		
		double Wbottom = 0;
		for (int i = 1; i <= 1040; i++) {
			double dis = distance[i] * distance[i];
			Wbottom += dis;
		}
		
		for (int i = 1; i <= 1040; i++) {
			double dis = distance[i] * distance[i];
			W[i] = dis / Wbottom;
		}
		
		double[] Worder = new double[W.length];
		distance[0] = 0;
		System.arraycopy(W, 0, Worder, 0, W.length);
		Arrays.sort(W);
		
//		double add = 0;
//		for(int i = 1; i <= 1040; i ++) {
//			System.out.println(W[i]);
//			add += W[i];
//		}
//		System.out.println("ADD: " + add);
//		System.out.println();
		
		double sum = 0;
		double rand = Math.random();
		int answer = -1;
		for(int i = 1; i <= 1040; i ++) {
			if(rand > sum && rand < sum + W[i]) {
				answer = i;
				break;
			}
			sum += W[i];
			
		}
		
		for(int i = 1; i <= 1040; i++) {
			if(W[answer] == Worder[i]) {
				answer = i;
				break;
			}
		}
		
		int index = answer;
		for(int i = 1; i < distance.length; i++) {
			double x2 = vectors.get(index)[0];
			double y2 = vectors.get(index)[1];
			double x1 = vectors.get(i)[0];
			double y1 = vectors.get(i)[1];
			double distance2 = Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
			
			if(distance2 < distance[i]) {
				distance[i] = distance2;
				clusters[i] = index;
			}
		}
		
//		for(double el: distance) {
//			System.out.println(el);
//		}	
		
		W = new double[vectors.size() + 1];
		W[0] = (int) Double.NEGATIVE_INFINITY;
		
		Wbottom = 0;
		for (int i = 1; i <= 1040; i++) {
			double dis = distance[i] * distance[i];
			Wbottom += dis;
		}
		
		for (int i = 1; i <= 1040; i++) {
			double dis = distance[i] * distance[i];
			W[i] = dis / Wbottom;
		}
		
		Worder = new double[W.length];
		distance[0] = 0;
		System.arraycopy(W, 0, Worder, 0, W.length);
		Arrays.sort(W);
		
		sum = 0;
		rand = Math.random();
		int answer1 = -1;
		for(int i = 1; i <= 1040; i ++) {
			if(rand > sum && rand < sum + W[i]) {
				answer1 = i;
				break;
			}
			sum += W[i];
			
		}
		
		for(int i = 1; i <= 1040; i++) {
			if(W[answer] == Worder[i]) {
				answer1 = i;
				break;
			}
		}
		
		index = answer1;
		for(int i = 1; i < distance.length; i++) {
			double x2 = vectors.get(index)[0];
			double y2 = vectors.get(index)[1];
			double x1 = vectors.get(i)[0];
			double y1 = vectors.get(i)[1];
			double distance2 = Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
			
			if(distance2 < distance[i]) {
				distance[i] = distance2;
				clusters[i] = index;
			}
		}
		
		Set<double[]> set11 = new HashSet<double[]>();
		Set<double[]> set12 = new HashSet<double[]>();
		Set<double[]> set13 = new HashSet<double[]>();
		Set<double[]> set21 = new HashSet<double[]>();
		Set<double[]> set22 = new HashSet<double[]>();
		Set<double[]> set23 = new HashSet<double[]>();
		
		System.out.println("SET");
		for (int i = 1; i < 1041; i++) {
			if(clusters[i] == 1) {
				System.out.print("(" + vectors.get(i)[0] + "," + vectors.get(i)[1] + ")");
				set11.add(vectors.get(i));
			}
		}
		System.out.println("\nSET");
		for (int i = 1; i < 1041; i++) {
			if(clusters[i] == answer) {
				System.out.print("(" + vectors.get(i)[0] + "," + vectors.get(i)[1] + ")");
				set12.add(vectors.get(i));
			}
		}
		System.out.println("\nSET");
		for (int i = 1; i < 1041; i++) {
			if(clusters[i] == answer1) {
				System.out.print("(" + vectors.get(i)[0] + "," + vectors.get(i)[1] + ")");
				set13.add(vectors.get(i));
			}
		}
		
		double distanceSum = 0;
		for(int i = 1; i <= 1040; i++) {
			double dis = distance[i] * distance[i];
			distanceSum += dis;
		}
		System.out.println("\n" + Math.sqrt(distanceSum / 1040));
		
		System.out.println(distance[getMaxIndex(distance)]);
		
		System.out.println(answer);
		System.out.println(answer1);
		
		/***********************************************************************************************/
	
//
//		int trialNumber = 1;
//		int[] second = { 1003, 485, 922, 522, 1040, 803, 793, 846, 1000, 433, 904, 828, 749, 887, 598, 721, 739, 941,
//				403, 912 };
//		int[] third = { 545, 449, 591, 13, 1010, 662, 550, 503, 438, 460, 458, 515, 489, 526, 549, 374, 661, 519, 608,
//				490 };
//		double[] trails = new double[trialNumber];
//
//		for (int a = 0; a < trialNumber; a++) {
//
//			int startIndex1 = 1;
//			int startIndex2 = 2; // 1010;  // second[a];
//			int startIndex3 = 3; // 1040; // third[a];
//
//			double[] location1 = vectors.get(startIndex1);
//			double[] location2 = vectors.get(startIndex2);
//			double[] location3 = vectors.get(startIndex3);
//			double distance1 = 2;
//			double distance2 = 2;
//			double distance3 = 2;
//			double diffence = 0;
//			double[] distances = new double[1041];
//			Arrays.fill(distances, Double.POSITIVE_INFINITY);
//			int[] center = new int[1041];
//			Arrays.fill(center, 4);
//			distances[0] = -1;
//			center[0] = -1;
//
//			while (distance1 > diffence && distance2 > diffence && distance3 > diffence) {
//
//				System.out.println("(" + location1[0] + "," + location1[1] + ")");
//				System.out.println("(" + location2[0] + "," + location2[1] + ")");
//				System.out.println("(" + location3[0] + "," + location3[1] + ")\n");
//
//				for (int i = 1; i <= 1040; i++) {
//					double x2 = location1[0];
//					double y2 = location1[1];
//					double x1 = vectors.get(i)[0];
//					double y1 = vectors.get(i)[1];
//					double dis = Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
//
//					distances[i] = dis;
//					center[i] = 1;
//
//					x2 = location2[0];
//					y2 = location2[1];
//					dis = Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
//
//					if (dis < distances[i]) {
//						distances[i] = dis;
//						center[i] = 2;
//					}
//
//					x2 = location3[0];
//					y2 = location3[1];
//					dis = Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
//
//					if (dis < distances[i]) {
//						distances[i] = dis;
//						center[i] = 3;
//					}
//				}
//
//				double[] newLocation1 = newCenter(center, vectors, 1);
//				double x2 = location1[0];
//				double y2 = location1[1];
//				double x1 = newLocation1[0];
//				double y1 = newLocation1[1];
//				double dis = Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
//				distance1 = dis;
//				location1 = newLocation1;
//
//				double[] newLocation2 = newCenter(center, vectors, 2);
//				x2 = location2[0];
//				y2 = location2[1];
//				x1 = newLocation2[0];
//				y1 = newLocation2[1];
//				dis = Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
//				distance2 = dis;
//				location2 = newLocation2;
//
//				double[] newLocation3 = newCenter(center, vectors, 3);
//				x2 = location3[0];
//				y2 = location3[1];
//				x1 = newLocation3[0];
//				y1 = newLocation3[1];
//				dis = Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
//				distance3 = dis;
//				location3 = newLocation3;
//
//			}
//
//			//System.out.println("SET:::::::::::::::::::::::::::::::::::::::::::");
//			for (int i = 1; i <= 1040; i++) {
//				if (center[i] == 1) {
//					// System.out.print("(" + vectors.get(i)[0] + "," + vectors.get(i)[1] + ")");
//					//System.out.print(i + " , ");
//					set21.add(vectors.get(i));
//				}
//			}
//
//			//System.out.println("\nSET:::::::::::::::::::::::::::::::::::::::::::");
//			for (int i = 1; i <= 1040; i++) {
//				if (center[i] == 2) {
//					//System.out.print("(" + vectors.get(i)[0] + "," + vectors.get(i)[1] + ")");
//					// System.out.print(i + " , ");
//					set22.add(vectors.get(i));
//				}
//			}
//
//			//System.out.println("\nSET:::::::::::::::::::::::::::::::::::::::::::");
//			for (int i = 1; i <= 1040; i++) {
//				if (center[i] == 3) {
//					//System.out.print("(" + vectors.get(i)[0] + "," + vectors.get(i)[1] + ")");
//					set23.add(vectors.get(i));
//				}
//			}
//
//			distanceSum = 0;
//			for (int i = 1; i <= 1040; i++) {
//				double dis = distances[i] * distances[i];
//				distanceSum += dis;
//			}
//			trails[a] = Math.sqrt(distanceSum / 1040);
//		}
//
//		System.out.println("\n" + trails[0]);
//
////		Arrays.sort(trails);
////		System.out.println();
////		double[] sumProbability = new double[trialNumber];
////		for (int i = 0; i < trialNumber; i++) {
////			int amount = numberOfElements(trails, trails[i]);
////			double currentProbability = ((double) amount) / trialNumber;
////			if (i == 0) {
////				sumProbability[i] = currentProbability;
////			} else {
////				sumProbability[i] = currentProbability + sumProbability[i - 1];
////			}
////			System.out.printf("%.5f %.5f\n", trails[i], sumProbability[i]);
////		}
//		
//		
//		System.out.println();
//		System.out.println(set11.size());
//		System.out.println(set12.size());
//		System.out.println(set13.size());
//		
//		System.out.println();
//		System.out.println(set21.size());
//		System.out.println(set22.size());
//		System.out.println(set23.size());
		
	}

}
