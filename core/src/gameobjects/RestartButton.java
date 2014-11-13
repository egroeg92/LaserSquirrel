package gameobjects;

import gameworld.GameWorld;
import gameworld.GameWorld.GameState;

public class RestartButton extends Button {

	private GameWorld world;
	public RestartButton(int x, int y, int width, int height, GameWorld gameWorld) {
		super(x, y, width, height, "Restart");
		world = gameWorld;
	}

	@Override
	public void action() {
		world.setState(GameState.READY);
	}

}
