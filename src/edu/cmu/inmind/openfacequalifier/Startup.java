package edu.cmu.inmind.openfacequalifier;

import java.io.IOException;

import edu.cmu.inmind.openfacequalifier.eventDetector.EventDetector;
import edu.cmu.inmind.openfacequalifier.eventDetector.RuleBasedEventDetector;
import edu.cmu.inmind.openfacequalifier.input.OpenFaceInput;
import edu.cmu.inmind.openfacequalifier.input.ProcessInput;
import edu.cmu.inmind.openfacequalifier.input.TestFeaturesFactory;
import edu.cmu.inmind.openfacequalifier.output.VHTOutput;

public class Startup {
	// the main method should probably be replaced by some MUF integration code
	public static void main(String args[]) throws IOException, InterruptedException {
		OpenFaceInput ofi = new ProcessInput("rtsp://34.203.204.136:8554/live/myStreamcdf20e73589e8763");
		EventDetector ed = new RuleBasedEventDetector(ofi);
		ed.addListener(new VHTOutput(/*some parameters?*/));
		ed.run();
	}
}
