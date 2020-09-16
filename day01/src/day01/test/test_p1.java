package day01.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import day01.day01_p1;

public class test_p1 {
	@Test
	public void testMassToFull12() {
		final int result = day01_p1.calculate(12);
		assertEquals(2, result);
	}
	
	@Test
	public void testMassToFull14() {
		final int result = day01_p1.calculate(14);
		assertEquals(2, result);
	}
	
	@Test
	public void testMassToFull1969() {
		final int result = day01_p1.calculate(1969);
		assertEquals(654, result);
	}
	
	@Test
	public void testMassToFull100756() {
		final int result = day01_p1.calculate(100756);
		assertEquals(33583, result);
	}
	
	@Test
	public void testMassToFullSum() {
		List<Integer> list = Arrays.asList(12, 14, 1969, 100756);
		final int result = day01_p1.fuelSumFromMass(list.stream().mapToInt(arg -> arg));
		assertEquals(34241, result);
	}
}
