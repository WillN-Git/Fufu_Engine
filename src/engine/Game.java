package engine;

import org.lwjgl.input.Keyboard;

import engine.math.Transform;
import engine.math.Vector3f;
import engine.rendering.Mesh;
import engine.rendering.ResourceManager;
import engine.rendering.Shader;
import engine.rendering.Vertex;

public class Game {
	private Mesh mesh;
	private Shader shader;
	
	public Game() {
		mesh = ResourceManager.loadMesh("cube.obj");
		shader = new Shader();
		
//		Vertex[] vertices = new Vertex[] {
//			new Vertex(new Vector3f(-1, -1, 0)),
//			new Vertex(new Vector3f(0, 1, 0)),
//			new Vertex(new Vector3f(1, -1, 0)),
//			new Vertex(new Vector3f(0, -1, 1)),
//		};
//		
//		int[] indices = new int[] {
//				0, 1, 3,
//				3, 1, 2,
//				2, 1, 0,
//				0, 2, 3
//			};
//		
//		mesh.addVertices(vertices, indices);
		shader.addVertexShader(ResourceManager.loadShader("basicVertex.vert"));
		shader.addFragmentShader(ResourceManager.loadShader("basicFragment.frag"));
		shader.compileShader();
		
		transform = new Transform();
		shader.addUniform("uTransform");
	}
	
	public void pollInput() {
		if (InputSystem.isKeyDown(Keyboard.KEY_UP)) {
			System.out.println("We've just pressed up");
		}
		if (InputSystem.isKeyUp(Keyboard.KEY_UP)) {
			System.out.println("We've just released up");
		}
		
		if (InputSystem.isMouseBtnDown(1)) {
			System.out.println("We've just pressed right click + ");
		}
		if (InputSystem.isMouseBtnUp(1)) {
			System.out.println("We've just released right click");
		}
	}
	
	float temp = 0.0f;
	Transform transform;
	public void update() {
		temp += TimeSystem.getDeltaTime();
		//transform.setTranslation((float)Math.sin(temp), 0, 0);
		transform.setRotation((float)Math.sin(temp) * 180, (float)Math.sin(temp) * 90 , 0);
		transform.setScale(0.3f, .3f, 0.3f);
	}
	
	public void render() {
		shader.bind();
		shader.setUniform("uTransform", transform.getTransformMatrix());
		mesh.draw();
	}
}
