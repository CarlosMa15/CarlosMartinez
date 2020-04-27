package autocompleteMe;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class BinarySearchDeluxeTest {

	@Test
	public void testFirstIndexOf() {
		Term item = new Term("Carl",6);
		Term item1 = new Term("Carlo",10);
		Term item2 = new Term("Carlos",6);
		Term item3 = new Term("Martinez",10);
		Term item4 = new Term("Guadarrama",12);
		Term item5 = new Term("Humberto",8);
		Term item6 = new Term("Carlos",12);
		Term item7 = new Term("Car",8);
		Term item8 = new Term("Martinez",10);
		
		Term[] terms = {item2, item1, item, item3, item4
				, item5, item6, item7, item8};
		Arrays.sort(terms, Term.byPrefixOrder(3));
		
		Term actualTerm = new Term("Martinez",0);
		int actual = BinarySearchDeluxe.firstIndexOf(terms, actualTerm, Term.byPrefixOrder(3));
		int expected = 7;
		assertEquals(expected, actual);

	}
	
	@Test
	public void testLastIndexOf() {
		Term item = new Term("Carl",6);
		Term item1 = new Term("Carlo",10);
		Term item2 = new Term("Carlos",6);
		Term item3 = new Term("Martinez",10);
		Term item4 = new Term("Guadarrama",12);
		Term item5 = new Term("Humberto",8);
		Term item6 = new Term("Carlos",12);
		Term item7 = new Term("Car",8);
		Term item8 = new Term("Martinez",10);
		
		Term[] terms = {item2, item1, item, item3, item4
				, item5, item6, item7, item8};
		Arrays.sort(terms, Term.byPrefixOrder(3));
		
		Term actualTerm = new Term("Car",0);
		int actual = BinarySearchDeluxe.lastIndexOf(terms, actualTerm, Term.byPrefixOrder(3));
		int expected = 4;
		assertEquals(expected, actual);

	}
	
	@Test
	public void testLastIndexOf1() {
		Term item = new Term("Carl",6);
		Term item1 = new Term("Carlo",10);
		Term item2 = new Term("Carlos",6);
		Term item3 = new Term("Martinez",10);
		Term item4 = new Term("Guadarrama",12);
		Term item5 = new Term("Humberto",8);
		Term item6 = new Term("Carlos",12);
		Term item7 = new Term("Car",8);
		Term item8 = new Term("Martinez",10);
		
		Term[] terms = {item2, item1, item, item3, item4
				, item5, item6, item7, item8};
		Arrays.sort(terms, Term.byPrefixOrder(3));
		
		Term actualTerm = new Term("Martinez",0);
		int actual = BinarySearchDeluxe.lastIndexOf(terms, actualTerm, Term.byPrefixOrder(3));
		int expected = 8;
		assertEquals(expected, actual);

	}
	
	@Test
	public void testFirstIndexOf1() {
		Term item = new Term("Carl",6);
		Term item1 = new Term("Carlo",10);
		Term item2 = new Term("Carlos",6);
		Term item3 = new Term("Martinez",10);
		Term item4 = new Term("Guadarrama",12);
		Term item5 = new Term("Humberto",8);
		Term item6 = new Term("Carlos",12);
		Term item7 = new Term("Car",8);
		Term item8 = new Term("Martinez",10);
		
		Term[] terms = {item2, item1, item, item3, item4
				, item5, item6, item7, item8};
		Arrays.sort(terms, Term.byPrefixOrder(3));
		
		Term actualTerm = new Term("Car",0);
		int actual = BinarySearchDeluxe.firstIndexOf(terms, actualTerm, Term.byPrefixOrder(3));
		int expected = 0;
		assertEquals(expected, actual);

	}
	
}