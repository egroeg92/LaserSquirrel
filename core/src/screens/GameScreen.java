package screens;

import gameworld.GameRenderer;
import gameworld.GameWorld;
import shelpers.InputHandler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;


public class GameScreen implements Screen {
	private GameWorld world;
	private GameRenderer renderer;
	private float runTime = 0;
	
	public final static float SCREENWIDTH = Gdx.graphics.getWidth();
	public final static float SCREENHEIGHT = Gdx.graphics.getHeight();
	public final static float GAMEWIDTH = 225;
	public final static float GAMEHEIGHT = SCREENHEIGHT / (SCREENWIDTH/GAMEWIDTH);
	
	public GameScreen(){
		world = new GameWorld();
		renderer = new GameRenderer(world, (int) GAMEHEIGHT);
		
//		//set input processor
		Gdx.input.setInputProcessor(new InputHandler(world, (int) SCREENWIDTH));
	}
	
	@Override
	public void render(float delta) {
		runTime += delta;
		world.update(delta); // GameWorld updates
		renderer.render(runTime);	//Renderer renders
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
