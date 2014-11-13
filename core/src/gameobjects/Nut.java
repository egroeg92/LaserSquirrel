package gameobjects;

import gameworld.GameWorld;

import java.util.Random;

import shelpers.AssetLoader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class Nut extends Scrollable{
	private static int gap;
	private final int NUT_WIDTH = 12;
	private final int NUT_HEIGHT = 18;
	private Rectangle nutRect;
	
	private Random r;
	private boolean collected;
	private boolean isLasered;
	public Nut(float x, float y, int width, int height, float scrollSpeed) {
		super(x, y, width, height, scrollSpeed);
		r = new Random();
		nutRect = new Rectangle(x,y+height,width,height);
		collected = false;
		isLasered = false;
	}
	@Override
	public void reset(float newX){
		super.reset(newX);
		//y needs to be random between 30 and -80
		position.y = r.nextInt(120)+30;
		isLasered = false;
		collected = false;
	}
	@Override
	public void update(float delta, float scrollSpeedX, float scrollSpeedY){
		super.update(delta, scrollSpeedX, scrollSpeedY);
		nutRect.set(position.x, position.y+height, width, height);
		
	}
	public void onRestart(float x, float scrollSpeed){
		velocity.x = scrollSpeed;
		reset(x);
	}
	public boolean collides(Squirrel squirrel, GameWorld world){
		if(position.x < squirrel.getX() + squirrel.getWidth() && !collected && !isLasered){
			if((Intersector.overlaps(squirrel.getBoundingCircle(), nutRect))){
				collected = true;
				AssetLoader.nut.play();
				world.incScore();
				return true;
			}
		}
		return false;
	}
	public Rectangle getNutRect(){
		return nutRect;
	}
	public boolean isCollected(){
		return collected;
	}
	public void setLasered() {
		isLasered = true;
	}
	public boolean isLasered() {
		return isLasered;
	}

}
