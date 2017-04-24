package edu.cmu.inmind.openfacequalifier.input;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Map;

import org.junit.Test;

import edu.cmu.inmind.openfacequalifier.FeatureType;
import edu.cmu.inmind.openfacequalifier.output.CSVOutput;

public class TestProcessInput {

	@Test(timeout = 20000)
	public void testAlPacino() throws IOException {
		OpenFaceInput ofi = new ProcessInput(this.getClass().getResource("0188_03_021_al_pacino.avi").toString());
		//OpenFaceInput ofi = new ProcessInput("rtsp://34.203.204.136:8554/live/myStream54201342a4cfb96d");
		CSVOutput out = new CSVOutput(System.err);
		while (ofi.hasMoreFrames()) {
			Map<FeatureType,Float> f = ofi.getFeaturesForNextFrame();
			if (f != null)
				out.consumeFrame(f);
		}
	}

	@Test(timeout = 70000)
	public void testTimo() throws IOException {
		OpenFaceInput ofi = new ProcessInput(this.getClass().getResource("timo1.mp4").toString());
		//OpenFaceInput ofi = new ProcessInput("rtsp://34.203.204.136:8554/live/myStream54201342a4cfb96d");
		CSVOutput out = new CSVOutput(System.err);
		while (ofi.hasMoreFrames()) {
			Map<FeatureType,Float> f = ofi.getFeaturesForNextFrame();
			if (f != null)
				out.consumeFrame(f);
		}
	}

}
