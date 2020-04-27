package inheritance;

import java.util.ArrayList;
import java.util.List;

public class InstrumentApp {

	public static void main(String[] args) {
		Instrument myInstrument = new Instrument(PitchRanch.LOW);
		System.out.println("myIntrument: " + myInstrument);
		myInstrument.play();
		System.out.println();
		
		StringInstrument violin = new StringInstrument(PitchRanch.HIGH,4);
		System.out.println("Violin: " + violin);
		violin.play();
		System.out.println();
		
		PurcussionInstrument drum = new PurcussionInstrument(PitchRanch.LOW);
		System.out.println("Drum: " + drum);
		drum.play();
		System.out.println();
		
		List<Instrument> Instruments = new ArrayList<>();
		Instruments.add(myInstrument);
		Instruments.add(violin);
		Instruments.add(drum);
		
		for(Instrument el: Instruments){
			el.play();
			
		}
	}
}