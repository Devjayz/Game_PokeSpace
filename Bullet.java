import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Bullet extends GameObject implements EntityA {

	private Textures tex;
	private Game game;
	
	Animation anim;
	

	public Bullet(double x, double y, Textures tex, Game game){
		super(x, y);
		this.tex = tex;
		this.game = game;
		
		anim = new Animation(5, tex.fire[0], tex.fire[1], tex.fire[2]);
		
	}

	public void tick(){
		y -= 10;
		
		
		anim.runAnimation();
		
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 32, 32);
	} 

	public void render(Graphics g){
		anim.drawAnimation(g, x, y, 0);
	}

	public double getY(){
		return y;
	}

	public double getX() {
		return x;
	}

}