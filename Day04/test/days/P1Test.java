package days;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class P1Test {
	@Test
	public void checkValidTest1() {
		boolean answer = day04_p1.validPassword(111111);
		assertEquals(true, answer);
	}
	
	@Test
	public void checkValidTest2() {
		boolean answer = day04_p1.validPassword(223450);
		assertEquals(false, answer);
	}
	
	@Test
	public void checkValidTest3() {
		boolean answer = day04_p1.validPassword(123789);
		assertEquals(false, answer);
	}
	
	@Test
	public void checkValidTest4() {
		boolean answer = day04_p1.validPassword(122345);
		assertEquals(true, answer);
	}
	
	@Test
	public void checkValidTest5() {
		boolean answer = day04_p1.validPassword(111123);
		assertEquals(true, answer);
	}
	
	@Test
	public void getDigetTest() {
		for(int i = 0; i < 6; i++) {
			int answer = day04_p1.getDiget(654321, i);
			assertEquals(i+1, answer);
		}
	}
}
