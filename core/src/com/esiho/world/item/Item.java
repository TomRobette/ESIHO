package com.esiho.world.item;

import com.badlogic.gdx.graphics.Texture;

public abstract class Item{
    private ItemType type;

    public void create(ItemType type) {
        this.type=type;
    }


    public abstract void useItem();
}
