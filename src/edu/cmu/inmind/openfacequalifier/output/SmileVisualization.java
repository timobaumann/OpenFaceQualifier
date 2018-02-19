package edu.cmu.inmind.openfacequalifier.output;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import edu.cmu.inmind.openfacequalifier.Event;

public class SmileVisualization extends JPanel implements EventOutput {

	private static int MAX_FRAMES = 1200;
	private static int LINE_HEIGHT = 10;
	
	private float maxSmile = 1.0f;
	
	boolean[] referenceFrames;
	float[] smileStrength;
	
	public SmileVisualization() {
		this.setSize(MAX_FRAMES, 2 * LINE_HEIGHT);
		referenceFrames = getReferenceFrames();
		smileStrength = new float[MAX_FRAMES];
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame f = new JFrame();
				f.add(SmileVisualization.this);
				f.pack();
				f.setVisible(true);
			}
		});
	}
	
	private static boolean[] getReferenceFrames() {
		boolean[] referenceFrames = new boolean[MAX_FRAMES];
		for (int i = 122; i < 153; i++) 
			referenceFrames[i] = true;
		for (int i = 186; i < 188; i++) 
			referenceFrames[i] = true;
		for (int i = 343; i < 363; i++) 
			referenceFrames[i] = true;
		for (int i = 509; i < 518; i++) 
			referenceFrames[i] = true;
		for (int i = 616; i < 623; i++) 
			referenceFrames[i] = true;
		for (int i = 666; i < 673; i++) 
			referenceFrames[i] = true;
		for (int i = 695; i < 702; i++) 
			referenceFrames[i] = true;
		for (int i = 741; i < 766; i++) 
			referenceFrames[i] = true;
		for (int i = 812; i < 826; i++) 
			referenceFrames[i] = true;
		for (int i = 964; i < 978; i++) 
			referenceFrames[i] = true;
		for (int i = 1007; i < 1012; i++) 
			referenceFrames[i] = true;
		return referenceFrames;
	}
	
	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		for (int i = 0; i < referenceFrames.length; i++) {
			if (referenceFrames[i])
				g2.drawLine(i, 0, i, LINE_HEIGHT);
		}
		for (int i = 0; i < smileStrength.length; i++) {
			if (smileStrength[i] > 0.0f) {
				g2.setColor(new Color(1f - smileStrength[i] / maxSmile, 1f - smileStrength[i] / maxSmile, 1f - smileStrength[i] / maxSmile));
				g2.drawLine(i, LINE_HEIGHT + 1, i, 2 * LINE_HEIGHT);
			}
		}
		g2.setColor(Color.RED);
		g2.drawLine(currentFrame, 0, currentFrame, 2 * LINE_HEIGHT);
	}
	
	int currentFrame = 0;
	
	@Override
	public void nextEvent(Event e) {
		if (e != null && e.getSmile()) {
			smileStrength[currentFrame] = e.getSmileStrength();
			if (smileStrength[currentFrame] > maxSmile) {
				System.err.println("increasing maxsmile to " + smileStrength[currentFrame]);
			}
			maxSmile = Math.max(maxSmile, smileStrength[currentFrame]);
		}
		currentFrame++;
		repaint();
	}

}
