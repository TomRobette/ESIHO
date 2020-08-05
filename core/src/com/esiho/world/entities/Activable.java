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
    protected Boolean activated;
    protected Boolean oneUse;
    protected Boolean loop;
    protected long startTime = 0;

    @Override
    public void create(EntitySnapshot snapshot, EntityType type, GameMap map){
        super.create(snapshot, type, map);
        startTime = TimeUtils.millis();
        activated = snapshot.getBoolean("activated",false);
        oneUse = snapshot.getBoolean("oneUse", false);
        loop = snapshot.getBoolean("loop", false);
        onCreate(snapshot);
    }

    @Override
    public void update(float deltaTime){
        if (Gdx.input.isKeyJustPressed(Input.Keys.E)){
            if (activated!=true || oneUse!=true){
                Entity player = super.map.getPlayer();
                if (player!=null){
                    if (Game.gameMap.doesEntityCollideWithEntity(player, player.getX()+2, player.getY()+2, this)){
                        Game.pause = true;
                        activated = true;
                        onUse();
                    }
                }
            }
        }
        routine();
    }

    public abstract void onUse(/*SpriteBatch batch*/);

    @Override
    public abstract void render(SpriteBatch batch);

    public abstract void routine();

    public abstract void onCreate(EntitySnapshot snapshot);
}
