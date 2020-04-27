/************************************************************
 * Author: Carlos Martinez
 * Date: March 1, 2017
 * Assignment: AutoComplete
 * Acknowledgement: Robert Sedgewick, Kevin Wayne
 **********************************************************/
package autocompleteMe;

import java.util.Arrays;

import edu.princeton.cs.algs4.Merge;

/**
 * This is a method that has two methods, one that returns an array of items that match the value
 * and a method that that returns the number of items that match that term
 * @author Carlos Martinez
 */
public class Autocomplete {
	private Term[] terms;
	
	/**
	 * Constructor that sorts the array that you will search through with binary search
	 * @param terms
	 */
	public Autocomplete(Term[] terms) {
		Merge.sort(terms);
		this.terms = terms;
	}
	
	/**
	 * This method return a array that match the the specified value
	 * @param prefix the specified value to search for
	 * @return an array with values that match the specified value
	 */
	public Term[] allMatches(String prefix) {
		Term prefix1 = new Term(prefix,0);
		int firstIndex = BinarySearchDeluxe.firstIndexOf(terms, prefix1, Term.byPrefixOrder(prefix.length()));
		int lastIndex = BinarySearchDeluxe.lastIndexOf(terms, prefix1, Term.byPrefixOrder(prefix.length()));
		int capacity = lastIndex -firstIndex + 1;
		int index = 0;
				
		Term[] smallTerms = new Term[capacity];
		
		System.out.println(firstIndex + " " + lastIndex);
		
		for (int i = firstIndex; i <= lastIndex; i++) {
			smallTerms[index ++] = terms[i];
		}
		
		Arrays.sort(smallTerms,Term.byReverseWeightOrder());

		return smallTerms;
	}
	
	/**
	 * This method returns a the number of items that match a specific value
	 * @param prefix a specific value to search for
	 * @return an number for the number of values that match a specific value
	 */
	public int numberofMatches(String prefix) {
		Term prefix1 = new Term(prefix,0);
		int capacity = BinarySearchDeluxe.lastIndexOf(terms, prefix1, Term.byPrefixOrder(prefix.length())) 
				- BinarySearchDeluxe.firstIndexOf(terms, prefix1, Term.byPrefixOrder(prefix.length())) + 1;
		return capacity;
	}

}