package days;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

class IntcodeProcessor {
	private List<Integer> state;
	private IModeHandler modeHandler;
	
	public IntcodeProcessor(final List<Integer> input, IModeHandler modeHandler) {
		this.state = new ArrayList<Integer>(input);
		this.modeHandler = modeHandler;
	}
	
	public int get(int index) {
		return state.get(index);
	}
	
	public void add(final int pos) {
		int[] modes = modeHandler.getModes(state.get(pos), 3);
		int value1 = modeHandler.getValueByMode(modes[0], pos + 1, state);
		int value2 = modeHandler.getValueByMode(modes[1], pos + 2, state);
		modeHandler.setState(modes[2], pos + 3, value1 + value2, state);
	}

	public void multiply(final int pos) {
		int[] modes = modeHandler.getModes(state.get(pos), 3);
		int value1 = modeHandler.getValueByMode(modes[0], pos + 1, state);
		int value2 = modeHandler.getValueByMode(modes[1], pos + 2, state);
		modeHandler.setState(modes[2], pos + 3, value1 * value2, state);
	}

	public void input(final int pos, BufferedReader console, PrintStream out)
			throws IOException {
		int[] modes = modeHandler.getModes(state.get(pos), 1);
		out.print("Input: ");
		final int inputNr = Integer.parseInt(console.readLine());
		modeHandler.setState(modes[0], pos + 1, inputNr, state);
	}

	public void output(final int pos, PrintStream out) {
		int[] modes = modeHandler.getModes(state.get(pos), 1);
		out.println("Output: " + modeHandler.getValueByMode(modes[0], pos + 1, state));
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

	public void lessThen(final int pos) {
		int[] modes = modeHandler.getModes(state.get(pos), 3);
		int value1 = modeHandler.getValueByMode(modes[0], pos + 1, state);
		int value2 = modeHandler.getValueByMode(modes[1], pos + 2, state);
		if (value1 < value2) {
			modeHandler.setState(modes[2], pos + 3, 1, state);
		} else {
			modeHandler.setState(modes[2], pos + 3, 0, state);
		}
	}

	public void equals(final int pos) {
		int[] modes = modeHandler.getModes(state.get(pos), 3);
		int value1 = modeHandler.getValueByMode(modes[0], pos + 1, state);
		int value2 = modeHandler.getValueByMode(modes[1], pos + 2, state);
		if (value1 == value2) {
			modeHandler.setState(modes[2], pos + 3, 1, state);
		} else {
			modeHandler.setState(modes[2], pos + 3, 0, state);
		}
	}
}
