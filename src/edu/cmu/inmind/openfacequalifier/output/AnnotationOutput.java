package edu.cmu.inmind.openfacequalifier.output;

import edu.cmu.inmind.openfacequalifier.Event;

public class AnnotationOutput implements EventOutput {

	private static double FRAME_RATE = 15.028756;
	private int currentFrame = 0;
	private Double prevTime = null; 
	boolean previousSmileState = true;
	
	@Override
	public void nextEvent(Event e) {
		if (e != null && e.getSmile() != previousSmileState) {
			double currentTime = currentFrame / FRAME_RATE;
			if (prevTime != null) {
				System.err.println(prevTime + "\t" + currentTime + "\t" + (previousSmileState ? "smile" : "nosmile"));
			}
			prevTime = currentTime;
			previousSmileState = e.getSmile();
		}
		currentFrame++;
	}

}
