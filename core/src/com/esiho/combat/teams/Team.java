package com.esiho.combat.teams;

import com.esiho.combat.entities.CombatEntity;
import com.esiho.world.item.Item;

import java.util.ArrayList;

public class Team {
    private TeamType type;

    public void create(String id, Integer argent, ArrayList<Item> inventaire, ArrayList<CombatEntity> listeCbtEntities){

    }

    public void addPNJ(CombatEntity newEntity){
        if (listeCbtEntities == null){
            listeCbtEntities = new ArrayList<>();
        }
        this.listeCbtEntities.add(newEntity);
    }

    public void removePNJ(CombatEntity entityRemoved){
        this.listeCbtEntities.remove(entityRemoved);
    }

    public ArrayList<Item> getInventaire(){
        return inventaire;
    }

    public ArrayList<CombatEntity> getListeCbtEntities() {
        return listeCbtEntities;
    }

    public void addArgent(Integer argentAjoutee){
        this.argent+=argentAjoutee;
    }

    public void removeArgent(Integer argentRetiree){
        if (this.argent-argentRetiree>=0){//Si le résultat de la déduction est supérieur ou égal à zero
            this.argent-=argentRetiree;
        }/*S'il n'y a pas assez d'argent pour en déduire*/else{
            this.argent=0;
        }
    }

    public Integer getArgent(){
        return this.argent;
    }
}
