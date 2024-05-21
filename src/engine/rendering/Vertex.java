package engine.rendering;

import engine.math.Vector2f;
import engine.math.Vector3f;

public class Vertex {
	public static final int SIZE = 5;
	
	private Vector3f pos;
	private Vector2f texCoord;

	public Vertex(Vector3f pos) {
		this.pos = pos;
		this.texCoord = new Vector2f();
	}
	
	public Vertex(Vector3f pos, Vector2f texCoord) {
		this.pos = pos;
		this.texCoord = texCoord;
	}

	public Vector3f getPos() {
		return pos;
	}

	public void setPos(Vector3f pos) {
		this.pos = pos;
	}

	public Vector2f getTexCoord() {
		return texCoord;
	}

	public void setTexCoord(Vector2f texCoord) {
		this.texCoord = texCoord;
	}
}
