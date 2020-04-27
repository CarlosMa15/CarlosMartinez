package number_theory;

public class Test {

	public static void main(String[] args) {
		int counter = 0;
		for(int i = 0; i < 561;i++) {
			if(i % 17 != 0 && i % 11 != 0 && i % 3 != 0) {
				counter += 1;
			}
		}
		System.out.println(counter);
	}
}