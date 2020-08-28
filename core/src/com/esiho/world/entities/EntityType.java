package com.esiho.world.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.esiho.world.entities.activables.Coffre;
import com.esiho.world.entities.activables.Npc;
import com.esiho.world.entities.activables.Npcs.Chat;
import com.esiho.world.entities.activables.Npcs.Mauricette;
import com.esiho.world.entities.activables.Npcs.Monstre;
import com.esiho.world.entities.activables.Npcs.Roger;
import com.esiho.world.entities.activables.Teleporteur;
import com.esiho.world.map.GameMap;

import java.util.HashMap;

public enum EntityType {
    JOUEUR("P0", Player.class, "Actor1", 0, 32, 32, true, 1f/4f, true),
    TELEPORTEUR("P1", Teleporteur.class, "!Door1", 4, 32, 32, false, 1f/4f, false),
    COFFRE("P2", Coffre.class, "!Chest", 1, 32, 32, false, 1f/4f, true),
    FLAMME("P3", Divers.class, "!Other2", 6, 32, 64, true, 1f/4f, false),
    CRISTAL("P4", Divers.class, "!Crystal",1, 32, 64, false, 1f/8f, true),
    MAURICETTE("P5", Mauricette.class, "Actor1", 3, 32, 32, true, 1f/4f, true),
    ROGER("P6", Roger.class, "Actor1", 2, 32, 32, true, 1f/4f, true),
    PAPILLON("P7", Divers.class, "Animal", 7, 32, 32, true, 1f/8f, false),
    CHAT("P8", Chat.class, "Animal", 1, 32, 32, true, 1f/4f, true),
    SLIME("P9", Monstre.class, "Monster2", 2, 32, 32, true, 1f/4f, true);

    private String id;
    private Class loaderClass;
    private TextureRegion[][] sprites;
    private int height, width;
    public int spritePosition;
    public Animation[] animations;
    public Boolean collidable;

    private EntityType(String id, Class loaderClass, String spriteName, int spritePosition, int pxlWidth, int pxlHeight, boolean sens, float rythme, Boolean collidable){
        this.id = id;
        this.loaderClass = loaderClass;
        this.spritePosition = spritePosition;
        if (!spriteName.isEmpty()){
            this.sprites = TextureRegion.split(new Texture("pnjs/"+spriteName+".png"), pxlWidth, pxlHeight);
            animations = new Animation[4];
            for (int a = 0; a<4; a++){
                TextureRegion[] assets;
                int indexY = 0;
                int indexZ = 0;
                if (spritePosition>3 && spritePosition<8){
                    indexY = -12;
                    indexZ = 4;
                }
                if (sens){
                    assets = new TextureRegion[3];
                    for (int b = 0; b<3; b++){
                        assets[b] = sprites[indexZ+a][3*spritePosition+indexY+b];
                    }

                }else{
                    assets = new TextureRegion[4];
                    for (int b = 0; b<4; b++){
//                        assets[b] = getSprite(b, spritePosition);
                        assets[b] = sprites[indexZ+b][3*spritePosition+indexY+a];
                    }
                }
                animations[a] = new Animation<TextureRegion>(rythme, assets);
            }
        }
        this.height = pxlHeight;
        this.width = pxlWidth;
        this.collidable = collidable;
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
