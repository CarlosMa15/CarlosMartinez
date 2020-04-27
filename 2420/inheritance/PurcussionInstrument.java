package inheritance;

public class PurcussionInstrument extends Instrument{
	
	/**
	 * Creates a new object of PurcussionInstrument
	 * @param sound The Pitch of the Instrument
	 */
	public PurcussionInstrument(PitchRanch sound) {
		super(sound);
	}
	
	@Override
	public void play() {
		System.out.println("Play by Striking");
	}

}
