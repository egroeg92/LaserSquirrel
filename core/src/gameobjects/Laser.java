package gameobjects;

import screens.GameScreen;
import shelpers.AssetLoader;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Laser {
	
	private final int WIDTH = 15;
	private final int HEIGHT = 3;
	
	private float positionX;
	private float positionY;
	private float speedX;
	private float speedY;
	private Rectangle rect;
	private boolean scrolledRight;
	
	private Squirrel squirrel;
	private ScrollHandler scroller;
	
	private static float laserSpeed;
	
	public Laser(Squirrel squirrel, Vector2 velocity, ScrollHandler scroller) {
		
		this.squirrel = squirrel;
		this.scroller = scroller;
		
		scrolledRight = false;
		positionX = squirrel.getX()+ squirrel.getWidth() - 10;
		positionY = squirrel.getY()+ 9;
		rect = new Rectangle(positionX, positionY, WIDTH,HEIGHT);
		
		 
		// 65 is base original speed
		System.out.println(this.scroller.getScrollSpeed());
		laserSpeed = -1*(this.scroller.getScrollSpeed() + 65) + 95;
		speedX = velocity.x + laserSpeed;
		
		if(velocity.y > 0)
			speedY = 20;
		else if (velocity.y < 0)
			speedY = -20;
		else
			speedY = 0;
	}
	
	public static void restart(){
		laserSpeed = 95;
	}
	
	public void update(float delta) {
		positionX += (delta * speedX);
		positionY += (delta * speedY);
		
		
		if(positionX-WIDTH > GameScreen.GAMEWIDTH){
			scrolledRight = true;
		}
		else
			scrolledRight = false;
		
		rect.x = positionX;
		rect.y = positionY;
	}
	public boolean collidesBug(Bug bug) {
		if(positionX < bug.getX() + bug.getWidth() && !bug.isLasered()){
			if((Intersector.overlaps(bug.getBugCircle(), rect))){
				scroller.incScrollSpeed();
				bug.setLasered();
				AssetLoader.kill.play();
				scrolledRight = true;
				return true;
			}
		}
		return false;
	}
	public boolean collidesNut(Nut nut) {
		if(positionX < nut.getX() + nut.getWidth() && !nut.isLasered()){
			if((Intersector.overlaps(rect, nut.getNutRect()))){
				scroller.incScrollSpeed();
				nut.setLasered();
				AssetLoader.kill.play();
				scrolledRight = true;
				return true;
			}
		}
		return false;
	}
	
	public boolean isScrolledRight(){
		return scrolledRight;
	}
	public Rectangle getRect(){
		return rect;
	}
	

	

}
