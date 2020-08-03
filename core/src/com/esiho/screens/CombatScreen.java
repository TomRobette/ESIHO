package com.esiho.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.esiho.Game;
import com.esiho.ScreenLoader;
import com.esiho.combat.CombatState;
import com.esiho.combat.entities.Combattant;
import com.esiho.combat.moves.MoveType;

import java.util.concurrent.ThreadLocalRandom;

public class CombatScreen implements Screen {
    Game game;
    private CombatState cbtState;
    private SpriteBatch batch;
    protected Stage stage;
    private Viewport viewport;
    private OrthographicCamera camera;
    ProgressBar barAdv, barAli;
    Label nameAdv, nameAli;
    ProgressBar.ProgressBarStyle full, yellow, red, grey;
    Skin skin;
    Table cbtScreen, swapScreen, victoryScreen;
    int pointerV = 0;

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
        skin = new Skin();
        skin.addRegions(new TextureAtlas(Gdx.files.internal("default/skin/uiskin.atlas")));
        skin.load(Gdx.files.internal("default/skin/uiskin.json"));
        createCbtScreen();
        stage.addActor(cbtScreen);
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
        if (cbtState.getFin()==true){
            String text;
            if (cbtState.getVictoire()==1) {
                text = "Victoire !";
            }else{
                text = "Défaite !";
            }
            changeScreen(new TextArea(text, new Skin(Gdx.files.internal("uiskin.json"))));
            for (int a=0; a<160; a++){}
            ScreenLoader.changeScreen(Game.gameScreen);

        }
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

    private void updateBarValue(){
        barAdv.setValue(Math.round((cbtState.entity2.getPv()*100)/cbtState.entity2.getPvMax()));
        barAli.setValue(Math.round((cbtState.entity1.getPv()*100)/cbtState.entity1.getPvMax()));
        barAdv = refreshColor(barAdv);
        barAli = refreshColor(barAli);
    }

    private void enemySelection(){
        int random = ThreadLocalRandom.current().nextInt(0, cbtState.entity2.getMoves().getSize());
        cbtState.memorizeMove(cbtState.entity2.getMoves().get(random), cbtState.entity2, cbtState.entity1);
    }

    private void createCbtScreen(){
        Table rootTable = new Table();

        rootTable.setFillParent(true);

        rootTable.top();

        Table entitiesTable = new Table();
        Table adversTable = new Table();
        nameAdv = new Label(""+cbtState.entity2.getName()+"  lvl:"+cbtState.entity2.getLvl(), skin);
        adversTable.add(nameAdv);
        barAdv = new ProgressBar(0, 100, 1, false, updateBarStyle(Color.LIME));
        barAdv.setValue(Math.round((cbtState.entity2.getPv()*100)/cbtState.entity2.getPvMax()));
        barAdv = refreshColor(barAdv);
        adversTable.add(barAdv);
        adversTable.add(new Image(cbtState.entity2.getTexture()));
        Table allieTable = new Table();
        nameAli = new Label(""+cbtState.entity1.getName()+"  lvl:"+cbtState.entity1.getLvl(), skin);
        allieTable.add(nameAli);
        barAli = new ProgressBar(0, 100, 1, false, updateBarStyle(Color.LIME));
        barAli.setValue(Math.round((cbtState.entity1.getPv()*100)/cbtState.entity1.getPvMax()));
        barAli = refreshColor(barAli);
        allieTable.add(barAli);
        allieTable.add(new Image(cbtState.entity1.getTexture()));
        entitiesTable.add(adversTable);
        entitiesTable.row();
        entitiesTable.add(allieTable);
        rootTable.add(entitiesTable);

        Table btnTable = new Table();
        Table quadTable = new Table();
        for (int a = 0; a<4; a++){
            TextButton btn;
            if (cbtState.entity1.getMoves().get(a)!=null){
                btn = new TextButton(cbtState.entity1.getMoves().get(a).getNom(), skin);
                final int index = a;
                btn.addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        cbtState.memorizeMove(cbtState.entity1.getMoves().get(index), cbtState.entity1, cbtState.entity2);
                        enemySelection();
                        cbtState.tour();
                        updateBarValue();
                        createCbtScreen();
                        changeScreen(cbtScreen);
                    }
                });
            }else{
                btn = new TextButton("  ...  ", skin);
                final int index = a;
                btn.addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {

                    }
                });
            }
            quadTable.add(btn);
            if (a == 1) quadTable.row();
        }
        TextButton swapBtn = new TextButton("Swap", skin);
        swapBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                createSwapScreen();
                changeScreen(swapScreen);
            }
        });
        btnTable.add(quadTable);
        btnTable.row();
        btnTable.add(swapBtn);

        rootTable.row();
        rootTable.add(btnTable);
        cbtScreen = rootTable;
    }

    private void createSwapScreen(){
        Table rootTable = new Table();

        rootTable.setFillParent(true);

        rootTable.top();

        int compteur = 0;

        for (Combattant entity:cbtState.team1.getListeCbtEntities()) {
            Table entityTable = new Table();
            TextButton btn = new TextButton(""+entity.getName()+"  lvl:"+entity.getLvl(), skin);
            final Combattant entite = entity;
            btn.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    cbtState.memorizeMove(MoveType.NULL, cbtState.entity1, cbtState.entity2);
                    cbtState.entity1 = entite;
                    enemySelection();
                    cbtState.tour();
                    updateBarValue();
                    createCbtScreen();
                    changeScreen(cbtScreen);
                }
            });
            entityTable.add(btn);
            ProgressBar bar = new ProgressBar(0, 100, 1, false, updateBarStyle(Color.LIME));
            bar.setValue(Math.round((entity.getPv()*100)/entity.getPvMax()));
            bar = refreshColor(bar);
            entityTable.add(bar);

            if (compteur%2==0) rootTable.row();
            rootTable.add(entityTable);

            compteur++;
        }
        TextButton btnRetour = new TextButton("Retour", skin);
        btnRetour.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                createCbtScreen();
                changeScreen(cbtScreen);
            }
        });
        rootTable.row();
        rootTable.add(btnRetour);
        swapScreen = rootTable;
    }

    private void createVictoryScreen(){
        Table rootTable = new Table();

        rootTable.setFillParent(true);

        rootTable.top();
        Combattant entity = cbtState.team1.getListeCbtEntities().get(pointerV);

        rootTable.add(new Label(""+entity.getName()+"  lvl:"+entity.getLvl(), skin));
//        TextButton btn = new TextButton(, skin);

        victoryScreen = rootTable;
    }

    private void changeScreen(Actor actor){
        stage = new Stage(viewport, batch);
        Gdx.input.setInputProcessor(stage);
        stage.addActor(actor);
    }
}
