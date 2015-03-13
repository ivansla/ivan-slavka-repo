package test.types;

public final class Point2D {
	public float x;
	public float y;

	public Point2D(float px, float py) {
		this.x = px;
		this.y = py;
	}

	public void setZero() {
		this.x = this.y = 0;
	}

	public Point2D multiply(float scalar) {
		return new Point2D(this.x * scalar, this.y * scalar);
	}
}
