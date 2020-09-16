package days;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class LineTest {
	@Test
	public void LineTestY() {
		Point a = new Point(2, 2);
		Point b = new Point(2, 4);
		Line answer = new Line(a,b);
		assertEquals(a, answer.a);
		assertEquals(b, answer.b);
		assertEquals(Plane.Vertical, answer.plane);
	}
	
	@Test
	public void LineTestX() {
		Point a = new Point(2, 2);
		Point b = new Point(4, 2);
		Line answer = new Line(a,b);
		assertEquals(a, answer.a);
		assertEquals(b, answer.b);
		assertEquals(Plane.Horizontal, answer.plane);
	}
	
	@Test
	public void LineTestYDiff() {
		Point a = new Point(2, 4);
		Point b = new Point(2, 2);
		Line answer = new Line(a,b);
		assertEquals(a, answer.b);
		assertEquals(b, answer.a);
		assertEquals(Plane.Vertical, answer.plane);
	}
	
	@Test
	public void LineTestXDiff() {
		Point a = new Point(4, 2);
		Point b = new Point(2, 2);
		Line answer = new Line(a,b);
		assertEquals(a, answer.b);
		assertEquals(b, answer.a);
		assertEquals(Plane.Horizontal, answer.plane);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void LineTestIllegalArguemntException() {
		Point a = new Point(2, 2);
		Point b = new Point(4, 4);
		new Line(a,b);
	}
	
	@Test
	public void LineTestCrossHorizontal() {
		Line a = new Line(new Point(2, 2), new Point(4,2));
		Line b = new Line(new Point(3, 1), new Point(3, 5));
		Point answer = a.crossing(b);
		assertEquals(new Point(3, 2), answer);
	}
	
	@Test
	public void LineTestCrossVertical() {
		Line a = new Line(new Point(2, 4), new Point(2,2));
		Line b = new Line(new Point(1, 3), new Point(4, 3));
		Point answer = a.crossing(b);
		assertEquals(new Point(2, 3), answer);
	}
}
