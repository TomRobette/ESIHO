package com.esiho.world.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.esiho.world.map.GameMap;

public abstract class Entity {
    protected Vector2 pos;
    protected EntityType type;
    protected Boolean haut;
    protected Boolean bas;
    protected Boolean gauche;
    protected Boolean droite;
    protected TextureRegion sprite;
    protected GameMap map;

    private int pointerX = 0;
    private int pointerY = 0;

    public void create(EntitySnapshot snapshot, EntityType type, GameMap map){
        this.pos = new Vector2(snapshot.x, snapshot.y);
        this.type = type;
        this.haut=false;
        this.bas=true;
        this.gauche=false;
        this.droite=false;
        this.map=map;
    }

    protected void moveX(int amount){
        float newX = pos.x + amount;
        if (!map.doesRectCollideWithMap(newX, pos.y, getWidth(), getHeight())){
            if (amount > 0){
                if (!droite){
                    this.haut=false;
                    this.bas=false;
                    this.droite=true;
                    this.gauche=false;
                    pointerY=2;
                    pointerX=0;
                }else{
                    if (pointerX<2){
                        pointerX++;
                    }else{
                        pointerX=0;
                    }
                }

            }else{
                if (!gauche){
                    this.haut=false;
                    this.bas=false;
                    this.droite=false;
                    this.gauche=true;
                    pointerY=1;
                    pointerX=0;
                }else{
                    if (pointerX<2){
                        pointerX++;
                    }else{
                        pointerX=0;
                    }
                }
            }
            this.sprite = type.getSprite(4*type.spritePosition+pointerY, 3*type.spritePosition+pointerX);
            this.pos.x = newX;
        }
    }

    protected void moveY(int amount){
        float newY = pos.y + amount;
        if (!map.doesRectCollideWithMap(pos.x, newY, getWidth(), getHeight())){
            if (amount > 0){
                if (!haut){
                    this.haut=true;
                    this.bas=false;
                    this.droite=false;
                    this.gauche=false;
                    pointerY=3;
                    pointerX=0;
                }else{
                    if (pointerX<2){
                        pointerX++;
                    }else{
                        pointerX=0;
                    }
                }
            }else{
                if (!bas){
                    this.haut=false;
                    this.bas=true;
                    this.droite=false;
                    this.gauche=false;
                    pointerY=0;
                    pointerX=0;
                }else{
                    if (pointerX<2){
                        pointerX++;
                    }else{
                        pointerX=0;
                    }
                }
            }
            this.sprite = type.getSprite(4*type.spritePosition+pointerY, 3*type.spritePosition+pointerX);
            this.pos.y = newY;
        }
    }

    public EntitySnapshot getSaveSnapshot(){
        return new EntitySnapshot(type.getId(), pos.x, pos.y);
    }

    public abstract void update(float deltaTime, float gravity);

    public abstract void render(SpriteBatch batch);

    public Vector2 getPos() {
        return pos;
    }

    public float getX(){
        return pos.x;
    }

    public float getY() {
        return pos.y;
    }

    public EntityType getType() {
        return type;
    }

    public float getWidth(){
        return type.getWidth();
    }

    public float getHeight(){
        return type.getHeight();
    }

}
