package clustering;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class HierarchicalClustering1 {
	public static void main(String[] args) {
		File file1 = new File("/Users/carlosmartinez/Desktop/Java/Clustering/src/clustering/C1.txt");
		Map<Integer, double[]> vectors1 = new HashMap<Integer, double[]>();
		ArrayList<PointPair> pointPairs = new ArrayList<PointPair>();
		Map<Integer, ArrayList<Point>> clusters = new HashMap<Integer, ArrayList<Point>>();

		try {
			@SuppressWarnings("resource")
			BufferedReader bufferReader = new BufferedReader(new FileReader(file1));

			while (bufferReader.ready()) {
				String data = bufferReader.readLine();
				String[] parts = data.split("	");
				double[] vector = { Double.parseDouble(parts[1]), Double.parseDouble(parts[2]) };
				vectors1.put(Integer.parseInt(parts[0]), vector);
			}

		} catch (FileNotFoundException e) {
			System.err.println("File Not Found Exception!");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("IOException!");
			e.printStackTrace();
		}

		for (int i = 1; i <= 19; i++) {
			for (int j = i + 1; j <= 19; j++) {
				pointPairs.add(new PointPair(i, j, vectors1.get(i)[0], vectors1.get(i)[1], vectors1.get(j)[0],
						vectors1.get(j)[1]));
			}
		}

		Collections.sort(pointPairs);

		int[] ids = new int[19];
		for (int i = 1; i <= 19; i++) {
			ids[i - 1] = i;
		}

		for (int i = 0; i < 15; i++) {
			int left = pointPairs.get(0).getLeftID();
			Point leftPoint = new Point(pointPairs.get(0).getX1(), pointPairs.get(0).getY1());
			int right = pointPairs.get(0).getRightID();
			Point rightPoint = new Point(pointPairs.get(0).getX2(), pointPairs.get(0).getY2());
			int min = Math.min(right, left);
			int max = Math.max(right, left);

			ids[max - 1] = 0;

			// Cluster The Points
			if (!clusters.containsKey(min)) {
				clusters.put(min, new ArrayList<Point>());
				if (right == min) {
					clusters.get(min).add(rightPoint);
				} else {
					clusters.get(min).add(leftPoint);
				}
			}

			if (clusters.containsKey(max)) {
				for (Point el : clusters.get(max)) {
					clusters.get(min).add(el);
				}
				clusters.remove(max);
			} else {
				if (right == max) {
					clusters.get(min).add(rightPoint);
				} else {
					clusters.get(min).add(leftPoint);
				}
			}

			pointPairs = new ArrayList<PointPair>();
			// New pointPair calculated
			for (int id : ids) {
				if (id != 0) {
					if (clusters.containsKey(id)) {
						for (int otherID : ids) {
							if (otherID != 0 && otherID != id) {
								if (clusters.containsKey(otherID)) { // BOTH ARE CLUSTORS
									double distance = 0;
									double x_1 = Double.POSITIVE_INFINITY;
									double y_1 = Double.POSITIVE_INFINITY;
									double x_2 = Double.POSITIVE_INFINITY;
									double y_2 = Double.POSITIVE_INFINITY;
									for (Point point : clusters.get(id)) {
										double x1 = point.x();
										double y1 = point.y();
										for (Point point2 : clusters.get(otherID)) {
											double x2 = point2.x();
											double y2 = point2.y();
											PointPair pointPair = new PointPair(id, otherID, x1, y1, x2, y2);
											if (pointPair.getDistance() > distance) {
												distance = pointPair.getDistance();
												x_1 = x1;
												y_1 = y1;
												x_2 = x2;
												y_2 = y2;
											}
										}
									}
									PointPair pointPair = new PointPair(id, otherID, x_1, y_1, x_2, y_2);
									pointPair.setDistance(distance);
									pointPairs.add(pointPair);
								} else { // LEFT CLUSTER RIGHT NOT CLUSTER
									double distance = 0;
									double x_1 = Double.POSITIVE_INFINITY;
									double y_1 = Double.POSITIVE_INFINITY;
									double x_2 = Double.POSITIVE_INFINITY;
									double y_2 = Double.POSITIVE_INFINITY;
									for (Point point : clusters.get(id)) {
										double x1 = point.x();
										double y1 = point.y();
										double[] point2 = vectors1.get(otherID);
										double x2 = point2[0];
										double y2 = point2[1];
										PointPair pointPair = new PointPair(id, otherID, x1, y1, x2, y2);
										if (pointPair.getDistance() > distance) {
											distance = pointPair.getDistance();
											x_1 = x1;
											y_1 = y1;
											x_2 = x2;
											y_2 = y2;
										}

									}
									PointPair pointPair = new PointPair(id, otherID, x_1, y_1, x_2, y_2);
									pointPair.setDistance(distance);
									pointPairs.add(pointPair);
								}
							}
						}
					} else {
						for (int otherID : ids) {
							if (otherID != 0 && otherID != id) { // LEFT NOT CLUSTER RIGHT CLUSTER
								if (clusters.containsKey(otherID)) {
									double distance = 0;
									double x_1 = Double.POSITIVE_INFINITY;
									double y_1 = Double.POSITIVE_INFINITY;
									double x_2 = Double.POSITIVE_INFINITY;
									double y_2 = Double.POSITIVE_INFINITY;
									double[] point = vectors1.get(id);
									double x1 = point[0];
									double y1 = point[1];
									for (Point point2 : clusters.get(otherID)) {
										double x2 = point2.x();
										double y2 = point2.y();
										PointPair pointPair = new PointPair(id, otherID, x1, y1, x2, y2);
										if (pointPair.getDistance() > distance) {
											distance = pointPair.getDistance();
											x_1 = x1;
											y_1 = y1;
											x_2 = x2;
											y_2 = y2;
										}
									}
									PointPair pointPair = new PointPair(id, otherID, x_1, y_1, x_2, y_2);
									pointPair.setDistance(distance);
									pointPairs.add(pointPair);
								} else { // BOTH NOT CLUSTER
									double[] point = vectors1.get(id);
									double x1 = point[0];
									double y1 = point[1];
									double[] point2 = vectors1.get(otherID);
									double x2 = point2[0];
									double y2 = point2[1];
									PointPair pointPair = new PointPair(id, otherID, x1, y1, x2, y2);
									pointPairs.add(pointPair);
								}
							}
						}
					}
				}
			}

			Collections.sort(pointPairs);
		}

		for (int el : clusters.keySet()) {
			ArrayList<Point> elem = clusters.get(el);
			System.out.println("SET:");
			for (Point ele : elem) {
				System.out.println(ele.toString());
			}
		}

	}
}