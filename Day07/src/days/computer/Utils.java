package days.computer;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Utils {
	public static ArrayList<Integer[]> getEveryPrumitation(int length) {
		return getEveryPrumitation(new LinkedList<Integer>(), length);
	}

	private static ArrayList<Integer[]> getEveryPrumitation(List<Integer> start, int length) {
		ArrayList<Integer[]> result = new ArrayList<Integer[]>();

		for (int i = 0; i < length; i++) {
			if (!start.contains(i)) {
				List<Integer> list = new LinkedList<Integer>(start);
				list.add(i);
				if (start.size() == length - 1) {
					Integer[] value = (Integer[]) list.toArray(new Integer[list.size()]);
					result.add(value);
				} else {
					result.addAll(getEveryPrumitation(list, length));
				}
			}
		}
		return result;
	}

	public static Reader getReader(Object... objs) {
		StringBuilder sb = new StringBuilder();
		for (Object v : objs) {
			sb.append(v.toString() + "\n");
		}
		return new StringReader(sb.toString());
	}
	
	public static int[] shuffal(int[] values, Integer[] x) {
		if(values.length != x.length) {
			throw new IllegalArgumentException("both arrays need to be the same length.");
		}
		int[] result = new int[values.length];
		for(int i = 0; i < x.length; i++) {
			result[i] = values[x[i]];
		}
		return result;
	}
}
