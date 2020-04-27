package sandBox;

import java.util.Random;

public class SandBox {

	public static void main(String[] args) {
		Random rand = new Random();
		for(int i = 0; i < 25; i++) {
			System.out.println(rand.nextInt(3));
		}
	}
}