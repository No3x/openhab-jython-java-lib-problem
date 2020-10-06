package de.no3x.core;

import de.no3x.core.history.History;

// Illustrates the usage of my library classes
public class Main {

	public static void main(String[] args) {
		new Main().run();
	}

	private void run() {
		History history = History.create();
		LEDBrightnessCalculator brightnessCalculator = new LEDBrightnessCalculator(history, () -> true, System.out::println);
		brightnessCalculator.start();
	}

}

