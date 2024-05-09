package engine;

public class Vector2f {
	private float x;
	private float y;
	
	public Vector2f(float inX, float inY) {
		x = inX;
		y = inY;
	}

	public float getX() {
		return x;
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public float getY() {
		return y;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
}
