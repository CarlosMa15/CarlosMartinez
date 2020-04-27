/************************************************************
 * Author: Carlos Martinez
 * Date: March 1, 2017
 * Assignment: AutoComplete
 * Acknowledgement: Robert Sedgewick, Kevin Wayne
 **********************************************************/
package autocompleteMe;

// Import Statements
import java.util.Comparator;

/**
 * This class creates a new object of Term out of
 * any reference type, with a value of weight and
 * query
 * @author Carlos Martinez
 */
public class Term implements Comparable<Term> {
	
	/**
	 * This is the name of the Term
	 */
	private String query;
	
	/**
	 * This is the weight of the Term
	 */
	private double weight;
	
	/**
	 * This constructor creates an object of Term
	 * @param query the name of the term
	 * @param weight the weight of the term
	 */
	public Term(String query, double weight) {

		if (query == null) {
			throw new NullPointerException();
		}

		if (weight < 0) {
			throw new IllegalArgumentException();
		}

		this.query = query;
		this.weight = weight;
	}
	
	/**
	 * This is a comparator that allows you to sort items by
	 * Reverse Weight Order using the terms weight
	 * @return a comparator
	 */
	public static Comparator<Term> byReverseWeightOrder() {
		return new comparebyReverseWeightOrder();
	}
	
	/**
	 * This is a comparator that allows you to order items by
	 * a special number of Charactors
	 * @param r the number of Charactors that need to be compared
	 * @return
	 */
	public static Comparator<Term> byPrefixOrder(int r) {

		if (r < 0) {
			throw new IllegalArgumentException();
		}

		return new comparebyPrefixOrder(r);
	}
	
	/**
	 * This compares the item by the full amount of the query
	 */
	@Override
	public int compareTo(Term other) {
		return query.compareTo(other.query);
	}

	/**
	 * This is a toSrting method that prints in the form of
	 * weight query
	 */
	@Override
	public String toString() {
		return this.weight + "\t" + this.query;
	}
	
	/**
	 * Private class that is a comparator for the method byReverseWeightOrder
	 * @author carlosmartinez
	 */
	private static class comparebyReverseWeightOrder implements Comparator<Term> {
		
		/**
		 * This compares the terms by weight
		 */
		@Override
		public int compare(Term term1, Term term2) {
			if (term1.weight > term2.weight) {
				return -1;
			}
			if (term1.weight < term2.weight) {
				return 1;
			}
			return 0;
		}
	}
	
	/**
	 * Private class is a comparator for the method byPrefixOrder to use
	 * @author Carlos Martinez
	 */
	private static class comparebyPrefixOrder implements Comparator<Term> {
		
		/**
		 * The number of charactors that are going to be compared
		 */
		private int numberOfCharactors;
		
		/**
		 * this constructor inisializes the number of charactors
		 * @param numberOfCharactors
		 */
		public comparebyPrefixOrder(int numberOfCharactors) {
			this.numberOfCharactors = numberOfCharactors;
		}
		
		/**
		 * this compares the terms query by only a certain number
		 * of charactors
		 */
		@Override
		public int compare(Term term1, Term term2) {
			String one = term1.query;
			String two = term2.query;

			if (term1.query.length() >= numberOfCharactors) {
				one = one.substring(0, numberOfCharactors);
			}

			if (term2.query.length() >= numberOfCharactors) {
				two = two.substring(0, numberOfCharactors);
			}

			return one.compareTo(two);
		}
	}

}