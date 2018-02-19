package edu.cmu.inmind.openfacequalifier.output;

import java.io.PrintStream;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import edu.cmu.inmind.openfacequalifier.FeatureType;

public class CSVOutput  {

	final PrintStream out;

	private static boolean[] getReferenceFrames() {
		boolean[] referenceFrames = new boolean[1200];
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
	int frameCounter = 0; 
	
	public CSVOutput(PrintStream out) {
		this.out = out;
		this.out.print(
				String.join(", ", 
						Stream.of(FeatureType.values()).map(FeatureType::name).collect(Collectors.toList())
			));
		this.out.println(", class");
	}

	public void consumeFrame(Map<FeatureType, Float> f) {
		this.out.print(
				String.join(", ", 
						Stream.of(FeatureType.values()).map(ft -> f.get(ft)).map(fl -> fl.toString()).collect(Collectors.toList())
			));
		this.out.println(", " + (getReferenceFrames()[frameCounter] ? "smile" : "nosmile"));
		frameCounter++;
	}
	
}
