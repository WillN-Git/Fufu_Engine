package engine.rendering;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL32.*;

import java.util.HashMap;

import engine.math.Matrix4f;
import engine.math.Vector3f;

public class Shader {
	private int program;
	private HashMap<String, Integer> uniforms;
	
	public Shader() {
		program = glCreateProgram();
		uniforms = new HashMap<String, Integer>();
		
		if (program == 0) {
			System.err.println("Shader creation failed : Could not find valid memory location in constructor");
			System.exit(1);
		}
	}
	
	public void bind() {
		glUseProgram(program);
	}
	
	public void addUniform(String uniform) {
		int uniformLocation = glGetUniformLocation(program, uniform);
		
		if (uniformLocation == 0xffffffff) {
			System.err.println("Error : Could not find uniform : " + uniform);
			new Exception().printStackTrace();
			System.exit(1);
		}
		
		uniforms.put(uniform, uniformLocation);
	}
	
	public void updateUniforms(Matrix4f worldMatrix, Matrix4f projectedMatrix, Material material) {
		
	}
	
	public void addVertexShader(String text) {
		addProgram(text, GL_VERTEX_SHADER);
	}
	
	public void addGeometryShader(String text) {
		addProgram(text, GL_GEOMETRY_SHADER);
	}
	
	public void addFragmentShader(String text) {
		addProgram(text, GL_FRAGMENT_SHADER);
	}
	
	public void compileShader() {
		glLinkProgram(program);
		
		if (glGetProgram(program, GL_LINK_STATUS) == 0) {
			System.out.println(glGetProgramInfoLog(program, 1024));
			System.exit(1);
		}
		
		glValidateProgram(program);
		
		if (glGetProgram(program, GL_VALIDATE_STATUS) == 0) {
			System.out.println(glGetProgramInfoLog(program, 1024));
			System.exit(1);
		}
	}
	
	private void addProgram(String text, int type) {
		int shader =  glCreateShader(type);
		
		if (shader == 0) {
			System.err.println("Shader creation failed : Could not find valid memory location when adding shader");
			System.exit(1);
		}
		
		glShaderSource(shader, text);
		glCompileShader(shader);
		
		if (glGetShader(shader, GL_COMPILE_STATUS) == 0) {
			System.out.println(glGetShaderInfoLog(shader, 1024));
			System.exit(1);
		}
		
		glAttachShader(program, shader);
	}
	
	public void setUniform(String uniform, int value) {
		glUniform1i(uniforms.get(uniform), value);
	}
	
	public void setUniform(String uniform, float value) {
		glUniform1f(uniforms.get(uniform), value);
	}
	
	public void setUniform(String uniform, Vector3f value) {
		glUniform3f(uniforms.get(uniform), value.getX(), value.getY(), value.getZ());
	}
	
	public void setUniform(String uniform, Matrix4f value) {
		glUniformMatrix4(uniforms.get(uniform), true, RenderUtil.createFlippedBuffer(value));
	}
}
