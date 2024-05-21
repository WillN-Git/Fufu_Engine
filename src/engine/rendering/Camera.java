package engine.rendering;

import engine.InputSystem;
import engine.TimeSystem;
import engine.math.Vector3f;

public class Camera {
	private final Vector3f yAxis = new Vector3f(0, 1, 0);
	private final float MOVE_AMOUNT = 10.f;
	private final float ROT_AMOUNT = 100.f;
	
	
	private Vector3f pos;
	private Vector3f forward;
	private Vector3f up;
	
	public Camera() {
		pos = new Vector3f();
		forward = new Vector3f(0, 0, 1);
		up = new Vector3f(0, 1, 0);
	}
	
	public Camera(Vector3f pos, Vector3f forward, Vector3f up) {
		this.pos = pos;
		this.forward = forward;
		this.up = up;
		
		up.normalize();
		forward.normalize();
	}

	public void pollInput() {
		float moveAmount = (float)(MOVE_AMOUNT * TimeSystem.getDeltaTime());
		float rotAmount = (float)(ROT_AMOUNT * TimeSystem.getDeltaTime());
		
		if (InputSystem.isKeyPressed(InputSystem.KEY_UP))
			move(forward, moveAmount);
		if (InputSystem.isKeyPressed(InputSystem.KEY_DOWN))
			move(forward, -moveAmount);
		if (InputSystem.isKeyPressed(InputSystem.KEY_LEFT))
			move(getLeft(), moveAmount);
		if (InputSystem.isKeyPressed(InputSystem.KEY_RIGHT))
			move(getLeft(), -moveAmount);
		
		if (InputSystem.isKeyPressed(InputSystem.KEY_D))
			rotateX(rotAmount);
		if (InputSystem.isKeyPressed(InputSystem.KEY_Q))
			rotateX(-rotAmount);
		if (InputSystem.isKeyPressed(InputSystem.KEY_Z))
			rotateY(rotAmount);
		if (InputSystem.isKeyPressed(InputSystem.KEY_S))
			rotateY(-rotAmount);
	}
	
	public void move(Vector3f direction, float amount) {
		pos = pos.add(direction.scale(amount));
	}
	
	public Vector3f getLeft() {
		Vector3f v = forward.cross(up);
		
		v.normalize();
		
		return v;
	}
	
	
	public Vector3f getRight() {
		Vector3f v = up.cross(forward);
		
		v.normalize();
		
		return v;
	}
	
	public void rotateX(float angle) {
		Vector3f hAxis = yAxis.cross(forward);
		hAxis.normalize();
		
		forward.rotate(angle, yAxis);
		forward.normalize();
		
		up = forward.cross(hAxis);
		up.normalize();
	}
	
	public void rotateY(float angle) {
		Vector3f hAxis = yAxis.cross(forward);
		hAxis.normalize();
		
		forward.rotate(angle, hAxis);
		forward.normalize();
		
		up = forward.cross(hAxis);
		up.normalize();
	}
	
	public Vector3f getPos() {
		return pos;
	}

	public void setPos(Vector3f pos) {
		this.pos = pos;
	}

	public Vector3f getForward() {
		return forward;
	}

	public void setForward(Vector3f forward) {
		this.forward = forward;
	}

	public Vector3f getUp() {
		return up;
	}

	public void setUp(Vector3f up) {
		this.up = up;
	}

	public Vector3f getYAxis() {
		return yAxis;
	}

}
