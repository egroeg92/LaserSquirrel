package gameobjects;

import gameworld.GameWorld;
import gameworld.GameWorld.GameState;

import java.util.Random;

public class ScrollHandler {
	private Log log1,log2;
	private Mountain mountain1, mountain2;
	private Squirrel squirrel;
	private Nut nut;
	private Bug bug;
	
	private float runTime;
	private float incTime;
	
	private static int scroll_run = -65;
	private static int scroll_mountain = -20;

	private static int scroll_base_run = -65;
	private static int scroll_base_mountain = -20;
	
	private GameWorld world;
	
	public ScrollHandler(GameWorld gameWorld){
		world = gameWorld;
		log1 = new Log(0, 167, 275, 40, scroll_run);
		log2 = new Log(log1.getTailX(), 167, 275, 40, scroll_run);
		mountain1 = new Mountain(0,0,550,155, scroll_mountain);
		mountain2 = new Mountain(mountain1.getTailX(),0,550,155, scroll_mountain);
		bug = new Bug(400,147,17,25,0);
		nut = new Nut(470,147,12,18,0);
		
		squirrel = world.getSquirrel();
		
		runTime = 0;
		incTime = 0;
	}
	
	public void onRestart() {
		
		restartScrolling();
		
		runTime = 0;
		incTime = 0;
		
		bug.setPosition(400,147);
		nut.setPosition(470,147);
		
		
	}
	private void restartScrolling(){
		scroll_run = -65;
		scroll_mountain = -20;
		
		scroll_base_run = -65;
		scroll_base_mountain = -20;
	}

	public void updateRunning(float delta){
		runTime += delta;
		if(runTime > incTime + 10){
			incTime = runTime;
			scroll_base_run -= 25;
			scroll_base_mountain -= 25;
			
			//TODO update laserSpeed, killing bug increase scroll speed
			
			System.out.println(scroll_base_run);
			
		}
		
		log1.update(delta,scroll_run,0);
		log2.update(delta,scroll_run,0);
		mountain1.update(delta,scroll_mountain,0);
		mountain2.update(delta,scroll_mountain,0);
		
		bug.update(delta,scroll_run,0);
		nut.update(delta,scroll_run,0);
		
		nut.collides(squirrel, world);
		
		if(bug.collidesSquirrel(squirrel, world)){
			world.setState(GameState.GAMEOVER);
		}
		
		for(Laser l: squirrel.getLasers()){
			l.update(delta);
			l.collidesBug(bug);
			l.collidesNut(nut);
		}
		
		
		if(squirrel.isJumping() && world.isRunning()){
			scroll_run = scroll_base_run - (int)squirrel.getVelocityX();
			scroll_mountain = scroll_base_mountain - (int)squirrel.getVelocityX()/3 ;
		}
		else if (world.isRunning()){
			scroll_run = scroll_base_run;
			scroll_mountain = scroll_base_mountain ;
		}
		else{
			scroll_run = 0;
			scroll_mountain = 0;
		}
			
		if (log1.isScrolledLeft()){
			log1.reset(log2.getTailX());
		}
		if (log2.isScrolledLeft()){
			log2.reset(log1.getTailX());
		}
		if(mountain1.isScrolledLeft()){
			mountain1.reset(mountain2.getTailX());
		}
		if(mountain2.isScrolledLeft()){
			mountain2.reset(mountain1.getTailX());
		}
		if(nut.isScrolledLeft()){
			Random r = new Random();
			float nut_gap = r.nextInt(300) + 250;
			nut.reset(nut.getTailX()+ nut_gap );
		}
		if(bug.isScrolledLeft() ){
			Random r = new Random();
			float bug_gap = r.nextInt(300)+225;
			bug.reset(bug.getTailX() + bug_gap );
		}
	}
	public void updateReady(float delta){
		
		log1.update(delta,scroll_run,0);
		log2.update(delta,scroll_run,0);
		mountain1.update(delta,scroll_mountain,0);
		mountain2.update(delta,scroll_mountain,0);
		
		if (log1.isScrolledLeft()){
			log1.reset(log2.getTailX());
		}
		if (log2.isScrolledLeft()){
			log2.reset(log1.getTailX());
		}
		if(mountain1.isScrolledLeft()){
			mountain1.reset(mountain2.getTailX());
		}
		if(mountain2.isScrolledLeft()){
			mountain2.reset(mountain1.getTailX());
		}
	}
	public void updateGameOver(float delta) {
		log1.update(delta,scroll_run,0);
		log2.update(delta,scroll_run,0);
		mountain1.update(delta,scroll_mountain,0);
		mountain2.update(delta,scroll_mountain,0);
		bug.update(delta,scroll_run,0);
		nut.update(delta,scroll_run,0);
		for(Laser l: squirrel.getLasers()){
			l.update(delta);
			l.collidesBug(bug);
			l.collidesNut(nut);
		}
		
	}
	public void incScrollSpeed(){
		scroll_base_run -=5;
		scroll_base_mountain -=5;
		
	}
	
	public Log getLog1() {
		return log1;
	}
	public Log getLog2() {
		return log2;
	}

	public Mountain getMountain1() {
		return mountain1;
	}
	public Mountain getMountain2() {
		return mountain2;
	}
	public Bug getBug() {
		return bug;
	}
	public Nut getNut() {
		return nut;
	}

	public int getScrollSpeed() {
		return scroll_base_run;
	}
	

}
