package engine;

import org.lwjgl.input.Keyboard;

import engine.math.Vector3f;
import engine.rendering.Mesh;
import engine.rendering.Vertex;

public class Game {
	private Mesh mesh;
	
	public Game() {
		mesh = new Mesh();
		
		Vertex[] data = new Vertex[] {
			new Vertex(new Vector3f(-1, -1, 0)),
			new Vertex(new Vector3f(0, 1, 0)),
			new Vertex(new Vector3f(1, -1, 0)),
		};
		
		mesh.addVertices(data);
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
	
	public void update() {
		
	}
	
	public void render() {
		mesh.draw();
	}
}
