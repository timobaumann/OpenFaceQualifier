package edu.cmu.inmind.openfacequalifier.input;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import edu.cmu.inmind.openfacequalifier.FeatureType;

/** 
 * start OpenFace as a separate process which writes to a temporary file
 * read from the temporary file 
 */
public class ProcessInput implements OpenFaceInput {

	Process openFace;
	BufferedReader ofInput;
	FeaturesFactory featFac;
	
	public ProcessInput(String fileOrURL) throws IOException, InterruptedException {
		String binaryName = System.getProperty("openface.featureExtraction.binaryName", "/home/timo/uni/software/OpenFace/OpenFace/build/bin/FeatureExtraction");
		File tmpFile = File.createTempFile("OpenFace", ".out");
		ProcessBuilder pb = new ProcessBuilder(binaryName, "-q", "-f", fileOrURL, "-of", tmpFile.toString());
		pb.inheritIO();
		openFace = pb.start();
		Thread.sleep(5000);
		ofInput = new BufferedReader(new FileReader(tmpFile));
		ofInput.mark(8096);
		String line = ofInput.readLine();
		featFac = FeaturesFactory.newFeaturesFactoryFromLine(line);
	}

	@Override
	public Map<FeatureType,Float> getFeaturesForNextFrame() {
		Map<FeatureType,Float> f = null;
		try {
			String line = ofInput.readLine();
			if (line != null) {
				System.err.println(line.length());
				assert !line.startsWith("frame") : "I was not expecting this line in the middle";
				f = featFac.newFromLine(line);
			}
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
