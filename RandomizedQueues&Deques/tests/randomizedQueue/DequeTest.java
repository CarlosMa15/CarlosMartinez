package randomizedQueue;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.Before;
import org.junit.Test;

public class DequeTest {
	private Deque<String> deque123 = new Deque<>();
	private Deque<String> dequeEmpty = new Deque<>();
	
	@Before
	public void SetUp() {
		deque123 = new Deque<>();
		deque123.addLast("one");
		deque123.addLast("two");
		deque123.addLast("three");	
		
		dequeEmpty = new Deque<>();
	}
	
	@Test
	public void testDeque() {  
		Deque<String> newDeque = new Deque<>();
		assertTrue(newDeque.isEmpty());
	}

	@Test
	public void testIsEmpty() {  
		assertTrue(dequeEmpty.isEmpty());
	}
	
	@Test
	public void testIsNotEmpty() {
		assertFalse(deque123.isEmpty());
	}

	@Test
	public void testSize0() {
		assertEquals(0, dequeEmpty.size());
	}

	@Test
	public void testSize3() {
		assertEquals(3, deque123.size());
	}
	
	@Test
	public void testSizeEmptyCombo() {
		dequeEmpty.addFirst("one");
		assertEquals(1, dequeEmpty.size());
		dequeEmpty.addLast("two");
		assertEquals(2, dequeEmpty.size());
		
		dequeEmpty.removeFirst();
		assertEquals(1, dequeEmpty.size());
		assertFalse(dequeEmpty.isEmpty());		
		dequeEmpty.removeLast();
		assertEquals(0, dequeEmpty.size());
		assertTrue(dequeEmpty.isEmpty());		
	}
	
	@Test
	public void testAddFirst() {
		deque123.addFirst("zero");	
		
		assertEquals(4, deque123.size());
		assertEquals("zero", deque123.removeFirst());
	}
	
	@Test (expected = NullPointerException.class)
	public void testAddNullFirst() {
		deque123.addFirst(null);
	}
	
	@Test
	public void testAddLast() {
		deque123.addLast("four");

		assertEquals(4, deque123.size());
		assertEquals("four", deque123.removeLast());
	}
	
	@Test (expected = NullPointerException.class)
	public void testAddNullLast() {
		deque123.addLast(null);
	}

	@Test
	public void testRemoveFirst() {
		assertEquals("one", deque123.removeFirst());
	}
	
	@Test (expected = NoSuchElementException.class)
	public void testRemoveFirstFromEmptyQueue() {
		dequeEmpty.removeFirst();
	}

	@Test
	public void testRemoveLast() {
		assertEquals("three", deque123.removeLast());
	}
	
	@Test (expected = NoSuchElementException.class)
	public void testRemoveLastFromEmptyList() {
		dequeEmpty.removeLast();
	}

	@Test
	public void testIterator() {
		Iterator<String> iterator = deque123.iterator();		
		
		assertEquals("one", iterator.next());
		assertEquals("two", iterator.next());
		assertEquals("three", iterator.next());
	}
	
	@Test
	public void testIteratorIndependence() {
		Iterator<String> iteratorA = deque123.iterator();		
		Iterator<String> iteratorB = deque123.iterator();		
		
		assertEquals("one", iteratorA.next());
		assertEquals("two", iteratorA.next());
		assertEquals("one", iteratorB.next());
		assertEquals("three", iteratorA.next());
		assertEquals("two", iteratorB.next());
		assertEquals("three", iteratorB.next());
	}
	
	@Test
	public void testIteratorHasNext() {
		Iterator<String> iterator = deque123.iterator();
		
		assertTrue(iterator.hasNext());		
		iterator.next();
		assertTrue(iterator.hasNext());		
		iterator.next();
		assertTrue(iterator.hasNext());		
		iterator.next();
		assertFalse(iterator.hasNext());
	}
	
	@Test (expected = NoSuchElementException.class)
	public void testIteratorNoSuchElement() {
		Iterator<String> iterator = dequeEmpty.iterator();
		iterator.next();
	}
	
	@Test (expected = UnsupportedOperationException.class)
	public void testIteratorRemove() {
		Iterator<String> iterator = deque123.iterator();
		iterator.remove();
	}
}