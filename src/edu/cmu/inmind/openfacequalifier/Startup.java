package edu.cmu.inmind.openfacequalifier;

import edu.cmu.inmind.openfacequalifier.eventDetector.EventDetector;
import edu.cmu.inmind.openfacequalifier.eventDetector.RuleBasedEventDetector;
import edu.cmu.inmind.openfacequalifier.input.OpenFaceInput;
import edu.cmu.inmind.openfacequalifier.input.ProcessInput;
import edu.cmu.inmind.openfacequalifier.output.VHTOutput;

public class Startup {
	// the main method should probably be replaced by some MUF integration code
	public static void main(String args[]) {
		OpenFaceInput ofi = new ProcessInput("rtsp:123.234.345.456:1234/some/url");
		EventDetector ed = new RuleBasedEventDetector(ofi);
		ed.addListener(new VHTOutput(/*some parameters?*/));
		ed.run();
	}
}
