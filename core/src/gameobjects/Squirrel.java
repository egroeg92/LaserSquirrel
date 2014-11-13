package gameobjects;

import gameworld.GameWorld;

import java.util.HashSet;

import screens.GameScreen;
import shelpers.AssetLoader;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Squirrel {
	private float positionX;
	private float positionY;
	private Vector2 velocity;
	private Vector2 acceleration;
	private float restingY;
	
	private float rotation;
	private int height;
	private int width;
	
	private int laserCount;
	private int jumpCount;
	private boolean hold;
	
	private Rectangle boundingRectangle;
	
	private boolean isAlive;
	private boolean isJumping;
	private boolean isLaser;
	
	private HashSet<Laser> lasers = new HashSet<Laser>();
	
	private GameWorld world;
	
	
	public Squirrel(float x, float y, int width, int height, GameWorld world){
		this.width = width;
		this.height = height;
		positionX = x;
		positionY = y;
		velocity = new Vector2(0,0);
		acceleration = new Vector2(0,0);
		boundingRectangle = new Rectangle(x+13,y+5,width-15,height-10);
		isAlive = true;
		isJumping = false;
		restingY = y;
		jumpCount = 0;
		hold = false;
		isLaser = false;
		laserCount = 0;
		this.world = world;
	}
	public void onRestart(float x, float y){
		positionX = x;
		positionY = y;
		velocity.set(0, 0);
		acceleration.set(0,0);
		boundingRectangle.set(x+13,y+5,width-15,height-10);
		isAlive = true;
		isJumping = false;
		restingY = y;
		jumpCount = 0;
		hold = false;
		isLaser = false;
		laserCount = 0;
		rotation = 0;
	}
	public void update(float delta){
		if(!isAlive){
			if(positionY < GameScreen.GAMEHEIGHT + 100){
				acceleration.y +=  10;
				velocity.add(acceleration.cpy().scl(delta));
				positionY += velocity.cpy().scl(delta).y;
				rotation -= 200 * delta;
			}
		}
		else{
			if(isJumping && hold){
				if(velocity.y <0)
					acceleration.y += 8;
	
			}else if(isJumping && !hold){
				if(velocity.y <0)
					acceleration.y += 20;
			}
			velocity.add(acceleration.cpy().scl(delta));
			
			positionY += velocity.cpy().scl(delta).y;
			if(isJumping){
				if(velocity.y<-30){
					boundingRectangle.y = positionY +3;
					rotation -= 90 * delta;
					if(rotation < -30)
						rotation = -30;
				}
				else{
					boundingRectangle.y = positionY +5;
					rotation += 100 * delta;
					if(rotation >20)
						rotation = 20;
				}
				if(positionY > restingY ){
					velocity.y = 0;
					velocity.x = 0;
					acceleration.y = 0;
					rotation = 0;
					positionY = restingY;
					isJumping = false;
					boundingRectangle.y = positionY +5;
					jumpCount = 0;
				}
				
			}
			else
				boundingRectangle.y = positionY +5;
		}		
	}
	public void jump() {
		if(jumpCount ==0){
			AssetLoader.jump.play();
			velocity.y = -150;
			velocity.x = 30;
			isJumping = true;
			jumpCount = 1;
			hold = true;
			rotation = -20;
		}
		else if (jumpCount == 1){
			AssetLoader.jump.play();
			velocity.y = -150;
			velocity.x = 40;
			isJumping = true;
			jumpCount = 2;
			acceleration.y = 0;
			rotation = 0;
			hold = true;
			rotation = -20;
		}
	}
	
	public Laser shoot() {
		ScrollHandler scroller = world.getScroller();
		isLaser = true;
		if(laserCount < 5){
			AssetLoader.laser.play();
			laserCount++;
			Laser laser = new Laser(this, velocity, scroller);
			lasers.add(laser);
			return laser;
		}
		return null;
	}
	
	public void release() {
		hold = false;
		isLaser = false;
	}

	public Rectangle getBoundingCircle(){
		return boundingRectangle;
	}
	public float getX() {
		return positionX;
	}
	public float getY() {
		return positionY;
	}
	public float getWidth() {
		return width;
	}
	public float getHeight() {
		return height;
	}
	public float getRotation(){
		return rotation;
	}
	public boolean isJumping(){
		return isJumping;
	}
	public boolean isAlive(){
		return isAlive;
	}
	public float getVelocityX(){
		return velocity.x;
	}
	public int getJumpCount(){
		return jumpCount;
	}
	public boolean getLaser() {
		return isLaser;
	}
	
	public HashSet<Laser> getLasers(){
		return (HashSet<Laser>) lasers.clone();
	}
	public void removeLasers(Laser laser){
		lasers.remove(laser);
		laserCount --;
	}
	public void setDead() {
		isAlive = false;
		velocity.y = -200;
	}
	


}

