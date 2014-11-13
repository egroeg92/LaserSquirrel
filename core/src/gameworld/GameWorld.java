package gameworld;

import gameobjects.RestartButton;
import gameobjects.ScrollHandler;
import gameobjects.Squirrel;
import gameobjects.StartButton;
import screens.GameScreen;
import shelpers.AssetLoader;

public class GameWorld {

	private ScrollHandler scroller;
	private Squirrel squirrel;
	
	private RestartButton restartButton;
	private StartButton jumpStartButton, shootStartButton;
	
	
	private final int S_HEIGHT = 30;
	private final int S_WIDTH = 53;
	private final int S_STARTX = 10;
	private final int S_STARTY = 178;
	
	private static int score = 0;
	private GameState currentState;
	
	public enum GameState{
		MENU,RUNNING,READY,GAMEOVER;
	}
	
	public GameWorld() {
		
		squirrel = new Squirrel(S_STARTX, S_STARTY, S_WIDTH, S_HEIGHT, this);
		scroller = new ScrollHandler(this);
		
		
		// x should be half gamewidth - 0.5 button width- y should be similar
		restartButton = new RestartButton(75, 100, 75, 50, this);
		
		//
		jumpStartButton = new StartButton((int)GameScreen.GAMEWIDTH/2,0,(int)GameScreen.GAMEWIDTH/2,(int)GameScreen.GAMEHEIGHT,"jumpStart",this);
		shootStartButton = new StartButton(0,0,(int)GameScreen.GAMEWIDTH/2,(int)GameScreen.GAMEHEIGHT,"shootStart",this);
		
		
		// TODO should be initialized to MENU
		currentState = GameState.RUNNING;
		
		
	}


	public void update(float delta){
		switch(currentState){
		case MENU:
			updateMenu(delta);
			break;
		case READY:
			updateReady(delta);
			break;
		case RUNNING:
			updateRunning(delta);
			break;
		case GAMEOVER:
			updateGameover(delta);
			break;
		default:
			break;

		}
	}
	private void updateGameover(float delta) {
		if(delta >.15f){
			delta = .15f;
		}
		squirrel.update(delta);
		scroller.updateGameOver(delta);
		
		
	}


	private void updateMenu(float delta) {
		//TODO
	}
	
	private void updateReady(float delta) {
		if(delta >.15f){
			delta = .15f;
		}
		squirrel.update(delta);
		scroller.updateReady(delta);
		
	}

	public void updateRunning(float delta) {
		if(delta >.15f){
			delta = .15f;
		}
		
		squirrel.update(delta);
		scroller.updateRunning(delta);
		
		if(score > AssetLoader.getHighScore()){
			AssetLoader.setHighScore(score);
		}
	}

	public ScrollHandler getScroller() {
		return scroller;
	}

	public Squirrel getSquirrel() {
		return squirrel;
	}
	
	public void incScore(){
		score++;
	}

	public String getScore() {
		return Integer.toString(score);
	}
	
	public boolean isReady(){
		return (currentState == GameState.READY);
	}
	public boolean isRunning(){
		return (currentState == GameState.RUNNING);
	}	
	public boolean isGameOver(){
		return (currentState == GameState.GAMEOVER);
	}
	public void start(){
		currentState = GameState.RUNNING;
	}
	public void restart(){
		score = 0;
		squirrel.onRestart(S_STARTX, S_STARTY);
		scroller.onRestart();
		currentState = GameState.READY;
	}


	public void setState(GameState gameState) {
		currentState = gameState;
	}


	public GameState getState() {
		return currentState;
	}
	public RestartButton getRestartButton(){
		return restartButton;
	}


	public StartButton getShootStartButton() {
		return shootStartButton;
	}
	
	public StartButton getJumpStartButton(){
		return jumpStartButton;
	}

}
