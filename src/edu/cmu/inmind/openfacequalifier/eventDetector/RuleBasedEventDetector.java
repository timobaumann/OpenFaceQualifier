package edu.cmu.inmind.openfacequalifier.eventDetector;

import java.util.Map;

import edu.cmu.inmind.openfacequalifier.Event;
import edu.cmu.inmind.openfacequalifier.FeatureType;
import edu.cmu.inmind.openfacequalifier.input.OpenFaceInput;

public class RuleBasedEventDetector extends EventDetector {

	public RuleBasedEventDetector(OpenFaceInput ofi) {
		super(ofi);
	}

	@Override
	Event consumeFrame(Map<FeatureType,Float> f) {
		// TODO Auto-generated method stub
		return null;
	}

}
