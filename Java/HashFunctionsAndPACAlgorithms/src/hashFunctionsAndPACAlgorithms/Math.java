package hashFunctionsAndPACAlgorithms;

public class Math {

	public static void main(String[] args) {

		for (int k = 2; k < 100; k++) {
			int n = 5000;
			double top1 = n - 1;
			double mult = top1 / n;
			for (int i = 2; i < k; i++) {
				double top = n - i;
				double fraction = top / n;
				mult = mult * fraction;
			}
			double answer = 1 - mult;
			System.out.println("Answer " + (k+1) + ": " + answer);
		}
	}
}