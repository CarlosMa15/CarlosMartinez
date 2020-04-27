package underTheRainbow;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UnderTheRainbow {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		List<Hotel> milesList = new ArrayList<Hotel>();
		int numberOfLines = Integer.parseInt(input.nextLine());
		
		// Read Each Line
		for(int i = 0;i <= numberOfLines;i++) {
			String newLine = input.nextLine();
			milesList.add(new Hotel(Integer.parseInt(newLine)));
		}
		
		// Calculate the answers
		milesList.get(0).total = 0;
		for (int i = 0; i < milesList.size(); i++) {
			updateList(i, milesList);
		}
		System.out.println(milesList.get(milesList.size() - 1).total);

	}

	/**
	 * This method updates the hotel with the correct total
	 * @param i the index of the previous hotel in the list
	 * @param list the list with the answers
	 */
	public static void updateList(int i, List<Hotel> list) {
		int current = i + 1;
		while (current < list.size() && (list.get(current).miles - list.get(i).miles) < 800) {
			int temp = (int) ((int) (list.get(i).total)
					+ Math.pow(Math.abs(400 - (list.get(current).miles - list.get(i).miles)), 2));

			if (temp < list.get(current).total) {
				list.get(current).total = temp;
			}
			current++;
		}
	}

	/**
	 * This class creates an object of a Hotel.
	 * @author Carlos Martinez
	 */
	static class Hotel {
		/**
		 * Cost to get to the hotel
		 */
		int miles;
		
		/**
		 * total cost to stop at hotel
		 */
		int total;

		public Hotel(int miles) {
			this.miles = miles;
			this.total = Integer.MAX_VALUE;
		}
	}
}