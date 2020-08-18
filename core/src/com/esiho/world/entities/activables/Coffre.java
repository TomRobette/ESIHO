package com.esiho.world.entities.activables;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.esiho.Game;
import com.esiho.ScreenLoader;
import com.esiho.world.entities.Activable;
import com.esiho.world.entities.EntitySnapshot;
import com.esiho.world.entities.EntityType;
import com.esiho.world.item.Arme;
import com.esiho.world.item.ArmeType;
import com.esiho.world.item.Armure;
import com.esiho.world.item.ArmureType;
import com.esiho.world.item.Item;
import com.esiho.world.map.GameMap;
import com.esiho.world.scenarii.Conversation;

import java.util.ArrayList;
import java.util.Arrays;

public class Coffre extends Activable {
    ArrayList<Item> listeItems;

    @Override
    public void onUse(/*SpriteBatch batch*/) {
//        render(batch);
        if (Game.debug) {
            System.out.println("COFFRE");
            if (listeItems.size()!=0){
                for (Item objet:listeItems) {
                    System.out.println(objet.getNom());
                }
            }
        }
        if (Game.gameScreen!=null){
//            listeItems.add(new Arme(ArmeType.BATON));
//            listeItems.add(new Armure(ArmureType.ARMURE_LEGENDAIRE));
//            listeItems.add(new Arme(ArmeType.EPEELEGENDAIRE));
//            listeItems.add(new Arme(ArmeType.ARC));
//            listeItems.add(new Arme(ArmeType.DAGUE));
//            listeItems.add(new Armure(ArmureType.ARMURE_BANALE));
            Game.gameScreen.newItems(listeItems);
        }
        Game.pause = false;
    }

    private void readItemsFromString(String string){
        if (!string.equals("NULL")){
            String items[] = string.trim().split("@@"); //Coupe entre les deux conversations (lues et nonlues)
            ArrayList<Item> listeObjets = new ArrayList<>();
            for (String chaine:items) {
                Item item = Item.getItemFromID(chaine);
                if (item!=null){
                    listeObjets.add(item);
                }
            }
            listeItems = listeObjets;
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        TextureRegion texture;
        if (activated==true){
            super.stateTime += Gdx.graphics.getDeltaTime();
            texture = (TextureRegion) type.animations[0].getKeyFrame(stateTime, loop);
        }else{
            texture = (TextureRegion) type.animations[0].getKeyFrame(0);
        }
        batch.draw(texture, pos.x, pos.y, getWidth(), getHeight());
    }

    @Override
    public void routine() {

    }

    @Override
    public void onCreate(EntitySnapshot snapshot) {
        listeItems = new ArrayList<>();
//        readItemsFromString(snapshot.getString("items", "NULL"));
        listeItems.add(new Arme(ArmeType.ARC));
    }
}
