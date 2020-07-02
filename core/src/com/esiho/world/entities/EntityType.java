package com.esiho.world.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.esiho.world.map.GameMap;

import java.util.ArrayList;
import java.util.HashMap;

public enum EntityType {
    JOUEUR("P0", Player.class, "Actor1", 0, 32, 32, true),
    TELEPORTEUR("P1", Teleporteur.class, "", 0, 32, 32, true),
    PORTE("P2", Divers.class, "!Door1", 0, 32, 32, false),
    FLAMME("P3", Divers.class, "!Other2", 1, 32, 64, true);

    private String id;
    private Class loaderClass;
    private TextureRegion[][] sprites;
    private int height, width;
    public int spritePosition;
    public Animation[] animations;

    public static final int ENTITY_SIZE = 32;

    private EntityType(String id, Class loaderClass, String spriteName, int spritePosition, int pxlWidth, int pxlHeight, boolean sens){
        this.id = id;
        this.loaderClass = loaderClass;
        this.spritePosition = spritePosition;
        if (!spriteName.isEmpty()){
            this.sprites = TextureRegion.split(new Texture("pnjs/"+spriteName+".png"), pxlWidth, pxlHeight);
            System.out.println("id:"+id+" , "+sprites.length+" ; "+sprites[0].length);
            animations = new Animation[4];
            for (int a = 0; a<4; a++){
                TextureRegion[] assets = new TextureRegion[3];
                for (int b = 0; b<3; b++){
                    if (sens){
                        assets[b] = getSprite(spritePosition, b);
                    }else{
                        assets[b] = getSprite(b, spritePosition);
                    }
                }
                animations[a] = new Animation<TextureRegion>(1f/4f, assets);
            }
        }
        this.height = pxlHeight;
        this.width = pxlWidth;
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
        textureRegion = sprites[sens][etape];
        return textureRegion;
    }

    public String getId() {
        return this.id;
    }

}
