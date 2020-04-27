package part2;

import java.util.ArrayList;

public class Exercise2 {

	public static void main(String[] args) {
		//Create Array List
		ArrayList<String> departments = new ArrayList<>();
		departments.add("english");
		departments.add("math");
		departments.add("philosophy");
		departments.add("computer science");
		//departments.add("engineering");

		//Builds the StringBuilder depending on the size of the ArrayList
		StringBuilder sb = new StringBuilder();
		
		sb.append("Representatives from the ");
		
		int size = departments.size();
		
		//Checks how many departments were present
		if(size > 2){
			for (int i = 0; i < size - 1; i++){
				sb.append(departments.get(i));
				sb.append(" department, ");
			}
			sb.append(" and ");
			sb.append(departments.get(size -1));
			sb.append(" departmment");
		}
		
		else if (size == 2) {
			sb.append(departments.get(size -2));
			sb.append(" department, and ");
			sb.append(departments.get(size-1));
			sb.append(" department");
		}
		
		
		else if(size == 1) {
			sb.append(departments.get(size -1));
			sb.append(" department");
		}
		else {
			//ArrayList is empty
			sb.append("no departments");
		}
		
		
		sb.append(" were present.");
		
		System.out.println(sb);
	}
}