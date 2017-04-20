package edu.cmu.inmind.openfacequalifier.output;

import edu.cmu.inmind.openfacequalifier.Event;

public class VHTOutput implements EventOutput {

	@Override
	public void nextEvent(Event e) {
		System.err.println("not sending to VHT");
	}

}
