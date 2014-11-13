package com.egroeg.squirrelgame.desktop;

import squirrelgame.SquirrelGame;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Squirrel Run";
		config.width = 550;
		config.height = 700;
		
		new LwjglApplication(new SquirrelGame(), config);
	}
}
