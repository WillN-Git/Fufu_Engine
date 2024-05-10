package engine.rendering;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;

public class Mesh {
	// Vertex buffer of the mesh
	private int vbo;
	
	// Size of the vertex buffer
	private int size;
	
	public Mesh() {
		vbo = glGenBuffers();
		size = 0;
	}
	
	public void addVertices(Vertex[] vertices) {
		size = vertices.length * Vertex.SIZE;
		
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glBufferData(GL_ARRAY_BUFFER, RenderUtil.createFlippedBuffer(vertices), GL_STATIC_DRAW);
		
	}
	
	public void draw() {
		glEnableVertexAttribArray(0);
		
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glVertexAttribPointer(0, 3, GL_FLOAT, false, Vertex.SIZE * 4, 0);
		
		glDrawArrays(GL_TRIANGLES, 0, size);
		
		glEnableVertexAttribArray(0);
	}
}