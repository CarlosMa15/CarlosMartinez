package flinal;

import java.io.InputStreamReader;
import java.util.Date;
import java.util.Scanner;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;

public class Exercise2Prep {
	
	private static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		ST<Date,Double> st = new ST<>();
		In in = new In("src/flinal/Toyota.csv");
		
		String line = "";
		in.readLine();
		while(in.hasNextLine()) {
			line = in.readLine();
			String[] elements = line.split(",");
			String[] elements1 = elements[0].split("-");
			Date date = new Date(Integer.parseInt(elements1[0]),
					Integer.parseInt(elements1[1]),Integer.parseInt(elements1[2]));
			st.put(date, Double.parseDouble(elements[4]));
		}
		
		int selection = 0;
		
		while (selection == 0) {
			Date searchDate = new Date();
			System.out.print("Year: ");
			int year = input.nextInt();
			System.out.print("Month: ");
			int month = input.nextInt();
			System.out.print("Day: ");
			int day = input.nextInt();
			Date s = new Date(year,month,day);
			
			System.out.printf("%2d/%2d/%4d: ",s.getMonth(),s.getDate(),s.getYear());
			System.out.println(st.get(s));
			System.out.println();
			
			System.out.println("Enter any number to stop or zero to go again: ");
			selection = input.nextInt();
			System.out.println();
		}
		
//		int count = 1;
//		for (Date s : st.keys()) {
//			System.out.printf("%4d: ",count++);
//			System.out.printf("%2d/%2d/%4d: ",s.getMonth(),s.getDate(),s.getYear());
//			System.out.println(st.get(s));
//		}
	}
}