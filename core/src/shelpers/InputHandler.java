package shelpers;

import gameobjects.Squirrel;
import gameworld.GameWorld;
import gameworld.GameWorld.GameState;
import screens.GameScreen;

import com.badlogic.gdx.InputProcessor;

public class InputHandler implements InputProcessor {
	private GameWorld world;
	private int width;
	private Squirrel squirrel;
	public InputHandler(GameWorld world, int screenWidth){
		this.world = world;
		width = screenWidth;
		squirrel = world.getSquirrel();
	}
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		switch(world.getState()){
		case MENU:
			
			break;
		case READY:
			readyDown(screenX,screenY);
			break;
		case RUNNING:
			runningDown(screenX);
			break;
		case GAMEOVER:
			gameOverDown(screenX,screenY);
			break;
		default:
			break;
		}
		return false;		
	}
	private void readyDown(int screenX, int screenY) {
		if(world.getJumpStartButton().pushed((int)(GameScreen.GAMEWIDTH*(screenX / GameScreen.SCREENWIDTH)), (int)(GameScreen.GAMEHEIGHT*(screenY / GameScreen.SCREENHEIGHT))) 
				|| (world.getShootStartButton().pushed((int)(GameScreen.GAMEWIDTH*(screenX / GameScreen.SCREENWIDTH)), (int)(GameScreen.GAMEHEIGHT*(screenY / GameScreen.SCREENHEIGHT)))))			
		{
			world.start();
		}		
	}
	private void gameOverDown(int screenX, int screenY) {

		if(world.getRestartButton().pushed((int)(GameScreen.GAMEWIDTH*(screenX / GameScreen.SCREENWIDTH)), (int)(GameScreen.GAMEHEIGHT*(screenY / GameScreen.SCREENHEIGHT))))
		{
			world.restart();
		}
	}
	private void runningDown(int screenX) {
		if( screenX >= width / 2)
			squirrel.jump();
		else
			squirrel.shoot();
	}
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		switch(world.getState()){
		case MENU:
			break;
		case READY:
			break;
		case RUNNING:
			runningUp(screenX);
			break;
		case GAMEOVER:
			break;
		default:
			break;
		}
		return false;
	}
	
	private void runningUp(int screenX) {
		squirrel.release();
	}
	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}





	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
}
