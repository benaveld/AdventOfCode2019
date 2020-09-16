package days;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class Day07_Test_p1 {
	@Test
	public void testAmpCircuit1() throws IOException {
		List<Integer> input = Arrays.asList(3,15,3,16,1002,16,10,16,1,16,15,15,4,15,99,0,0);
		int result = Day07_p1.ampCircuit(input, 4,3,2,1,0);
		assertEquals(43210, result);
	}
	
	@Test
	public void testAmpCircuit2() throws IOException {
		List<Integer> input = Arrays.asList(3,23,3,24,1002,24,10,24,1002,23,-1,23,101,5,23,23,1,24,23,23,4,23,99,0,0);
		int result = Day07_p1.ampCircuit(input, 0,1,2,3,4);
		assertEquals(54321, result);
	}
	
	@Test
	public void testAmpCircuit3() throws IOException {
		List<Integer> input = Arrays.asList(3,31,3,32,1002,32,10,32,1001,31,-2,31,1007,31,0,33,1002,33,7,33,1,33,31,31,1,32,31,31,4,31,99,0,0,0);
		int result = Day07_p1.ampCircuit(input, 1,0,4,3,2);
		assertEquals(65210, result);
	}
	
	@Test
	public void testFindAmp1() throws IOException {
		List<Integer> input = Arrays.asList(3,15,3,16,1002,16,10,16,1,16,15,15,4,15,99,0,0);
		int result = Day07_p1.getHighestAmpSignal(input, 5);
		assertEquals(43210, result);
	}
	
	@Test
	public void testFindAmp2() throws IOException {
		List<Integer> input = Arrays.asList(3,23,3,24,1002,24,10,24,1002,23,-1,23,101,5,23,23,1,24,23,23,4,23,99,0,0);
		int result = Day07_p1.getHighestAmpSignal(input, 5);
		assertEquals(54321, result);
	}
	
	@Test
	public void testFindAmp3(){
		List<Integer> input = Arrays.asList(3,31,3,32,1002,32,10,32,1001,31,-2,31,1007,31,0,33,1002,33,7,33,1,33,31,31,1,32,31,31,4,31,99,0,0,0);
		int result = Day07_p1.getHighestAmpSignal(input, 5);
		assertEquals(65210, result);
	}
	
//	@Test
//	public void testPumitations() {
//		List<Integer[]> result = Day07_p1.getEveryPrumitation(3);
//		List<Integer[]> expected = new ArrayList<Integer[]>(6);
//		expected.add(new Integer[] {0,1,2});
//		expected.add(new Integer[] {0,2,1});
//		expected.add(new Integer[] {1,0,2});
//		expected.add(new Integer[] {1,2,0});
//		expected.add(new Integer[] {2,0,1});
//		expected.add(new Integer[] {2,1,0});
//		
//		for(Integer[] v : expected) {
//			assertEquals(true, result.contains(v));
//		}
//	}
}
