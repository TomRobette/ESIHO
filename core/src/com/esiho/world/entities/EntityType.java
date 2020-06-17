package com.esiho.world.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.esiho.world.map.GameMap;

import java.util.ArrayList;
import java.util.HashMap;

public enum EntityType {
    JOUEUR("P0", Player.class, "Actor1", 0);

    private String id;
    private Class loaderClass;
    private TextureRegion[][] sprites;
    private int height, width;

    public static final int ENTITY_SIZE = 32;

    private EntityType(String id, Class loaderClass, String spriteName, int spritePosition){
        this.id = id;
        this.loaderClass = loaderClass;
        TextureRegion[][] allsprites = TextureRegion.split(new Texture("pnjs/"+spriteName+".png"), ENTITY_SIZE*3, ENTITY_SIZE*4);
        ArrayList<TextureRegion> listeSprites = new ArrayList<>();
        for (TextureRegion[] row:allsprites) {
            for (TextureRegion col:row) {
                listeSprites.add(col);
            }
        }
        this.sprites = TextureRegion.split(listeSprites.get(spritePosition).getTexture(), ENTITY_SIZE, ENTITY_SIZE);
        int b = 0;
        int c = 0;
//        for (int a = 0; a<12; a++){
//            if (a%3==0){
//                b++;
//            }
//            if (b%3==0){
//                c++;
//                b=0;
//            }
//            this.sprites[c][b] = allsprites[c][3*spritePosition+b];
//        }
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

}
