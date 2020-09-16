package days.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import days.day02_p1;

public class test_p1 {
	@Test
	public void testAdd() {
		final List<Integer> input = Arrays.asList(1,0,0,0,99);
		final List<Integer> result = day02_p1.compute(input);
		final List<Integer> expected = Arrays.asList(2,0,0,0,99);
		assertEquals(expected, result);
	}
	
	@Test
	public void testMult() {
		final List<Integer> input = Arrays.asList(2,3,0,3,99);
		final List<Integer> result = day02_p1.compute(input);
		final List<Integer> expected = Arrays.asList(2,3,0,6,99);
		assertEquals(expected, result);
	}
	
	@Test
	public void testMult2() {
		final List<Integer> input = Arrays.asList(2,4,4,5,99,0);
		final List<Integer> result = day02_p1.compute(input);
		final List<Integer> expected = Arrays.asList(2,4,4,5,99,9801);
		assertEquals(expected, result);
	}
	
	@Test
	public void testBig() {
		final List<Integer> input = Arrays.asList(1,1,1,4,99,5,6,0,99);
		final List<Integer> result = day02_p1.compute(input);
		final List<Integer> expected = Arrays.asList(30,1,1,4,2,5,6,0,99);
		assertEquals(expected, result);
	}
}
