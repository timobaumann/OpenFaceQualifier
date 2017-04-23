package edu.cmu.inmind.openfacequalifier.eventDetector;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import edu.cmu.inmind.openfacequalifier.Event;
import edu.cmu.inmind.openfacequalifier.FeatureType;
import edu.cmu.inmind.openfacequalifier.input.OpenFaceInput;
import edu.cmu.inmind.openfacequalifier.output.CSVOutput;
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
	abstract Event consumeFrame(Map<FeatureType,Float> f);
	
	private void notifyListeners(Event e) {
		for (EventOutput eo : listeners) {
			eo.nextEvent(e);
		}
	}
	
	public void run() {
		//CSVOutput out = new CSVOutput(System.err);
		while (ofi.hasMoreFrames()) {
			Map<FeatureType, Float> f = ofi.getFeaturesForNextFrame();
			//out.consumeFrame(f);
			if (f != null) {
				Event e = consumeFrame(f);
				notifyListeners(e);
			}
		}
	}
	
}
