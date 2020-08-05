package com.esiho;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.esiho.combat.CombatState;
import com.esiho.screens.CombatScreen;
import com.esiho.screens.GameScreen;
import com.esiho.screens.MainScreen;
import com.esiho.world.map.GameMap;
import com.esiho.world.map.TiledGameMap;

public class Game extends com.badlogic.gdx.Game {
	public SpriteBatch batch;
	public static OrthographicCamera cam;
	public static GameMap gameMap;

	public final static int WIDTH = 720;
	public final static int HEIGHT = 480;

	public static boolean debug = false;
	public static Screen activeScreen;
	public static GameScreen gameScreen;
	public static Boolean pause = false;

	@Override
	public void create () {
		batch = new SpriteBatch();

		cam = new OrthographicCamera();
		cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.update();

		gameMap = new TiledGameMap("maison");
		gameMap.refreshEntities();

		activeScreen = new MainScreen(this);
		this.setScreen(activeScreen);
	}

	public void changeScreen(Screen screen){
		Screen backupScreen = activeScreen;
		try{
			activeScreen = screen;
			this.setScreen(activeScreen);
			String classWrote = ""+activeScreen.getClass().getCanonicalName();
			if (debug)System.out.println(classWrote);
			if (classWrote.equals("com.esiho.screens.GameScreen")){
				gameScreen= (GameScreen) activeScreen;
				if (debug)System.out.println("GameScreen est initialisé");

			}
		}catch (Exception e){
			activeScreen = backupScreen;
		}
	}

	public static void setActiveScreen(Screen activeScreen) {
		Game.activeScreen = activeScreen;
	}

	public void cbtScreen(CombatState cbt){
		activeScreen = new CombatScreen(this, cbt);
		this.setScreen(activeScreen);
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose () {
		batch.dispose();
		gameMap.dispose();
	}
}
