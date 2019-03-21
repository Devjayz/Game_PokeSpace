
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Player extends GameObject implements EntityA{
	
	private double velX = 0;
	private double velY = 0;
	private Textures tex; 
	
	Animation anim;

	public Player(double x, double y, Textures tex){
		super(x, y);
		this.tex = tex;
		
		anim = new Animation(5, tex.player[0], tex.player[1], tex.player[2]);
	}

	public void tick(){
		x+=velX;
		y+=velY;

		if(x <= 0)
			x = 0;
		if(x >= 640 - 20)
			x = 640 - 20;
		if(y <= 0)
			y = 0;
		if(y >= 480 - 32)
			y = 480 - 32;
		
		anim.runAnimation();
		
		}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 32, 32);
	} 

	public void render(Graphics g){
		anim.drawAnimation(g, x, y, 0);
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
	public void setVelX(double velX) {
		this.velX = velX;
	}
	public void setVelY(double velY) {
		this.velY = velY;
	}
	
	
}