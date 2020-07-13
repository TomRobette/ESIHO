package com.esiho.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.esiho.Game;
import com.esiho.combat.CombatState;
import com.esiho.combat.entities.CombatEntity;
import com.esiho.combat.entities.CombatEntityType;
import com.esiho.combat.teams.Team;
import com.esiho.combat.teams.TeamType;
import com.esiho.world.item.Arme;
import com.esiho.world.item.ArmeType;
import com.esiho.world.item.Item;

public class ScreensScreen implements Screen {
    Game game;
    private SpriteBatch batch;
    protected Stage stage;
    private Viewport viewport;
    private OrthographicCamera camera;

    public ScreensScreen(Game game){
        this.game=game;

        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new FitViewport(Game.WIDTH, Game.HEIGHT, camera);
        viewport.apply();

        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();

        stage = new Stage(viewport, batch);
    }

    @Override
    public void show() {

        Gdx.input.setInputProcessor(stage);


        Table mainTable = new Table();

        mainTable.setFillParent(true);

        mainTable.top();

        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));


        TextButton mainScreenButton = new TextButton("MainScreen", skin);
        TextButton gameScreenButton = new TextButton("GameScreen", skin);
        TextButton combatScreenButton = new TextButton("CombatScreen", skin);
        TextButton inventoryScreenButton = new TextButton("InventoryScreen", skin);
        TextButton lvlupScreenButton = new TextButton("LvlupScreen", skin);
        TextButton exitButton = new TextButton("Quitter", skin);
        Team teamOne = new Team();
        teamOne.create(TeamType.JOUEUR);
        CombatEntity joueur = new CombatEntity();
        joueur.create(CombatEntityType.JOUEUR);
        teamOne.addPNJ(joueur);
        teamOne.addArgent(100);
        CombatEntity rat = new CombatEntity();
        rat.create(CombatEntityType.RAT);
        Team teamTwo = new Team();
        teamTwo.create(TeamType.MONSTRES);
        teamTwo.addPNJ(rat);
        teamTwo.addArgent(10);
        teamTwo.addItem(new Arme(ArmeType.BATON));
        final CombatState combat = new CombatState(teamOne, teamTwo);

        mainScreenButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainScreen(game));
            }
        });

        gameScreenButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game));
            }
        });
        combatScreenButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                final CombatState cbt = combat;
                game.setScreen(new CombatScreen(game, cbt));
            }
        });
        inventoryScreenButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game));
            }
        });
        lvlupScreenButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game));
            }
        });
        exitButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });


        mainTable.add(mainScreenButton);
        mainTable.row();
        mainTable.add(gameScreenButton);
        mainTable.row();
        mainTable.add(combatScreenButton);
        mainTable.row();
        mainTable.add(inventoryScreenButton);
        mainTable.row();
        mainTable.add(lvlupScreenButton);
        mainTable.row();
        mainTable.add(exitButton);


        stage.addActor(mainTable);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

//		if (Gdx.input.isTouched()){
//			cam.translate(-Gdx.input.getDeltaX(), Gdx.input.getDeltaY());
//		}


        game.batch.begin();

        stage.act();
        stage.draw();

        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();
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
    }
}
