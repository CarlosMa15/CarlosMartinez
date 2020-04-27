/*************************************
 * Author: Carlos Martinez
 * Date: March 9, 2017
 * Assignment: ListVsSet
 ************************************/
package listVsSet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * This demonstrates how sets and List Work
 * @author Carlos Martinez
 *
 */
public class ListVsSetDemo {
	/**
	 * This a list of ColoredSquares
	 */
	private List<ColoredSquare> list;
	
	/**
	 * This is a set of ColoredSquares
	 */
	private Set<ColoredSquare> set;
	
	/**
	 * This constructor creates a list and set out of coloredSquares
	 * @param elements an array or bunch of coloredSquares to create
	 * a set and list out of the ColoredSquares
	 */
	public ListVsSetDemo (ColoredSquare... elements) {
		list = new ArrayList<ColoredSquare>();
		set = new HashSet<ColoredSquare>();
		
		for (ColoredSquare el : elements) {
			list.add(el);
			set.add(el);
		}
	}
	
	/**
	 * This method creates and returns a string out of the list
	 * @return a String(Text) representation of the List
	 */
	public String getListElements() {
		StringBuilder sb = new StringBuilder();
		sb.setLength(0);
		
		for(int i = 0; i < list.size(); i++) {
			sb.append(list.get(i) + "\n");
		}
		
		return sb.toString();
	}
	
	/**
	 * This method creates and returns a string out of the list
	 * @return a String(Text) representation of the set
	 */
	public String getSetElements() {
		
		StringBuilder sb = new StringBuilder();
		sb.setLength(0);
		
		for (ColoredSquare el: set) {
			sb.append(el + "\n");
		}
		
		return sb.toString();
	}
	
	/**
	 * This method adds the element to both list and set
	 * @param el the element that is going to be added
	 * to both list and set
	 */
	public void addElement(ColoredSquare el) {
		list.add(el);
		set.add(el);
	}
	
}