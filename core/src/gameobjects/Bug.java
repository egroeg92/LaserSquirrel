package gameobjects;

import gameworld.GameWorld;

import java.util.Random;

import shelpers.AssetLoader;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;

public class Bug extends Scrollable {
	private Circle bugRect;
	
	private Random r;
	private boolean killedSquirrel;
	private boolean isLasered;
	
	
	public Bug(float x, float y, int width, int height, float scrollSpeed) {
		super(x, y, width, height, scrollSpeed);
		r = new Random();
		bugRect = new Circle();
		killedSquirrel = false;
		isLasered = false;
	
	}
	
	@Override
	public void reset(float newX){
		super.reset(newX);
		//height needs to be random between 30 and -80
		position.y = r.nextInt(120) + 30;
		isLasered = false;
	}
	@Override
	public void update(float delta, float scrollSpeedX, float scrollSpeedY){
		super.update(delta, scrollSpeedX, scrollSpeedY);
		
		bugRect.set(position.x+8, position.y+width+width + 2, width/2 +2);
		
	}
	public void onRestart(float x, float scrollSpeed){
		velocity.x = scrollSpeed;
		reset(x);
	}
	public boolean collidesSquirrel(Squirrel squirrel, GameWorld world){
		
		if(position.x < squirrel.getX() + squirrel.getWidth() && !isLasered){
			if((Intersector.overlaps(bugRect,squirrel.getBoundingCircle() ))){
				squirrel.setDead();
				killedSquirrel = true;
				AssetLoader.die.play();
				return true;
			}
		}
		return false;
	}
	
	
	public Circle getBugCircle(){
		return bugRect;
	}
	
	public boolean isDeath(){
		return isLasered;
	}
	public boolean hasKilledSquirrel(){
		return killedSquirrel;
	}
	public void setLasered() {
		isLasered = true;
	}

	public boolean isLasered() {
		return isLasered;
	}

}
