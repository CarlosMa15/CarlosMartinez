/*******************************************************
 * Author: Carlos Martinez
 * Date: February 11, 2017
 * Assignment 2:  Randomized Queues and Deques
 ******************************************************/
package randomizedQueue;

// imports
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
	
	//Fields
	/**
	 * Number of items in the queue
	 */
	private int count;
	
	/**
	 * The amount of memory that the Array uses
	 */
	private int capacity;
	
	/**
	 * The queue in the form of an array
	 */
	public Item[] queue;
	
	/**
	 * Creates an empty queue in the form of an array
	 */
	public RandomizedQueue(){
		this.count = 0;
		this.capacity = 8;
		this.queue = (Item[]) new Object[this.capacity];
	}
	
	/**
	 *  This method Checks if the queue is empty
	 * @return true if the queue is empty, returns
	 * false if the queue is not empty.
	 */
	public boolean isEmpty(){
		return this.count == 0;
	}
	
	/**
	 * This method checks how many items are in the queue
	 * @return The number of items in th queue
	 */
	public int size(){
		return this.count;
	}
	
	/**
	 * This method creates a new array of the appropriate memory
	 * @param newCapacity the new appropriate size
	 */
	public void resize(int newCapacity){
		Item[] newQueue = (Item[]) new Object[newCapacity];
		
		//Copy items to the new queue
		for(int i = 0; i < this.count; i++){
			newQueue[i] = this.queue[i];
		}
		this.queue = newQueue;
	}
	
	/**
	 * This method adds item to the queue at the end
	 * @param item The item being add
	 */
	public void enqueue(Item item){
		if (item == null) {
			throw new NullPointerException();
		}
		
		queue[this.count] = item;
		this.count++;
		
		// If queue is full double capacity
		if(this.count == this.capacity) {
			this.capacity *= 2;
			resize(this.capacity);
		}
	}
	
	/**
	 * This method removes and returns a random element
	 * @return a random removed element
	 */
	public Item dequeue(){
		int randomIndex = StdRandom.uniform(count);
		Item randomItem = this.queue[randomIndex];
		this.queue[randomIndex] = this.queue[--this.count];
		this.queue[this.count] = null;
		
		if(this.capacity / 4 == this.count){
			//If queue is 1/4 full reduce size by half
			this.capacity /= 2;
			resize(this.capacity);
		}
		return randomItem;
	}
	
	/**
	 * This method returns a random item
	 * @return a random item
	 */
	public Item sample(){
		int randomIndex = StdRandom.uniform(count);
		return this.queue[randomIndex];
	}
	
	/**
	 * Creates an iterator
	 */
	@Override
	public Iterator<Item> iterator() {
		return new ListIterator();
	}
	
	/**
	 * This class over rides the 3 methods from the Itorator
	 * for better functionality to the program
	 * @author Carlos Martinez
	 *
	 */
	private class ListIterator implements Iterator<Item> {
		
		/**
		 * This keeps track of the item that the Iterator is at
		 */
		private int current = 0;
		
		/**
		 * This array of random indexes to make the
		 * Iterator random and independent from the queue array
		 */
		private int[] shuffledQueueIndex = new int[count];
		
		/**
		 * This method creates an array of random indexes to make the
		 * Iterator random and independent from the queue array
		 */
		public ListIterator(){
			for(int i = 0; i < count; i++){
				shuffledQueueIndex[i] = i;
			}
			StdRandom.shuffle(shuffledQueueIndex);
		}
		
		/**
		 * Checks if their is a next item
		 */
		@Override
		public boolean hasNext() {
			return current < count;
		}
		
		/**
		 * This moves and returns the nexr current item
		 */
		@Override
		public Item next() {
			if (current >= count || count == 0) {
				throw new NoSuchElementException();
			}
			return queue[shuffledQueueIndex[current++]];
		}
		
		/**
		 * This method is not supported in this program
		 */
		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
	
	/**
	 * This main method is used to test the queue array
	 * @param args
	 */
	public static void main(String[] args){
		RandomizedQueue<String> num = new RandomizedQueue();
		
		System.out.println("The Deque has " + num.size() + " items in it");
		System.out.print("Is the Deque Empty? : ");
		if (num.isEmpty()) {
			System.out.println("The Deque is Empty");
		}
		else {
			System.out.println("The Deque is Not Empty");
		}
		System.out.println();
		
		System.out.println("Adding itmes ...");
		num.enqueue("Carlos");
		num.enqueue("Humberto");
		num.enqueue("Martinez");
		num.enqueue("Guadarrama");
		num.enqueue("Salvador");
		num.enqueue("Uno");
		num.enqueue("Dos");
		num.enqueue("Tres");
		num.enqueue("Cuatro");
		num.enqueue("Cinco");
		num.enqueue("Seis");
		num.enqueue("Siete");
		num.enqueue("Ocho");
		num.enqueue("Nueve");
		num.enqueue("Dies");
		
		System.out.print("Iterator: ");
		Iterator i = num.iterator();
		
		while (i.hasNext()) {
			System.out.print(i.next() + " ");
		}
		System.out.println();
		System.out.println("The Deque has " + num.size() + " items in it");
		System.out.print("Is the Deque Empty? : ");
		if (num.isEmpty()) {
			System.out.println("The Deque is Empty");
		}
		else {
			System.out.println("The Deque is Not Empty");
		}
		System.out.println();
		
		System.out.println("Sample: " + num.sample());
		System.out.println("Sample: " + num.sample());
		System.out.println("Sample: " + num.sample());
		System.out.println("Sample: " + num.sample());
		System.out.println("Sample: " + num.sample());
		System.out.println("Sample: " + num.sample());
		System.out.println("Sample: " + num.sample());
		System.out.println("Sample: " + num.sample());
		System.out.println("Sample: " + num.sample());
		System.out.println("Sample: " + num.sample());
		System.out.println();
		
		System.out.print("Iterator: ");
		Iterator it = num.iterator();
		while (it.hasNext()) {
			System.out.print(it.next() + " ");
		}
		System.out.println();
		System.out.println("The Deque has " + num.size() + " items in it");
		System.out.print("Is the Deque Empty? : ");
		if (num.isEmpty()) {
			System.out.println("The Deque is Empty");
		}
		else {
			System.out.println("The Deque is Not Empty");
		}
		System.out.println();
		
		System.out.println("Dequeue: " + num.dequeue());
		System.out.println("Dequeue: " + num.dequeue());
		System.out.println("Dequeue: " + num.dequeue());
		System.out.println("Dequeue: " + num.dequeue());
		System.out.println("Dequeue: " + num.dequeue());
		System.out.println("Dequeue: " + num.dequeue());
		System.out.println("Dequeue: " + num.dequeue());
		System.out.println();
		
		System.out.print("Iterator: ");
		Iterator ite = num.iterator();
		while (ite.hasNext()) {
			System.out.print(ite.next() + " ");
		}
		System.out.println();
		System.out.println("The Deque has " + num.size() + " items in it");
		System.out.print("Is the Deque Empty? : ");
		if (num.isEmpty()) {
			System.out.println("The Deque is Empty");
		}
		else {
			System.out.println("The Deque is Not Empty");
		}
	}
}