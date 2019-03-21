import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Enemy extends GameObject implements EntityB{

	Random r = new Random();
	
	private int speed = r.nextInt(3) + 1;
	
	Animation anim; 

	private Textures tex;

	public Enemy(double x, double y,Textures tex){
		super(x, y);
		this.tex = tex;
		
		anim = new Animation(5, tex.enemy[0], tex.enemy[1], tex.enemy[2]);
	}

	public void tick(){
		y += speed;

		if(y > (Game.HEIGHT * Game.SCALE)){
			speed = r.nextInt(3) + 1; // change speed // 
			y = -10;
			x = r.nextInt(Game.WIDTH * Game.SCALE);
		}
		
		anim.runAnimation();
	}

	public void render(Graphics g){
		anim.drawAnimation(g, x, y, 0);

	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 32, 32);
	} 

	public double getX(){
		return x;
	}

	public double getY(){
		return y;
	}

	public void setY(double y){
		this.y = y;
	}

}