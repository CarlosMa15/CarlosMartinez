
import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import puzzleAssignment.Board;

public class BoardTest {

	private final Board board1 = new Board(new int[][] { { 0 } });
	private final Board board2 = new Board(new int[][] { { 2, 0 }, { 1, 3 } });
	private final Board board3 = new Board(new int[][] { { 8, 1, 3 }, { 4, 0, 2 }, { 7, 6, 5 } });
	private final Board board4 = new Board(
			new int[][] { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 0 }, { 13, 14, 15, 12 } });
	private final Board goalBoard = new Board(
			new int[][] { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 0 } });

	@Test
	public void testBoard() {
		// Test if immutable
		int[][] boardArray = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } };
		Board immutableBoard = new Board(boardArray);
		boardArray[2][2] = 9;
		assertEquals("3\n 1  2  3 \n 4  5  6 \n 7  8  0 \n", immutableBoard.toString());
	}

	@Test
	public void testSize() {
		assertEquals(3, board3.size());
		assertEquals(4, goalBoard.size());
		assertEquals(2, board2.size());
		assertEquals(1, board1.size());
	}

	@Test
	public void testHamming() {
		assertEquals(5, board3.hamming());
		assertEquals(0, goalBoard.hamming());
		assertEquals(3, board2.hamming());
		assertEquals(0, board1.hamming());
		Board offByOneBoard = new Board(new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 0, 8 } });
		assertEquals(1, offByOneBoard.hamming());
	}

	@Test
	public void testManhattan() {
		assertEquals(10, board3.manhattan());
		assertEquals(0, goalBoard.manhattan());
		assertEquals(3, board2.manhattan());
		assertEquals(0, board1.manhattan());
		Board offByOneBoard = new Board(new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 0, 8 } });
		assertEquals(1, offByOneBoard.manhattan());
	}

	@Test
	public void testIsGoal() {
		assertEquals(false, board3.isGoal());
		assertEquals(true, goalBoard.isGoal());
		assertEquals(false, board2.isGoal());
		assertEquals(true, board1.isGoal());
	}

	@Test
	public void testIsSolvable() {
		assertEquals(true, board3.isSolvable());
		assertEquals(true, goalBoard.isSolvable());
		assertEquals(true, board2.isSolvable());
		assertEquals(true, board1.isSolvable());
		Board notSolvable = new Board(new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 8, 7, 0 } });
		assertEquals(false, notSolvable.isSolvable());
	}

	@Test
	public void testEquals() {
		assertEquals(false, board3.equals(goalBoard));
		assertEquals(false, goalBoard.equals(board3));
		Board equalsTestBoard = new Board(new int[][] { { 8, 1, 3 }, { 4, 0, 2 }, { 7, 6, 5 } });
		assertEquals(true, board3.equals(equalsTestBoard));
		assertEquals(true, equalsTestBoard.equals(board3));
		assertEquals(true, board1.equals(new Board(new int[][] { { 0 } })));
	}

	@Test
	public void testNeighbors() {
		Board[] board3Neighbors = { new Board(new int[][] { { 8, 1, 3 }, { 4, 2, 0 }, { 7, 6, 5 } }),
				new Board(new int[][] { { 8, 1, 3 }, { 0, 4, 2 }, { 7, 6, 5 } }),
				new Board(new int[][] { { 8, 1, 3 }, { 4, 6, 2 }, { 7, 0, 5 } }),
				new Board(new int[][] { { 8, 0, 3 }, { 4, 1, 2 }, { 7, 6, 5 } }) };
		Iterable<Board> neighbors = board3.neighbors();
		// check for right number of neighbors
		assertEquals(board3Neighbors.length, count(neighbors));
		// check individually because neighbors can be listed in arbitrary order
		for (Board n : neighbors) {
			if (!Arrays.asList(board3Neighbors).contains(n))
				fail("Fail.\nGiven:\n" + neighbors.toString() + "Expected:\n" + TestUtil.boardsToString(neighbors));
		}

		Board[] board4Neighbors = {
				new Board(new int[][] { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 0 } }),
				new Board(new int[][] { { 1, 2, 3, 4 }, { 5, 6, 7, 0 }, { 9, 10, 11, 8 }, { 13, 14, 15, 12 } }),
				new Board(new int[][] { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 0, 11 }, { 13, 14, 15, 12 } }) };
		neighbors = board4.neighbors();
		assertEquals(board4Neighbors.length, count(neighbors));
		for (Board n : neighbors) {
			if (!Arrays.asList(board4Neighbors).contains(n))
				fail("Fail.\nGiven:\n" + neighbors.toString() + "Expected:\n" + TestUtil.boardsToString(neighbors));
		}

		Board[] board2Neighbors = { new Board(new int[][] { { 0, 2 }, { 1, 3 } }),
				new Board(new int[][] { { 2, 3 }, { 1, 0 } }) };
		neighbors = board2.neighbors();
		assertEquals(board2Neighbors.length, count(neighbors));
		for (Board n : neighbors) {
			if (!Arrays.asList(board2Neighbors).contains(n))
				fail("Fail.\nGiven:\n" + neighbors.toString() + "Expected:\n" + TestUtil.boardsToString(neighbors));
		}

		neighbors = board1.neighbors();
		assertEquals(0, count(neighbors));
		assertEquals("[]", TestUtil.boardsToString(neighbors));

		neighbors = goalBoard.neighbors();
		//assertEquals(0, count(neighbors));
		//assertEquals("[]", TestUtil.boardsToString(neighbors));
	}

	private int count(Iterable<Board> boards) {
		int count = 0;
		for (Board b : boards)
			count++;

		return count;
	}

	@Test
	public void testToString() {
		assertEquals("3\n 8  1  3 \n 4  0  2 \n 7  6  5 \n", board3.toString());
		assertEquals("4\n 1  2  3  4 \n 5  6  7  8 \n 9 10 11 12 \n13 14 15  0 \n", goalBoard.toString());
		assertEquals("2\n 2  0 \n 1  3 \n", board2.toString());
		assertEquals("1\n 0 \n", board1.toString());
	}
}
