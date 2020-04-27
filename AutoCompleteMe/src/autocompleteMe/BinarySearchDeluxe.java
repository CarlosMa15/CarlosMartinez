/************************************************************
 * Author: Carlos Martinez
 * Date: March 1, 2017
 * Assignment: AutoComplete
 * Acknowledgement: Robert Sedgewick, Kevin Wayne
 **********************************************************/
package autocompleteMe;

import java.util.Comparator;

/**
 * This class has two methods the find the index of the first item that matches the
 * desired value as well as the last item on the list.
 * @author Carlos Martinez
 */
public class BinarySearchDeluxe {
	
	/**
	 * This method finds the first the index of the first value in that matches the key
	 * @param a an array of items to search that needs to be sorted
	 * @param key a value that you use to compare the value
	 * @param comparator a comparator that tells the method how to compare the items
	 * @return
	 */
	public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
		if (a == null || key == null || comparator == null) {
			throw new java.lang.NullPointerException();
		}

		if (a.length == 0) {
			return -1;
		}

		int low = 0;
		int high = a.length - 1;
		int index = -1;
		while (low <= high) {
			int middle = low + (high - low) / 2;
			int compare = comparator.compare(key, a[middle]);

			if (compare < 0) {
				high = middle - 1;
			} else if (compare > 0) {
				low = middle + 1;
			} else {
				index = middle;
				high = middle - 1;
			}
		}


		return index;
	}
	
	/**
	 * This method finds the last value that matches the key
	 * @param a an array of sorted items to be searched
	 * @param key a value to be found in the array
	 * @param comparator a comparator that tells the method how to compare the items
	 * @return
	 */
	public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
		if (a == null || key == null || comparator == null) {
			throw new java.lang.NullPointerException();
		}

		if (a.length == 0) {
			return -1;
		}

		int low = 0;
		int high = a.length - 1;
		int index = -1;
		while (low <= high) {
			int middle = low + (high - low) / 2;
			int compare = comparator.compare(key, a[middle]);

			if (compare < 0) {
				high = middle - 1;
			} else if (compare > 0) {
				low = middle + 1;
			} else {
				index = middle;
				low = middle + 1;
			}
		}
		
		return index;
	}
}