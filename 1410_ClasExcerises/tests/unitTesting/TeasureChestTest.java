package unitTesting;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TeasureChestTest {
	private TeasureChest chest = new TeasureChest(100);

	@Before
	public void setUp() throws Exception {
		chest = new TeasureChest(100);
	}

	@Test
	public void testTeasureChest() {//tests constructor
		TeasureChest chest0 = new TeasureChest(1000000);
		
		//test correct initialization of the constructor
		int expected = 1000000;
		int actual = chest0.getGold();//TestGetGold must call getGold, must call method we are testing
		assertEquals(expected, actual,0.0005);// must use assertions
	}

	@Test
	public void testGetGold() {
		int expected = 100;
		int actual = chest.getGold();//TestGetGold must call getGold, must call method we are testing
		assertEquals(expected, actual,0.0005);// must use assertions
		
	}

	@Test
	public void testToString() {
		String expected = "(" + 100 + ")";
		String actual = chest.toString();
		assertEquals(expected, actual,0.0005);// must use assertions
	}

	@Test
	public void testRemove() {
		chest.remove(40);
		int expected = 60;
		int actual = chest.getGold();
		assertEquals(expected, actual,0.005);
		
	}
	
	@Test
	public void testRemoveTooMuch() {
		
		boolean expected = false;
		boolean actual = chest.remove(400);
		assertEquals(expected, actual);
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testRemoveNeg() {
		chest.remove(-40);
	}

	@Test
	public void testAddGold() {
		chest.addGold(250);
		int expected = 350;
		int actual = chest.getGold();
		assertEquals(expected, actual,0.005);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAddGoldNeg() {
		chest.addGold(-40);
	}

}
