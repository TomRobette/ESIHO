package com.esiho.world.entities.activables;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.Timer;
import com.esiho.world.entities.Activable;
import com.esiho.world.entities.EntitySnapshot;
import com.esiho.world.entities.EntityType;
import com.esiho.world.map.GameMap;

import java.util.concurrent.ThreadLocalRandom;

public class Npc extends Activable {
    int[] tab;
    float stateTime = 0;
    public String text;
    int amount = 16;
    int direction = 0;
    int wait = 0;

    @Override
    public void routine() {
        if (amount==0){
            direction = ThreadLocalRandom.current().nextInt(0, 4);
            amount=16;
            wait=160;
        }
        if (wait==0){
            move();
            amount--;
        }else{
            wait--;
        }
    }

    public void move(){
        switch(direction){
            case 0:
                tab = super.moveY(-1);
                break;
            case 1:
                tab = super.moveY(1);
                break;
            case 2:
                tab = super.moveX(-1);
                break;
            case 3:
                tab = super.moveX(1);
        }
    }

    @Override
    public void onUse() {
    }

    @Override
    public void render(SpriteBatch batch) {
        if (tab!=null){
            if (pas <2){
                pas++;
            }else{
                pas=0;
            }
            super.movingAnim(tab[0], tab[1]);
            stateTime += Gdx.graphics.getDeltaTime();
            TextureRegion texture = (TextureRegion) type.animations[sens].getKeyFrame(stateTime, true);
            batch.draw(texture, pos.x, pos.y, getWidth(), getHeight());
            this.sprite = type.getSprite(sens, 3*type.spritePosition+pas);
            tab=null;
        }else{
            batch.draw(super.sprite, pos.x, pos.y, getWidth(), getHeight());
        }
    }
}
