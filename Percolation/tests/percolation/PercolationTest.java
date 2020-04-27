/********************************************
 * Author: Carlos Martinez
 * Date: January 31, 2016
 * This Test Class Was Used To additionally 
 * Test My Percolation Class
 *******************************************/
package percolation;

import static org.junit.Assert.*;

import org.junit.Test;

public class PercolationTest {

	@Test
	public void testCheckingEverthing() {
		Percolation per = new Percolation(5);
		per.open(0, 0);
		per.open(1, 0);
		per.open(2, 0);
		per.open(3, 0);
		per.open(4, 0);
		per.open(4, 4);
		per.open(3, 4);
		per.open(2, 4);
		per.open(1, 4);
		per.open(2, 2);
		
		//Checking if it's open
		assertEquals(true, per.isOpen(0,0));
		assertEquals(true, per.isOpen(1,0));
		assertEquals(true, per.isOpen(2,0));
		assertEquals(true, per.isOpen(3,0));
		assertEquals(true, per.isOpen(4,0));
		
		//Checking if it's open
		assertEquals(false, per.isOpen(0,1));
		assertEquals(false, per.isOpen(1,1));
		assertEquals(false, per.isOpen(2,1));
		assertEquals(false, per.isOpen(3,1));
		assertEquals(false, per.isOpen(4,1));
		assertEquals(false, per.isOpen(0,2));
		assertEquals(false, per.isOpen(1,2));
		
		//Checking if it's open
		assertEquals(true, per.isOpen(2,2));
		
		//Checking if it's open
		assertEquals(false, per.isOpen(3,2));
		assertEquals(false, per.isOpen(4,2));
		assertEquals(false, per.isOpen(0,3));
		assertEquals(false, per.isOpen(1,3));
		assertEquals(false, per.isOpen(2,3));
		assertEquals(false, per.isOpen(3,3));
		assertEquals(false, per.isOpen(4,3));
		assertEquals(false, per.isOpen(0,4));
		
		//Preparing for Back Wash
		assertEquals(true, per.isOpen(1,4));
		assertEquals(true, per.isOpen(2,4));
		assertEquals(true, per.isOpen(3,4));
		assertEquals(true, per.isOpen(4,4));
		
		//Checking if it percolates
		assertEquals(true, per.percolates());
		
		//Checking if it's Full
		assertEquals(true, per.isFull(0,0));
		assertEquals(true, per.isFull(1,0));
		assertEquals(true, per.isFull(2,0));
		assertEquals(true, per.isFull(3,0));
		assertEquals(true, per.isFull(4,0));
		assertEquals(false, per.isFull(0,1));
		assertEquals(false, per.isFull(1,1));
		assertEquals(false, per.isFull(2,1));
		assertEquals(false, per.isFull(3,1));
		assertEquals(false, per.isFull(4,1));
		assertEquals(false, per.isFull(0,2));
		assertEquals(false, per.isFull(1,2));
		
		//Checking if it's Full
		assertEquals(false, per.isFull(2,2));
		
		//Checking if it's Full
		assertEquals(false, per.isFull(3,2));
		assertEquals(false, per.isFull(4,2));
		assertEquals(false, per.isFull(0,3));
		assertEquals(false, per.isFull(1,3));
		assertEquals(false, per.isFull(2,3));
		assertEquals(false, per.isFull(3,3));
		assertEquals(false, per.isFull(4,3));
		
		//Checking for Back Wash Problem
		assertEquals(false, per.isFull(0,4));
		assertEquals(false, per.isFull(1,4));
		assertEquals(false, per.isFull(2,4));
		assertEquals(false, per.isFull(3,4));
		assertEquals(false, per.isFull(4,4));
	}
}