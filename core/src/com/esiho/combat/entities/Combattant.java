package com.esiho.combat.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.esiho.combat.moves.MoveList;
import com.esiho.combat.types.Type;
import com.esiho.world.item.Arme;
import com.esiho.world.item.Armure;

import java.util.concurrent.ThreadLocalRandom;

public class Combattant {

    protected Type type;
    private Texture sprite;
    protected String name;
    protected int lvl;
    protected int xpmax;
    protected int xp;
    protected int pvmax;
    protected int pv;
    protected int att;
    protected int def;
    protected int attspe;
    protected int defspe;
    protected int vitesse;
    protected Arme arme;
    protected Armure armure;
    protected MoveList moves;

    public void create(CombattantType entityType) {
        this.type=entityType.type;
        this.sprite=entityType.getSprite();
        this.name=entityType.name;
        this.lvl=entityType.lvl;
        this.xpmax=entityType.xpmax;
        this.xp=entityType.xp;
        this.pvmax=entityType.pvmax;
        this.pv=entityType.pv;
        this.att=entityType.att;
        this.def=entityType.def;
        this.attspe=entityType.attspe;
        this.defspe=entityType.defspe;
        this.vitesse=entityType.vitesse;
        this.arme=entityType.arme;
        this.armure=entityType.armure;
        this.moves=entityType.moves;
    }


    public void render(SpriteBatch batch){

    }

    public void setPv(Integer pvmodif) {
        this.pv = pvmodif;
    }

    public void setPvMax(Integer pvmaxmodif){
        this.pvmax = pvmaxmodif;
    }

    public int getPv(){
        return this.pv;
    }

    public int getPvMax(){
        return this.pvmax;
    }

    public void addXp(Integer xpobtenu){
        Integer xpold = xp;
        Integer xpmaxold = xpmax;
        this.xp=xpold+xpobtenu;
        if (xpmaxold<=xp){//S'il y a plus ou juste assez d'exp que nécessaire
            double xpnd=xpmaxold*1.1;
            this.xpmax=(int)xpnd;
            this.lvl++;
            this.xp=xp-xpmaxold;
            lvlUp();
        }
        if (xpmax<=xp){
            addXp(0);
        }
    }

    private void lvlUp(){
        Integer ptsObtenus = lvl;
        for (int a =0; a<ptsObtenus; a++){
            Integer ran = ThreadLocalRandom.current().nextInt(0, 5);
            switch (ran){
                case 0:
                    this.pvmax++;
                    break;
                case 1:
                    this.att++;
                    break;
                case 2:
                    this.def++;
                    break;
                case 3:
                    this.attspe++;
                    break;
                case 4:
                    this.defspe++;
                    break;
                case 5:
                    this.vitesse++;
            }
        }
    }//On ajoute les points aux stats

    public String getName(){ return name;}

    public Arme getArme() {
        return arme;
    }

    public void setArme(Arme arme) {
        this.arme = arme;
    }

    public Armure getArmure() {
        return armure;
    }

    public void setArmure(Armure armure) {
        this.armure = armure;
    }

    public MoveList getMoves() {
        return moves;
    }

    public void setMoves(MoveList moves) {
        this.moves = moves;
    }

    public Integer getLvl(){
        return this.lvl;
    }

    public Integer getAtk(){
        return this.att;
    }

    public Integer getDef(){
        return this.def;
    }

    public Integer getAtkSpe(){
        return this.attspe;
    }

    public Integer getDefSpe(){
        return this.defspe;
    }

    public Integer getSpeed(){
        return this.vitesse;
    }

    public Integer getXp(){
        return this.xp;
    }

    public void regenPVabs(Integer regen){
        if (pvmax>=pv+regen){
            pv+=regen;
        }else{
            pv=pvmax;
        }
    }

    public void regenPVprct(double pourcentage){
        Integer regen = Math.toIntExact(Math.round(pourcentage*pvmax));
        if (pvmax>=pv+regen){
            pv+=regen;
        }else{
            pv=pvmax;
        }
    }

    public void degatsPVabs(Integer degats){
        if (pv-degats>0){
            pv-=degats;
        }else{
            pv=0;
        }
        System.out.println(degats);
    }

    public void degatsPVprct(double pourcentage){
        Integer degats = Math.toIntExact(Math.round(pourcentage * pvmax));
        if (pv-degats>0){
            pv-=degats;
        }else{
            pv=0;
        }
    }

    public Type getType(){
        return this.type;
    }

    public Texture getTexture(){
        return this.sprite;
    }
}
