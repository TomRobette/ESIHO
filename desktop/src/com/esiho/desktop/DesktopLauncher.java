package com.esiho.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.esiho.Game;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.foregroundFPS = 60;
		config.width = Game.WIDTH;
		config.height = Game.HEIGHT;
		config.resizable = false;
		new LwjglApplication(new Game(), config);
	}
}
