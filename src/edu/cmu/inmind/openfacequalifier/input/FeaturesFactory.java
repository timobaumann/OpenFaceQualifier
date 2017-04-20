package edu.cmu.inmind.openfacequalifier.input;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import edu.cmu.inmind.openfacequalifier.FeatureType;

public class FeaturesFactory {

	final List<FeatureType> orderingOfFeatures;
	
	private FeaturesFactory(List<FeatureType> orderingOfFeatures) {
		this.orderingOfFeatures = orderingOfFeatures;
	}
	
	public static FeaturesFactory newFeaturesFactoryFromLine(String line) {
		List<FeatureType> orderingOfFeatures = new ArrayList<>();
		for (String tok : line.trim().split(", ")) {
			FeatureType ft = FeatureType.valueOf(tok);
			assert ft != null;
			orderingOfFeatures.add(ft);
		}
		return new FeaturesFactory(orderingOfFeatures);
	}
	
	public Map<FeatureType,Float> newFromLine(String line) {
		EnumMap<FeatureType, Float> f = new EnumMap<>(FeatureType.class);
		Iterator<FeatureType> featIt = orderingOfFeatures.iterator();
		for (String tok : line.trim().split(", ")) {
			float value = Float.parseFloat(tok);
			f.put(featIt.next(), value);
		}
		return f;
	}

}
