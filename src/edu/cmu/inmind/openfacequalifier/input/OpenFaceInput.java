package edu.cmu.inmind.openfacequalifier.input;

import edu.cmu.inmind.openfacequalifier.Features;

public interface OpenFaceInput {

	Features getFeaturesForNextFrame();

	boolean hasMoreFrames();
}
