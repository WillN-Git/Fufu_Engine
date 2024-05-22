package engine.rendering;

import engine.math.Matrix4f;
import engine.math.Vector3f;

public class PhongShader extends Shader {
	private static PhongShader instance = null;
	
	private static Vector3f ambientLight = new Vector3f();
	
	private PhongShader() {
		super();
		
		addVertexShader(ResourceManager.loadShader("phongVertex.vert"));
		addFragmentShader(ResourceManager.loadShader("phongFragment.frag"));
		compileShader();
		
		addUniform("uTransform");	
		addUniform("uBaseColor");
		addUniform("uAmbientLight");
	}
	
	public void updateUniforms(Matrix4f worldMatrix, Matrix4f projectedMatrix, Material material) {
		bind();
		
		if (material.getTexture() != null)
			material.getTexture().bind();
		else
			RenderUtil.unbindTextures();
		
		setUniform("uTransform", projectedMatrix);
		setUniform("uBaseColor", material.getColor());
		setUniform("uAmbientLight", ambientLight);
	}
	
	public static PhongShader getInstance() {
		if (instance == null)
			instance = new PhongShader();
		
		return instance;
	}

	public static Vector3f getAmbientLight() {
		return ambientLight;
	}

	public static void setAmbientLight(Vector3f ambientLight) {
		PhongShader.ambientLight = ambientLight;
	}

	public static void setInstance(PhongShader instance) {
		PhongShader.instance = instance;
	}
}
