package de.no3x.core;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import de.no3x.core.history.History;
import de.no3x.core.input.ActivitySupplier;
import de.no3x.core.output.BrightnessConsumer;

public class LEDBrightnessCalculator {

	private History history;
	private ActivitySupplier activitySupplier;
	private BrightnessConsumer brightnessConsumer;

	/**
	 * @param history stores activity-states and provides access to statistics
	 * @param activitySupplier the activity supplier for a given state (boolean, 1 = hasActivity, 0 = noActivity)
	 * @param brightnessConsumer output for the calculated brightness of led
	 */
	public LEDBrightnessCalculator(History history, ActivitySupplier activitySupplier, BrightnessConsumer brightnessConsumer) {
		this.history = history;
		this.activitySupplier = activitySupplier;
		this.brightnessConsumer = brightnessConsumer;
	}

	public void start() {
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		executor.scheduleAtFixedRate(this::update, 0, 500, TimeUnit.MILLISECONDS);
	}

	private void update() {
		history.addState(activitySupplier.supplyInput());
		brightnessConsumer.output(history.calculateAverage());
	}
}
