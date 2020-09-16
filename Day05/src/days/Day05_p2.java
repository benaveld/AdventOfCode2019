package days;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day05_p2 {
	public static final String filename = "day05_data.txt";

	public static void compute(final List<Integer> input, BufferedReader bufferedReader, PrintStream printStream)
			throws IOException {
		IntcodeProcessor intcodeProcessor = new IntcodeProcessor(input, new ModeHandler());
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
				intcodeProcessor.input(pos, bufferedReader, printStream);
				pos += 2;
				break;

			case 4: // Output
				intcodeProcessor.output(pos, printStream);
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

	// use the input of 5
	public static void main(String[] args) {
		File file = new File("day05_data.txt");
		try (BufferedReader fileBufferedReader = new BufferedReader(new FileReader(file));
				BufferedReader console = new BufferedReader(new InputStreamReader(System.in))) {
			List<Integer> input = Arrays.stream(fileBufferedReader.readLine().split(",")).map(Integer::parseInt)
					.collect(Collectors.toList());
			compute(input, console, System.out);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
