package dimensionalityReduction;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class DimensionalityReduction {

	public static void main(String[] args) {
		File file = new File("/Users/carlosmartinez/Desktop/Java/DimensionalityReduction/src/dimensionalityReduction/data2.txt");
		String fileContent = "";
		try {
			@SuppressWarnings("resource")
			BufferedReader bufferReader = new BufferedReader(new FileReader(file));

			while (bufferReader.ready()) {
				String data = bufferReader.readLine();
				String[] parts = data.trim().split(","); // .split("\\s+");
				fileContent = "(" + parts[0] + "," + parts[1] + ") [a]";
				System.out.println(fileContent);
			}

		} catch (FileNotFoundException e) {
			System.err.println("File Not Found Exception!");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("IOException!");
			e.printStackTrace();
		}

//		FileWriter fileWriter;
//		try {
//			fileWriter = new FileWriter("/Users/carlosmartinez/Desktop/Java/DimensionalityReduction/src/dimensionalityReduction/data1.txt");
//			PrintWriter printWriter = new PrintWriter(fileWriter);
//		    printWriter.print(fileContent);
//		    printWriter.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}
}