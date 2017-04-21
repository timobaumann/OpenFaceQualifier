package edu.cmu.inmind.openfacequalifier.eventDetector;

import java.util.Map;

import edu.cmu.inmind.openfacequalifier.Event;
import edu.cmu.inmind.openfacequalifier.FeatureType;
import edu.cmu.inmind.openfacequalifier.input.OpenFaceInput;

public class RuleBasedEventDetector extends EventDetector {

	Event e = new Event();
	boolean isSmiling;
	boolean wasSmiling;
	
	public RuleBasedEventDetector(OpenFaceInput ofi) {
		super(ofi);
	}

	@Override
	Event consumeFrame(Map<FeatureType,Float> f) {
		// TODO Auto-generated method stub
		
		wasSmiling = e.getSmile();
		
		if (f.get(FeatureType.AU12_r)>0.5){
			isSmiling = true;
		} else {
			isSmiling = false;
		}
		e.setSmile(isSmiling);
		
		if (isSmiling && !wasSmiling){
			return e;		
		} else {
			return e;
		}
	
	}

}
