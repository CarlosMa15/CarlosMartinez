package RTTEstimateTimeoutComputation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class RTTEstimateTimeoutComputation {

	public static void main(String[] args) {
		File file = new File("/Users/carlosmartinez/Desktop/Java/RTTEstimateTimeoutComputation/src/data.txt");
		double[] numbers = new double[101];
		
		try {
			@SuppressWarnings("resource")
			BufferedReader bufferReader = new BufferedReader(new FileReader(file));

			int count = 0;
			while (bufferReader.ready()) {
				String data = bufferReader.readLine();
				String[] parts = data.split(" ");
				String[] time = parts[6].split("=");
				numbers[count++] = Double.parseDouble(time[1]);
			}

		} catch (FileNotFoundException e) {
			System.err.println("File Not Found Exception!");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("IOException!");
			e.printStackTrace();
		}
		
		System.out.println("NUMBERS");
		int count = 0;
		for(double el: numbers) {
			System.out.println("(" + count++ + "," + el + ")");
		}
		
		double [] estimatedRTT = new double[101];
		estimatedRTT[0] = numbers[0];
		
		double[] timeOutInterval = new double[101];
		timeOutInterval[0] = 0;
		
		double[] devRTT = new double[101];
		devRTT[0] = 0;
		
		for(int i = 1; i <= 100; i++) {
			double dif = 1 - 0.125;
			double mult1 = dif * estimatedRTT[i -1];
			double mult2 = 0.125 * numbers[i];
			double answer = mult1 + mult2;
			estimatedRTT[i] = answer;
			
			double dif1 = 1 - .25;
			double mult3 = dif1 * devRTT[i - 1];
			double a = numbers[i] - estimatedRTT[i];
			double abs = Math.abs(a);
			double mult4 = .25 * abs;
			double dev = mult3 + mult4;
			devRTT[i] = dev;
			
			double mult5 = 4 * dev;
			double mult6 = mult5 + estimatedRTT[i];
			timeOutInterval[i] = mult6;
			
		}
		
		System.out.println("ESTIMATED RTT");
		count = 0;
		for(double el : estimatedRTT) {
			System.out.println("(" + count++ + "," + el + ")");
		}
		
		System.out.println("TIMEOUTINTERVAL");
		count = 0;
		for(double el : timeOutInterval) {
			System.out.println("(" + count++ + "," + el + ")");
		}
	}
}