/***********************************************
 * Carlos Martinez
 * Final 1410
 * April 2, 2017
 **********************************************/
package final1410;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * Class Writing To a File
 * @author Carlos Martinez
 */
public class Excercise1 {

	public static void main(String[] args) {
		
		int[] numbers = {22,44,66,88,66,44,22};
		
		try( PrintWriter writer = new PrintWriter("src/final1410/Numbers.txt")) {
			
			//Printing writing each element
			boolean first = true;
			for(int el: numbers) {
				if(first) {
					writer.print(el);
					first = false;
				} else {
					writer.print(" "+ el);
				}
			}
			writer.println();
			
			//writing binary number of each element
			first = true;
			for(int el: numbers) {
				if(first) {
					writer.print(Integer.toBinaryString(el));
					first = false;
				} else {
					writer.print(" "+ Integer.toBinaryString(el));
				}
			}
			writer.println();
			
			//Writing elements in order
			first = true;
			Arrays.sort(numbers);
			for(int el: numbers) {
				if(first) {
					writer.print(el);
					first = false;
				} else {
					writer.print(" "+ el);
				}
			}
			
			System.out.println("Done Writing");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
}