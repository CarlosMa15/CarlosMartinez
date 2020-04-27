package genericMethods;

public class ExampleSmallest {

	public static void main(String[] args) {
		float result = smallest( 4.5f, 5.6f, 2.3f);
		System.out.println("Smallest: " + result);
		
		long result2 = smallest( 12803, 907609, 858658);
		System.out.println("Smallest: " + result2);
	}
	
	private static <T extends Comparable<T>> T smallest(T x, T y, T z) {
		
		T result = x;
		
		if(y.compareTo(result) < 0) {
			result = y;
		}
		
		if(z.compareTo(result) < 0) {
			result = z;
		}
		
		return result;
	}
}