package com.esiho.world.item;

import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;

public enum ItemType {


    private int height, width;

    public static final int ITEM_SIZE = 16;



    private static HashMap<String, ItemType> itemTypes;

    static {
        itemTypes = new HashMap<String, ItemType>();
        for (ItemType itemType : ItemType.values()){
            itemTypes.put(itemType.id, itemType);
        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Texture getSprite(){
        Texture texture = sprite;
        return texture;
    }

    public String getId() {
        return this.id;
    }
}
