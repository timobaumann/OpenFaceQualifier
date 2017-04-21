package edu.cmu.inmind.openfacequalifier.input;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Map;

import org.junit.Test;

import edu.cmu.inmind.openfacequalifier.FeatureType;
import edu.cmu.inmind.openfacequalifier.output.CSVOutput;

public class TestFileInput {

	@Test(timeout = 10000)
	public void test() throws IOException, InterruptedException {
		OpenFaceInput ofi = new FileInput(this.getClass().getResource("timo1.openface").toString().replaceFirst("^file:", ""));
		//OpenFaceInput ofi = new ProcessInput("rtsp://34.203.204.136:8554/live/myStreamcdf20e73589e8763");
		CSVOutput out = new CSVOutput(System.err);
		while (ofi.hasMoreFrames()) {
			Map<FeatureType,Float> f = ofi.getFeaturesForNextFrame();
			if (f != null)
				out.consumeFrame(f);
			Thread.sleep(5);
		}
	}

}
