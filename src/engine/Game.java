package engine;

import org.lwjgl.input.Keyboard;

import engine.math.Transform;
import engine.math.Vector2f;
import engine.math.Vector3f;
import engine.rendering.Camera;
import engine.rendering.Mesh;
import engine.rendering.ResourceManager;
import engine.rendering.Shader;
import engine.rendering.Texture;
import engine.rendering.Vertex;

public class Game {
	private Mesh mesh;
	private Shader shader;
	private Transform transform;
	private Texture texture;
	private Camera camera;
	
	public Game() {
//		mesh = ResourceManager.loadMesh("cube.obj");
		mesh = new Mesh();
		shader = new Shader();
		texture = ResourceManager.loadTexture("grid.png");
		
		Vertex[] vertices = new Vertex[] {
			new Vertex(new Vector3f(-1, -1, 0), new Vector2f(0, 0)),	
			new Vertex(new Vector3f(0, 1, 0), new Vector2f(.5f, 0)),	
			new Vertex(new Vector3f(1, -1, 0), new Vector2f(1.f, 0)),	
			new Vertex(new Vector3f(0, -1, 1), new Vector2f(0, .5f)),	
		};
		
		int[] indices = new int[] {
			0, 1, 3,
			3, 1, 2,
			2, 1, 0,
			0, 2, 3
		};
		
		
		mesh.addVertices(vertices, indices);
		shader.addVertexShader(ResourceManager.loadShader("basicVertex.vert"));
		shader.addFragmentShader(ResourceManager.loadShader("basicFragment.frag"));
		shader.compileShader();
		
		camera = new Camera();
		
		Transform.setProjectionMatrix(70f, Window.getWidth(), Window.getHeight(), 0.1f, 1000f);
		Transform.setCamera(camera);
		
		transform = new Transform();
		shader.addUniform("uTransform");
	}
	
	public void pollInput() {
//		if (InputSystem.isKeyDown(Keyboard.KEY_UP)) {
//			System.out.println("We've just pressed up");
//		}
//		if (InputSystem.isKeyUp(Keyboard.KEY_UP)) {
//			System.out.println("We've just released up");
//		}
//		
//		if (InputSystem.isMouseBtnDown(1)) {
//			System.out.println("We've just pressed right click + ");
//		}
//		if (InputSystem.isMouseBtnUp(1)) {
//			System.out.println("We've just released right click");
//		}
		
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
		shader.bind();
		shader.setUniform("uTransform", transform.getProjectedTransformationMatrix());
		texture.bind();
		mesh.draw();
	}
}
