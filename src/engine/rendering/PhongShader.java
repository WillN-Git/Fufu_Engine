package engine.rendering;

import static org.lwjgl.opengl.GL20.glUniform1i;

import engine.math.Matrix4f;
import engine.math.Vector3f;

public class PhongShader extends Shader {
	private static PhongShader instance = null;
	
	private static Vector3f ambientLight = new Vector3f(1, 1, 1);
	private static DirectionalLight directionalLight;
	
	private PhongShader() {
		super();
		
		addVertexShader(ResourceManager.loadShader("phongVertex.vert"));
		addFragmentShader(ResourceManager.loadShader("phongFragment.frag"));
		compileShader();
		
		addUniform("uTransform");	
		addUniform("uTransformProjected");
		addUniform("uBaseColor");
		addUniform("uAmbientLight");
		
		addUniform("uDirectionalLight.base.color");
		addUniform("uDirectionalLight.base.intensity");
		addUniform("uDirectionalLight.direction");
	}
	
	public void updateUniforms(Matrix4f worldMatrix, Matrix4f projectedMatrix, Material material) {
		bind();
		
		if (material.getTexture() != null)
			material.getTexture().bind();
		else
			RenderUtil.unbindTextures();
		
		setUniform("uTransform", worldMatrix);
		setUniform("uTransformProjected", projectedMatrix);
		setUniform("uBaseColor", material.getColor());
		setUniform("uAmbientLight", ambientLight);
		setUniform("uDirectionalLight", directionalLight);
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
	
	public static void setDirectionalLight(DirectionalLight directionalLight) {
		PhongShader.directionalLight = directionalLight;
	}

	public static void setInstance(PhongShader instance) {
		PhongShader.instance = instance;
	}
	
	public void setUniform(String uniform, DirectionalLight directionalLight) {
		setUniform(uniform + ".base.color", directionalLight.getLight().getColor());
		setUniform(uniform + ".base.intensity", directionalLight.getLight().getIntensity());
		setUniform(uniform + ".direction", directionalLight.getDirection());
	}
}
