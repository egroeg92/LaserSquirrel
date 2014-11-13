package gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class Scrollable {
	protected Vector2 position;
	protected Vector2 velocity;
	protected int width;
	protected int height;
	protected boolean isScrolledLeft;
	
	public Scrollable(float x, float y, int width, int height , float scrollSpeed){
		position = new Vector2(x,y);
		velocity = new Vector2(scrollSpeed, 0);
		this.width = width;
		this.height = height;
		isScrolledLeft = false;
	}
	
	public void update(float delta, float scrollSpeedX, float scrollSpeedY){
		velocity.x = scrollSpeedX;
		velocity.y = scrollSpeedY;
		position.add(velocity.cpy().scl(delta));
	// if the scrollable object is no longer visible:
		if(position.x + width < 0){
			isScrolledLeft = true;
		}
		
	}
	// Rest: Should Override in subclass for more specific behavior
	public void reset(float newX){
		position.x = newX;
		isScrolledLeft = false;
	}
	
	public void stop(){
		velocity.x = 0;
	}
	public void setPosition(float x, float y){
		position.set(x,y);
	}
	//Getters for instance variables
	public boolean isScrolledLeft(){
		return isScrolledLeft;
	}
	public float getTailX(){
		return position.x+width;
	}
	public float getX(){
		return position.x;
	}
	public float getY(){
		return position.y;
	}
	public float getWidth(){
		return width;
	}
	public float getHeight(){
		return height;
	}
}
