package edu.cmu.inmind.openfacequalifier.input;

import java.util.Map;

import edu.cmu.inmind.openfacequalifier.FeatureType;

public interface OpenFaceInput {

	Map<FeatureType,Float> getFeaturesForNextFrame();

	boolean hasMoreFrames();
}
