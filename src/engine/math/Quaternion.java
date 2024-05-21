package engine.math;

public class Quaternion {
	private float w;
	private float x;
	private float y;
	private float z;
	
	public Quaternion(float w, float x, float y, float z) {
		this.w = w;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public float length() {
		return (float)Math.sqrt(x * x + y * y + z * z + w * w);
	}
	
	public Quaternion normalize() {
		float l = length();
		
		if (l != 0) {
			w /= l;
			x /= l;
			y /= l;
			z /= l;
		}
		
		return this;
	}
	
	public Quaternion conjugate() {
		return new Quaternion(w, -x, -y, -z);
	}
	
	public Quaternion mul(Quaternion q2) {
		return new Quaternion(
					w * q2.getW() - x * q2.getX() - y * q2.getY() - z * q2.getZ(),
					x * q2.getW() + w * q2.getX() + y * q2.getZ() - z * q2.getY(),
					y * q2.getW() + w * q2.getY() + z * q2.getX() - x * q2.getZ(),
					z * q2.getW() + w * q2.getZ() + x * q2.getY() - y * q2.getX()					
				);
	}
	
	public Quaternion mul(Vector3f v) {
		return new Quaternion(
					-x * v.getX() - y * v.getY() - z * v.getZ(),
					w * v.getX() + y * v.getZ() - z * v.getY(),
					w * v.getY() + z * v.getX() - x * v.getZ(),
					w * v.getZ() + x * v.getY() - y * v.getX()
				);
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
	
	public float getW() {
		return w;
	}
	
	public void setW(float w) {
		this.w = w;
	}
}
