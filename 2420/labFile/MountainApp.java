package labFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MountainApp {
	public static void main(String[] args) {
		List<Mountain> mountainList = new ArrayList<>();
		String line = "";
		
		try (Scanner reader = new Scanner(MountainApp.class.getResourceAsStream("Mountains.csv"))) {
			while(reader.hasNextLine()) {
				line = reader.nextLine();
				Mountain mountain = getMountain(line);
				
				if(mountain != null) {
					mountainList.add(mountain);
				}
			}
			System.out.println();
			
			for(Mountain el: mountainList) {
				System.out.println(el);
			}
		}
	}

	private static Mountain getMountain(String nextLine) {
		try {
			String line = nextLine;
			String[] lineElements = line.split(",");
			
			Mountain mountain = new Mountain(lineElements[0],Integer.parseInt(lineElements[1]),Boolean.parseBoolean(lineElements[2]));
			return mountain;
		
		} catch (NumberFormatException e) {
			System.err.println(nextLine + ".. cound not be read in as a mountain.");
			return null;
		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.println(nextLine + ".. cound not be read in as a mountain.");
			return null;
		}
	}
}
