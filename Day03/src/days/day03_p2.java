package days;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class day03_p2 {

	public static int checkWires(final String[] strWire1, final String[] strWire2) {
		List<Direction> wire1 = Arrays.asList(strWire1).parallelStream().map(str -> new Direction(str))
				.collect(Collectors.toList());
		List<Direction> wire2 = Arrays.asList(strWire2).parallelStream().map(str -> new Direction(str))
				.collect(Collectors.toList());
		return checkWires(wire1, wire2);
	}

	public static int checkWires(final List<Direction> wire1, final List<Direction> wire2) {
		List<Integer> distinsesToOrigin = new LinkedList<Integer>();
		Point pointA1 = new Point(0, 0);
		int distans1 = 0;
		for (Direction d : wire1) {
			Point pointA2 = d.getNewPoint(pointA1);
			Line line1 = new Line(pointA1, pointA2);
			int distans2 = 0;
			Point pointB1 = new Point(0,0);
			
			for (Direction d2 : wire2) {
				Point pointB2 = d2.getNewPoint(pointB1);
				Line line2 = new Line(pointB1, pointB2);
				Point cross = line1.crossing(line2);
				if (cross != null) {
					int distans3;
					if(line1.plane == Plane.Horizontal) {
						distans3 = subPointsWithAbs(pointA1.x, cross.x);
						distans3 += subPointsWithAbs(pointB1.y, cross.y);
					} else {
						distans3 = subPointsWithAbs(pointA1.y, cross.y);
						distans3 += subPointsWithAbs(pointB1.x, cross.x);
					}
					distinsesToOrigin.add(distans1 + distans2 + distans3);
				}
				distans2 += d2.steps;
				pointB1 = pointB2;
			}
			
			distans1 += d.steps;
			pointA1 = pointA2;
		};
		distinsesToOrigin.removeIf(x -> x == 0);
		return getMinValue(distinsesToOrigin);
	}

	public static int subPointsWithAbs(int a, int b) {
		return Math.abs(Math.abs(a) - Math.abs(b));
	}
	
//	private static List<Line> directionToLineList(final List<Direction> directions){
//		List<Line> lineWire = new LinkedList<Line>();
//		Point lastPoint = new Point(0, 0);
//		for (Direction d : directions) {
//			Point p = d.getNewPoint(lastPoint);
//			lineWire.add(new Line(lastPoint, p));
//			lastPoint = p;
//		}
//		return lineWire;
//	}
	
	private static int getMinValue(List<Integer> list) {
		return list.stream().min(new Comparator<Integer>() {
			@Override
			public int compare(Integer v1, Integer v2) {
				return v1 < v2 ? -1 : v1 > v2 ? +1 : 0;
			}
		}).get();
	}
	
	public static void main(String[] args) {
		File file = new File("day03_data.txt");
		try (BufferedReader br = new BufferedReader(new FileReader(file))){
			String[] wire1 = br.readLine().split(",");
			String[] wire2 = br.readLine().split(",");
			int answer = checkWires(wire1, wire2);
			System.out.format("Answer: %d\n", answer);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}
