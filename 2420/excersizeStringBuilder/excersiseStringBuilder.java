package excersizeStringBuilder;

public class excersiseStringBuilder {

	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder("Cats live");
		sb.setCharAt(0,'r');
		sb.append(" on ");
		System.out.println(sb);
		
		StringBuilder name = new StringBuilder(sb.reverse());
		sb.reverse();
		sb.append(name);
		System.out.println(sb);
		
		System.out.println(sb.indexOf("  "));
		
		sb.deleteCharAt(12);
		System.out.println(sb);
		
		//sb.setCharAt(0, 'C');
		//sb.append(".");
		sb.replace(0, 1, "C").append(".");
		System.out.println(sb);
	}
}
