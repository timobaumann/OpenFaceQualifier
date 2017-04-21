package edu.cmu.inmind.openfacequalifier.input;

import java.io.File;
import java.io.IOException;

/** 
 * start OpenFace as a separate process which writes to a temporary file
 * read from the temporary file 
 */
public class ProcessInput extends FileInput {

	Process openFace;
	
	public ProcessInput(String fileOrURL) throws IOException, InterruptedException {
		String binaryName = System.getProperty("openface.featureExtraction.binaryName", "/home/timo/uni/software/OpenFace/OpenFace/build/bin/FeatureExtraction");
		File tmpFile = File.createTempFile("OpenFace", ".out");
		tmpFile.deleteOnExit();
		ProcessBuilder pb = new ProcessBuilder(binaryName, "-q", "-f", fileOrURL, "-of", tmpFile.toString());
		pb.inheritIO();
		openFace = pb.start();
		Thread.sleep(15000);
		openFile(tmpFile.toString());
	}

	@Override
	public void destroyForcibly() {
		super.destroyForcibly();
		openFace.destroyForcibly();
	}

	@Override
	public boolean hasMoreFrames() {
		return super.hasMoreFrames() && openFace.isAlive();
	}

}
