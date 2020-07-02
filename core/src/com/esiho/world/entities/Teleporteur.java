package com.esiho.world.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.esiho.Game;
import com.esiho.world.map.GameMap;
import com.esiho.world.map.TiledGameMap;

public class Teleporteur extends Entity {
    float stateTime = 0;
    Boolean activation = false;
    @Override
    public void create (EntitySnapshot snapshot, EntityType type, GameMap map){
        super.create(snapshot, type, map);
        super.sprite = type.getSprite(type.spritePosition, 0);
    }

    @Override
    public void update(float deltaTime) {
        Entity player = super.map.getPlayer();
        if (player!=null){
            if (super.map.doesEntityCollideWithEntity(player, player.getX(), player.getY(), this)){
                activation = true;
                Game.gameMap = new TiledGameMap("plaine");

            }
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        if (activation){
            stateTime += Gdx.graphics.getDeltaTime();
            TextureRegion texture = (TextureRegion) type.animations[0].getKeyFrame(stateTime, false);
            batch.draw(texture, pos.x, pos.y, getWidth(), getHeight());
        }else{
            batch.draw(sprite, pos.x, pos.y, getWidth(), getHeight());
        }
    }
}
