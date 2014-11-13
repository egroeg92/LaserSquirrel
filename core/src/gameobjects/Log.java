package gameobjects;

public class Log extends Scrollable {

	public Log(float x, float y, int width, int height, float scrollSpeed) {
		super(x, y, width, height, scrollSpeed);
	}
	public void onRestart(float x, float scrollSpeed){
		position.x = x;
		velocity.x = scrollSpeed;
	}

}
