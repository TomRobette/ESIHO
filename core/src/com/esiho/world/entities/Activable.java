package com.esiho.world.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.TimeUtils;
import com.esiho.Game;
import com.esiho.world.map.GameMap;

public abstract class Activable extends Entity {
    protected float stateTime = 0;
    protected Boolean activated = false;
    protected Boolean loop = false;
    protected long startTime = 0;

    @Override
    public void update(float deltaTime){
        if (Gdx.input.isKeyJustPressed(Input.Keys.E) && activated!=true){
            Entity player = super.map.getPlayer();
            if (player!=null){
                if (Game.gameMap.doesEntityCollideWithEntity(player, player.getX()+4, player.getY()+4, this)){
                    onUse();
                    activated = true;
                }
            }
        }
    }

    public abstract void onUse(/*SpriteBatch batch*/);

    @Override
    public abstract void render(SpriteBatch batch);
}
