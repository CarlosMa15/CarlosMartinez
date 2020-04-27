package inheritance;

public class Instrument {
	private PitchRanch sound;

	public Instrument(PitchRanch sound) {
		this.sound = sound;
	}
	
	public void play(){
		System.out.println("Playing....");
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName()+"(" + sound + ")";
	}
}