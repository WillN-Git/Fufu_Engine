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
	public static final double FRAME_CAP = 5000.0;
	
	private boolean bRunning;
	
	private Game game;
	
	public MainComponent() {
		bRunning = false;
		
		game = new Game();
	}
	
	public void start() {
		
		if (bRunning)
			return;
		
		run();
	}
	
	public void stop() {
		if (!bRunning)
			return;
		
		bRunning = false;
	}
	
	private void run() {
		bRunning = true;
		
		int fps = 0;
		long frameCounter = 0;
		
		final double frameTime = 1.0 / FRAME_CAP;
		
		long lastTime = TimeSystem.getTime();
		double unprocessedTime = 0;
		
		// Game loop
		while (bRunning) {
			boolean render = false;
			
			long startTime = TimeSystem.getTime();
			long passedTime = startTime - lastTime;
			lastTime = startTime;
			
			unprocessedTime += passedTime / (double)TimeSystem.SECOND;
			frameCounter += passedTime;
			
			while (unprocessedTime > frameTime) {
				render = true;
				
				unprocessedTime -= frameTime;
				
				if (Window.isCloseRequested())
					stop();
				
				TimeSystem.setDeltaTime(frameTime);
				
				game.pollInput();
				game.update();
				InputSystem.update();
				
				// TODO : update game
				
				if (frameCounter >= TimeSystem.SECOND) {
					System.out.println(fps);
					
					fps = 0;
					frameCounter = 0;
				}
			}
			
			if (render)  {
				render();
				
				fps++;
			}
			else {
				try {
					Thread.sleep(1);
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
		shutdown();
	}
	
	private void render() {
		game.render();
		
		Window.render();
	}
	
	// Clean up
	private void shutdown() {
		Window.dispose();
	}
	
	public static void main(String[] args) {
		Window.createWindow(WIDTH, HEIGHT, TITLE);
		
		MainComponent game = new MainComponent();
		
		game.start();
	}

}
