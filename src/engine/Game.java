package engine;

import org.lwjgl.input.Keyboard;

import engine.math.Transform;
import engine.math.Vector2f;
import engine.math.Vector3f;
import engine.rendering.BasicShader;
import engine.rendering.Camera;
import engine.rendering.Material;
import engine.rendering.Mesh;
import engine.rendering.PhongShader;
import engine.rendering.RenderUtil;
import engine.rendering.ResourceManager;
import engine.rendering.Shader;
import engine.rendering.Texture;
import engine.rendering.Vertex;

public class Game {
	private Mesh mesh;
	private Transform transform;
	private Texture texture;
	private Material material;
	private Camera camera;
	
	private Shader shader;
	
	public Game() {
//		mesh = ResourceManager.loadMesh("cube.obj");
		mesh = new Mesh();
		shader = PhongShader.getInstance();
		texture = ResourceManager.loadTexture("grid.png");
		material = new Material(texture, new Vector3f(0.2f, 0.4f, 1));
		transform = new Transform();
		
		Vertex[] vertices = new Vertex[] {
			new Vertex(new Vector3f(-1, -1, 0), new Vector2f(0, 0)),	
			new Vertex(new Vector3f(0, 1, 0), 	new Vector2f(.5f, 0)),	
			new Vertex(new Vector3f(1, -1, 0), 	new Vector2f(1.f, 0)),	
			new Vertex(new Vector3f(0, -1, 1),	new Vector2f(0, .5f))	
		};
		
		int[] indices = new int[] {
			0, 1, 3,
			3, 1, 2,
			2, 1, 0,
			0, 2, 3
		};
		
		mesh.addVertices(vertices, indices);
		
		camera = new Camera();
		
		Transform.setProjectionMatrix(70f, Window.getWidth(), Window.getHeight(), 0.1f, 1000f);
		Transform.setCamera(camera);
		
		PhongShader.setAmbientLight(new Vector3f(1, 1, 1));
	}
	
	public void pollInput() {
		camera.pollInput();
	}
	
	float temp = 0.0f;
	public void update() {
		temp += TimeSystem.getDeltaTime();
		transform.setTranslation(0, 0, 5);
		transform.setRotation(0, (float)Math.sin(temp) * 180, 0);
		// transform.setScale(0.3f, .3f, 0.3f);
	}
	
	public void render() {
		RenderUtil.setClearColor(Transform.getCamera().getPos().scale(1.f / 2048).abs());
		shader.updateUniforms(transform.getTransformationMatrix(), transform.getProjectedTransformationMatrix(), material);
		texture.bind();
		mesh.draw();
	}
}
