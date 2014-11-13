package squirrelgame;

import screens.GameScreen;
import shelpers.AssetLoader;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SquirrelGame extends Game {
	SpriteBatch batch;
	Texture img;
	
	@Override
	public void create() {
		AssetLoader.load();
		setScreen(new GameScreen());
	}
	
	@Override
	public void dispose(){
		super.dispose();
		AssetLoader.dispose();
	}
}
