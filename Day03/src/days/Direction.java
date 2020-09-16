package days;

class Direction {
	public final Dir dir;
	public final int steps;

	public Direction(final Dir dir, final int steps) {
		this.dir = dir;
		this.steps = steps;
		if (this.steps <= 0) {
			throw new IllegalArgumentException("Cant have zero or negitive nummber of steps.");
		}
	}

	public Direction(final String str) {
		this.steps = Integer.parseInt(str.substring(1));
		if (this.steps <= 0) {
			throw new IllegalArgumentException("Cant have zero or negitive nummber of steps.");
		}
		switch (str.charAt(0)) {
		case 'R':
			this.dir = Dir.Right;
			break;

		case 'U':
			this.dir = Dir.Up;
			break;

		case 'L':
			this.dir = Dir.Left;
			break;

		case 'D':
			this.dir = Dir.Down;
			break;

		default:
			throw new IllegalArgumentException("Unexpected input: \"" + str
					+ "\". It needs to have the format where the first letter is the dir [R]ight, [U]p, [L]eft, [D]own followed by a nummber.");
		}
	}
	
	public Point getNewPoint(final Point startPoint) {
		switch (dir) {
		case Up:
			return new Point(startPoint.x, startPoint.y - this.steps);
		case Down:
			return new Point(startPoint.x, startPoint.y + this.steps);
		case Right:
			return new Point(startPoint.x + this.steps, startPoint.y);
		case Left:
			return new Point(startPoint.x - this.steps, startPoint.y);

		default:
			throw new IllegalStateException("Direction dir is not right, up, left, down.");
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Direction) {
			Direction other = (Direction) obj;
			return other.dir == this.dir && other.steps == this.steps;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return dir.hashCode() + steps * 2;
	}
	
	@Override
	public String toString() {
		return dir.name() + steps;
	}
}
