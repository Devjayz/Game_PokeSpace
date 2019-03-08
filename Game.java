import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.BufferStrategy;
import java.awt.Graphics;
import javax.imageio.ImageIO;
import java.io.IOException;

public class Game extends Canvas implements Runnable {
	
	public static final int WIDTH = 320;
	public static final int HEIGHT = WIDTH /12 * 9;
	public static final int SCALE = 2;
	public final String TITLE = "Drogo's Adventure";
	
	private boolean running = false;
	private Thread thread;

	private BufferedImage image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
	private BufferedImage spriteSheet = null;

	//temp lang
	private BufferedImage player;

	public void init(){
		BufferedImageLoader loader = new BufferedImageLoader();
		
		try{
			
			spriteSheet = loader.loadImage("spritesheet.png");

		}catch(IOException e){
			e.printStackTrace();
		}

		SpriteSheet ss = new SpriteSheet(spriteSheet);
		player = ss.grabImage(1, 1,32,32);

	}
	

	private synchronized void start() {
		if(running)
			return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
		
	}
	
	private synchronized void stop() {
		if(!running)
			return;
		
		running = false;
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(1);
		
	}
	
	//game loop//
	public void run() {
		init();
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();


		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if(delta >= 1){
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;

			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println(updates + "Ticks, Fps" + frames);
				updates = 0;
				frames = 0;
			}

		}
		stop();
	}

//game that update//
	private void tick(){

	}

//game that render//
	private void render(){

		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		/////////////////////////////////////

		g.drawImage(image, 0 , 0, getWidth(), getHeight(), this);

		g.drawImage(player, 100 , 100, this);


		/////////////////////////////////////
		g.dispose();
		bs.show();
	}
	
	public static void main (String args[]) {
		Game game = new Game();
		
		game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE ));
		game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE ));
		game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE ));
		
		JFrame frame = new JFrame(game.TITLE);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		game.start();
	}
	
}
