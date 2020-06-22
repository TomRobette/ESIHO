package com.esiho.world.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.esiho.Game;
import com.esiho.world.map.GameMap;

public class Player extends Entity {
    Animation animation;
    int[] tab;

    @Override
    public void create (EntitySnapshot snapshot, EntityType type, GameMap map){
        super.create(snapshot, type, map);
        super.sprite = new TextureRegion(type.getSprite(0, 0));
    }

    @Override
    public void update(float deltaTime) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)){
            tab = super.moveX(-32);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)){
            tab = super.moveX(32);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)){
            tab = super.moveY(32);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)){
            tab = super.moveY(-32);
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(super.sprite, pos.x, pos.y, getWidth(), getHeight());
        if (tab!=null){
            super.movingAnim(tab[0], tab[1]);
//            Launcher.cam.translate(tab[0]*32+pos.x, tab[1]*32+pos.y, 0);
            tab=null;
        }else
        Game.cam.position.set(pos.x, pos.y, 0);
    }
}
