package com.esiho.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.esiho.Game;

public class GameScreen implements Screen {
    Game game;

    public GameScreen(Game game){
        this.game=game;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

//		if (Gdx.input.isTouched()){
//			cam.translate(-Gdx.input.getDeltaX(), Gdx.input.getDeltaY());
//		}

        game.cam.update();
        game.gameMap.update(Gdx.graphics.getDeltaTime());
        game.gameMap.render(game.cam, game.batch);

        if (Gdx.input.isButtonJustPressed(Input.Keys.ESCAPE)){
            game.dispose();
            game.setScreen(new MainScreen(game));
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        game.gameMap.saveEntities();

    }
}
