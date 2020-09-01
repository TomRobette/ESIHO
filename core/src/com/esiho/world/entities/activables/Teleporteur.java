package com.esiho.world.entities.activables;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.esiho.Game;
import com.esiho.world.entities.Activable;
import com.esiho.world.entities.Entity;
import com.esiho.world.entities.EntitySnapshot;
import com.esiho.world.map.TiledGameMap;

public class Teleporteur extends Activable {
    int wait = 16;
    String destination;
    int destiX;
    int destiY;

    @Override
    public void routine() {
        if (activated){
            if (wait==0){
                activated=false;
                Game.gameMap = new TiledGameMap(destination);
                if (Game.gameMap.getPlayer()!=null){
                    Game.gameMap.getPlayer().hardPositionning(destiX, destiY);
                }
                Game.gameMap.refreshEntities();
            }else{
                wait--;
            }
        }
    }

    @Override
    public void onCreate(EntitySnapshot snapshot) {
        this.destination = snapshot.getString("destination", "maison");
        this.destiX = snapshot.getInt("destiX", 0);
        this.destiY = snapshot.getInt("destiY", 0);
    }

    @Override
    public void onDeath() {

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
        Game.pause = false;
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
