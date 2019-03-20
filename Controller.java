import java.util.LinkedList;
import java.awt.Graphics;
import java.util.Random;

public class Controller{

	private LinkedList<EntityA> ea = new LinkedList<EntityA>();
	private LinkedList<EntityB> eb= new LinkedList<EntityB>();
	
	EntityA enta;
	EntityB entb;
	
	private Textures tex;
	Random r = new Random();
	
	public 	Controller(Textures tex) {
		this.tex = tex;
		addEntity(new Enemy(100, 100, tex));
	}

	public void createEnemy(int enemy_count){
		for(int i = 0; i < enemy_count; i++){
			addEntity(new Enemy(r.nextInt(640), -10, tex));
		}
	}
	
	public void tick(){
		//A CLASS
		for(int i = 0; i < ea.size(); i++){
			enta = ea.get(i);

			enta.tick(); 
		}	
		
		//B CLASS
		for(int i = 0; i < eb.size(); i++){
			entb = eb.get(i);

			entb.tick(); 
		}

	}

	public void render(Graphics g){
		// A CLASS
		for(int i = 0; i < ea.size(); i++){
			enta = ea.get(i);

			enta.render(g); 
		}
		
		// B CLASS
				for(int i = 0; i < eb.size(); i++){
					entb = eb.get(i);

					entb.render(g); 
				}
		
	}

	public void addEntity(EntityA block){
		ea.add(block);
	}

	public void removeEntity(EntityA block){
		ea.remove(block);
	}
	
	public void addEntity(EntityB block){
		eb.add(block);
	}

	public void removeEntity(EntityB block){
		eb.remove(block);
	}
	public LinkedList<EntityA> getEntityA(){
		return ea;
	}
	public LinkedList<EntityB> getEntityB(){
		return eb;
	}

}