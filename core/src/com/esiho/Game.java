package com.esiho;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.esiho.screens.GameScreen;
import com.esiho.screens.MainScreen;
import com.esiho.world.map.GameMap;
import com.esiho.world.map.TiledGameMap;

public class Game extends com.badlogic.gdx.Game {
	public SpriteBatch batch;
	public static OrthographicCamera cam;
	public GameMap gameMap;

	public final static int WIDTH = 720;
	public final static int HEIGHT = 480;

	@Override
	public void create () {
		batch = new SpriteBatch();

		cam = new OrthographicCamera();
		cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.update();

		gameMap = new TiledGameMap();

		this.setScreen(new MainScreen(this));
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
