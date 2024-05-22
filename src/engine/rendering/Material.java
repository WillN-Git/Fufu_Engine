package engine.rendering;

import engine.math.Vector3f;

public class Material {
	private Texture texture;
	private Vector3f color;
	
	public Material(Texture inTexture, Vector3f inColor) {
		texture = inTexture;
		color = inColor;
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	public Vector3f getColor() {
		return color;
	}

	public void setColor(Vector3f color) {
		this.color = color;
	}
}
