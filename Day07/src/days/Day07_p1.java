package days;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import days.computer.GenericValueProvider;
import days.computer.GenericWriter;
import days.computer.IValueProvider;
import days.computer.IValueWriter;
import days.computer.IntcodeProcessor;
import days.computer.ModeHandler;
import days.computer.Utils;

public class Day07_p1 {
	public static final String filename = "day07_data.txt";

	public static void compute(final List<Integer> input, IValueProvider<Integer> inputValues,
			IValueWriter<Integer> outputValues) throws IOException {
		IntcodeProcessor intcodeProcessor = new IntcodeProcessor(input, new ModeHandler(), inputValues, outputValues);
		int pos = 0;
		boolean running = true;

		while (running) {
			int opCode = intcodeProcessor.get(pos);

			switch (opCode % 100) {
			case 1: // Add
				intcodeProcessor.add(pos);
				pos += 4;
				break;

			case 2: // Multiply
				intcodeProcessor.multiply(pos);
				pos += 4;
				break;

			case 3: // Input
				intcodeProcessor.input(pos);
				pos += 2;
				break;

			case 4: // Output
				intcodeProcessor.output(pos);
				pos += 2;
				break;

			case 5: // Jump if true
				pos = intcodeProcessor.jumpIfTrue(pos);
				break;

			case 6: // Jump if false
				pos = intcodeProcessor.jumpIfFalse(pos);
				break;

			case 7: // Less then
				intcodeProcessor.lessThen(pos);
				pos += 4;
				break;

			case 8: // Equals
				intcodeProcessor.equals(pos);
				pos += 4;
				break;

			case 99: // Exit
				running = false;
				break;

			default:
				throw new RuntimeException("Unexpected op-code: " + intcodeProcessor.get(pos) + " at pos: " + pos);

			}
		}
	}

	public static int ampCircuit(final List<Integer> program, Integer... settings) throws IOException {
		int result = 0;
		for (int input : settings) {
			IValueProvider<Integer> valueProvider = new GenericValueProvider<Integer>(new Integer[] { input, result });
			GenericWriter<Integer> valueOutput = new GenericWriter<Integer>();
			compute(program, valueProvider, valueOutput);

			result = valueOutput.getLast();
		}
		return result;
	}

	public static int getHighestAmpSignal(final List<Integer> program, int length) {
		List<Integer[]> list = Utils.getEveryPrumitation(length);
		return list.parallelStream().mapToInt(x -> {
			try {
				return ampCircuit(program, x);
			} catch (IOException e) {
				e.printStackTrace();
				return -1;
			}
		}).max().getAsInt();
	}

	public static void main(String[] args) {
		File file = new File(filename);
		try (BufferedReader fileBufferedReader = new BufferedReader(new FileReader(file));
				BufferedReader console = new BufferedReader(new InputStreamReader(System.in))) {
			List<Integer> input = Arrays.stream(fileBufferedReader.readLine().split(",")).map(Integer::parseInt)
					.collect(Collectors.toList());
			int result = getHighestAmpSignal(input, 5);
			System.out.println(result);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
