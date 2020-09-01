package com.esiho.world.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Divers extends Entity {
    float stateTime = 0;
    public boolean hide = false;

    @Override
    public void update(float deltaTime) {

    }

    @Override
    public void render(SpriteBatch batch) {
        stateTime += Gdx.graphics.getDeltaTime();
        TextureRegion texture = (TextureRegion) type.animations[0].getKeyFrame(stateTime, true);
        batch.draw(texture, pos.x, pos.y, getWidth(), getHeight());
    }

    @Override
    public void onDeath() {

    }
}
