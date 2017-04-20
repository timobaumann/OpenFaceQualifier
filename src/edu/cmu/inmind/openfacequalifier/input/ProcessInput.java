package edu.cmu.inmind.openfacequalifier.input;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import edu.cmu.inmind.openfacequalifier.Features;

/** 
 * start OpenFace as a separate process which writes to a temporary file
 * read from the temporary file 
 */
public class ProcessInput implements OpenFaceInput {

	Process openFace;
	BufferedReader ofInput;
	FeaturesFactory featFac;
	
	public ProcessInput(String fileOrURL) throws IOException {
		File tmpFile = File.createTempFile("OpenFace", ".out");
		ProcessBuilder pb = new ProcessBuilder("FeatureExtraction", "-q", "-f", fileOrURL, "-of", tmpFile.toString());
		pb.inheritIO();
		openFace = pb.start();
		ofInput = new BufferedReader(new FileReader(tmpFile));
	}

	@Override
	public Features getFeaturesForNextFrame() {
		Features f = null;
		try {
			String line = ofInput.readLine();
			if (line != null && line.startsWith("frame")) {
				assert featFac == null : "I was not expecting this line in the middle";
				featFac = FeaturesFactory.newFeaturesFactoryFromLine(line);
				line = ofInput.readLine();
			}
			assert featFac != null;
			if (line != null)
				f = featFac.newFromLine(line);
		} catch (IOException e) {
			e.printStackTrace();
			ofInput = null;
			openFace.destroyForcibly();
		}
		return f;
	}

	@Override
	public boolean hasMoreFrames() {
		return ofInput != null && openFace.isAlive();
	}

}
