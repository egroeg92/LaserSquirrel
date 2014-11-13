package gameworld;

import gameobjects.Bug;
import gameobjects.Laser;
import gameobjects.Log;
import gameobjects.Mountain;
import gameobjects.Nut;
import gameobjects.RestartButton;
import gameobjects.ScrollHandler;
import gameobjects.Squirrel;
import gameobjects.StartButton;
import gameworld.GameWorld.GameState;

import java.util.HashSet;

import shelpers.AssetLoader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

public class GameRenderer {
	private GameWorld world;
	private OrthographicCamera cam;
	private ShapeRenderer shapeRenderer;
	private SpriteBatch batcher;
	
	private ScrollHandler scroller;
	
	private TextureRegion back,mountain,log1,log2;
	private Animation squirrelRunAnimation, squirrelLaserRunAnimation,
	squirrelJumpAnimation, squirrelLaserJumpAnimation, 
	bugAnimation, nutAnimation;
	
	private Log scroll_log1,scroll_log2;
	private Mountain scroll_mountain1, scroll_mountain2;
	private Bug scroll_bug;
	private Nut scroll_nut;
	
	private Squirrel squirrel;
	
	private RestartButton restartButton;
	private StartButton jumpStartButton;
	private StartButton shootStartButton;
	
	public GameRenderer(GameWorld world, int gameHeight) {
		this.world = world;

		cam = new OrthographicCamera();
		cam.setToOrtho(true, 225, gameHeight);
        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(cam.combined);
        
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);
        
        initGameObjects();
        initAssets();
	}

	private void initAssets() {
		back = AssetLoader.back;
		mountain = AssetLoader.mnt;
		log1 = AssetLoader.lg1;
		squirrelRunAnimation = AssetLoader.squirrelRunAnimation;
		squirrelLaserRunAnimation = AssetLoader.squirrelLaserRunAnimation;
		squirrelJumpAnimation = AssetLoader.squirrelJumpAnimation;
		squirrelLaserJumpAnimation = AssetLoader.squirrelLaserJumpAnimation;
		nutAnimation = AssetLoader.nutAnimation;
		bugAnimation = AssetLoader.bugAnimation;
		
		
	}

	private void initGameObjects() {
		scroller = world.getScroller();
		scroll_log1 = scroller.getLog1();
		scroll_log2 = scroller.getLog2();
		scroll_mountain1 = scroller.getMountain1();
		scroll_mountain2 = scroller.getMountain2();
		squirrel = world.getSquirrel();
		scroll_bug = scroller.getBug();
		scroll_nut = scroller.getNut();
		
		restartButton = world.getRestartButton();
		shootStartButton = world.getShootStartButton();
		jumpStartButton = world.getJumpStartButton();
		
	}

	public void render(float runTime) {
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batcher.begin();
		
		batcher.draw(back, 0, 0);
		batcher.enableBlending();
		batcher.draw(mountain, scroll_mountain1.getX(), scroll_mountain1.getY() + scroll_mountain1.getHeight());
		batcher.draw(mountain, scroll_mountain2.getX(), scroll_mountain2.getY() + scroll_mountain2.getHeight());
		batcher.draw(log1, scroll_log1.getX() , scroll_log1.getY() + scroll_log1.getHeight());
		batcher.draw(log1, scroll_log2.getX() , scroll_log2.getY() + scroll_log2.getHeight());
		
		if(!squirrel.isAlive()){
			batcher.draw(squirrelJumpAnimation.getKeyFrame(2), squirrel.getX(), squirrel.getY(),
					squirrel.getWidth()/2, squirrel.getHeight()/2,
					squirrel.getWidth(), squirrel.getHeight(),
					1,1,
					squirrel.getRotation());	
			
		}else if(squirrel.isJumping() && !squirrel.getLaser()){
			batcher.draw(squirrelJumpAnimation.getKeyFrame(runTime), squirrel.getX(), squirrel.getY(),
					squirrel.getWidth()/2, squirrel.getHeight()/2,
					squirrel.getWidth(), squirrel.getHeight(),
					1,1,
					squirrel.getRotation());	
		} else if(squirrel.isJumping() && squirrel.getLaser()){
			batcher.draw(squirrelLaserJumpAnimation.getKeyFrame(runTime), squirrel.getX(), squirrel.getY(),
					squirrel.getWidth()/2, squirrel.getHeight()/2,
					squirrel.getWidth(), squirrel.getHeight(),
					1,1,
					squirrel.getRotation());	
		}else if(!squirrel.isJumping() && squirrel.getLaser()){
			batcher.draw(squirrelLaserRunAnimation.getKeyFrame(runTime), squirrel.getX(), squirrel.getY(),
					squirrel.getWidth()/2, squirrel.getHeight()/2,
					squirrel.getWidth(), squirrel.getHeight(),
					1,1,
					squirrel.getRotation());	
		}else{
			batcher.draw(squirrelRunAnimation.getKeyFrame(runTime), squirrel.getX(), squirrel.getY(),
					squirrel.getWidth()/2, squirrel.getHeight()/2,
					squirrel.getWidth(), squirrel.getHeight(),
					1,1,
					squirrel.getRotation());
		}
		
		
		if(!scroll_nut.isCollected() && !scroll_nut.isLasered()  && 
				(world.getState() == GameState.RUNNING || world.getState() == GameState.GAMEOVER)){
			batcher.draw(nutAnimation.getKeyFrame(runTime), scroll_nut.getX(), scroll_nut.getY() + scroll_nut.getHeight());
		}
		if(!scroll_bug.isLasered() && 
				(world.getState() == GameState.RUNNING || world.getState() == GameState.GAMEOVER)){
			batcher.draw(bugAnimation.getKeyFrame(runTime), scroll_bug.getX(), scroll_bug.getY() + scroll_bug.getHeight());
		}
		
		AssetLoader.font.draw(batcher, world.getScore(), 100-(3*world.getScore().length()), 10);
		
		batcher.end();
		
		
		Gdx.graphics.getGL20().glEnable(GL20.GL_BLEND);
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(0,1,0,0.5f);
		HashSet<Laser> lasers = world.getSquirrel().getLasers();
		
		for(Laser l : lasers){
			if(l.isScrolledRight())
				squirrel.removeLasers(l);
			else{
				Rectangle r = l.getRect();
				shapeRenderer.rect(r.x, r.y, r.width, r.height);
			}
		}
		
		
		Gdx.graphics.getGL20().glEnable(GL20.GL_BLEND);
//		Rectangle r = myWorld.getSquirrel().getBoundingCircle(); 
//		shapeRenderer.setColor(new Color(0,1,0,0.5f));
//		shapeRenderer.rect(r.x, r.y, r.width, r.height);

//		Circle r2 = scroll_bug.getBugCircle(); 
//		shapeRenderer.setColor(new Color(0,1,0,0.5f));
//		shapeRenderer.circle(r2.x, r2.y, r2.radius);
		
		if(world.isGameOver()){
			Rectangle r = restartButton.getRect(); 
			shapeRenderer.setColor(new Color(0,1,0,0.5f));
			shapeRenderer.rect(r.x, r.y, r.width, r.height);
		}
		
		if(world.isReady()){
			Rectangle s = shootStartButton.getRect();
			Rectangle j = jumpStartButton.getRect();
			if((int)runTime % 2 == 0){
				shapeRenderer.setColor(new Color(0,0,1,0.5f));
				shapeRenderer.rect(s.x, s.y, s.width, s.height);
				
				shapeRenderer.setColor(new Color(1,0,0,0.5f));
				shapeRenderer.rect(j.x, j.y, j.width, j.height);
			}
			
		}
		shapeRenderer.end();
		

	}

}
