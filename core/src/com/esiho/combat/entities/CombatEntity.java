package com.esiho.combat.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.esiho.combat.moves.MoveList;
import com.esiho.combat.types.Type;
import com.esiho.world.item.Arme;
import com.esiho.world.item.Armure;

import java.util.concurrent.ThreadLocalRandom;

public class CombatEntity {
    protected CombatEntityType entityType;


    public void create(CombatEntityType entityType) {
        this.entityType = entityType;

    }


    public void render(SpriteBatch batch){

    }

    public void setPv(Integer pvmodif) {
        this.entityType.pv = pvmodif;
    }

    public void setPvMax(Integer pvmaxmodif){
        this.entityType.pvmax = pvmaxmodif;
    }

    public int getPv(){
        return this.entityType.pv;
    }

    public int getPvMax(){
        return this.entityType.pvmax;
    }

    public void addXp(Integer xpobtenu){
        Integer xpold = entityType.xp;
        Integer xpmaxold = entityType.xpmax;
        this.entityType.xp=xpold+xpobtenu;
        if (xpmaxold<=entityType.xp){//S'il y a plus ou juste assez d'exp que nécessaire
            double xpnd=xpmaxold*1.1;
            this.entityType.xpmax=(int)xpnd;
            this.entityType.lvl++;
            this.entityType.xp=entityType.xp-xpmaxold;
            lvlUp();
        }
        if (entityType.xpmax<=entityType.xp){
            addXp(0);
        }
    }

    private void lvlUp(){
        Integer ptsObtenus = entityType.lvl;
        for (int a =0; a<ptsObtenus; a++){
            Integer ran = ThreadLocalRandom.current().nextInt(0, 5);
            switch (ran){
                case 0:
                    this.entityType.pvmax++;
                    break;
                case 1:
                    this.entityType.att++;
                    break;
                case 2:
                    this.entityType.def++;
                    break;
                case 3:
                    this.entityType.attspe++;
                    break;
                case 4:
                    this.entityType.defspe++;
                    break;
                case 5:
                    this.entityType.vitesse++;
            }
        }
    }//On ajoute les points aux stats

    public String getName(){ return entityType.name;}

    public Arme getArme() {
        return entityType.arme;
    }

    public void setArme(Arme arme) {
        this.entityType.arme = arme;
    }

    public Armure getArmure() {
        return entityType.armure;
    }

    public void setArmure(Armure armure) {
        this.entityType.armure = armure;
    }

    public MoveList getMoves() {
        return entityType.moves;
    }

    public void setMoves(MoveList moves) {
        this.entityType.moves = moves;
    }

    public Integer getLvl(){
        return this.entityType.lvl;
    }

    public Integer getAtk(){
        return this.entityType.att;
    }

    public Integer getDef(){
        return this.entityType.def;
    }

    public Integer getAtkSpe(){
        return this.entityType.attspe;
    }

    public Integer getDefSpe(){
        return this.entityType.defspe;
    }

    public Integer getSpeed(){
        return this.entityType.vitesse;
    }

    public Integer getXp(){
        return this.entityType.xp;
    }

    public void regenPVabs(Integer regen){
        if (entityType.pvmax>=entityType.pv+regen){
            entityType.pv+=regen;
        }else{
            entityType.pv=entityType.pvmax;
        }
        setPv(entityType.pv);
    }

    public void regenPVprct(double pourcentage){
        Integer regen = Math.toIntExact(Math.round(pourcentage*entityType.pvmax));
        if (entityType.pvmax>=entityType.pv+regen){
            entityType.pv+=regen;
        }else{
            entityType.pv=entityType.pvmax;
        }
        setPv(entityType.pv);
    }

    public void degatsPVabs(Integer degats){
        if (entityType.pv-degats>0){
            entityType.pv-=degats;
        }else{
            entityType.pv=0;
        }
        setPv(entityType.pv);
        System.out.println(degats);
    }

    public void degatsPVprct(double pourcentage){
        Integer degats = Math.toIntExact(Math.round(pourcentage * entityType.pvmax));
        if (entityType.pv-degats>0){
            entityType.pv-=degats;
        }else{
            entityType.pv=0;
        }
        setPv(entityType.pv);
    }

    public Type getType(){
        return this.entityType.type;
    }

    public Texture getTexture(){
        return this.entityType.getSprite();
    }
}
