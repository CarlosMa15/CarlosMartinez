/*************************************************************
 * Author: Carlos Martinez
 * Date: September 1, 2018
 * Assignment: Ceiling Function
 ************************************************************/

package ceilingFunction;

import java.util.HashSet;
import java.util.Scanner;

public class CeilingFunction {

	public static void main(String[] args) {
		//Objects
		Scanner input = new Scanner(System.in);
		String info = input.nextLine();
		String parts[] = info.split(" ");
		int n = Integer.parseInt(parts[0]);
		int k = Integer.parseInt(parts[1]);
		HashSet<String> solution = new HashSet<String>();
		int value;
		String answer;
		
		for(int i = 0;i < n;i++) {//Iterating through the binary tree
			answer = "";
			binarySearchTree bst = new binarySearchTree();
			for(int j= 0;j < k;j++) {//Iterating through the binary tree input
				value = input.nextInt();
				bst.add(value);
			}
			
			//Getting the solution from the binary tree created
			answer = bst.toString();
			solution.add(answer);
		}
		System.out.println(solution.size());
	}
}