package com.esiho.world.entities.activables;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.esiho.world.entities.Activable;
import com.esiho.world.entities.EntitySnapshot;
import com.esiho.world.entities.EntityType;
import com.esiho.world.map.GameMap;

public class Coffre extends Activable {

    @Override
    public void onUse(/*SpriteBatch batch*/) {
//        render(batch);
        System.out.println("COFFRE");

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
}
