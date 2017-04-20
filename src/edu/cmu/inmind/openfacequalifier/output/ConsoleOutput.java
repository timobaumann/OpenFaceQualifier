package edu.cmu.inmind.openfacequalifier.output;

import edu.cmu.inmind.openfacequalifier.Event;

public class ConsoleOutput implements EventOutput {

	@Override
	public void nextEvent(Event e) {
		System.err.println(e);
	}

}
