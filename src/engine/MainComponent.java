package engine;

/**
 *
 *
 * https://stackoverflow.com/questions/67644836/why-am-i-getting-this-error-for-my-lwjgl-program
 */

public class MainComponent {
	public static final int WIDTH = 800;
	public static final int HEIGHT = 800;
	public static final String TITLE = "FUFU";
	
	
	public void start() {
		
		run();
	}
	
	public void stop() {
		
	}
	
	private void run() {
		while (!Window.isCloseRequested()) {
			render();
		}
	}
	
	private void render() {
		Window.render();
	}
	
	private void cleanUp() {
		
	}
	
	public static void main(String[] args) {
		Window.createWindow(WIDTH, HEIGHT, TITLE);
		
		MainComponent game = new MainComponent();
		
		game.start();
	}

}
