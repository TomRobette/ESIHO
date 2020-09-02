package com.esiho.world.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.loaders.resolvers.ExternalFileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.LocalFileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.esiho.world.entities.Entity;
import com.esiho.world.entities.EntityLoader;

public class TiledGameMap extends GameMap {
    private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer tiledMapRenderer;

    public TiledGameMap(String mapName){
        this.mapName=mapName;
        FileHandle mapOfTheDayFile = Gdx.files.internal("maps/"+mapName+".tmx");
        this.tiledMap = new TmxMapLoader(new InternalFileHandleResolver()).load(mapOfTheDayFile.file().getPath());
        try{
            if (tiledMap!=null)
                this.tiledMap = new TmxMapLoader().load("core/assets/maps/"+mapName+".tmx");
        }catch (Exception e){
            this.tiledMap = new TmxMapLoader().load("maps/"+mapName+".tmx");
        }
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
            entity.update(delta);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.S) && Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
            save();
        }

    }

    @Override
    public void save(){
        System.out.println("Sauvegarde...");
        EntityLoader.saveEntities(mapName, entities);
        System.out.println("Sauvegarde réussie !");
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
        for (int row = (int)(y / GameTile.TILE_SIZE); row < Math.ceil((y + height - 16) / GameTile.TILE_SIZE); row++){
            for (int col = (int)(x / GameTile.TILE_SIZE); col < Math.ceil((x + width) / GameTile.TILE_SIZE); col++){
                if (layer.getCell(col, row)!=null){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Boolean doesEntityCollideWithEntity(Entity entity1, float x, float y, Entity entity2) {
        if (!entity1.equals(entity2)){
            if (entity2.getHeight()+entity2.getY()>=y && entity2.getY()<=y+entity1.getHeight() && entity2.getWidth()+entity2.getX()>=x && entity2.getX()<=x+entity1.getWidth() && !entity2.dead){
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean doesEntityCollideWithEntities(Entity entity1, float x, float y) {
        for (Entity entity2:entities){
            if (!entity1.equals(entity2)){
                if (entity2.getHeight()+entity2.getY()>=y && entity2.getY()<=y+entity1.getHeight() && entity2.getWidth()+entity2.getX()>=x && entity2.getX()<=x+entity1.getWidth() && entity2.getType().collidable && !entity2.dead){
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
