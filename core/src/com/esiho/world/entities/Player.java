package com.esiho.world.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.Timer;
import com.esiho.Game;
import com.esiho.world.map.GameMap;

public class Player extends Entity {
    int[] tab;
    float stateTime = 0;
    long startTime = 0;



    @Override
    public void create (EntitySnapshot snapshot, EntityType type, GameMap map){
        super.create(snapshot, type, map);
        super.sprite = new TextureRegion(type.getSprite(sens, 3*type.spritePosition+pas));
        startTime = TimeUtils.millis();
    }

    @Override
    public void update(float deltaTime) {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) tab = super.moveX(-2);
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) tab = super.moveX(2);
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) tab = super.moveY(2);
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) tab = super.moveY(-2);

        if (Gdx.input.isKeyJustPressed(Input.Keys.Y)){
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


}
