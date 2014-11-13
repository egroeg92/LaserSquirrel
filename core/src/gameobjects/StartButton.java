package gameobjects;

import gameworld.GameWorld;
import gameworld.GameWorld.GameState;

public class StartButton extends Button{

	private GameWorld world;

	public StartButton(int x, int y, int width, int height, String title, GameWorld gameWorld) {
		super(x, y, width, height, title);
		world = gameWorld;
	}

	@Override
	public void action() {
		world.setState(GameState.RUNNING);
	}
}
