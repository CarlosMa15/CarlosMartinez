package concert;

public class ConcertApp {

	public static void main(String[] args) {
		
		Concert myConcert = new Concert("Last Stand",new Time(20,0,0),new Time(23,0,0));
		
		System.out.println(myConcert);
	}

}
