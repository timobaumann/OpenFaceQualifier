package edu.cmu.inmind.openfacequalifier.input;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;

import edu.cmu.inmind.openfacequalifier.FeatureType;

public class TestFeaturesFactory {

	@Test
	public void test() {
		FeaturesFactory featfac = FeaturesFactory.newFeaturesFactoryFromLine("frame, timestamp, confidence");
		Map<FeatureType,Float> f = featfac.newFromLine("1, 2, 3");
		assertEquals(f.get(FeatureType.frame), 1, 0.0001);
		assertEquals(f.get(FeatureType.timestamp), 2, 0.0001);
		assertEquals(f.get(FeatureType.confidence), 3, 0.0001);
		f = featfac.newFromLine("1, 2, 3\n");
		assertEquals(f.get(FeatureType.frame), 1, 0.0001);
		assertEquals(f.get(FeatureType.timestamp), 2, 0.0001);
		assertEquals(f.get(FeatureType.confidence), 3, 0.0001);
	}
}
