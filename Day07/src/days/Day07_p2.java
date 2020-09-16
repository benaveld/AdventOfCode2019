package days;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

import days.computer.FeedbackLoopProvider;
import days.computer.GenericValueProvider;
import days.computer.GenericWriter;
import days.computer.IValueProvider;
import days.computer.IntcodeProcessor;
import days.computer.ModeHandler;
import days.computer.Utils;

public class Day07_p2 {
	public static final String filename = "day07_data.txt";

	public static int ampCircuit(final List<Integer> program, Integer... settings) {
		int result = 0;
		for (int input : settings) {
			IValueProvider<Integer> valueProvider = new GenericValueProvider<Integer>(new Integer[] { input, result });
			GenericWriter<Integer> valueOutput = new GenericWriter<Integer>();
			IntcodeProcessor intcodeProcessor = new IntcodeProcessor(program, new ModeHandler(), valueProvider,
					valueOutput);
			intcodeProcessor.run();

			result = valueOutput.getLast();
		}
		return result;
	}

	public static int getHighestAmpSignal(final List<Integer> program, int length) {
		List<Integer[]> list = Utils.getEveryPrumitation(length);
		return list.parallelStream().mapToInt(x -> ampCircuit(program, x)).max().getAsInt();
	}

	public static int feedbackAmpCircuit(ExecutorService executor, final List<Integer> program, int timeout,
			int... settings) {
		FeedbackLoopProvider[] queues = new FeedbackLoopProvider[settings.length];
		queues[0] = new FeedbackLoopProvider(settings[0], 0);
		for (int i = 1; i < settings.length; i++) {
			queues[i] = new FeedbackLoopProvider(settings[i]);
		}

		List<Future<?>> resultList = new ArrayList<Future<?>>(queues.length);
		for (int i = 0; i < queues.length; i++) {
			IntcodeProcessor processor;
			if (i + 1 < queues.length) {
				processor = new IntcodeProcessor(program, new ModeHandler(), queues[i], queues[i + 1]);
			} else {
				processor = new IntcodeProcessor(program, new ModeHandler(), queues[i], queues[0]);
			}
			resultList.add(executor.submit(processor));
		}

		try {
			resultList.get(resultList.size() - 1).get(timeout, TimeUnit.MILLISECONDS);
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			e.printStackTrace();
			System.exit(-1);
		}

		return queues[0].poll();
	}

	public static int getHighestFeedbackAmpSignal(final List<Integer> program, int... settings) {
		List<Integer[]> list = Utils.getEveryPrumitation(settings.length);
		ExecutorService executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(settings.length * list.size());
		return list.parallelStream().mapToInt(x -> {
			int[] thisSettings = Utils.shuffal(settings, x);
			return feedbackAmpCircuit(executor, program, 10000, thisSettings);
		}).max().getAsInt();
	}

	public static void main(String[] args) {
		File file = new File(filename);
		try (BufferedReader fileBufferedReader = new BufferedReader(new FileReader(file))) {
			List<Integer> input = Arrays.stream(fileBufferedReader.readLine().split(",")).map(Integer::parseInt)
					.collect(Collectors.toList());
			int result = getHighestFeedbackAmpSignal(input, 5, 6, 7, 8, 9);
			System.out.println(result);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
