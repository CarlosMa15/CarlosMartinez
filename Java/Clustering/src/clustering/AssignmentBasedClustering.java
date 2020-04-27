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

public class AssignmentBasedClustering {
	
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
		
//		System.out.println("Max Distance: " + distance[getMaxIndex(distance)]);
//		System.out.println("Index: " + getMaxIndex(distance));
//		System.out.println("(" + vectors.get(1040)[0] + "," + vectors.get(1040)[1] + ")");
		
		int index = getMaxIndex(distance);
		for(int i = 1; i < distance.length; i++) {
			double x2 = vectors.get(index)[0];
			double y2 = vectors.get(index)[1];
			double x1 = vectors.get(i)[0];
			double y1 = vectors.get(i)[1];
			double distance2 = Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
			
			if(distance2 < distance[i]) {
				distance[i] = distance2;
				clusters[i] = 1040;
			}
		}
		
		index = getMaxIndex(distance);
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
				System.out.println("(" + vectors.get(i)[0] + "," + vectors.get(i)[1] + ")");
				set11.add(vectors.get(i));
			}
		}
		System.out.println("SET");
		for (int i = 1; i < 1041; i++) {
			if(clusters[i] == 1010) {
				System.out.println("(" + vectors.get(i)[0] + "," + vectors.get(i)[1] + ")");
				set12.add(vectors.get(i));
			}
		}
		System.out.println("SET");
		for (int i = 1; i < 1041; i++) {
			if(clusters[i] == 1040) {
				System.out.println("(" + vectors.get(i)[0] + "," + vectors.get(i)[1] + ")");
				set13.add(vectors.get(i));
			}
		}
		
		System.out.println("MAX: " +distance[getMaxIndex(distance)]);
		
		double distanceSum = 0;
		for(int i = 1; i <= 1040; i++) {
			double dis = distance[i] * distance[i];
			distanceSum += dis;
		}
		System.out.println(Math.sqrt(distanceSum / 1040));
		
		/**************************************************************************************/
		
		
		
		clusters = new int[vectors.size() + 1];
		Arrays.fill(clusters, 1);
		clusters[0] = (int) Double.NEGATIVE_INFINITY;
		distance = new double[vectors.size() + 1];
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
		
		index = answer;
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
		
		System.out.println("SET");
		for (int i = 1; i < 1041; i++) {
			if(clusters[i] == 1) {
				System.out.println("(" + vectors.get(i)[0] + "," + vectors.get(i)[1] + ")");
				set21.add(vectors.get(i));
			}
		}
		System.out.println("SET");
		for (int i = 1; i < 1041; i++) {
			if(clusters[i] == answer) {
				System.out.println("(" + vectors.get(i)[0] + "," + vectors.get(i)[1] + ")");
				set22.add(vectors.get(i));
			}
		}
		System.out.println("SET");
		for (int i = 1; i < 1041; i++) {
			if(clusters[i] == answer1) {
				System.out.println("(" + vectors.get(i)[0] + "," + vectors.get(i)[1] + ")");
				set23.add(vectors.get(i));
			}
		}
		
		distanceSum = 0;
		for(int i = 1; i <= 1040; i++) {
			double dis = distance[i] * distance[i];
			distanceSum += dis;
		}
		System.out.println(Math.sqrt(distanceSum / 1040));
		
		// System.out.println(answer);
		// System.out.println(answer1);
		
		System.out.println();
		System.out.println(set11.size());
		System.out.println(set12.size());
		System.out.println(set13.size());
		
		System.out.println();
		System.out.println(set21.size());
		System.out.println(set22.size());
		System.out.println(set23.size());		
	}
}