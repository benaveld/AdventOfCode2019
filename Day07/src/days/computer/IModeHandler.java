package days.computer;

import java.util.List;

public interface IModeHandler {
	abstract int getValueByMode(final int mode, final int pos, final List<Integer> state);
	abstract void setState(final int mode, final int pos, final int value, List<Integer> state);
	abstract int[] getModes(final int opCode, final int nrOfModes);
}
