package com.esiho.world.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.esiho.world.map.GameMap;

public class Teleporteur extends Entity {
    @Override
    public void create (EntitySnapshot snapshot, EntityType type, GameMap map){
        super.create(snapshot, type, map);
        super.sprite = null;
    }

    @Override
    public void update(float deltaTime) {

    }

    @Override
    public void render(SpriteBatch batch) {

    }
}
