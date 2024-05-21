package engine.math;

import engine.rendering.Camera;

public class Transform {
	private static Camera camera;
	
	private Vector3f translation;
	private Vector3f rotation;
	private Vector3f scale;
	
	// Camera parameters
	private static float zNear;
	private static float zFar;
	private static float width;
	private static float height;
	private static float fov;
	
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
	
	public Matrix4f getTransformationMatrix() {
		Matrix4f mt = new Matrix4f();
		mt.initTranslationMatrix(translation.getX(), translation.getY(), translation.getZ());
		
		Matrix4f mr = new Matrix4f();
		mr.initRotationMatrix(rotation.getX(), rotation.getY(), rotation.getZ());
		
		Matrix4f ms = new Matrix4f();
		ms.initScaleMatrix(scale.getX(), scale.getY(), scale.getZ());
		
		return mt.mul(mr.mul(ms));
	}
	
	public static void setProjectionMatrix(float fov, float width, float height, float zNear, float zFar) {
		Transform.zNear = zNear; 
		Transform.zFar = zFar;
		Transform.width = width;
		Transform.height = height;
		Transform.fov = fov;
	}
	
	public Matrix4f getProjectedTransformationMatrix() {
		Matrix4f transformationMatrix = getTransformationMatrix();
		
		Matrix4f projectionMatrix = new Matrix4f();
		projectionMatrix.initProjectionMatrix(fov, width, height, zNear, zFar);
		
		Matrix4f cameraRotation = new Matrix4f();
		cameraRotation.initViewMatrix(camera.getForward(), camera.getUp());
		
		Matrix4f cameraTranslation = new Matrix4f();
		cameraTranslation.initTranslationMatrix(-camera.getPos().getX(), -camera.getPos().getY(), -camera.getPos().getZ());
		
		
		return projectionMatrix.mul(cameraRotation.mul(cameraTranslation.mul(transformationMatrix)));
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

	public static Camera getCamera() {
		return camera;
	}

	public static void setCamera(Camera camera) {
		Transform.camera = camera;
	}
}
