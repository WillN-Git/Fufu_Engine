package engine.math;

public class Vector3f {
	private float x;
	private float y;
	private float z;
	
	public Vector3f() {
		x = 0;
		y = 0;
		z = 0;
	}
	
	public Vector3f(float inX, float inY, float inZ) {
		x = inX;
		y = inY;
		z = inZ;
	}
	
	public float length() {
		return (float)Math.sqrt(x * x + y * y + z * z);
	}
	
	public Vector3f normalize() {
		float l = length();
		
		if (l != 0) {
			x /= l;
			y /= l;
			z /= l;
		}
		
		return this;
	}
	
	public float dot(Vector3f v) {
		return x * v.getX() + y * v.getY() + z * v.getZ();
	}
	
	public Vector3f cross(Vector3f v) {
		return new Vector3f((y * v.getZ() - z * v.getY()), -1 * (x * v.getZ() - z * getX()), (x * v.getY() - y * getX()));
	}
	
	public Vector3f add(Vector3f v) {
		return new Vector3f(x + v.getX(), y + v.getY(), z + getZ());
	}

	public Vector3f add(float r) {
		return new Vector3f(x + r, y + r, z + r);
	}
	
	public Vector3f scale(float k) {
		return new Vector3f(x * k, y * k, z * k);
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
	
	public float getZ() {
		return z;
	}
	
	public void setZ(float z) {
		this.z = z;
	}
	
}
