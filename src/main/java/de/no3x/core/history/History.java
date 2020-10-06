package de.no3x.core.history;

import java.util.List;
import java.util.Queue;
import java.util.stream.IntStream;

import com.google.common.collect.EvictingQueue;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

public class History {

	private static final int HISTORY_SIZE = 100;

	@SuppressWarnings("UnstableApiUsage")
	private final Queue<Boolean> history = EvictingQueue.create(HISTORY_SIZE);

	private History() {
		initializeHistoryWithZero();
	}

	public static History create() {
		return new History();
	}

	private void initializeHistoryWithZero() {
		IntStream.range(0, HISTORY_SIZE).mapToObj(i -> false).forEach(history::add);
	}

	public void addState(boolean isActivityRecognized) {
		history.add(isActivityRecognized);
	}

	public double calculateAverage() {
		final double avg = getHistoryAsList().stream()
				.mapToDouble(bool -> bool ? 1 : 0)
				.average().getAsDouble();
		return avg;
	}

	private ImmutableList<Boolean> getHistoryAsList() {
		final List<Boolean> historyAsList = Lists.newArrayList(history);
		return ImmutableList.copyOf(Lists.reverse(historyAsList));
	}

}
