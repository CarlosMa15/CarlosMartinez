package labExceptionHandling;

import java.util.InputMismatchException;
import java.util.Scanner;

public class LabExceptionHandilng {
	public static void main(String[] args) {
		try {
			int digit = numberFromUser();
			int result = sevenModulusN(digit);
			System.out.printf("7 %% %d = %d", digit, result);
		} catch (Exception e) {
			System.out.println("A problem occured: " + e.getMessage());
		}
	}

	private static int numberFromUser() {
		Scanner input = new Scanner(System.in);
		int number;

		while (true) {
			try {
				System.out.print("number: ");
				number = input.nextInt();
				return number;
			} catch (InputMismatchException e) {
				System.out.println("Input Number needs to be a whole number:");
				input.next();
			}
		}
	}

	private static int sevenModulusN(int number) {
		if (number == 0) {
			throw new IllegalArgumentException("Can Not Calculate 7 % 0");
		}
		return 7 % number;
	}
}