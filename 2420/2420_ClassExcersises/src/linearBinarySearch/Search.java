package linearBinarySearch;
/**
 * Class search provides two methods that allow us to search according 
 * to the linear search algorithm and binary search binary algorithms.
 * Class for teaching us how to use binary and linear searching.
 * @author Carlos Martinez
 */
public class Search {
	/**
	 * Searches the key in the array in linear search algorithms
	 * @param array an array of number
	 * @param key the element that is being search
	 * @return the index of the array element that includes the first 
	 * occurrence of the key or -1 if the key is not found
	 */
	public static int linear(int[] array, int key){
		for(int i = 0; i < array.length; i++){
			if(array[i] == key){
				return i;
			}
		}
		return -1;
		
		//int checker = -1;
		//int index = 0;
		//for( int el: array){
		//	if (el == key){
		//		checker = index;
		//	}
		//	index++;
		//}
		//return checker;
	}
	
	/**
	 * Searches the key in the array in binary search algorithms.
	 * The array has to be sorted in binary search.
	 * @param array an array of number
	 * @param key the element that is being search
	 * @return the index of the array element that includes the key
	 * or -1 if the key is not found
	 */
	public static int binary(int[] array, int key){
		return 0; // TODO
	}

}
