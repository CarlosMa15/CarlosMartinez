/*******************************************
 * Author: Carlos Martinez
 * Date: March 21, 2017
 * Assignment: Midterm 2
 ******************************************/
package midterm2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class demonstrates how to use Collections with an Arraylist
 * @author Carlos Martinez
 */
public class ExerciseCollection {

	public static void main(String[] args) {
		
		List<Double> numbers = new ArrayList<>();
		Collections.addAll(numbers, .2, .4, .6, .8, .25, .5, .75);
		System.out.println("numbers: " + numbers);
		
		Double smallest = Collections.min(numbers);
		System.out.println("smallest: " + smallest);
		
		Collections.reverse(numbers);
		System.out.println("numbers: " + numbers);
		
		Collections.swap(numbers, 0, numbers.size()-1);
		System.out.println("numbers: " + numbers);
	}
}