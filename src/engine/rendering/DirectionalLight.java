package engine.rendering;

import engine.math.Vector3f;

public class DirectionalLight {
	private BaseLight light;
	private Vector3f direction;
	
	public DirectionalLight(BaseLight light, Vector3f direction) {
		this.light = light;
		this.direction = direction.normalize();
	}

	public BaseLight getLight() {
		return light;
	}

	public void setLight(BaseLight light) {
		this.light = light;
	}

	public Vector3f getDirection() {
		return direction;
	}

	public void setDirection(Vector3f direction) {
		this.direction = direction;
	}
}
