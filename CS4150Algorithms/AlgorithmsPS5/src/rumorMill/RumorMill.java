/*************************************************************
 * Author: Carlos Martinez
 * Date: September 21, 2018
 * Assignment: Rumor Mill
 ************************************************************/
package rumorMill;

import java.util.ArrayList;
//Import Statements
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * This class just keeps track of the dependees and dependents
 * of a collection of data. This version of the dependency Graph
 * is highly inspired by the dependency graph that was coded in
 * CS3500 for the spreadsheet assignment. The unnecessary functions
 * were removed from the program. 
 * @author Carlos Martinez
 */
class DependencyGraph {
	/**
	 * This keeps track of the objects dependents
	 */
	public HashMap<String, HashSet<String>> dependents;
	
	/**
	 * This keeps track of the objects dependees
	 */
	public HashMap<String, HashSet<String>> dependees;

	/**
	 * Creates the dependency graph object
	 */
	DependencyGraph() {
		this.dependents = new HashMap<String, HashSet<String>>();
		this.dependees = new HashMap<String, HashSet<String>>();
	}

	/**
	 * This checks if object has dependents.
	 * @param s The string who we want to know if they have dependents
	 * @return true if have dependents, false otherwise.
	 */
	public boolean hasDependents(String s) {
		HashSet<String> temp;
		if (this.dependents.containsKey(s)) {
			temp = this.dependents.get(s);
			if (temp.size() == 0)
				return false;
			else
				return true;
		}
		return false;
	}

	/**
	 * This checks if the object has dependees
	 * @param s The string who we want to know if they have dependees
	 * @return true if have dependees, false otherwise
	 */
	public boolean hasDependee(String s) {
		HashSet<String> temp;
		if (this.dependees.containsKey(s)) {
			temp = this.dependees.get(s);
			if (temp.size() == 0)
				return false;
			else
				return true;
		}
		return false;
	}

	/**
	 * This adds a dependency to the graph
	 * @param s the dependee
	 * @param t the dependent
	 */
	public void AddDependency(String s, String t) {
		HashSet<String> temp;
		if (this.dependents.containsKey(s)) {
			temp = this.dependents.get(s);
			if (!temp.contains(t)) {
				temp.add(t);
				this.dependents.put(s, temp);
				if (!dependees.containsKey(t)) {
					temp = new HashSet<String>();
					temp.add(s);
					this.dependees.put(t, temp);
				} else {
					temp = dependees.get(t);
					temp.add(s);
					this.dependees.put(t, temp);
				}
			}
		} else {
			temp = new HashSet<String>();
			temp.add(t);
			this.dependents.put(s, temp);
			if (!this.dependees.containsKey(t)) {
				temp = new HashSet<String>();
				temp.add(s);
				this.dependees.put(t, temp);
			} else {
				temp = this.dependees.get(t);
				temp.add(s);
				this.dependees.put(t, temp);
			}
		}
	}

	/**
	 * returns a HashSet with the dependents of the object
	 * @param s the object who you want the dependents
	 * @return a HashSet with the dependents of the object
	 */
	public HashSet<String> getDependents(String s) {
		if (this.dependents.containsKey(s)) {
			return this.dependents.get(s);
		}
		return new HashSet<String>();
	}

	/**
	 * returns a HashSet of the dependees of the object
	 * @param s the object who you want the dependees
	 * @return a HashSet of the dependees of the object
	 */
	public HashSet<String> getDependee(String s) {
		if (this.dependees.containsKey(s)) {
			return this.dependees.get(s);
		}
		return new HashSet<String>();
	}

	/**
	 * returns a Hashset of the all the dependents and
	 * dependees of the object
	 * @param s the object who you want a dependents and dependees
	 * @returna Hashset of the all the dependents and
	 * dependees of the object
	 */
	public HashSet<String> GetChildren(String s) {
		HashSet<String> set = new HashSet<String>();
		set.addAll(getDependee(s));
		set.addAll(getDependents(s));
		return set;
	}

}


public class RumorMill {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		//Objects
		ArrayList<String> childrenList = new ArrayList<String>();
		ArrayList<String> rumorStarterList = new ArrayList<String>();
		Scanner input = new Scanner(System.in);
		DependencyGraph dependencyGraph = new DependencyGraph();
		
		//Gets the number and name of the children
		int studentNumber = Integer.parseInt(input.nextLine());
		ArrayList<String> childrenNameList = new ArrayList<String>();
		for (int i = 0; i < studentNumber; i++) {
			childrenNameList.add(input.nextLine());
		}
		
		//Gets the dependencies of the graph
		int friendPairs = Integer.parseInt(input.nextLine());
		for (int i = 0; i < friendPairs; i++) {
			String[] pairs = input.nextLine().split(" ");
			String s = pairs[0];
			String t = pairs[1];
			dependencyGraph.AddDependency(s, t);
		}
	
		//Get the rumor Starters
		int starters = Integer.parseInt(input.nextLine());
		for (int i = 0; i < starters; i++) {
		
			String starter = input.nextLine();
			rumorStarterList.add(starter);
		}
		
		//Creates report, starting from the person who starts the rumor
		for(String starter : rumorStarterList){
			PriorityQueue<String> thisQueue = new PriorityQueue<String>();
			
			//Gets the results for print
			ArrayList<String> resultsForPrint = new ArrayList<String>();
			
			//Keeps tracks of the students that exist, after functionality
			//Only the losers should be left in the list
			HashSet<String> copyStudent = new HashSet<String>(childrenNameList);
			
			
			thisQueue.add(starter);
			while (!thisQueue.isEmpty()) {
				
				//Starting child, gets most of the results except the losers
				childrenList.clear();
				while (!thisQueue.isEmpty()) {
					String temp = thisQueue.poll();
					resultsForPrint.add(temp);
					copyStudent.remove(temp);
					childrenList.add(temp);
				}
				
				//Gets the friends
				for (String s : childrenList) {
					HashSet<String> set = dependencyGraph.GetChildren(s);
					if (set.size() != 0) {
						for (String s1 : set) {
							if (copyStudent.contains(s1) & !thisQueue.contains(s1))
								thisQueue.add(s1);
						}

					}
				}
			}
			thisQueue.clear();
			
			//Get the losers that have no friends
			for(String name :copyStudent){
				thisQueue.add(name);
			}
			
			//gets result for print
			while(!thisQueue.isEmpty()){
			resultsForPrint.add(thisQueue.poll());	
			}
			
			//Prints Results
			for (int j = 0; j < resultsForPrint.size(); j++) {
				if(j==resultsForPrint.size()-1){
					System.out.print(resultsForPrint.get(j));
				}
				else
				System.out.print(resultsForPrint.get(j) + " ");
			}
			
			System.out.print('\n');
		}
	}
}