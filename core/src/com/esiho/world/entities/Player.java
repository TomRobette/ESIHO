package com.esiho.world.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.esiho.Launcher;
import com.esiho.world.map.GameMap;

public class Player extends Entity {
    @Override
    public void create (EntitySnapshot snapshot, EntityType type, GameMap map){
        super.create(snapshot, type, map);
        super.sprite = new TextureRegion(type.getSprite(0, 0));
    }

    @Override
    public void update(float deltaTime, float gravity) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)){
            super.moveX(-32);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)){
            super.moveX(32);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)){
            super.moveY(32);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)){
            super.moveY(-32);
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(super.sprite, pos.x, pos.y, getWidth(), getHeight());
        Launcher.cam.position.set(pos.x, pos.y, 0);
    }
}
