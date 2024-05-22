package engine.rendering;

import engine.math.Matrix4f;

public class BasicShader extends Shader {
	private static BasicShader instance = null;
	
	private BasicShader() {
		super();
		
		addVertexShader(ResourceManager.loadShader("basicVertex.vert"));
		addFragmentShader(ResourceManager.loadShader("basicFragment.frag"));
		compileShader();
		
		addUniform("uTransform");	
		addUniform("uColor");
	}
	
	public void updateUniforms(Matrix4f worldMatrix, Matrix4f projectedMatrix, Material material) {
		bind();
		
		if (material.getTexture() != null)
			material.getTexture().bind();
		else
			RenderUtil.unbindTextures();
		
		setUniform("uTransform", projectedMatrix);
		setUniform("uColor", material.getColor());
	}
	
	public static BasicShader getInstance() {
		if (instance == null)
			instance = new BasicShader();
		
		return instance;
	}
}
