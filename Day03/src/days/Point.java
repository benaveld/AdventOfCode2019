package days;

class Point{
	public final int x, y;
	public Point(final int x, final int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Point) {
			Point other = (Point) obj;
			return other.x == this.x && other.y == this.y;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return x * 2 + y * 3;
	}
}
