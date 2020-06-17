package com.esiho.world.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.esiho.world.entities.Entity;
import com.esiho.world.entities.EntityLoader;
import com.esiho.world.entities.EntityType;
import com.esiho.world.entities.Player;

public class TiledGameMap extends GameMap {
    private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer tiledMapRenderer;

    public TiledGameMap(){
        this.tiledMap = new TmxMapLoader().load("maps/"+"maison"+".tmx");
        this.tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

    }

    @Override
    public void render(OrthographicCamera cam, SpriteBatch batch) {
        tiledMapRenderer.setView(cam);
        tiledMapRenderer.render();

        batch.setProjectionMatrix(cam.combined);
        batch.begin();
        super.render(cam, batch);
        batch.end();
    }

    @Override
    public void update(float delta) {
        for (Entity entity : entities) {
            entity.update(delta, -9.8f);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.S) && Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
            EntityLoader.saveEntities("maison", entities);
            System.out.println("BRUH");
        }

    }

    @Override
    public void dispose() {
        tiledMap.dispose();
    }

    @Override
    public GameTile getTileByLocation(int layer, float x, float y) {
        return null;
    }

    @Override
    public GameTile getTileByCoordinates(int layer, int col, int row) {
        TiledMapTileLayer.Cell cell = ((TiledMapTileLayer) tiledMap.getLayers().get(layer)).getCell(col, row);
        if (cell!=null){
            TiledMapTile tile= cell.getTile();
            if (tile!=null){
                int id = tile.getId();
                return GameTile.getTileById(id);
            }
        }
        return null;
    }

    @Override
    public Boolean doesRectCollideWithMap(float x, float y, float width, float height) {
        if (x < 0 || y < 0 || x+width > getPixelWidth() || y+height > getPixelHeight()){
            return true;
        }
        TiledMapTileLayer layer = (TiledMapTileLayer) tiledMap.getLayers().get(0);
        for (int row = (int)(y / GameTile.TILE_SIZE); row < Math.ceil((y + height) / GameTile.TILE_SIZE); row++){
            for (int col = (int)(x / GameTile.TILE_SIZE); col < Math.ceil((x + width) / GameTile.TILE_SIZE); col++){
                if (layer.getCell(col, row).getTile().getId()==0){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int getWidth() {
        return ((TiledMapTileLayer) tiledMap.getLayers().get(0)).getWidth();
    }

    @Override
    public int getHeight() {
        return ((TiledMapTileLayer) tiledMap.getLayers().get(0)).getHeight();
    }

    @Override
    public int getLayers() {
        return tiledMap.getLayers().getCount();
    }
}
