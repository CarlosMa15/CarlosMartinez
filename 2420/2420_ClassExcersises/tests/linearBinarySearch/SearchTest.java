package linearBinarySearch;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SearchTest {
	private int[] a1 = {0,8,4,2,1,5,9,3,7};

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testLinearLastElement() {
		assertEquals(8, Search.linear(a1, 7));
	}
	
	@Test
	public void testLinearFirstElement() {
		assertEquals(0, Search.linear(a1, 0));
	}
	
	@Test
	public void testLinearMiddleElement() {
		assertEquals(4, Search.linear(a1, 1));
	}
	
	@Test
	public void testLinearNonExistingElement() {
		assertEquals(-1, Search.linear(a1, 6));
	}

	@Test
	public void testBinary() {
		fail("Not yet implemented");
	}

}
