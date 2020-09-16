package days.computer;

import java.util.ArrayList;
import java.util.List;

public class IntcodeProcessor implements Runnable {
	private List<Integer> state;
	private IModeHandler modeHandler;
	private IValueProvider<Integer> inputValues;
	private IValueWriter<Integer> outputValue;

	public IntcodeProcessor(final List<Integer> input, IModeHandler modeHandler, IValueProvider<Integer> inputValues,
			IValueWriter<Integer> outputValue) {
		this.state = new ArrayList<Integer>(input);
		this.modeHandler = modeHandler;
		this.inputValues = inputValues;
		this.outputValue = outputValue;
	}

	@Override
	public void run() {
		int pos = 0;
		boolean running = true;

		while (running) {
			int opCode = get(pos);

			switch (opCode % 100) {
			case 1: // Add
				pos = add(pos);
				break;

			case 2: // Multiply
				pos = multiply(pos);
				break;

			case 3: // Input
				pos = input(pos);
				break;

			case 4: // Output
				pos = output(pos);
				break;

			case 5: // Jump if true
				pos = jumpIfTrue(pos);
				break;

			case 6: // Jump if false
				pos = jumpIfFalse(pos);
				break;

			case 7: // Less then
				pos = lessThen(pos);
				break;

			case 8: // Equals
				pos = equals(pos);
				break;

			case 99: // Exit
				running = false;
				break;

			default:
				throw new RuntimeException("Unexpected op-code: " + get(pos) + " at pos: " + pos);

			}
		}
	}

	public int get(int index) {
		return state.get(index);
	}

	public int add(final int pos) {
		int[] modes = modeHandler.getModes(state.get(pos), 3);
		int value1 = modeHandler.getValueByMode(modes[0], pos + 1, state);
		int value2 = modeHandler.getValueByMode(modes[1], pos + 2, state);
		modeHandler.setState(modes[2], pos + 3, value1 + value2, state);
		return pos + 4;
	}

	public int multiply(final int pos) {
		int[] modes = modeHandler.getModes(state.get(pos), 3);
		int value1 = modeHandler.getValueByMode(modes[0], pos + 1, state);
		int value2 = modeHandler.getValueByMode(modes[1], pos + 2, state);
		modeHandler.setState(modes[2], pos + 3, value1 * value2, state);
		return pos + 4;
	}

	public int input(final int pos) {
		int[] modes = modeHandler.getModes(state.get(pos), 1);
		modeHandler.setState(modes[0], pos + 1, inputValues.getNextValue(), state);
		return pos + 2;
	}

	public int output(final int pos) {
		int[] modes = modeHandler.getModes(state.get(pos), 1);
		outputValue.write(modeHandler.getValueByMode(modes[0], pos + 1, state));
		return pos + 2;
	}

	public int jumpIfTrue(final int pos) {
		int[] modes = modeHandler.getModes(state.get(pos), 2);
		if (modeHandler.getValueByMode(modes[0], pos + 1, state) != 0) {
			return modeHandler.getValueByMode(modes[1], pos + 2, state);
		} else {
			return pos + 3;
		}
	}

	public int jumpIfFalse(final int pos) {
		int[] modes = modeHandler.getModes(state.get(pos), 2);
		if (modeHandler.getValueByMode(modes[0], pos + 1, state) == 0) {
			return modeHandler.getValueByMode(modes[1], pos + 2, state);
		} else {
			return pos + 3;
		}
	}

	public int lessThen(final int pos) {
		int[] modes = modeHandler.getModes(state.get(pos), 3);
		int value1 = modeHandler.getValueByMode(modes[0], pos + 1, state);
		int value2 = modeHandler.getValueByMode(modes[1], pos + 2, state);
		if (value1 < value2) {
			modeHandler.setState(modes[2], pos + 3, 1, state);
		} else {
			modeHandler.setState(modes[2], pos + 3, 0, state);
		}
		return pos + 4;
	}

	public int equals(final int pos) {
		int[] modes = modeHandler.getModes(state.get(pos), 3);
		int value1 = modeHandler.getValueByMode(modes[0], pos + 1, state);
		int value2 = modeHandler.getValueByMode(modes[1], pos + 2, state);
		if (value1 == value2) {
			modeHandler.setState(modes[2], pos + 3, 1, state);
		} else {
			modeHandler.setState(modes[2], pos + 3, 0, state);
		}
		return pos + 4;
	}
}
