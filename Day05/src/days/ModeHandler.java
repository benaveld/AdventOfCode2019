package days;

import java.util.List;
import java.util.stream.IntStream;

class ModeHandler implements IModeHandler {
	@Override
	public int[] getModes(final int opCode, final int nrOfModes) {
		return IntStream.iterate(2, n -> n + 1).limit(nrOfModes + 2).parallel().map(x -> getDiget(opCode, x)).toArray();
	}

	private static int getDiget(final int digets, final int index) {
		return Math.floorDiv(digets % (int) Math.pow(10, index + 1), (int) Math.pow(10, index));
	}

	@Override
	public int getValueByMode(final int mode, final int pos, final List<Integer> state) {
		int value = state.get(pos);
		switch (mode) {
		case 0:
			return state.get(value);

		case 1:
			return value;

		default:
			throw new IllegalArgumentException("Invalid mode, it can only be 0 or 1.");
		}
	}

	// Warning: changes the "state" parameter.
	@Override
	public void setState(final int mode, final int pos, final int value, List<Integer> state) {
		switch (mode) {
		case 0:
			state.set(state.get(pos), value);
			break;

// i think it says that mode 1 is invalid.
//		case 1:
//			state.set(pos, value);
//			break;

		default:
			throw new IllegalStateException("Input mode is invalid.");
		}
	}
}
