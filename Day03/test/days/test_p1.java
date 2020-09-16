package days;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class test_p1 {
	@Test
	public void test1() {
		final String[] wire1 = {"R8","U5","L5","D3"};
		final String[] wire2 = {"U7","R6","D4","L4"};
		int result = day03_p1.checkWires(wire1, wire2);
		assertEquals(6, result);
	}
	
	@Test
	public void test2() {
		final String[] wire1 = {"R75","D30","R83","U83","L12","D49","R71","U7","L72"};
		final String[] wire2 = {"U62","R66","U55","R34","D71","R55","D58","R83"};
		int result = day03_p1.checkWires(wire1, wire2);
		assertEquals(159, result);
	}
	
	@Test
	public void test3() {
		final String[] wire1 = {"R98","U47","R26","D63","R33","U87","L62","D20","R33","U53","R51"};
		final String[] wire2 = {"U98","R91","D20","R16","D67","R40","U7","R15","U6","R7"};
		int result = day03_p1.checkWires(wire1, wire2);
		assertEquals(135, result);
	}
	
	@Test
	public void testGetMinValue() throws Exception {
		List<Integer> input = Arrays.asList(4,2,6,1,5,3);
		Method getMinValue = day03_p1.class.getDeclaredMethod("getMinValue", List.class);
		getMinValue.setAccessible(true);
		int answer = (int) getMinValue.invoke(null, input);
		assertEquals(1, answer);
	}
}
