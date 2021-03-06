package com.esiho.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.esiho.Game;
import com.esiho.combat.CombatState;
import com.esiho.combat.combattants.Combattant;
import com.esiho.combat.teams.TeamType;
import com.esiho.world.scenario.Conversation;
import com.esiho.world.item.Item;
import com.esiho.world.scenario.Quest;
import com.esiho.world.scenario.QuestsStatus;

import java.util.ArrayList;

public class GameScreen implements Screen {
    Game game;
    private SpriteBatch batch;
    protected Stage stage;
    private Viewport viewport;
    private OrthographicCamera camera;
    Skin skin;
    Label labelConv;
    Table rootElement;
    Table descriptionTable;
    int pointerConv = 0;
    Conversation conversationTempo;
    Quest questTempo;
    Dialog newItemDialog, conversationDialog, inventaireDialog, finCbtDialog, journalDialog;

    int pointerFinCbt;
    int pointerLvlUp;

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
        if (!Game.newItemActif){
            Game.newItemActif = true;
            createNewItemScreen(objets);
//            changeScreen(newItemUI);
//        }else{
//            newItemUI.clear();
        }
    }

    public void newConversation(Conversation conversation){
        this.conversationTempo = conversation;
        if (!Game.dialogueActif){
            Game.dialogueActif = true;
            createConversationScreen();
//            changeScreen(conversationUI);
        }
    }

    private void createNewItemScreen(ArrayList<Item> objets) {
        newItemDialog = new Dialog("Vous avez trouvé :", skin);
        Table rootTable = new Table();
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

        newItemDialog.add(rootTable);
//        newItemUI = rootTable;
//        newItemUI.align(Align.center);
        newItemDialog.show(stage);
//        stage.addActor(dia);
    }

    private void createConversationScreen(){
        conversationDialog = new Dialog("Discussion", skin);
        Table rootTable = new Table();
        rootTable.top();

        labelConv = new Label(conversationTempo.getPhrase(0), skin);

        rootTable.row();
        rootTable.add(labelConv);
        TextButton btn = new TextButton("Suivant", skin);
        btn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                pointerConv++;
                updateLabelConv();
            }
        });
        rootTable.row();
        rootTable.add(btn).align(Align.bottomRight);
        rootTable.align(Align.bottom);
        conversationDialog.add(rootTable);
        conversationDialog.show(stage);
    }

    private void createInventoryScreen(){
        ArrayList<Item> inventaire = TeamType.JOUEUR.inventaire;
        ArrayList<Combattant> listeCbts = TeamType.JOUEUR.listeCbtEntities;
        inventaireDialog = new Dialog("Inventaire", skin);
        inventaireDialog.setResizable(true);
        Table rootTable = new Table();
        Table itemScrollTable = new Table();
        itemScrollTable.align(Align.topLeft);
        ScrollPane itemScrollPane = new ScrollPane(itemScrollTable, skin);
        for (Item item:inventaire){
            Table line = new Table();
            if (item.getSprite()!=null){
                Image image = new Image(item.getSprite());
                image.setScale(1.5f, 1.5f);
                line.add(image).pad(10);
                line.right();
            }
            line.add(new Label(""+item.getNom(), skin));
            itemScrollTable.add(line);
            itemScrollTable.row();
        }
        rootTable.add(new Label(""+TeamType.JOUEUR.argent+" Or", skin));
        rootTable.add(itemScrollPane).align(Align.topLeft);
        Table cbtsScrollTable = new Table();
        ScrollPane cbtsScrollPane = new ScrollPane(cbtsScrollTable, skin);
        for (Combattant cbt:listeCbts){
            Table line = new Table();
            if (cbt.getTexture()!=null){
                Image image = new Image(cbt.getTexture());
                image.setSize(32, 32);
                line.add(image);
                line.right();
            }
            line.add(new Label(""+cbt.getName()+" lvl: "+cbt.getLvl(), skin));
            cbtsScrollTable.add(line);
            cbtsScrollTable.row();
        }
        rootTable.add(cbtsScrollPane).align(Align.left);
        inventaireDialog.add(rootTable).align(Align.topLeft);
    }

    private void createFinCbtScreen(CombatState combatState){
        Game.finCbt = false;
        finCbtDialog = new Dialog(""+combatState.messageFin, skin);
        finCbtDialog.setResizable(true);
        Table rootTable = new Table();
//
//        rootTable.setFillParent(true);
//        rootTable.top();
        rootElement = new Table();
        rootElement.top();

        rootElement.add(new Label("Vous avez gagné :", skin)).align(Align.center);
        rootElement.row();

        for (Item item:combatState.itemsObtenus) {
            Table line = new Table();
            if (item.getSprite()!=null){
                Image image = new Image(item.getSprite());
                image.setScale(1.5f, 1.5f);
                line.add(image).pad(10);
                line.right();
            }
            line.add(new Label(""+item.getNom(), skin));
            rootElement.add(line).align(Align.center);
            rootElement.row();
        }

        rootTable.add(rootElement);
        TextButton btn = new TextButton("Suivant", skin);
        final CombatState cbtState = combatState;
        btn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                updateRootFinCbt(cbtState);
            }
        });
        rootTable.add(btn).align(Align.bottomRight);

        finCbtDialog.add(rootTable);
        finCbtDialog.show(stage);
    }

    private void createJournalScreen(){
        QuestsStatus.refreshQuestsStatus();
        journalDialog = new Dialog("Journal", skin);
        journalDialog.setResizable(true);
        Table rootTable = new Table();
        Table questScrollTable = new Table();
        questScrollTable.align(Align.topLeft);
        ScrollPane questScrollPane = new ScrollPane(questScrollTable, skin);
        int compteur = 0;
        for (Quest quest:QuestsStatus.questsList){
            TextButton btn = new TextButton(""+quest.getNom(), skin);
            final int compteurFinal = compteur;
            btn.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    questTempo = QuestsStatus.questsList.get(compteurFinal);
                    updateDescriptionTable();
                }
            });
            questScrollTable.add(btn);
            questScrollTable.row();
            compteur++;
        }
        rootTable.add(questScrollPane).align(Align.topLeft);

        descriptionTable = new Table();

        if (questTempo!=null){
            if (QuestsStatus.questsList.get(0)!=null){
                questTempo=QuestsStatus.questsList.get(0);
            }
        }
        rootTable.add(descriptionTable);
        journalDialog.add(rootTable).align(Align.topLeft);
    }

    private void updateDescriptionTable(){
        descriptionTable.reset();
        descriptionTable.top();
        if (questTempo!=null){
            descriptionTable.add(new Label(""+questTempo.getNom(), skin)).align(Align.center);
            descriptionTable.row();
            if (questTempo.status){
                descriptionTable.add(new Label("Terminé", skin));
            }else {
                descriptionTable.add(new Label("En cours", skin));
            }
            descriptionTable.row();
            descriptionTable.add(new Label(""+questTempo.getDescription(), skin)).align(Align.center);
            descriptionTable.row();
            descriptionTable.add(new Label("Récompense : "+questTempo.getSommeVictoire()+" Or", skin));
            if (questTempo.getCadeauxVictoire()!=null){
                for (Item item:questTempo.getCadeauxVictoire()) {
                    descriptionTable.row();
                    Table line = new Table();
                    if (item.getSprite()!=null){
                        Image image = new Image(item.getSprite());
                        image.setScale(1.5f, 1.5f);
                        line.add(image).pad(10);
                        line.right();
                    }
                    line.add(new Label(""+item.getNom(), skin));
                    descriptionTable.add(line);
                }
            }
        }
    }

    private void updateRootFinCbt(CombatState combatState){
        rootElement.reset();
        rootElement.top();
        switch (pointerFinCbt){
            case 0 :
                pointerFinCbt++;//Étape Or
                rootElement.add(new Label("Vous avez gagné :", skin)).align(Align.center);
                rootElement.row();
                rootElement.add(new Label(""+combatState.orObtenu+" pièces d'or", skin)).align(Align.center);
                break;
            case 1 :
                //Étape XP
                ArrayList<Combattant> listeCbts = combatState.team1.getListeCbtEntities();
                if (pointerLvlUp < listeCbts.size()){
                    rootElement.add(new Label(""+listeCbts.get(pointerLvlUp).getName()+" a gagné "+combatState.listeXP.get(pointerLvlUp)+" points d'exp", skin)).align(Align.top);
                    pointerLvlUp++;
                }else{
                    pointerFinCbt=0;
                    pointerLvlUp=0;
                    Game.finCbt = false;
                    Game.finCbtState = null;
                    finCbtDialog.hide();
                }
        }
        if (Game.debug) System.out.println(pointerFinCbt+" "+pointerLvlUp);
    }

    private void updateLabelConv(){
        if (Game.debug){
            System.out.println("PointerConv : "+pointerConv);
            System.out.println("ConversationTempo : "+conversationTempo.getPhrase(pointerConv));
        }
        if (pointerConv==conversationTempo.getConversation().size()){
            Game.dialogueActif = false;
            conversationTempo.isRead();
            conversationDialog.hide();
            pointerConv=0;
            Game.pause = false;
        }else{
            labelConv.setText(conversationTempo.getPhrase(pointerConv));
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

        if (Game.finCbt && Game.finCbtState!=null){
            createFinCbtScreen(Game.finCbtState);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            if (Game.newItemActif){
                Game.newItemActif=false;
                newItemDialog.hide();
            }else if (Game.dialogueActif){
                Game.dialogueActif = false;
                conversationDialog.hide();
            }else if (Game.inventaireActif){
                Game.inventaireActif = false;
                inventaireDialog.hide();
            }else if (Game.finCbt){
                Game.finCbt = false;
                Game.finCbtState = null;
                finCbtDialog.hide();
            }else if (Game.journalActif){
                Game.journalActif = false;
                journalDialog.hide();
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.I)){
            if (!Game.inventaireActif){
                Game.inventaireActif=true;
                createInventoryScreen();
                inventaireDialog.show(stage);
            }else{
                Game.inventaireActif = false;
                inventaireDialog.hide();
            }
            if (Game.debug) System.out.println(TeamType.JOUEUR.inventaire.size());
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.J)){
            if (!Game.journalActif){
                Game.journalActif=true;
                createJournalScreen();
                journalDialog.show(stage);
            }else{
                Game.journalActif = false;
                journalDialog.hide();
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
