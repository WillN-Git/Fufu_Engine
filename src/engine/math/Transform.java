package engine.math;

public class Transform {
	private Vector3f translation;
	private Vector3f rotation;
	private Vector3f scale;
	
	public Transform() {
		translation = new Vector3f();
		rotation = new Vector3f();
		scale = new Vector3f(1.f, 1.f, 1.f);
	}
	
	public Transform(Vector3f inTranslation, Vector3f inRotation, Vector3f inScale) {
		translation = inTranslation;
		rotation = inRotation;
		scale = inScale;
	}
	
	public Matrix4f getTransformMatrix() {
		Matrix4f mt = new Matrix4f();
		mt.initTranslation(translation.getX(), translation.getY(), translation.getZ());
		
		Matrix4f mr = new Matrix4f();
		mr.initRotation(rotation.getX(), rotation.getY(), rotation.getZ());
		
		Matrix4f ms = new Matrix4f();
		ms.initScale(scale.getX(), scale.getY(), scale.getZ());
		
		return mt.mul(mr.mul(ms));
	}
	
	public Vector3f getTranslation() {
		return translation;
	}
	
	public void setTranslation(Vector3f inTranslation) {
		translation = inTranslation;
	}
	
	public void setTranslation(float x, float y, float z) {
		translation = new Vector3f(x, y, z);
	}
	
	public Vector3f getRotation() {
		return rotation;
	}
	
	public void setRotation(Vector3f inRotation) {
		rotation = inRotation;
	}
	
	public void setRotation(float x, float y, float z) {
		rotation = new Vector3f(x, y, z);
	}
	
	public Vector3f getScale() {
		return scale;
	}
	
	public void setScale(Vector3f inScale) {
		scale = inScale;
	}
	
	public void setScale(float x, float y, float z) {
		scale = new Vector3f(x, y, z);
	}
}
