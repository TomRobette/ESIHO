package com.esiho.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
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
import com.esiho.combat.entities.Combattant;
import com.esiho.combat.entities.CombattantType;
import com.esiho.combat.teams.Team;
import com.esiho.combat.teams.TeamType;
import com.esiho.world.item.Arme;
import com.esiho.world.item.ArmeType;

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

        Skin skin = new Skin();
        skin.addRegions(new TextureAtlas(Gdx.files.internal("default/skin/uiskin.atlas")));
        skin.load(Gdx.files.internal("default/skin/uiskin.json"));


        TextButton mainScreenButton = new TextButton("MainScreen", skin);
        TextButton gameScreenButton = new TextButton("GameScreen", skin);
        TextButton combatScreenButton = new TextButton("CombatScreen", skin);
        TextButton inventoryScreenButton = new TextButton("InventoryScreen", skin);
        TextButton lvlupScreenButton = new TextButton("LvlupScreen", skin);
        TextButton exitButton = new TextButton("Quitter", skin);
        Team teamOne = new Team();
        teamOne.create(TeamType.JOUEUR);
        for (int a = 0; a<6; a++ ){
            Combattant joueur = new Combattant();
            joueur.create(CombattantType.JOUEUR);
            teamOne.addPNJ(joueur);
        }
        teamOne.addArgent(100);
        Combattant rat = new Combattant();
        rat.create(CombattantType.RAT);
        teamOne.addPNJ(rat);
        Team teamTwo = new Team();
        teamTwo.create(TeamType.MONSTRES);
        for (int a = 0; a<3; a++) {
            rat = new Combattant();
            rat.create(CombattantType.RAT);
            teamTwo.addPNJ(rat);
        }
        teamTwo.addArgent(10);
        teamTwo.addItem(new Arme(ArmeType.BATON));
        final CombatState combat = new CombatState(teamOne, teamTwo);

        mainScreenButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.changeScreen(new MainScreen(game));
            }
        });

        gameScreenButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.changeScreen(new GameScreen(game));
            }
        });
        combatScreenButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                final CombatState cbt = combat;
                game.changeScreen(new CombatScreen(game, cbt));
            }
        });
        inventoryScreenButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.changeScreen(new GameScreen(game));
            }
        });
        lvlupScreenButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.changeScreen(new GameScreen(game));
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
