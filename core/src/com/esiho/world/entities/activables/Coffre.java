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

import java.util.ArrayList;

public class Coffre extends Activable {

    @Override
    public void onUse(/*SpriteBatch batch*/) {
//        render(batch);
        if (Game.debug) {
            System.out.println("COFFRE");
        }
        if (Game.gameScreen!=null){
            ArrayList<Item> listeItems = new ArrayList<>();
            for (int a = 0; a<5; a++){
                listeItems.add(new Arme(ArmeType.BATON));
                listeItems.add( new Armure(ArmureType.ARMURE_LEGENDAIRE));

            }
            Game.gameScreen.newItems(listeItems);
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

    }
}
