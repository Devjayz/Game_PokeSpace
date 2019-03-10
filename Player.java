
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Player{

	private double x;
	private double y;

	private BufferedImage player;

	public Player(double x, double y, Game game){

		this.x = x;
		this.y = y;

		SpriteSheet ss = new SpriteSheet(game.getSpriteSheet());

		player = ss.grabImage(1, 1, 32, 32);
	}

	public void tick(){

	}

	public void render(Graphics g){
		g.drawImage(player, (int)x, (int)y, null);
	}

	public double getX(){
		return x;
	}

	public double getY(){
		return y;
	}

	public void setX(double x){
		this.x = x;
	}

	public void setY(double y){
		this.y = y;
	}
}