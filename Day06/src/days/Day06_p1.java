package days;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day06_p1 {
	public final static String filename = "day06_data.txt";

	public static int orbitMapChecksum(Stream<String> inputStream) {
		Map<String, String> file = inputStream.collect(Collectors.toMap(x -> x.split("\\)")[1], x -> x.split("\\)")[0],
				(oldValue, newValue) -> newValue, HashMap::new));
		file.put("COM", null);
		return orbitMapChecksum(file);
	}

	public static int orbitMapChecksum(final Map<String, String> input) {
		return input.keySet().parallelStream().mapToInt(key -> getDistensToCom(key, input)).sum();
	}

	private static int getDistensToCom(final String key, final Map<String, String> map) {
		int sum = 0;
		String name = key;
		while ((name = map.get(name)) != null) {
			sum++;
		}
		return sum;
	}

	public static void main(String[] args) {
		try(Stream<String> fileStream = Files.lines(Paths.get(filename))){
			int result = orbitMapChecksum(fileStream);
			System.out.format("Checksum: %d\n", result);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
