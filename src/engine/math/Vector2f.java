package engine.math;

public class Vector2f {
	private float x;
	private float y;
	
	public Vector2f() {
		x = 0;
		y = 0;
	}
	
	public Vector2f(float inX, float inY) {
		x = inX;
		y = inY;
	}
	
	public float length() {
		return (float)Math.sqrt(x * x + y * y);
	}
	
	public Vector2f normalize() {
		float l = length();
		
		if (l != 0) {
			x /= l;
			y /= l;			
		}
		
		return this;
	}
	
	public float dot(Vector2f v) {
		return x * v.getX() + y * v.getY();
	}
	
	// angle in degrees
	public Vector2f rotateInDegree(float angle) {
		double rad = Math.toRadians(angle);
		double cos = Math.cos(rad);
		double sin = Math.sin(rad);
		
		return new Vector2f((float)(x * cos - y * sin), (float)(x * sin + y * cos));
	}

	public Vector2f rotateInRadians(float angle) {
		double cos = Math.cos(angle);
		double sin = Math.sin(angle);
		
		return new Vector2f((float)(x * cos - y * sin), (float)(x * sin + y * cos));
	}
	
	public Vector2f add(Vector2f v) {
		return new Vector2f(x + v.getX(), y + v.getY());
	}

	public Vector2f add(float r) {
		return new Vector2f(x + r, y + r);
	}
	
	public Vector2f scale(float k) {
		return new Vector2f(x * k, y * k);
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
