package com.esiho.world.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;

public enum EntityType {
    JOUEUR("P0", "joueur");

    private String id;
    private TextureRegion[][] sprites;
    private int height, width;

    public static final int ENTITY_SIZE = 32;

    private EntityType(String id, String spriteName){
        this.id = id;
        this.sprites = TextureRegion.split(new Texture(spriteName+".png"), Pnj.PNJ_SIZE, Pnj.PNJ_SIZE);
        this.height = ENTITY_SIZE;
        this.width = ENTITY_SIZE;
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
}
