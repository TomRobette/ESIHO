package com.esiho.world.map;

import java.util.HashMap;

public enum GameTile {
    BLOCK(1, true, "Block");

    public final static int TILE_SIZE = 16;

    private Integer id;
    private Boolean collidable;
    private String name;

    private GameTile(Integer id, Boolean collidable, String name){
        this.id = id;
        this.collidable = collidable;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public Boolean isCollidable(){
        return collidable;
    }

    public String getName() {
        return name;
    }

    private static HashMap<Integer, GameTile> tileMap;

    static {
        tileMap = new HashMap<>();
        for (GameTile gt:GameTile.values()) {
            GameTile.tileMap.put(gt.getId(), gt);
        }
    }

    public static GameTile getTileById(int id){
        return tileMap.get(id);
    }
}
