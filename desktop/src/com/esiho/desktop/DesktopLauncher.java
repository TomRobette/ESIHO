package com.esiho.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.esiho.Game;
import com.esiho.ScreenLoader;

public class DesktopLauncher {
	public static Game game;
	public static ScreenLoader screenLoader;
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.foregroundFPS = 60;
		config.width = Game.WIDTH;
		config.height = Game.HEIGHT;
		config.resizable = false;
		game = new Game();
		screenLoader = new ScreenLoader(game);
		new LwjglApplication(ScreenLoader.game, config);
	}
}
