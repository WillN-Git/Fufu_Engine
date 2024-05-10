package engine;

import org.lwjgl.input.Keyboard;

public class Game {
	public Game() {
		
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
		
	}
}
