package edu.cmu.inmind.openfacequalifier.input;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import edu.cmu.inmind.openfacequalifier.FeatureType;

public class FileInput implements OpenFaceInput {

	BufferedReader ofInput;
	private FeaturesFactory featFac;

	/** implicit constructor to be used by subclasses */
	FileInput() {}
	
	public FileInput(String filename) throws IOException {
		openFile(filename);
	}
	
	void openFile(String filename) throws IOException {
		ofInput = new BufferedReader(new FileReader(filename));
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
				assert !line.startsWith("frame") : "I was not expecting this line in the middle";
				f = featFac.newFromLine(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
			destroyForcibly();
		}
		return f;
	}
	

	public void destroyForcibly() {
		try {
			ofInput.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ofInput = null;
	}
	
	@Override
	public boolean hasMoreFrames() {
		return ofInput != null;
	}

}
