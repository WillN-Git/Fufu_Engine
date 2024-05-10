package engine;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import engine.math.Vector2f;

public class InputSystem {
	public static final int NUM_KEYCODES = 256;
	public static final int NUM_MOUSEBTNS = 5;
	
	private static ArrayList<Integer> currentKeys = new ArrayList<Integer>();
	private static ArrayList<Integer> downKeys = new ArrayList<Integer>();
	private static ArrayList<Integer> upKeys = new ArrayList<Integer>();
	
	private static ArrayList<Integer> currentMouseBtns = new ArrayList<Integer>();
	private static ArrayList<Integer> downMouseBtns = new ArrayList<Integer>();
	private static ArrayList<Integer> upMouseBtns = new ArrayList<Integer>();
	
	public static void update() {		
		upMouseBtns.clear();
		for (int mouseBtn = 0; mouseBtn < NUM_MOUSEBTNS; mouseBtn++) {
			if (!isMouseBtnPressed(mouseBtn) && currentMouseBtns.contains(mouseBtn))
				upMouseBtns.add(mouseBtn);
		}
		
		downMouseBtns.clear();
		for (int mouseBtn = 0; mouseBtn < NUM_MOUSEBTNS; mouseBtn++) {
			if (isMouseBtnPressed(mouseBtn) && !currentMouseBtns.contains(mouseBtn))
				downMouseBtns.add(mouseBtn);
		}
		
		currentMouseBtns.clear();
		for (int mouseBtn = 0; mouseBtn < NUM_MOUSEBTNS; mouseBtn++) {
			if (isMouseBtnPressed(mouseBtn))
				currentMouseBtns.add(mouseBtn);
		}
		
		upKeys.clear();
		for (int keyCode = 0; keyCode < NUM_KEYCODES; keyCode++) {
			if (!isKeyPressed(keyCode) && currentKeys.contains(keyCode))
				upKeys.add(keyCode);
		}
		
		downKeys.clear();
		for (int keyCode = 0; keyCode < NUM_KEYCODES; keyCode++) {
			if (isKeyPressed(keyCode) && !currentKeys.contains(keyCode))
				downKeys.add(keyCode);
		}
		
		currentKeys.clear();
		for (int keyCode = 0; keyCode < NUM_KEYCODES; keyCode++) {
			if (isKeyPressed(keyCode))
				currentKeys.add(keyCode);
		}
	}
	
	public static boolean isKeyPressed(int keyCode) {
		return Keyboard.isKeyDown(keyCode);
	}
	
	public static boolean isKeyDown(int keyCode) {
		return downKeys.contains(keyCode);
	}
	
	public static boolean isKeyUp(int keyCode) {
		return upKeys.contains(keyCode);
	}
	
	public static boolean isMouseBtnPressed(int mouseBtn) {
		return Mouse.isButtonDown(mouseBtn);
	}
	
	public static boolean isMouseBtnDown(int mouseBtn) {
		return downMouseBtns.contains(mouseBtn);
	}
	
	public static boolean isMouseBtnUp(int mouseBtn) {
		return upMouseBtns.contains(mouseBtn);
	}	
	
	public static Vector2f getMousePosition() {
		return new Vector2f(Mouse.getX(), Mouse.getY());
	}
}
