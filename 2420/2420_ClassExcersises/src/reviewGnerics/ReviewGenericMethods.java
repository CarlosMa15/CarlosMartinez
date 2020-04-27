package reviewGnerics;

public class ReviewGenericMethods {

	public static void main(String[] args) {
		Integer[] numbers = {2,4,2,6,2,8,2,4,1};
		Integer n = 2;
        System.out.println(countOccurrences(numbers,n));
        
        String[] words = {"cat","dog","the","and","cat","and", "ant"};
		String w = "and";
        System.out.print(countOccurrences(words,w));
	}
	
	private static <T> int countOccurrences(T[] words, T key) {
		//this integer keeps track of the new of keys in the array list
				int counter = 0;
				
				for(T el : words){
					if (el == key){
						counter++;
					}
				}
				
				return counter;
	}
}
