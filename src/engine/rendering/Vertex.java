package engine.rendering;

import engine.math.Vector2f;
import engine.math.Vector3f;

public class Vertex {
	public static final int SIZE = 8; // 3 for pos + 2 for texCoord + 3 for normal
	
	private Vector3f pos;
	private Vector2f texCoord;
	private Vector3f normal;

	public Vertex(Vector3f pos) {
		this.pos = pos;
		this.texCoord = new Vector2f();
		this.normal = new Vector3f();
	}
	
	public Vertex(Vector3f pos, Vector2f texCoord) {
		this.pos = pos;
		this.texCoord = texCoord;
		this.normal = new Vector3f();
	}
	
	public Vertex(Vector3f pos, Vector2f texCoord, Vector3f normal) {
		this.pos = pos;
		this.texCoord = texCoord;
		this.normal = normal;
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

	public Vector3f getNormal() {
		return normal;
	}

	public void setNormal(Vector3f normal) {
		this.normal = normal;
	}
	
	
}
