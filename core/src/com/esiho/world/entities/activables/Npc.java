package com.esiho.world.entities.activables;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.esiho.world.scenario.Conversation;
import com.esiho.world.entities.Activable;
import com.esiho.world.entities.EntitySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Npc extends Activable {
    private int[] tab;
    private float stateTime = 0;
    protected Conversation conversation;
    private int amount;
    private int amountMax;
    private int direction = 0;
    private int wait;
    private int waitMax;
    private Boolean fixed;

    @Override
    public void routine() {
        if (!fixed){
            if (amount==0){
                direction = ThreadLocalRandom.current().nextInt(0, 4);
                amount=amountMax;
                wait=waitMax;
            }
            if (wait==0){
                move();
                amount--;
            }else{
                wait--;
            }
        }
    }

    @Override
    public void onCreate(EntitySnapshot snapshot) {
        readConversationFromString(snapshot.getString("text", "NULL"));
        readSensOriginFromString(snapshot.getString("sens", "false"));
        waitMax = snapshot.getInt("wait", 160);
        wait=waitMax;
        amountMax = snapshot.getInt("amount", 16);
        amount=amountMax;
        fixed = snapshot.getBoolean("fixed", true);
//        this.sprite = type.getSprite(0, 0);
    }

    private void readConversationFromString(String string){
        if (!string.equals("NULL")){
            String conversationsTab[] = string.trim().split("@@"); //Coupe entre les deux conversations (lues et nonlues)
            String phrasesReadTab[] = conversationsTab[0].trim().split("%%"); //Coupe entre les phrases lues
            String phrasesNotReadTab[] = conversationsTab[1].trim().split("%%"); //Coupe entre les phrases non lues
            ArrayList<String> phrasesReadArray = new ArrayList<>(Arrays.asList(phrasesReadTab));
            ArrayList<String> phrasesNotReadArray = new ArrayList<>(Arrays.asList(phrasesNotReadTab));
            this.conversation = new Conversation(phrasesNotReadArray, phrasesReadArray);
        }
    }

    private void readSensOriginFromString(String string){
        if (!string.equals("NULL")){
            String sensTab[] = string.trim().split("@@"); //Coupe entre les quatre sens
            ArrayList<Boolean> sensArray = new ArrayList<>();
            for (String chaine:sensTab) {
                try{
                    sensArray.add(Boolean.parseBoolean(chaine));
                }catch (Exception e){
                    sensArray.add(false);
                }
            }
            int compteur = 0;
            for (Boolean bool:sensArray) {
                if (bool==true){
                    compteur++;
                }
            }
            if (compteur>1){
                sensArray = new ArrayList<>();
                sensArray.add(false);
                sensArray.add(false);
                sensArray.add(true);
                sensArray.add(false);
            }
            super.haut = sensArray.get(0);
            super.droite = sensArray.get(1);
            super.bas = sensArray.get(2);
            super.gauche = sensArray.get(3);
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
    public abstract void onUse();

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
            if (sprite!=null){
                batch.draw(super.sprite, pos.x, pos.y, getWidth(), getHeight());
            }else{
                batch.draw(type.getSprite(sens, 3*type.spritePosition+pas), pos.x, pos.y, getWidth(), getHeight());
            }
        }
    }

    @Override
    public abstract void onDeath();
}
