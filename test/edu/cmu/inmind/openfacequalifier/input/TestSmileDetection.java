package edu.cmu.inmind.openfacequalifier.input;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import edu.cmu.inmind.openfacequalifier.Event;
import edu.cmu.inmind.openfacequalifier.eventDetector.EventDetector;
import edu.cmu.inmind.openfacequalifier.eventDetector.RuleBasedEventDetector;
import edu.cmu.inmind.openfacequalifier.output.EventOutput;

public class TestSmileDetection {

	@Test(timeout=40000)
	public void testSmile() throws IOException {
		assertTrue("found no smile although there should have been one", performTest(this.getClass().getResource("timosmile.mp4").toString()));
	}
	
	@Test(timeout=40000)
	public void testNoSmile() throws IOException {
		assertFalse("found a smile although there should not have been one", performTest(this.getClass().getResource("timonosmile.mp4").toString()));
	}

	private boolean performTest(String file) throws IOException {
		System.err.println(file);
		OpenFaceInput ofi = new ProcessInput(file);
		EventDetector ed = new RuleBasedEventDetector(ofi);
		MyListener ml = new MyListener();
		ed.addListener(ml);
		ed.run();
		return ml.smileDetected;
	}
	
	class MyListener implements EventOutput {
		boolean smileDetected = false;
		@Override
		public void nextEvent(Event e) {
			if (e.getSmile())
				smileDetected = true;
		}
		
	}
}
