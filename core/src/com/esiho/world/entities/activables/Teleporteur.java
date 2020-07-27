package com.esiho.world.entities.activables;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.esiho.Game;
import com.esiho.world.entities.Activable;
import com.esiho.world.entities.Entity;
import com.esiho.world.entities.EntitySnapshot;
import com.esiho.world.entities.EntityType;
import com.esiho.world.map.GameMap;
import com.esiho.world.map.TiledGameMap;

public class Teleporteur extends Activable {
    int wait = 16;

    @Override
    public void routine() {
        if (activated){
            if (wait==0){
                activated=false;
                Game.gameMap = new TiledGameMap("plaine");
            }else{
                wait--;
            }
        }
    }

    @Override
    public void onCreate(EntitySnapshot snapshot) {

    }

    @Override
    public void onUse() {
        Entity player = super.map.getPlayer();
        System.out.println(1);
        if (player!=null){
            System.out.println(2);
            if (super.map.doesEntityCollideWithEntity(player, player.getX(), player.getY(), this)){
                activated = true;
                if (Game.debug) System.out.println("Téléportation");
            }
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        TextureRegion texture;
        if (activated==true){
            super.stateTime += Gdx.graphics.getDeltaTime();
            texture = (TextureRegion) type.animations[0].getKeyFrame(stateTime, loop);
        }else{
            texture = (TextureRegion) type.animations[0].getKeyFrame(0);
        }
        batch.draw(texture, pos.x, pos.y, getWidth(), getHeight());
    }
}
