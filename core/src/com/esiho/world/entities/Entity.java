package com.esiho.world.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.esiho.Game;
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
    public Boolean dead;

    protected int pas = 0;
    protected int sens = 0;

    public void create(EntitySnapshot snapshot, EntityType type, GameMap map){
        this.pos = new Vector2(snapshot.x, snapshot.y);
        this.type = type;
        this.haut=false;
        this.bas=true;
        this.gauche=false;
        this.droite=false;
        this.map=map;
        dead = false;
//        sprite = type.getSprite(0, 0);
    }

    protected int[] moveX(int amount){
        float newX = pos.x + amount;
        if (!map.doesRectCollideWithMap(newX, pos.y, getWidth(), getHeight()) && !map.doesEntityCollideWithEntities(this, newX, pos.y) && !Game.pause || Game.debug){
            if (amount > 0){
                if (!droite){
                    this.haut=false;
                    this.bas=false;
                    this.droite=true;
                    this.gauche=false;
                    sens = 2;
                    pas = 0;
                }
//                movingAnim(1, 0);
                int[] tab = new int[2];
                tab[0]=1;
                tab[1]=0;
                return tab;
            }else{
                if (!gauche){
                    this.haut=false;
                    this.bas=false;
                    this.droite=false;
                    this.gauche=true;
                    sens = 1;
                    pas = 0;
                }
//                movingAnim(-1, 0);
                int[] tab = new int[2];
                tab[0]=-1;
                tab[1]=0;
                return tab;
            }
        }
        return null;
    }

    protected int[] moveY(int amount){
        float newY = pos.y + amount;
        if (!map.doesRectCollideWithMap(pos.x, newY, getWidth(), getHeight()) && !map.doesEntityCollideWithEntities(this, pos.x, newY) && !Game.pause || Game.debug){
            if (amount > 0){
                if (!haut){
                    this.haut=true;
                    this.bas=false;
                    this.droite=false;
                    this.gauche=false;
                    sens =3;
                    pas =0;
                }
//                movingAnim(0, 1);
                int[] tab = new int[2];
                tab[0]=0;
                tab[1]=1;
                return tab;
            }else{
                if (!bas){
                    this.haut=false;
                    this.bas=true;
                    this.droite=false;
                    this.gauche=false;
                    sens =0;
                    pas =0;
                }
//                movingAnim(0,-1);
                int[] tab = new int[2];
                tab[0]=0;
                tab[1]=-1;
                return tab;
            }
        }
        return null;
    }

    protected void movingAnim(int sensX, int sensY){
        this.pos.x += 2*sensX;
        this.pos.y += 2*sensY;
    }

    public EntitySnapshot getSaveSnapshot(){
        return new EntitySnapshot(type.getId(), pos.x, pos.y);
    }

    public abstract void update(float deltaTime);

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

    public void hardPositionning(int x, int y) {
        this.pos.x = x;
        this.pos.y = y;
    }

    public abstract void onDeath();

    public void death(){
        onDeath();
        dead = true;
    }

    public void resurrect(){
        dead = false;
    }

}
