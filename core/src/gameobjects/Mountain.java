package gameobjects;

public class Mountain extends Scrollable{
	private int id;
	public Mountain(float x, float y, int width, int height, float scrollSpeed) {
		super(x, y, width, height, scrollSpeed);
		this.id = id;
	}
	public void onRestart(float x, float scrollSpeed){
		position.x = x;
		velocity.x = scrollSpeed;
	}


}
