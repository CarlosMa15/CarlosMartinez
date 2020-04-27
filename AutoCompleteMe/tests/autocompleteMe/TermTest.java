package autocompleteMe;

import static org.junit.Assert.*;

import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;

public class TermTest {
	
	private Term[] terms;
	private final Term[] originalTerms = {new Term("their", 2820265.0), //"3340398.0\tthey"
		new Term("they", 3340398.0), new Term("there", 1961200.0),
		new Term("them", 2509917.0), new Term("thereby", 2446500.0)};
	
	@Before
	public void setUp() throws Exception {
		terms = Arrays.copyOf(originalTerms, originalTerms.length);				
	}
	// Wrong
	@Test(expected = NullPointerException.class)
	public void testTerm_nullQuery() {
		new Term(null, 1);
	}
	// Passed
	@Test(expected = IllegalArgumentException .class)
	public void testTerm_weightNegative() {
		new Term("negative", -1);
	}
	
	@Test // Wrong
	public void testTerm() {
		Term automobile = new Term("automobile", 6197.0);
		assertEquals("6197.0\tautomobile", automobile.toString());
	}
		
	@Test // Wrong
	public void testByReverseOrder() {
		String termsReverseWeightOrder = "[3340398.0\tthey, 2820265.0\ttheir, " +
				"2509917.0\tthem, 2446500.0\tthereby, 1961200.0\tthere]";
		Arrays.sort(terms, Term.byReverseWeightOrder());
 
	    assertEquals(termsReverseWeightOrder, Arrays.toString(terms));
	}
	
	//Passed
	@Test(expected = IllegalArgumentException .class)
	public void testByPrefixOrder_negativeR() {
		Term.byPrefixOrder(-1);
	}
	
	@Test // Wrong
	public void testByPrefixOrder() {
		String termsPrefixOrder5 = "[2820265.0\ttheir, 2509917.0\tthem, " +
				"1961200.0\tthere, 2446500.0\tthereby, 3340398.0\tthey]";
		Arrays.sort(terms, Term.byPrefixOrder(5));
 
	    assertEquals(termsPrefixOrder5, Arrays.toString(terms));
	}

	@Test // Wrong
	public void testCompareTo() {
		String termsNaturalOrder = "[2820265.0\ttheir, " +
			"2509917.0\tthem, 1961200.0\tthere, 2446500.0\tthereby, 3340398.0\tthey]";
		Arrays.sort(terms);
 
	    assertEquals(termsNaturalOrder, Arrays.toString(terms));
	}

	@Test //Wrong
	public void testToString() {
		assertEquals("3340398.0\tthey", terms[1].toString());
	}

}


//import static org.junit.Assert.*;
//
//import java.util.Arrays;
//import java.util.Comparator;
//import static org.junit.Assert.*; import java.util.Arrays;
//import org.junit.Before; import org.junit.Test;
//import org.junit.Test;
//
//public class TermTest {
//	private Term[] terms;
//	private final Term[] originalTerms = {new Term("their", 2820265.0),
//		new Term("they", 3340398.0), new Term("there", 1961200.0),
//		new Term("them", 2509917.0), new Term("thereby", 2446500.0)};
//	
//	@Before
//	public void setUp() throws Exception {
//		terms = Arrays.copyOf(originalTerms, originalTerms.length);				
//	}
//
//	@Test(expected = NullPointerException.class)
//	public void testTerm_nullQuery() {
//		new Term(null, 1);
//	}
//
//	@Test(expected = IllegalArgumentException .class)
//	public void testTerm_weightNegative() {
//		new Term("negative", -1);
//	}
//	
//	@Test
//	public void testTerm() {
//		Term automobile = new Term("automobile", 6197.0);
//		assertEquals("6197.0\tautomobile", automobile.toString());
//	}
//		
//	@Test
//	public void testByReverseOrder() {
//		String termsReverseWeightOrder = "[3340398.0\tthey, 2820265.0\ttheir, " +
//				"2509917.0\tthem, 2446500.0\tthereby, 1961200.0\tthere]";
//		Arrays.sort(terms, Term.byReverseWeightOrder());
// 
//	    assertEquals(termsReverseWeightOrder, Arrays.toString(terms));
//	}
//
//	@Test(expected = IllegalArgumentException .class)
//	public void testByPrefixOrder_negativeR() {
//		Term.byPrefixOrder(-1);
//	}
//	
//	@Test
//	public void testByPrefixOrder() {
//		String termsPrefixOrder5 = "[2820265.0\ttheir, 2509917.0\tthem, " +
//				"1961200.0\tthere, 2446500.0\tthereby, 3340398.0\tthey]";
//		Arrays.sort(terms, Term.byPrefixOrder(5));
// 
//	    assertEquals(termsPrefixOrder5, Arrays.toString(terms));
//	}
//
//	@Test
//	public void testCompareTo() {
//		String termsNaturalOrder = "[2820265.0\ttheir, " +
//			"2509917.0\tthem, 1961200.0\tthere, 2446500.0\tthereby, 3340398.0\tthey]";
//		Arrays.sort(terms);
// 
//	    assertEquals(termsNaturalOrder, Arrays.toString(terms));
//	}
//
//	@Test
//	public void testToString() {
//		assertEquals("3340398.0\tthey", terms[1].toString());
//	}
//
//
//	@Test
//	public void testByReverseWeightOrder() {
//		Term item = new Term("Carlos",6);
//		Term item1 = new Term("Martinez",10);
//		Term item2 = new Term("Guadarrama",12);
//		Term item3 = new Term("Humberto",8);
//		
//		Term[] terms = {item, item1, item2, item3};
//		Arrays.sort(terms,Term.byReverseWeightOrder());
//		assertTrue(terms[3] == item);
//		assertTrue(terms[1] == item1);
//		assertTrue(terms[0] == item2);
//		assertTrue(terms[2] == item3);
//		
//	}
//
//	@Test
//	public void testcompareTo() {
//		Term item = new Term("Carlos",6);
//		Term item1 = new Term("Martinez",10);
//		Term item2 = new Term("Guadarrama",12);
//		Term item3 = new Term("Humberto",8);
//		
//		Term[] terms = {item, item1, item2, item3};
//		Arrays.sort(terms);
//		assertTrue(terms[0] == item);
//		assertTrue(terms[1] == item2);
//		assertTrue(terms[2] == item3);
//		assertTrue(terms[3] == item1);
//	}
//	
//	@Test
//	public void testByPrefixOrder() {
//		Term item = new Term("Carlos",6);
//		Term item1 = new Term("Car",10);
//		Term item2 = new Term("Carl",12);
//		Term item3 = new Term("Carlo",8);
//		
//		Term[] terms = {item, item1, item2, item3};
//		Arrays.sort(terms,Term.byPrefixOrder(6));
//		assertTrue(terms[0] == item1);
//		assertTrue(terms[1] == item2);
//		assertTrue(terms[2] == item3);
//		assertTrue(terms[3] == item);
//	}
//	
//	@Test
//	public void testByPrefixOrder1() {
//		Term item = new Term("Carl",6);
//		Term item1 = new Term("Carlo",10);
//		Term item2 = new Term("Carlos",12);
//		Term item3 = new Term("Car",8);
//		
//		Term[] terms = {item2, item1, item, item3};
//		Arrays.sort(terms,Term.byPrefixOrder(7));
//		assertTrue(terms[0] == item3);
//	    assertTrue(terms[1] == item);
//		assertTrue(terms[2] == item1);
//		assertTrue(terms[3] == item2);
//	}
//	
//	@Test
//	public void testByPrefixOrder2() {
//		Term item = new Term("bat",6);
//		Term item1 = new Term("dog",10);
//		Term item2 = new Term("cat",12);
//		Term item3 = new Term("ape",8);
//		
//		Term[] terms = {item, item1, item2, item3};
//		Arrays.sort(terms,Term.byPrefixOrder(3));
//		assertTrue(terms[0] == item3);
//		assertTrue(terms[1] == item);
//		assertTrue(terms[2] == item2);
//		assertTrue(terms[3] == item1);
//	}
//	
//	@Test
//	public void testByPrefixOrder3() {
//		Comparator compare = Term.byPrefixOrder(3);
//		Term term1 = new Term("Carlos",10);
//		Term term2 = new Term("Car",8);
//		int actual = compare.compare(term1, term2);
//		assertTrue(actual == 0);
//	}
//	
//	@Test
//	public void testByPrefixOrder4() {
//		Comparator compare = Term.byPrefixOrder(4);
//		Term term1 = new Term("Carlos",10);
//		Term term2 = new Term("Car",8);
//		int actual = compare.compare(term1, term2);
//		assertTrue(actual > 0);
//	}
//}