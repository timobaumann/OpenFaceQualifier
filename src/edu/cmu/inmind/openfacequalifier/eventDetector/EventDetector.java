package edu.cmu.inmind.openfacequalifier.eventDetector;

import java.util.ArrayList;
import java.util.List;

import edu.cmu.inmind.openfacequalifier.Event;
import edu.cmu.inmind.openfacequalifier.Features;
import edu.cmu.inmind.openfacequalifier.input.OpenFaceInput;
import edu.cmu.inmind.openfacequalifier.output.EventOutput;

public abstract class EventDetector {

	private final List<EventOutput> listeners = new ArrayList<>();
	OpenFaceInput ofi;
	
	public EventDetector(OpenFaceInput ofi) {
		this.ofi = ofi;
	}
	
	public void addListener(EventOutput listener) {
		listeners.add(listener);
	}
	
	/**   
	 * at present, this just produces output every 1 second and null otherwise
	 * @return null if no event occurs, return an actual event when something has changed
	 */
	abstract Event consumeFrame(Features f);
	
	private void notifyListeners(Event e) {
		for (EventOutput eo : listeners) {
			eo.nextEvent(e);
		}
	}
	
	public void run() {
		while (ofi.hasMoreFrames()) {
			Event e = consumeFrame(ofi.getFeaturesForNextFrame());
			notifyListeners(e);
		}
	}
	
}
