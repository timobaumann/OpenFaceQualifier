package edu.cmu.inmind.openfacequalifier.input;

import java.io.File;
import java.util.Map;
import java.util.concurrent.SynchronousQueue;

import org.apache.commons.io.input.Tailer;
import org.apache.commons.io.input.TailerListenerAdapter;

import edu.cmu.inmind.openfacequalifier.FeatureType;

public class FileInput extends TailerListenerAdapter implements OpenFaceInput {

	SynchronousQueue<String> lines;
	private FeaturesFactory featFac;
	Tailer tailer;
	/** stop once EOF is reached (i.e., do not use wait heuristics) */
	boolean singleFileMode = false;

	/** implicit constructor to be used by subclasses */
	FileInput() {}
	
	public FileInput(String filename) {
		openFile(filename);
		singleFileMode = true;
	}
	
	void openFile(String filename) {
		tailer = new Tailer(new File(filename), this, 20); // 20 milliseconds of delay between read attempts
		new Thread(tailer).start();
		lines = new SynchronousQueue<>();
	}
	
	@Override
	public Map<FeatureType,Float> getFeaturesForNextFrame() {
		Map<FeatureType,Float> f = null;
		try {
			String line = lines.take();
			if (line != null) {
				assert !line.startsWith("frame") : "I was not expecting this line in the middle";
				f = featFac.newFromLine(line);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
			destroyForcibly();
		}
		return f;
	}
	
	public void destroyForcibly() {
		tailer.stop();
		tailer = null;
	}
	
	@Override
	public boolean hasMoreFrames() {
		return tailer != null;
	}
	
	@Override
	public void endOfFileReached() {
		System.err.println("waiting for further input");
		if (singleFileMode) {
			destroyForcibly();
		} else {
			// TODO: implement some teardown timeout
		}
	}
	
	@Override
	public void handle(String line) {
		if (featFac == null)
			featFac = FeaturesFactory.newFeaturesFactoryFromLine(line);
		else 
			try {
				lines.put(line);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}

}
