package days;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class Day08_p1 {

	public static List<Integer[]>  decode(Reader reader, int width, int height) throws IOException {
		List<Integer[]> layers = new ArrayList<Integer[]>();
		int layer = 0;
		int index = 0;
		layers.add(new Integer[width*height]);
		int c;
		while((c = reader.read()) != -1) {
			layers.get(layer)[index++] = c - '0';
			if(index >= layers.get(layer).length) {
				layer++;
				layers.add(new Integer[width*height]);
				index = 0;
			}
		}
		layers.remove(layers.size()-1);
		return layers;
	}
	
	public static int count(Integer[] array, int value) {
		int nr = 0;
		for(Integer v : array) {
			if(v == value) {
				nr++;
			}
		}
		return nr;
	}
	
	public static int checksum(List<Integer[]> layers) {
		Integer[] minLayer = null;
		int minZeros = Integer.MAX_VALUE;
		for(Integer[] layer : layers) {
			int nrZeros = count(layer, 0);
			if(nrZeros < minZeros) {
				minZeros = nrZeros;
				minLayer = layer;
			}
		}
		if(minLayer == null) {
			throw new RuntimeException("could not find a layer with the most amount of 0s.");
		}
		return count(minLayer, 1) * count(minLayer, 2);
	}
	
	public static Integer[] generateImage(List<Integer[]> layers) {
		Integer[] layer = layers.get(0);
		for(int i = 0; i < layer.length; i++) {
			int deep = 1;
			while(layer[i] == 2) {
				layer[i] = layers.get(deep++)[i];
			}
		}
		return layer;
		
	}
	
	public static void main(String[] args) {
		String filename = "day08_data.txt";
		try(BufferedReader br = new BufferedReader(new FileReader(new File(filename)))){
			List<Integer[]> layers = decode(br, 25, 6);
			Integer[] image = generateImage(layers);
			
			StringBuilder sb = new StringBuilder();
			sb.append(image[0] == 1 ? "*" : " ");
			for(int i = 1; i < image.length; i++) {
				sb.append(image[i] == 1 ? "*" : " ");
				if(i % 25 == 0) {
					sb.append("\n");
				}
			}
			System.out.println(sb);
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

}