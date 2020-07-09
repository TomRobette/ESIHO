package com.esiho.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.esiho.Game;
import com.esiho.combat.CombatState;

public class CombatScreen implements Screen {
    Game game;
    private CombatState cbtState;
    private SpriteBatch batch;
    protected Stage stage;
    private Viewport viewport;
    private OrthographicCamera camera;
    ProgressBar barAdv, barAli;
    int bruh = 90;
    ProgressBar.ProgressBarStyle full, yellow, red, grey;

    public CombatScreen(Game game, CombatState cbtState){
        this.game=game;
        this.cbtState=cbtState;
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new FitViewport(Game.WIDTH, Game.HEIGHT, camera);
        viewport.apply();

        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();

        stage = new Stage(viewport, batch);

        full = updateBarStyle(Color.LIME);
        yellow = updateBarStyle(Color.ORANGE);
        red = updateBarStyle(Color.RED);
        grey = updateBarStyle(Color.DARK_GRAY);
    }

    @Override
    public void show() {

        Gdx.input.setInputProcessor(stage);


        Table rootTable = new Table();

        rootTable.setFillParent(true);

        rootTable.top();

        Table entitiesTable = new Table();
        Table adversTable = new Table();
        barAdv = new ProgressBar(0, 100, 1, false, updateBarStyle(Color.GREEN));
        barAdv.setValue(Math.round((cbtState.entity2.getPv()*100)/cbtState.entity2.getPvMax()));
        adversTable.add(barAdv);
        adversTable.add(new Image(cbtState.entity2.getTexture()));
        Table allieTable = new Table();
        barAli = new ProgressBar(0, 100, 1, false, updateBarStyle(Color.GREEN));
        barAli.setValue(Math.round((cbtState.entity1.getPv()*100)/cbtState.entity1.getPvMax()));
        adversTable.add(barAli);
        allieTable.add(new Image(cbtState.entity1.getTexture()));
        entitiesTable.add(adversTable);
        entitiesTable.row();
        entitiesTable.add(allieTable);
        rootTable.add(entitiesTable);

        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));

        Table btnTable = new Table();
        Table quadTable = new Table();
        TextButton firstBtn = new TextButton(cbtState.entity1.getMovesPhy().getMove(0).getNom(), skin);


        firstBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
//                System.out.println(cbtState.entity2.getPv());
//                cbtState.entity2.degatsPVprct(0.30);
//                System.out.println(cbtState.entity2.getPv());
//                barAdv.setValue(Math.round((cbtState.entity2.getPv()*100)/cbtState.entity2.getPvMax()));
                bruh--;
                barAdv.setValue(bruh);
                barAdv = refreshColor(barAdv);
            }
        });
        quadTable.add(firstBtn);
        btnTable.add(quadTable);
        rootTable.row();
        rootTable.add(btnTable);


        stage.addActor(rootTable);
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

    private ProgressBar.ProgressBarStyle updateBarStyle(Color color){
        Skin skinBar = new Skin();
        Pixmap pixmap = new Pixmap(1, 10, Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
        pixmap.fill();
        skinBar.add("white", new Texture(pixmap));

        TextureRegionDrawable textureBar = new TextureRegionDrawable(new TextureRegion(new Texture(pixmap)));
        ProgressBar.ProgressBarStyle barStyle = new ProgressBar.ProgressBarStyle(skinBar.newDrawable("white", Color.DARK_GRAY), textureBar);
        barStyle.knobBefore = barStyle.knob;
        return barStyle;
    }

    private ProgressBar refreshColor(ProgressBar bar){
        System.out.println(bar.getPercent());
        if (bar.getPercent()>=0.5){
            bar.setStyle(full);
        }else if (bar.getPercent()<=0.49 && bar.getPercent()>0.15){
            bar.setStyle(yellow);
        }else if (bar.getPercent()<=0.15 && bar.getPercent()>0){
            bar.setStyle(red);
        }else if (bar.getPercent()==0.0){
            bar.setStyle(grey);
        }
        return bar;
    }
}
