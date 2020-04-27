package autocompleteMe;

import java.util.Arrays;

public class tests {

	public static void main(String[] args) {
		
		Term item = new Term("Carl",6);
		Term item1 = new Term("Carlo",10);
		Term item2 = new Term("Carlos",6);
		Term item3 = new Term("Martinez",10);
		Term item4 = new Term("Guadarrama",12);
		Term item5 = new Term("Humberto",8);
		Term item6 = new Term("Carlos",12);
		Term item7 = new Term("Car",8);
		Term item8 = new Term("Martinez",11);
		
		Term[] terms = {item2, item1, item, item3, item4
				, item5, item6, item7, item8};
		
		// TEST FOR BINARY SEARCH DELUXE START {
//		Term key = new Term("Car",0);
//		
//		Arrays.sort(terms,Term.byPrefixOrder(3));
//		
//		System.out.println(Arrays.asList(terms));
//		
//		Term actualTerm = new Term("Martinez",0);
//		int actual = BinarySearchDeluxe.firstIndexOf(terms, actualTerm, Term.byPrefixOrder(3));
//		
//		Term actualTerm1 = new Term("Carlos",0);
//		int actual1 = BinarySearchDeluxe.lastIndexOf(terms, actualTerm1, Term.byPrefixOrder(3));
//		
//		int actual2 = BinarySearchDeluxe.lastIndexOf(terms, actualTerm, Term.byPrefixOrder(3));
//		
//		int actual3 = BinarySearchDeluxe.firstIndexOf(terms, actualTerm1, Term.byPrefixOrder(3));
//		
//		Arrays.sort(terms, Term.byPrefixOrder(4));
//		
//		
//		int actual4 = BinarySearchDeluxe.firstIndexOf(terms, actualTerm1, Term.byPrefixOrder(4));
//		
//		int actual5 = BinarySearchDeluxe.lastIndexOf(terms, actualTerm1, Term.byPrefixOrder(4));
//		
//		System.out.println();
//		System.out.println("With 3 PrefixOrder");
//		System.out.println("First Index of Martinez: " + actual + " Correct");
//		System.out.println("Last Index of Martinez: " + actual2 + " Correct");
//		System.out.println("First Index of Car: " + actual3 + " Correct");
//		System.out.println("Last Index of Car: " + actual1 + " Correct");
//		System.out.println();
//		
//		System.out.println(Arrays.asList(terms));
//		System.out.println();
//		System.out.println("With 4 PrefixOrder");
//		System.out.println("First Index of Carlos: " + actual4 + " Correct");
//		System.out.println("Last Index of Carlos: " + actual5 + " Correct");
		// TEST FOR BINARY SEARCH DELUXE END }
		
		// TEST FOR AUTO COMPLETE START {
		Autocomplete auto = new Autocomplete(terms);
		Term[] smallterms = auto.allMatches("Car");
		System.out.println(Arrays.asList(smallterms));
		System.out.println("Number of Items that start with Car: " +auto.numberofMatches("Car"));
		System.out.println();
		
		Term[] smallterms1 = auto.allMatches("Carlos");
		System.out.println(Arrays.asList(smallterms1));
		System.out.println("Number of Items that start with Carlos: " +auto.numberofMatches("Carlos"));
		System.out.println();
		
		Term[] smallterms2 = auto.allMatches("Mart");
		System.out.println(Arrays.asList(smallterms2));
		System.out.println("Number of Items that start with Mart: " +auto.numberofMatches("Mart"));
		// TEST FOR AUTO COMPLAETE END }
		
	}
}