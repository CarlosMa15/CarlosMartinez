package inheritance;

public class StringInstrument extends Instrument{
	
	private int numberOfString;
	
	/**
	 * Creates a new String Instrument
	 * @param sound - The Pitch
	 * @param numberOfString number of string
	 */
	public StringInstrument(PitchRanch sound, int numberOfString) {
		super(sound);
		this.numberOfString = numberOfString;
	}
	
	public void applyVibrato(){
		System.out.println("apply vibrato");
	}
	
	@Override
	public void play() {
		System.out.println("Play using Strings");
	}

	@Override
	public String toString() {
		return super.toString() + ".." + this.numberOfString + " Strings";
	}	
}