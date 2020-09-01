package com.esiho;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.esiho.combat.CombatState;
import com.esiho.combat.combattants.Combattant;
import com.esiho.combat.combattants.CombattantType;
import com.esiho.combat.teams.TeamType;
import com.esiho.screens.CombatScreen;
import com.esiho.screens.GameScreen;
import com.esiho.screens.MainScreen;
import com.esiho.world.entities.Entity;
import com.esiho.world.map.GameMap;
import com.esiho.world.map.TiledGameMap;
import com.esiho.world.scenario.QuestsStatus;

public class Game extends com.badlogic.gdx.Game {
	public SpriteBatch batch;
	public static OrthographicCamera cam;
	public static GameMap gameMap;

	public final static int WIDTH = 720;
	public final static int HEIGHT = 480;

	public static boolean debug = false;
	public static Screen activeScreen;
	public static GameScreen gameScreen;
	public static boolean pause = false;
	public static boolean inventaireActif = false;
	public static boolean lvlUpActif = false;
	public static boolean finCbt = false;
	public static boolean dialogueActif = false;
	public static boolean newItemActif = false;
	public static boolean journalActif = false;

	public static CombatState finCbtState;

	@Override
	public void create () {
		batch = new SpriteBatch();

		cam = new OrthographicCamera();
		cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.update();

		gameMap = new TiledGameMap("maison");
		gameMap.refreshEntities();

		TeamType.JOUEUR.listeCbtEntities.add(new Combattant(CombattantType.JOUEUR));

		QuestsStatus.create();

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

	public static void killEntityOnActiveMap(Entity entity){
		gameMap.killEntity(entity);
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
