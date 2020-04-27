package randomizedQueue;

import static org.junit.Assert.*;

import org.junit.Test;

public class RandomizedQueueTest {

	@Test // Testing is empty
	public void testIsEmpty() {
		RandomizedQueue<String> num = new RandomizedQueue();
		boolean expecteds = true;
		boolean actuals = num.isEmpty();
		assertEquals(expecteds, actuals);
	}

	@Test //testing size
	public void testSize() {
		RandomizedQueue<Integer> num = new RandomizedQueue();
		int expected = 5;
		num.enqueue(5);
		num.enqueue(3);
		num.enqueue(12);
		num.enqueue(0);
		num.enqueue(14);
		int actual = num.size();
		assertEquals(expected, actual,.000001);
	}
		
		@Test //testing size
		public void testSizeAfterRemove() {
			RandomizedQueue<Integer> num = new RandomizedQueue();
			int expected = 0;
			num.enqueue(5);
			num.enqueue(3);
			num.enqueue(12);
			num.enqueue(0);
			num.enqueue(14);
			num.dequeue();
			num.dequeue();
			num.dequeue();
			num.dequeue();
			num.dequeue();
			int actual = num.size();
			assertEquals(expected, actual,.000001);
	}

	@Test // Testing sample
	public void testSample() {
		RandomizedQueue<String> num = new RandomizedQueue();
		num.enqueue("Carlos");
		String actual = num.sample();
		String expected = "Carlos";
		assertEquals(expected, actual);
	}
	
	@Test 
	public void testSampleRange() {
		RandomizedQueue<Integer> num = new RandomizedQueue();
		num.enqueue(1);
		num.enqueue(0);
		num.enqueue(8);
		num.enqueue(2);
		num.enqueue(9);
		num.enqueue(7);
		num.enqueue(6);
		num.enqueue(3);
		num.enqueue(4);
		num.enqueue(5);
		num.enqueue(10);
		Integer actual = num.sample();
		Integer expected = 5;
		assertEquals(expected, actual,5);
	}

}
