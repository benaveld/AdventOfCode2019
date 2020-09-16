package days.computer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleProvider implements IValueWriter<Integer>, IValueProvider<Integer> {
	private BufferedReader in;

	public ConsoleProvider() {
		in = new BufferedReader(new InputStreamReader(System.in));
	}

	@Override
	public void write(Integer value) {
		System.out.println("Output: " + value);
	}

	@Override
	public Integer getNextValue() {
		System.out.print("Input: ");
		try {
			return Integer.parseInt(in.readLine().trim());
		} catch (NumberFormatException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
