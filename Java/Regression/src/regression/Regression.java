package regression;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Regression {

	public static void main(String[] args) {
		File file = new File("/Users/carlosmartinez/Desktop/Java/Regression/src/regression/x.txt");
		
		double[][] matrix;
		double[] vector;
		int colNum;
		int rowNum;
		

		try {
			@SuppressWarnings("resource")
			BufferedReader bufferReader = new BufferedReader(new FileReader(file));
			
			String[] info = bufferReader.readLine().split(" ");
			colNum = Integer.parseInt(info[1]);
			rowNum = Integer.parseInt(info[0]);
			matrix = new double[rowNum][colNum];
			vector= new double[rowNum * colNum];
			
			System.out.println("ColNum: " + colNum + " RowNum: " + rowNum);

			int index = 0;
			for(int i = 0; i < rowNum; i++) {
				String data[] = bufferReader.readLine().split(" ");
				for(int j = 0; j < colNum; j++) {
					vector[index++] = Double.parseDouble(data[j]);
					matrix[i][j] = Double.parseDouble(data[j]);
				}
			}

		} catch (FileNotFoundException e) {
			System.err.println("File Not Found Exception!");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("IOException!");
			e.printStackTrace();
		}
	}
}