package com.esiho.combat;

import com.esiho.combat.entities.CombatEntity;
import com.esiho.world.item.Item;

import java.util.ArrayList;

public class Team {
    private String id;
    private ArrayList<CombatEntity> listeCbtEntities;
    private ArrayList<Item> inventaire;
    private Integer argent;
    private String position;

    public Team(String id, Integer argent, ArrayList<Item> inventaire){
        this.id=id;
        this.argent=argent;
        this.inventaire = inventaire;
    }

    public Team(String id, Integer argent){
        this.id=id;
        this.argent=argent;
        this.inventaire= new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
