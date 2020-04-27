import puzzleAssignment.Board;

public class TestUtil {
	static String boardsToString(Iterable<Board> boards) {
		StringBuilder sb = new StringBuilder("[");
		for (Board b : boards) {
			sb.append(b).append(", ");
		}
		if (sb.length() >= 2)
			sb.replace(sb.length() - 2, sb.length(), "]");
		else
			sb.append("]");
		return sb.toString();
	}
}