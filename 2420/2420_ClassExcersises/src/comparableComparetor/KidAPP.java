package comparableComparetor;

import java.util.Arrays;

public class KidAPP {

	public static void main(String[] args) {
		Kid[] kids = { new Kid("Bob",8), new Kid("Sam",2),new Kid("ann",4),
				new Kid("Jen",5),new Kid("Art",4), new Kid("Jen", 5),new Kid("joe",6)};
		
		Arrays.sort(kids);
		
		for(Kid el: kids){
			System.out.print(el.getName() + " ");
		}
		System.out.println();
		
		String[] words = {"ape","Bat","cat","Cow","the","Zoe"};
		System.out.println(Arrays.toString(words));
		Arrays.sort(words);
		System.out.println(Arrays.toString(words));
		Arrays.sort(words,String.CASE_INSENSITIVE_ORDER);
		System.out.println(Arrays.toString(words));
	}
}
