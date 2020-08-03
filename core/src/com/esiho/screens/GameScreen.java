package com.esiho.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.esiho.Game;
import com.esiho.ScreenLoader;
import com.esiho.combat.entities.Combattant;
import com.esiho.world.item.Item;

import java.util.ArrayList;

public class GameScreen implements Screen {
    Game game;
    private SpriteBatch batch;
    protected Stage stage;
    private Viewport viewport;
    private OrthographicCamera camera;
    Skin skin;
    Table newItemUI, conversationUI;
    Boolean newItemUIState = false;
    Boolean conversationUIState = false;
    Label labelConv;
    int pointerConv = 0;

    public GameScreen(Game game){
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
        skin = new Skin();
        skin.addRegions(new TextureAtlas(Gdx.files.internal("default/skin/uiskin.atlas")));
        skin.load(Gdx.files.internal("default/skin/uiskin.json"));
//        createNewItemScreen();
//        stage.addActor(newItemScreen);
    }

    public void newItems(ArrayList<Item> objets){
        if (!newItemUIState){
            newItemUIState = true;
            createNewItemScreen(objets);
            changeScreen(newItemUI);
//        }else{
//            newItemUI.clear();
        }
    }

    private void createNewItemScreen(ArrayList<Item> objets) {
        Table rootTable = new Table();

        rootTable.setFillParent(true);

        rootTable.top();

        String listeNomsItems = "";
        int compteur = 0;
        for (Item objet:objets) {

            if (compteur==0){
                listeNomsItems = ""+objet.getNom();
            }else if (compteur==objets.size()-1){
                listeNomsItems = listeNomsItems+" et "+objet.getNom();
            }else{
                listeNomsItems = listeNomsItems + ", " + objet.getNom();
            }
            compteur++;
            if (objet.getSprite()!=null){
                Image image = new Image(objet.getSprite());
//            image.setSize(0.17f*Gdx.graphics.getWidth(), 0.17f*Gdx.graphics.getWidth());
//                image.setWidth(0.5f*Gdx.graphics.getWidth());
//                image.setHeight(0.5f*Gdx.graphics.getWidth());
                image.setScale(2, 2);
                rootTable.add(image).pad(10);
                rootTable.row();
            }
        }
        if (listeNomsItems.length()>=30){
            listeNomsItems = listeNomsItems.substring(0, 29);
            listeNomsItems = listeNomsItems+"...";
        }
        rootTable.row();
        rootTable.add(new Label("Vous avez gagné "+listeNomsItems, skin));
//        TextButton btn = new TextButton(, skin);

        newItemUI = rootTable;
        newItemUI.align(Align.center);
    }

    private void createConversationScreen(ArrayList<String> listePhrases){
        Table rootTable = new Table();

        rootTable.setFillParent(true);

        rootTable.top();

        labelConv = new Label(listePhrases.get(0), skin);

        rootTable.row();
        rootTable.add();
        TextButton btn = new TextButton("Suivant", skin);
        btn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                pointerConv++;
                updateLabelConv(listePhrases);
            }
        });
        rootTable.row();
        rootTable.add(btn).align(Align.bottomRight);

        conversationUI = rootTable;
        conversationUI.align(Align.bottom);
    }

    private void updateLabelConv(ArrayList<String> listePhrases){
        if (pointerConv>listePhrases.size()+1){
            //FIN CONVERSATION
        }else{
            labelConv = new Label(listePhrases.get(pointerConv), skin);
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.cam.update();
        game.gameMap.update(Gdx.graphics.getDeltaTime());
        game.gameMap.render(game.cam, game.batch);

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            if (newItemUIState){
                newItemUIState=false;
                System.out.println("AH");
                newItemUI.clear();
            }
        }

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
        game.gameMap.saveEntities();
    }

    private void changeScreen(Actor actor){
        stage = new Stage(viewport, batch);
        Gdx.input.setInputProcessor(stage);
        stage.addActor(actor);
    }
}
