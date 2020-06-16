package com.esiho.world.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.esiho.world.map.GameMap;

import java.util.ArrayList;

public class EntityLoader {
    private static Json json = new Json();

    public static ArrayList<Entity> loadEntity(String id, GameMap map, ArrayList<Entity> currentEntities){
        Gdx.files.local("maps/").file().mkdirs();
        FileHandle file = Gdx.files.local("maps/"+id+".entities");
        if (file.exists()){
            EntitySnapshot[] snapshots = json.fromJson(EntitySnapshot[].class, file.readString());
            ArrayList<Entity> entities = new ArrayList<>();
            for (EntitySnapshot snapshot: snapshots){
                entities.add(EntityType.createEntityUsingSnapshot(snapshot, map));
            }
            return entities;
        }else{
            saveEntities(id, currentEntities);
            return currentEntities;
        }
    }

    public static void saveEntities(String id, ArrayList<Entity> entities){
        ArrayList<EntitySnapshot> snapshots = new ArrayList<>();
        for (Entity entity:entities){
            snapshots.add(entity.getSaveSnapshot());
        }
        Gdx.files.local("maps/").file().mkdirs();
        FileHandle file = Gdx.files.local("maps/"+id+".entities");
        file.writeString(json.prettyPrint(snapshots), false);
    }
}
