package shelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {
	
	public static Sound nut,laser,jump,die,kill,select;
	
	public static Texture bg,mountains,squirrelSheet,log1;
	
	public static TextureRegion sr1,sr2,sr3,sr4,sr5,sr6,
	srl1,srl2,srl3,srl4,srl5,srl6,
	sj1,sj2,sj3,sj4,
	sjl1,sjl2,sjl3,sjl4,
	nut1,nut2,nut3,nut4,
	bug1,bug2,
	back,mnt,lg1,lg2;
	
	public static Animation squirrelRunAnimation, squirrelJumpAnimation,
	squirrelLaserRunAnimation, squirrelLaserJumpAnimation, bugAnimation,nutAnimation;

	public static BitmapFont font;
	
	private static Preferences prefs;
	
	public static void load(){
		nut = Gdx.audio.newSound(Gdx.files.internal("data/Nut.wav"));
		die = Gdx.audio.newSound(Gdx.files.internal("data/Die.wav"));
		laser = Gdx.audio.newSound(Gdx.files.internal("data/Laser.wav"));
		jump = Gdx.audio.newSound(Gdx.files.internal("data/Jump.wav"));
		kill = Gdx.audio.newSound(Gdx.files.internal("data/Kill.wav"));
		select = Gdx.audio.newSound(Gdx.files.internal("data/Select.wav"));
		
		bg = new Texture(Gdx.files.internal("data/back1.png"));
		bg.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		mountains = new Texture(Gdx.files.internal("data/mountains.png"));
		mountains.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		log1 = new Texture(Gdx.files.internal("data/log1.png"));
		log1.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
				
		squirrelSheet = new Texture(Gdx.files.internal("data/squirrel.png"));
		squirrelSheet.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		back = new TextureRegion(bg,0,0,550,300);
		back.flip(false, true);

		lg1 = new TextureRegion(log1,0,0,550,120);
		lg1.flip(false, true);

		mnt = new TextureRegion(mountains,0,0,550,155);
		mnt.flip(false, true);
		
		sr1 = new TextureRegion(squirrelSheet,0,0,53,30);
		sr1.flip(false, true);
		sr2 = new TextureRegion(squirrelSheet,0,31,53,30);
		sr2.flip(false, true);
		sr3 = new TextureRegion(squirrelSheet,0,61,53,30);
		sr3.flip(false, true);
		sr4 = new TextureRegion(squirrelSheet,0,91,53,30);
		sr4.flip(false, true);
		sr5 = new TextureRegion(squirrelSheet,0,121,53,30);
		sr5.flip(false, true);
		sr6 = new TextureRegion(squirrelSheet,0,151,53,30);
		sr6.flip(false, true);
		
		srl1 = new TextureRegion(squirrelSheet,53,0,53,30);
		srl1.flip(false, true);
		srl2 = new TextureRegion(squirrelSheet,53,31,53,30);
		srl2.flip(false, true);
		srl3 = new TextureRegion(squirrelSheet,53,61,53,30);
		srl3.flip(false, true);
		srl4 = new TextureRegion(squirrelSheet,53,91,53,30);
		srl4.flip(false, true);
		srl5 = new TextureRegion(squirrelSheet,53,121,53,30);
		srl5.flip(false, true);
		srl6 = new TextureRegion(squirrelSheet,53,151,53,30);
		srl6.flip(false, true);
		
		sj1 = new TextureRegion(squirrelSheet,106,0,53,30);
		sj1.flip(false, true);
		sj2 = new TextureRegion(squirrelSheet,106,31,53,30);
		sj2.flip(false, true);
		sj3 = new TextureRegion(squirrelSheet,106,61,53,30);
		sj3.flip(false, true);
		sj4 = new TextureRegion(squirrelSheet,106,91,53,30);
		sj4.flip(false, true);
		
		sjl1 = new TextureRegion(squirrelSheet,159,0,53,30);
		sjl1.flip(false, true);
		sjl2 = new TextureRegion(squirrelSheet,159,31,53,30);
		sjl2.flip(false, true);
		sjl3 = new TextureRegion(squirrelSheet,159,61,53,30);
		sjl3.flip(false, true);
		sjl4 = new TextureRegion(squirrelSheet,159,91,53,30);
		sjl4.flip(false, true);
		
		nut1 = new TextureRegion(squirrelSheet,113,137,12,18);
		nut1.flip(false, true);
		nut2 = new TextureRegion(squirrelSheet,126,137,12,18);
		nut2.flip(false, true);
		nut3 = new TextureRegion(squirrelSheet,139,137,12,18);
		nut3.flip(false, true);
		nut4 = new TextureRegion(squirrelSheet,152,137,12,18);
		nut4.flip(false, true);
		
		bug1 = new TextureRegion(squirrelSheet,115,156,18,24);
		bug1.flip(false, true);
		bug2 = new TextureRegion(squirrelSheet,134,156,18,24);
		bug2.flip(false, true);
		
		TextureRegion[] squirrelRun = {sr1,sr2,sr3,sr4,sr5,sr6};
		TextureRegion[] squirrelLaserRun = {srl1,srl2,srl3,srl4,srl5,srl6};
		TextureRegion[] squirrelJump = {sj1,sj2,sj3,sj4};
		TextureRegion[] squirrelJumpLaser = {sjl1,sjl2,sjl3,sjl4};
		TextureRegion[] nut = {nut1,nut2,nut3,nut4};
		TextureRegion[] bug = {bug1,bug2};
		
		squirrelRunAnimation = new Animation (0.1f, squirrelRun);
		squirrelRunAnimation.setPlayMode(Animation.PlayMode.LOOP);
		squirrelLaserRunAnimation = new Animation (0.1f, squirrelLaserRun);
		squirrelLaserRunAnimation.setPlayMode(Animation.PlayMode.LOOP);
		squirrelJumpAnimation = new Animation (0.1f, squirrelJump);
		squirrelJumpAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
		squirrelLaserJumpAnimation = new Animation (0.1f, squirrelJumpLaser);
		squirrelLaserJumpAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
		nutAnimation = new Animation (0.12f, nut);
		nutAnimation.setPlayMode(Animation.PlayMode.LOOP);
		bugAnimation = new Animation (0.5f, bug);
		bugAnimation.setPlayMode(Animation.PlayMode.LOOP);
		
		font = new BitmapFont(Gdx.files.internal("data/font.fnt"));
		font.setScale(.25f,-.25f);
		
		//create or retrieve existing) preferences file
		prefs = Gdx.app.getPreferences("Squirrel");
		
		//provide default high score of 0
		if(!prefs.contains("highScore"))
			prefs.putInteger("highScore", 0);
		
	}
	public static void dispose(){
		bg.dispose();
		mountains.dispose();
		squirrelSheet.dispose();
		log1.dispose();
		
		kill.dispose();
		select.dispose();
		nut.dispose();
		laser.dispose();
		jump.dispose();
		die.dispose();
		
		font.dispose();
	}
	
	public static void setHighScore(int val){
		prefs.putInteger("highScore", val);
		prefs.flush();
	}
	public static int getHighScore(){
		return prefs.getInteger("highScore");
	}
}
