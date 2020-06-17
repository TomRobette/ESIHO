package com.esiho.world.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.esiho.world.entities.Entity;
import com.esiho.world.entities.EntityLoader;

import java.util.ArrayList;

public abstract class GameMap {
    protected ArrayList<Entity> entities;

    public GameMap(){
        entities = new ArrayList<>();
        entities.addAll(EntityLoader.loadEntity("basic", this, entities));
    }

    public void render(OrthographicCamera cam, SpriteBatch batch){

        for (Entity entity:entities) {
            entity.render(batch);
        }
    }

    public void update(float delta){
        for (Entity entity : entities) {
            entity.update(delta, -9.8f);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.S)) {
            EntityLoader.saveEntities("basic", entities);
        }
    }

    public void dispose(){
        EntityLoader.saveEntities("basic", entities);
    }

    /****
     * Retourne un tile en fonction de sa position en pixel sur la map
     * @param layer
     * @param x
     * @param y
     * @return
     */
//    public GameTile getTileByLocation(int layer, float x, float y){
//        return this.getTileByCoordinates(layer, Math.round(x/GameTile.TILE_SIZE), Math.round(y/GameTile.TILE_SIZE));
//    }

    public abstract GameTile getTileByLocation(int layer, float x, float y);

    /****
     * Retourne un tile en fonction de ses coordonnées sur la map
     * @param layer
     * @param col
     * @param row
     * @return
     */
    public abstract GameTile getTileByCoordinates(int layer, int col, int row);

    public abstract Boolean doesRectCollideWithMap(float x, float y, float width, float height);

    public abstract int getWidth();
    public abstract int getHeight();
    public abstract int getLayers();

    public int getPixelWidth(){
        return this.getWidth()*GameTile.TILE_SIZE;
    }

    public int getPixelHeight(){
        return this.getHeight()*GameTile.TILE_SIZE;
    }
}
