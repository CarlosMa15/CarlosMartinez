/*************************************************************
 * Author: Carlos Martinez
 * Date: August 26, 2018
 * Assignment: Mr. Anaga
 ************************************************************/

package mrAnaga;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
//import java.util.Scanner;

public class mrAnaga {

	public static void main(String[] args) {
		//object
		//Scanner input = new Scanner(System.in);
		Random random = new Random();
		HashSet<String> solution = new HashSet<String>();
		HashSet<String> rejected = new HashSet<String>();

		//Get n and k from the first input
		String line;
		int n = 2000;
		int k = 250;

		long start = System.currentTimeMillis();
		//Iterate and processing through the n inputs
		for (int i = 0; i < n; i++) {
			//Getting input word
			//line = input.nextLine();
			
			line = "";
			for(int j = 0;j < k;j++) {
				line += (char) (random.nextInt(26) + 'a');
			}

			//String to array
			char tempArray[] = line.toCharArray();

			//Sorting the word array
			Arrays.sort(tempArray);

			//Array to string
			line = new String(tempArray);

			//Calculating the answer
			if (solution.contains(line)) {
				solution.remove(line);
				rejected.add(line);
			} else if (!rejected.contains(line)) {
				solution.add(line);
			}
		}
		long end = System.currentTimeMillis();

		//Displaying the solution
		System.out.println(solution.size());
		System.out.println(end - start);
	}
}