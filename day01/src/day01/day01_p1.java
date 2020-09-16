package day01;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class day01_p1 {

	public static int calculate(final int mass) {
		return (int) (Math.floor(mass / 3.0) - 2);
	}

	public static int fuelSumFromMass(IntStream stream) {
		return stream.map(day01_p1::calculate).sum();
	}
	
	public static void main(String[] args) {
		final String filename = "day01_data.txt";
		
		try (Stream<String> stream = Files.lines(Paths.get(filename))){
			int result = fuelSumFromMass(stream.mapToInt(Integer::parseInt));
			System.out.println(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Writes the results to a file
	public static void main2(String[] args) throws URISyntaxException {
		final String filename = "day01_data.txt";
		
		System.out.println("Start");
		
		try (Stream<String> stream = Files.lines(Paths.get(filename)); BufferedWriter writer = new BufferedWriter(new FileWriter("result.txt"))){
			stream.mapToInt(Integer::parseInt).map(day01_p1::calculate).forEach(arg0 -> {
				try {
					writer.write(String.valueOf(arg0) + "\n");
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Done");
	}

}
