package com.esiho.world.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.Timer;
import com.esiho.Game;
import com.esiho.world.map.GameMap;

import java.util.concurrent.ThreadLocalRandom;

public class Player extends Entity {
    int[] tab;
    float stateTime = 0;
    long startTime = 0;
    private int sens;
    private int direction = 0;
    private boolean inMove = false;
    private int amount;
    private int amountMax;
    private int wait;
    private int waitMax;
    private Boolean fixed;



    @Override
    public void create (EntitySnapshot snapshot, EntityType type, GameMap map){
        super.create(snapshot, type, map);
        super.sprite = new TextureRegion(type.getSprite(sens, 3*type.spritePosition+pas));
        this.amountMax = this.amount = 16;
        this.waitMax = this.wait = 1;
        this.fixed = false;
        startTime = TimeUtils.millis();
    }

    @Override
    public void update(float deltaTime) {
        if (!fixed){
            if (!inMove){
                if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
                    direction = 0;
                    sens=0;
                    inMove=true;
                }
                if (Gdx.input.isKeyPressed(Input.Keys.UP)){
                    direction = 1;
                    sens=3;
                    inMove=true;
                }
                if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
                    direction = 2;
                    sens=1;
                    inMove=true;
                }
                if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
                    direction = 3;
                    sens=2;
                    inMove=true;
                }
            }

            if (amount==0){
                amount=amountMax;
                wait=waitMax;
                inMove=false;
            }
            if (inMove){
                if (wait==0){
                    move();
                    amount--;
                }else{
                    wait--;
                }

            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.Y)){
            if (Game.debug){
                Game.debug=false;
                System.out.println("Mode debug désactivé");
            }else{
                Game.debug=true;
                System.out.println("Mode debug activé");
            }
        }
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
            Game.cam.position.set(pos.x, pos.y, 0);
            this.sprite = type.getSprite(sens, 3*type.spritePosition+pas);
            tab=null;
        }else{
            if (sprite!=null){
                batch.draw(super.sprite, pos.x, pos.y, getWidth(), getHeight());
            }else{
                batch.draw(type.getSprite(sens, 3*type.spritePosition+pas), pos.x, pos.y, getWidth(), getHeight());
            }
            Game.cam.position.set(pos.x, pos.y, 0);
        }
    }

    public void move(){
        switch(this.direction){
            case 0:
                tab = super.moveY(-2);
                break;
            case 1:
                tab = super.moveY(2);
                break;
            case 2:
                tab = super.moveX(-2);
                break;
            case 3:
                tab = super.moveX(2);
        }
    }

    @Override
    public void onDeath() {
        //Rien
    }


}
