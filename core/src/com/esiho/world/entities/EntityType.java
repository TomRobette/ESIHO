package com.esiho.world.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.esiho.world.map.GameMap;

import java.util.HashMap;

public enum EntityType {
    JOUEUR("P0", Player.class, "joueur");

    private String id;
    private Class loaderClass;
    private TextureRegion[][] sprites;
    private int height, width;

    public static final int ENTITY_SIZE = 32;

    private EntityType(String id, Class loaderClass, String spriteName){
        this.id = id;
        this.loaderClass = loaderClass;
        this.sprites = TextureRegion.split(new Texture(spriteName+".png"), ENTITY_SIZE, ENTITY_SIZE);
        this.height = ENTITY_SIZE;
        this.width = ENTITY_SIZE;
    }

    public static Entity createEntityUsingSnapshot(EntitySnapshot entitySnapshot, GameMap map){
        EntityType type = entityTypes.get(entitySnapshot.type);
        try{
            Entity entity = (Entity) ClassReflection.newInstance(type.loaderClass);
            entity.create(entitySnapshot, type, map);
            return entity;
        }catch (Exception e){
            Gdx.app.error("EntityLoader", "N'a pas pu charger le type"+type.id+" depuis la snapshot");
            return null;
        }
    }


    private static HashMap<String, EntityType> entityTypes;

    static {
        entityTypes = new HashMap<String, EntityType>();
        for (EntityType entityType : EntityType.values()){
            entityTypes.put(entityType.id, entityType);
        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public TextureRegion getSprite(int sens, int etape){
        TextureRegion textureRegion = new TextureRegion();
        if (sens<5 && sens>=0 && etape<4 && etape>=0){
            textureRegion = sprites[0][etape+3*sens];
        }else{
            textureRegion = sprites[0][0];
        }
        return textureRegion;
    }

    public String getId() {
        return this.id;
    }

//    @Override
//    public void render(OrthographicCamera cam, SpriteBatch batch) {
//        batch.setProjectionMatrix(cam.combined);
//        batch.begin();
//
//        for (int layer = 0; layer < getLayers(); layer++){
//            for (int row = 0; row < getHeight(); row++){
//                for (int col = 0; col < getWidth(); col++){
//                    GameTile tile = getTileByCoordinates(layer, col, row);
//                    if (tile!=null){
//                        batch.draw(tiles[0][tile.getId() - 1], col * GameTile.TILE_SIZE, row * GameTile.TILE_SIZE );
//                    }
//                }
//            }
//        }
//        super.render(cam, batch);
//        batch.end();
//    }
}
