package com.esiho.combat.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.esiho.combat.moves.MoveList;
import com.esiho.combat.types.Type;
import com.esiho.world.entities.Entity;
import com.esiho.world.entities.Pnj;

import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

public abstract class CombatEntity extends Pnj {
    protected CombatEntityType entityType;
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
    protected MoveList movesPhy;
    protected MoveList movesSpe;


    protected void create(CombatEntityType entityType, int lvl, int xpmax, int xp, int pvmax, int pv, int att, int def, int attspe, int defspe, int vitesse, Arme arme, Armure armure, MoveList movesPhy, MoveList movesSpe) {
        this.entityType = entityType;
        this.lvl = lvl;
        this.xpmax = xpmax;
        this.xp = xp;
        this.pvmax = pvmax;
        this.pv = pv;
        this.att = att;
        this.def = def;
        this.attspe = attspe;
        this.defspe = defspe;
        this.vitesse = vitesse;
        this.arme = arme;
        this.armure = armure;
        this.movesPhy = movesPhy;
        this.movesSpe = movesSpe;
    }


    public abstract void render(SpriteBatch batch);

    public void setPv(Integer pvmodif) {
        this.pv = pvmodif;
    }

    public void setPvMax(Integer pvmaxmodif){
        this.pvmax = pvmaxmodif;
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

    public MoveList getMovesPhy() {
        return movesPhy;
    }

    public void setMovesPhy(MoveList movesPhy) {
        this.movesPhy = movesPhy;
    }

    public MoveList getMovesSpe() {
        return movesSpe;
    }

    public void setMovesSpe(MoveList movesSpe) {
        this.movesSpe = movesSpe;
    }
}
