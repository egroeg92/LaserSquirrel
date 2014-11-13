package gameobjects;

import screens.GameScreen;

import com.badlogic.gdx.math.Rectangle;

public abstract class Button {
	private int x;
	private int y;
	private int width;
	private int height;
	private String title;
	private Rectangle rect;
	public Button(int x, int y, int width, int height, String title){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.title = title;
		
		rect = new Rectangle(x,y,width,height);
	}
	public boolean pushed(int x, int y) {
		if(x >= this.x && x <= this.x+width && y >= this.y && y <= this.y + height){
			return true;
		}
		return false;
	}
	public Rectangle getRect(){
		return rect;
	}
	public String getTitle(){
		return title;
	}
	public abstract void action();
	
	

}
