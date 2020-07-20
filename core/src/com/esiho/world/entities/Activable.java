package com.esiho.world.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.esiho.Game;

public abstract class Activable extends Entity {
    protected float stateTime = 0;
    protected Boolean cycle = false;
    protected Boolean activated = false;

    @Override
    public void update(float deltaTime){
        if (Gdx.input.isKeyJustPressed(Input.Keys.E)){
            Entity player = super.map.getPlayer();
            System.out.println("1");
            if (player!=null){
                System.out.println("2");
                if (Game.gameMap.doesEntityCollideWithEntity(player, player.getX()+4, player.getY()+4, this)){
                    onUse();
                    System.out.println("AH");
                }
            }
        }
    }

    public abstract void onUse(/*SpriteBatch batch*/);

    @Override
    public abstract void render(SpriteBatch batch);
}
