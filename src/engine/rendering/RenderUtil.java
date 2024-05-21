package engine.rendering;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.*;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;

import engine.math.Matrix4f;


public class RenderUtil {	
	public static void clearScreen() {
		// TODO : Stencil buffer
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		
	}
	
	public static void setTextures(boolean enabled) {
		if (enabled)
			glEnable(GL_TEXTURE_2D);
		else
			glDisable(GL_TEXTURE_2D);
	}
	
	public static void initGraphics() {
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		
		glFrontFace(GL_CW);
		glCullFace(GL_BACK);
		glEnable(GL_CULL_FACE);
		glEnable(GL_DEPTH_TEST);
		
		// TODO : Depth clamp for later
		
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_FRAMEBUFFER_SRGB); // gamma correction
	}
	
	public static FloatBuffer createFloatBuffer(int size) {
		return BufferUtils.createFloatBuffer(size);
	}
	
	public static IntBuffer createIntBuffer(int size) {
		return BufferUtils.createIntBuffer(size);
	}
	
	public static FloatBuffer createFlippedBuffer(Vertex[] vertices) {
		FloatBuffer buffer = createFloatBuffer(vertices.length * Vertex.SIZE);
		
		for (int i = 0; i < vertices.length; i++) {
			buffer.put(vertices[i].getPos().getX());
			buffer.put(vertices[i].getPos().getY());
			buffer.put(vertices[i].getPos().getZ());
			
			buffer.put(vertices[i].getTexCoord().getX());
			buffer.put(vertices[i].getTexCoord().getY());
		}
		
		buffer.flip();
		
		return buffer;
	}
	
	public static IntBuffer createFlippedBuffer(int... indices) {
		IntBuffer buffer = createIntBuffer(indices.length);
		buffer.put(indices);
		buffer.flip();
		
		return buffer;
	}
	
	public static FloatBuffer createFlippedBuffer(Matrix4f m) {
		FloatBuffer buffer = createFloatBuffer(4 * 4);
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				buffer.put(m.get(i, j));
			}			
		}
		
		buffer.flip();
		
		return buffer;
	}
	
	public static String getOpenGLVersion() {
		return glGetString(GL_VERSION);
	}
}
