package days;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class day02_p1 {
	private static final List<Integer> day02_data = Arrays.asList(1, 12, 2, 3, 1, 1, 2, 3, 1, 3, 4, 3, 1, 5, 0, 3, 2, 1,
			10, 19, 2, 9, 19, 23, 2, 23, 10, 27, 1, 6, 27, 31, 1, 31, 6, 35, 2, 35, 10, 39, 1, 39, 5, 43, 2, 6, 43, 47,
			2, 47, 10, 51, 1, 51, 6, 55, 1, 55, 6, 59, 1, 9, 59, 63, 1, 63, 9, 67, 1, 67, 6, 71, 2, 71, 13, 75, 1, 75,
			5, 79, 1, 79, 9, 83, 2, 6, 83, 87, 1, 87, 5, 91, 2, 6, 91, 95, 1, 95, 9, 99, 2, 6, 99, 103, 1, 5, 103, 107,
			1, 6, 107, 111, 1, 111, 10, 115, 2, 115, 13, 119, 1, 119, 6, 123, 1, 123, 2, 127, 1, 127, 5, 0, 99, 2, 14,
			0, 0);

	public static List<Integer> compute(final List<Integer> input) {
		List<Integer> state = new ArrayList<Integer>(input);
		int pos = 0;
		boolean running = true;

		while (running) {
			switch (state.get(pos)) {
			case 1:
				final int add1 = state.get(state.get(pos + 1));
				final int add2 = state.get(state.get(pos + 2));
				state.set(state.get(pos + 3), add1 + add2);
				break;

			case 2:
				final int mult1 = state.get(state.get(pos + 1));
				final int mult2 = state.get(state.get(pos + 2));
				state.set(state.get(pos + 3), mult1 * mult2);
				break;
				
			case 99:
				running = false;
				break;
				
			default:
				StringBuilder sb = new StringBuilder("Unexpected op-code: " + state.get(pos) + " at pos: " + pos + "\nState: ");
				state.stream().forEach(x -> sb.append(x + ", "));
				sb.deleteCharAt(sb.length() - 1);
				throw new RuntimeException(sb.toString());
			}
			pos += 4;
		}
		return state;
	}

	public static void main(String[] args) {
		List<Integer> result = compute(day02_data);
		System.out.println(result.get(0));
		result.stream().forEach(x -> System.out.print(x + ","));
	}
}
