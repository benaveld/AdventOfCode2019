package days;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day06_p2 {
	public final static String filename = "day06_data.txt";
//	public final static String filename = "test_data.txt";

	public static int orbitMapPathCost(Stream<String> inputStream, String from, String to) {
		Map<String, String> file = inputStream.collect(Collectors.toMap(x -> x.split("\\)")[1], x -> x.split("\\)")[0],
				(oldValue, newValue) -> newValue, HashMap::new));
		return orbitMapPathCost(file, from, to);
	}

	public static int orbitMapPathCost(final Map<String, String> input, String from, String to) {
		Tree<String> tree = new Tree<String>("COM");
		input.forEach((key, value) -> tree.put(key, value));

		System.out.println("All Connected: " + tree.isAllConnected());
		
		List<Node<String>> path = tree.getPath(from, to);
		return path.size() - 3;
	}

	public static void main(String[] args) {
		try (Stream<String> fileStream = Files.lines(Paths.get(filename))) {
			int result = orbitMapPathCost(fileStream, "YOU", "SAN");
			System.out.format("Distans: %d\n", result);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
