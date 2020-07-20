package com.esiho.world.entities.activables;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.esiho.Game;
import com.esiho.world.entities.Activable;
import com.esiho.world.entities.Entity;
import com.esiho.world.entities.EntityType;

public class Coffre extends Activable {

    @Override
    public void onUse(/*SpriteBatch batch*/) {
//        render(batch);
        System.out.println("COFFRE");
    }

    @Override
    public void render(SpriteBatch batch) {
        super.stateTime += Gdx.graphics.getDeltaTime();
        TextureRegion texture = (TextureRegion) type.animations[0].getKeyFrame(stateTime, false);
        batch.draw(texture, pos.x, pos.y, getWidth(), getHeight());
    }
}
