package edu.cmu.inmind.openfacequalifier.input;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Map;

import org.junit.Test;

import edu.cmu.inmind.openfacequalifier.FeatureType;
import edu.cmu.inmind.openfacequalifier.output.CSVOutput;

public class TestProcessInput {

	@Test
	public void test() throws IOException, InterruptedException {
		OpenFaceInput ofi = new ProcessInput(this.getClass().getResource("0188_03_021_al_pacino.avi").toString());
		CSVOutput out = new CSVOutput(System.err);
		while (ofi.hasMoreFrames()) {
			Map<FeatureType,Float> f = ofi.getFeaturesForNextFrame();
			if (f != null)
				out.consumeFrame(f);
			Thread.sleep(5);
		}
	}

}
