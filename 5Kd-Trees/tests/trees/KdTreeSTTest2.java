package trees;

import static org.junit.Assert.*;
import org.junit.Test;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

public class KdTreeSTTest2 {

	@Test
	public void testIsEmptyTrue() {
		KdTreeST<Integer> st = new KdTreeST<>();
		assertTrue(st.isEmpty());
	}
	
	@Test
	public void testIsEmptyFalse() {
		KdTreeST<Integer> st = new KdTreeST<>();
		st.put(new Point2D(23,45), 1);
		st.put(new Point2D(98,29), 2);
		st.put(new Point2D(38,56), 3);
		st.put(new Point2D(83,52), 4);
		st.put(new Point2D(28,95), 5);
		
		assertFalse(st.isEmpty());
	}

	@Test
	public void testSizeEmpty() {
		KdTreeST<Integer> st = new KdTreeST<>();
		int expected = 0;
		int actual = st.size();
		assertEquals(expected, actual);
	}
	
	@Test
	public void testSize() {
		KdTreeST<Integer> st = new KdTreeST<>();
		st.put(new Point2D(23,45), 1);
		st.put(new Point2D(98,29), 2);
		st.put(new Point2D(38,56), 3);
		st.put(new Point2D(83,52), 4);
		st.put(new Point2D(28,95), 5);
		
		int expected = 5;
		int actual = st.size();
		assertEquals(expected, actual);
	}

	@Test
	public void testGet() {
		KdTreeST<Integer> st = new KdTreeST<>();
		st.put(new Point2D(23,45), 1);
		st.put(new Point2D(98,29), 2);
		st.put(new Point2D(38,56), 3);
		st.put(new Point2D(83,52), 4);
		st.put(new Point2D(28,95), 5);
		
		Integer actual = st.get(new Point2D(38,56));
		Integer expected = 3;
		assertEquals(expected, actual);
		Integer actual1 = st.get(new Point2D(23,45));
		Integer expected1 = 1;
		assertEquals(expected1,actual1);
	}

	@Test
	public void testContainsTrue() {
		KdTreeST<Integer> st = new KdTreeST<>();
		st.put(new Point2D(83,95), 1);
		st.put(new Point2D(58,29), 2);
		st.put(new Point2D(38,56), 3);
		st.put(new Point2D(23,32), 4);
		st.put(new Point2D(98,15), 5);
		
		assertTrue(st.contains(new Point2D(98,15)));
	}
	
	@Test
	public void testContainsFalse() {
		KdTreeST<Integer> st = new KdTreeST<>();
		st.put(new Point2D(83,95), 1);
		st.put(new Point2D(58,29), 2);
		st.put(new Point2D(38,56), 3);
		st.put(new Point2D(23,32), 4);
		st.put(new Point2D(98,15), 5);
		
		assertFalse(st.contains(new Point2D(10,10)));
	}

	@Test
	public void testPoints() {
		KdTreeST<Integer> st = new KdTreeST<>();
		st.put(new Point2D(83,95), 1);
		st.put(new Point2D(58,29), 2);
		st.put(new Point2D(38,56), 3);
		st.put(new Point2D(23,32), 4);
		st.put(new Point2D(98,15), 5);
		
		Point2D[] points =new Point2D[5];
		int index = 0;
		for(Point2D el: st.points()) {
			points[index++] = el;
		}
		
		assertEquals(points[0],new Point2D(83,95));
		assertEquals(points[1],new Point2D(58,29));
		assertEquals(points[2],new Point2D(98,15));
		assertEquals(points[3],new Point2D(38,56));
		assertEquals(points[4],new Point2D(23,32));
	}
	
	@Test
	public void testPoints1() {
		KdTreeST<Integer> st = new KdTreeST<>();
		st.put(new Point2D(55,60), 1);
		st.put(new Point2D(75,20), 2);
		st.put(new Point2D(80,70), 3);
		st.put(new Point2D(60,10), 7);
		st.put(new Point2D(25,10), 4);
		st.put(new Point2D(40,30), 5);
		st.put(new Point2D(30,10), 6);
		
		Point2D[] points =new Point2D[7];
		int index = 0;
		for(Point2D el: st.points()) {
			points[index++] = el;
		}
		
		assertEquals(points[0],new Point2D(55,60));
		assertEquals(points[1],new Point2D(25,10));
		assertEquals(points[2],new Point2D(75,20));
		assertEquals(points[3],new Point2D(40,30));
		assertEquals(points[4],new Point2D(60,10));
		assertEquals(points[5],new Point2D(80,70));
		assertEquals(points[6],new Point2D(30,10));
	}

	@Test
	public void testRange() {
		KdTreeST<Integer> st = new KdTreeST<>();
		st.put(new Point2D(83,95), 1);
		st.put(new Point2D(58,29), 2);
		st.put(new Point2D(38,56), 3);
		st.put(new Point2D(23,32), 4);
		st.put(new Point2D(98,15), 5);
		
		Point2D expected = new Point2D(83,95);
		
		for(Point2D el: st.range(new RectHV(80,80,100,100))) {
			assertEquals(expected, el);
		}
	}
	
	public void testRange1() {
		KdTreeST<Integer> st = new KdTreeST<>();
		st.put(new Point2D(94,29), 1);
		st.put(new Point2D(75,25), 2);
		st.put(new Point2D(39,92), 3);
		st.put(new Point2D(75,62), 4);
		st.put(new Point2D(19,20), 5);
		
		Point2D expected = new Point2D(83,95);
		
		for(Point2D el: st.range(new RectHV(0,0,20,20))) {
			assertEquals(expected, el);
		}
	}

	@Test
	public void testNearest1() {
		KdTreeST<Integer> st = new KdTreeST<>();
		st.put(new Point2D(83,95), 1);
		st.put(new Point2D(58,29), 2);
		st.put(new Point2D(38,56), 3);
		st.put(new Point2D(23,32), 4);
		st.put(new Point2D(98,15), 5);
		
		Point2D expected = new Point2D(83,95);
		Point2D actual = st.nearest(new Point2D(100,100));
		assertEquals(expected, actual);
	}
	
	@Test
	public void testNearest2() {
		KdTreeST<Integer> st = new KdTreeST<>();
		st.put(new Point2D(25,25), 1);
		st.put(new Point2D(75,25), 2);
		st.put(new Point2D(25,75), 3);
		st.put(new Point2D(75,75), 4);
		st.put(new Point2D(50,50), 5);
		
		Point2D expected = new Point2D(25,75);
		Point2D actual = st.nearest(new Point2D(0,100));
		assertEquals(expected, actual);
	}
	
	@Test
	public void testNearest3() {
		KdTreeST<Integer> st = new KdTreeST<>();
		st.put(new Point2D(94,29), 1);
		st.put(new Point2D(75,25), 2);
		st.put(new Point2D(39,92), 3);
		st.put(new Point2D(75,62), 4);
		st.put(new Point2D(19,4), 5);
		
		Point2D expected = new Point2D(19,4);
		Point2D actual = st.nearest(new Point2D(0,0));
		assertEquals(expected, actual);
	}
	
	@Test
	public void testNearest4() {
		KdTreeST<Integer> st = new KdTreeST<>();
		st.put(new Point2D(30,20), 1);
		st.put(new Point2D(70,20), 2);
		st.put(new Point2D(30,80), 3);
		st.put(new Point2D(70,80), 4);
		st.put(new Point2D(55,52), 5);
		
		Point2D expected = new Point2D(55,52);
		Point2D actual = st.nearest(new Point2D(50,50));
		assertEquals(expected, actual);
	}
}
