package days;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import days.Direction;
import days.Dir;

public class Test_Direction {
	@Test
	public void testUp() {
		Direction anwser = new Direction("U13");
		assertEquals(new Direction(Dir.Up, 13), anwser);
	}
	
	@Test
	public void testDown() {
		Direction anwser = new Direction("D13");
		assertEquals(new Direction(Dir.Down, 13), anwser);
	}
	
	@Test
	public void testRight() {
		Direction anwser = new Direction("R13");
		assertEquals(new Direction(Dir.Right, 13), anwser);
	}
	
	@Test
	public void testLeft() {
		Direction anwser = new Direction("L1010");
		assertEquals(new Direction(Dir.Left, 1010), anwser);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testStrException() {
		new Direction("F13");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNr0Exception() {
		new Direction("D0");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNegativNrException() {
		new Direction("D-4");
	}
	
	@Test
	public void testNewPointUp() {
		Direction d = new Direction(Dir.Up, 13);
		Point answer = d.getNewPoint(new Point(10, 10));
		assertEquals(new Point(10, -3), answer);
	}
	
	@Test
	public void testNewPointDown() {
		Direction d = new Direction(Dir.Down, 13);
		Point answer = d.getNewPoint(new Point(10, 10));
		assertEquals(new Point(10, 23), answer);
	}
	
	@Test
	public void testNewPointRight() {
		Direction d = new Direction(Dir.Right, 13);
		Point answer = d.getNewPoint(new Point(10, 10));
		assertEquals(new Point(23, 10), answer);
	}
	
	@Test
	public void testNewPointLeft() {
		Direction d = new Direction(Dir.Left, 13);
		Point answer = d.getNewPoint(new Point(10, 10));
		assertEquals(new Point(-3, 10), answer);
	}
}
