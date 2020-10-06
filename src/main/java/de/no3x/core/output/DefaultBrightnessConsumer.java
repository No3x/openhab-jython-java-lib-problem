package de.no3x.core.output;

public class DefaultBrightnessConsumer implements BrightnessConsumer {
	public void output(double brightness) {
		System.out.println(brightness);
	}
}
