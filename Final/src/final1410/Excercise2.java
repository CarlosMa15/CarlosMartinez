/***********************************************
 * Carlos Martinez
 * Final 1410
 * April 2, 2017
 **********************************************/
package final1410;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Class Writing To a File
 * @author Carlos Martinez
 */
public class Excercise2 {

	public static void main(String[] args) {
		Set<Integer> distinctNumbers = new HashSet<>();
		String fileName = "src/final1410/Numbers.txt";
		File file = new File(fileName);
		
		try(Scanner reader = new Scanner(file)) { 
			while(reader.hasNextLine()) {
				int number = reader.nextInt();
				System.out.println(number);
				distinctNumbers.add(number);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println();
		
		System.out.println("Numbers in file ");
		for (Integer el: distinctNumbers) {
			System.out.print(el + " ");
		}
	}
}