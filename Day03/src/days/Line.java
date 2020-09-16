package days;

class Line {
	public final Point a, b;
	public final Plane plane;

	public Line(final Point a, final Point b) {
		if (a.x != b.x && a.y != b.y) {
			throw new IllegalArgumentException("The points need to be on the same X or Y line.");
		}

		if (a.x == b.x) {
			if (a.y < b.y) {
				this.a = a;
				this.b = b;
			} else {
				this.a = b;
				this.b = a;
			}
			plane = Plane.Vertical;
		} else {
			if (a.x < b.x) {
				this.a = a;
				this.b = b;
			} else {
				this.a = b;
				this.b = a;
			}
			plane = Plane.Horizontal;
		}
	}

	public Point crossing(final Line other) {
		if (plane == other.plane) {
			return null;
		}
		if (plane == Plane.Horizontal) {
			if (a.x <= other.a.x && b.x >= other.b.x && a.y >= other.a.y && b.y <= other.b.y) {
				return new Point(other.a.x, a.y);
			}
		} else {
			return other.crossing(this);
		}
		return null;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Line) {
			Line other = (Line) obj;
			if (other.a == this.a && other.b == this.b) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		return a.hashCode() * 5 + b.hashCode() * 7;
	}
}

enum Plane {
	Vertical, Horizontal
}