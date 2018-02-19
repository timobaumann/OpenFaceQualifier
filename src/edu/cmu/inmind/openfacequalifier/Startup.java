package edu.cmu.inmind.openfacequalifier;

import java.io.IOException;

import edu.cmu.inmind.openfacequalifier.eventDetector.EventDetector;
import edu.cmu.inmind.openfacequalifier.eventDetector.LinearClassifierDetector;
import edu.cmu.inmind.openfacequalifier.eventDetector.RuleBasedEventDetector;
import edu.cmu.inmind.openfacequalifier.input.OpenFaceInput;
import edu.cmu.inmind.openfacequalifier.input.ProcessInput;
import edu.cmu.inmind.openfacequalifier.output.AnnotationOutput;
import edu.cmu.inmind.openfacequalifier.output.ConsoleOutput;
import edu.cmu.inmind.openfacequalifier.output.SmileVisualization;
import edu.cmu.inmind.openfacequalifier.output.VHTOutput;

public class Startup {
	// the main method should probably be replaced by some MUF integration code
	public static void main(String args[]) throws IOException, InterruptedException {
		OpenFaceInput ofi = new ProcessInput("data/justine2.mp4");//"rtsp://34.203.204.136:8554/live/myStream54201342a4cfb96d");
//		OpenFaceInput ofi = new ProcessInput("rtsp://34.203.204.136:8554/live/myStream54201342a4cfb96d");
		EventDetector ed = new LinearClassifierDetector(ofi);
		ed.addListener(new SmileVisualization(/*some parameters?*/));
		ed.run();
	}
}
