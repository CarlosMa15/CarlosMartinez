package genericMethods;

import java.util.Arrays;

public class ExcersiceGenericMethods {
	
	public static void main(String[] args) {
		Integer[] array = {1, 2, 3, 4, 5};
		Integer[] reverseArray = reverse(array);
		
		System.out.println("Array: " + Arrays.toString(array));
		System.out.println("ReverseArray: " + Arrays.toString(reverseArray));
		
	}
	
	public static <T> T[] reverse(T[] array) {
		T[] arrayReturn = array.clone();
		
		for(int i = 0; i < array.length / 2; i++) {
			T temp = arrayReturn[i];
			arrayReturn[i] = arrayReturn[array.length - i -1];
			arrayReturn[array.length - i -1] = temp;
		}
		
		return arrayReturn;
	}
	
	public static <T> T[] reverse1(T[] array) {
		T[] arrayReturn = array.clone();
		
		for (int i = 0, j = array.length -1;i < array.length; i++, j--) {
			arrayReturn[i] = array[j];
		}
		
		return arrayReturn;
	}
	
}