/**********************************************
 * Author: Carlos Martinez
 * Date: Jan 28, 2017
 * Assignment: Inheritance
 *********************************************/
package Itinerary;

import java.util.ArrayList;
import java.util.Scanner;

public class LabItinary {

	public static void main(String [] args) {
		StringBuilder sb = new StringBuilder();
		Scanner input = new Scanner(System.in);
		String destination = null;
		ArrayList<String> locations = new ArrayList<>();
		
		do{
			System.out.print("Destination: ");
			destination = input.nextLine();
			locations.add(destination);
		}while(!destination.equalsIgnoreCase("done"));
		
		System.out.println();
		System.out.println("travel route:");
		
		for(String el: locations){
			if (!el.equalsIgnoreCase("done")){
				sb.append(el.toUpperCase());
				sb.append(" to ");
			}
		}
		sb.delete(sb.length()-4, sb.length());
		System.out.println(sb);
	}
}
