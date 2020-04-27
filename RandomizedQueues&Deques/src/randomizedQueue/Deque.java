/*******************************************************
 * Author: Carlos Martinez
 * Date: February 11, 2017
 * Assignment 2:  Randomized Queues and Deques
 ******************************************************/
package randomizedQueue;

//import Statement
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.lang.NullPointerException;

/**
 * This class creates a data structure of Deque
 * 
 * @author Carlos Martinez
 * @param <Item>
 *            The class can create a Deque data Structure of any Reference type
 */
public class Deque<Item> implements Iterable<Item> {

	// Fields
	/**
	 * The number of items that the Deque has in it
	 */
	private int n;

	/**
	 * A Node that keeps reference of the item on the left
	 *  side of the Deque, this side will be referred as the
	 *  head, start,first or left side.
	 */
	private Node head;

	/**
	 * A Node that keeps reference of the item on the right
	 *  side of the Deque, this side will be referred as the 
	 *  tail, end, last or right side.
	 */
	private Node tail;

	// Constructor
	/**
	 * Creates an empty Deque
	 */
	public Deque() {
		this.head = null;
		this.tail = null;
		this.n = 0;
	}

	// Methods
	/**
	 * This method checks if the Deque is empty
	 * 
	 * @return false if Deque is not empty, true if Deque if empty
	 */
	public boolean isEmpty() {
		return n == 0;
	}

	/**
	 * This method returns the size of the Deque
	 * 
	 * @return The size of the Deque, number of items in the Deque
	 */
	public int size() {
		return n;
	}

	/**
	 * This method adds Item to the front or left side
	 * 
	 * @param item
	 *            The item being added to the Deque.
	 */
	public void addFirst(Item item) {
		if (item == null) {
			throw new NullPointerException();
		}

		Node newFirst = new Node();
		newFirst.item = item;
		
		// setting up the new first
		if (head != null) {
			newFirst.next = head;
			head.previous = newFirst;
		}
		head = newFirst;
		
		//Checking if it is empty
		if (tail == null)
			tail = head;

		n++;
	}

	/**
	 * This method adds Item to the end or right side.
	 * 
	 * @param item
	 *            The item being added to the Deque.
	 */
	public void addLast(Item item) {
		if (item == null) {
			throw new NullPointerException();
		}

		Node newLast = new Node();
		newLast.item = item;
		
		//Setting up the new last
		if (tail != null) {
			newLast.previous = tail;
			tail.next = newLast;
		}
		tail = newLast;
		
		//checking if it is empty
		if (head == null)
			head = tail;

		n++;
	}

	/**
	 * This method removes Item from the front, or left side.
	 * 
	 * @return
	 */
	public Item removeFirst() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		
		
		Item item = head.item;
		head = head.next;
		
		//Checking if it is empty to Start all over
		if (head == null)
			tail = null;
		else
			head.previous = null; //To avoid loitering

		n--;

		return item;
	}

	/**
	 * This removes an item from the end, or right side.
	 * 
	 * @return item removed
	 */
	public Item removeLast() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		Item item = tail.item;
		tail = tail.previous;
		
		// Checking if it is empty to Start all over
		if (tail == null)
			head = null;
		else
			tail.next = null; //To avoid loitering

		n--;

		return item;
	}

	/**
	 * Overrides the 3 methods of Iterator
	 */
	@Override
	public Iterator<Item> iterator() {
		return new ListIterator();
	}

	/**
	 * This Class creates a Node
	 * @author Carlos Martinez
	 */
	private class Node {
		//Fields
		/**
		 * This is the item that the Node Stores
		 */
		private Item item;
		
		/**
		 * This keeps a reference of the node to the
		 * Right of this node
		 */
		private Node next;
		
		/**
		 * This keeps a reference of the node to the
		 * Left of the Node
		 */
		private Node previous;
	}

	/**
	 * This class over rides the 3 methods that the Iterator has,
	 * to give better functionality.
	 * 
	 * @author Carlos Martinez
	 */
	private class ListIterator implements Iterator<Item> {

		// Fields
		/**
		 * Keeps track of the current node that the Iterator is on
		 */
		private Node current;

		// Constructor
		/**
		 * This creates an object of ListIterator
		 */
		public ListIterator() {
			this.current = head;
		}

		// Methods
		/**
		 * Overrides the Iterator hasNext method
		 */
		@Override
		public boolean hasNext() {
			return current != null;
		}

		/**
		 * This Overrides the iterator remove method. This 
		 * Operation is not supported in this program
		 */
		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

		/**
		 * This method Overrides the Iterator next method
		 */
		@Override
		public Item next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}

			Item item = current.item;
			current = current.next;
			return item;
		}
	}

	/**
	 * This is the main method used to test the rest of the class
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// Creating your Deque
		Deque<String> num = new Deque();

		// Test Work Space Starts Here {

		// TEST ADDING IN MANY ORDERS - OTHER CODE DELETED
		num.addFirst("Humberto");
		num.addLast("Martinez");
		num.addFirst("Carlos");
		num.addLast("Guadarrama");
		num.addFirst("Add before C");
		num.addLast("Add after G");

		// TEST REMOVE IN MANY ODERS
		System.out.println("Removed: " + num.removeFirst());
												// Remove: Add before C													
		System.out.println("Removed: " + num.removeLast());
												//Remove: Add after G
		// System.out.println("Removed: " + num.removeFirst());
		// System.out.println("Removed: " + num.removeLast());
		// System.out.println("Removed: " + num.removeFirst());
		// System.out.println("Removed: " + num.removeLast());
		// System.out.println("Removed: " + num.removeFirst());
		// System.out.println("Removed: " + num.removeFirst());
		// System.out.println("Removed: " + num.removeLast());
		// System.out.println("Removed: " + num.removeLast());
		// System.out.println("Removed: " + num.removeFirst());
		// System.out.println("Removed: " + num.removeFirst());
		// System.out.println("Removed: " + num.removeFirst());
		// System.out.println("Removed: " + num.removeFirst());
		// System.out.println("Removed: " + num.removeFirst());
		// System.out.println("Removed: " + num.removeFirst());
		// System.out.println("Removed: " + num.removeFirst());
		// System.out.println("Removed: " + num.removeLast());
		// System.out.println("Removed: " + num.removeLast());
		// System.out.println("Removed: " + num.removeLast());
		// System.out.println("Removed: " + num.removeLast());
		// System.out.println("Removed: " + num.removeLast());
		// System.out.println("Removed: " + num.removeLast());
		// System.out.println("Removed: " + num.removeLast());

		System.out.println();

		// Test Work Space Ends Here }

		// TESTS CHECK LIST {
		// Empty Deque WORKS
		// Adding one item to the front WORKS
		// Adding one item to the end WORKS
		// Adding two items to the front WORKS
		// Adding two items to the end WORKS
		// NoSuchElementException thrown from removeFirst if Deque is empty WORKS
		// NoSuchElementException thrown from removeLast if Deque is empty WORKS
		// Remove method from first WORKS
		// Remove method from last WORKS
		// Adding multiple items from Randomly adding from first and last PASSED
		// - ADDITIONAL TEST REQUIRED
		// NullPointerException is thrown if add null from the first WORKS
		// NullPointerException is thrown if add null from the last WORKS
		// Remove multiple items from Randomly removing from first and last PASSED
		// - ADDITIONAL TEST REQUIRED

		// UnsupportedOperationException is thrown if remove from Iterator WORKS
		// NoSuchElementException is thrown if next is call with no element left in Iterator WORKS
		// Iterator and ListIterator tested below with ever type of implementation
		// Size method tested below with ever type of implementation
		// isEmpty method tested below with ever type of implementation
		// PLEASE DO NOT REMOVE CODE BELOW

		System.out.print("Iterator: ");

		Iterator it = num.iterator();

		// it.remove();

		while (it.hasNext()) {
			System.out.print(it.next() + " ");
		}

		// it.next();

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