package com.esiho.combat.teams;

import com.esiho.combat.combattants.Combattant;
import com.esiho.world.item.Item;

import java.util.ArrayList;

public class Team {
    private TeamType type;

    public void create(TeamSnapshot snapshot, TeamType type){
        this.type=type;
        this.type.argent = snapshot.argent;
        this.type.inventaire = snapshot.inventaire;
        this.type.listeCbtEntities = snapshot.listeCombatEntities;
    }

    public void create(TeamType type){
        this.type=type;
    }

    public void addPNJ(Combattant newEntity){
        if (type.listeCbtEntities == null){
            type.listeCbtEntities = new ArrayList<>();
        }
        this.type.listeCbtEntities.add(newEntity);
    }

    public void removePNJ(Combattant entityRemoved){
        this.type.listeCbtEntities.remove(entityRemoved);
    }

    public void removePNJ(int index){
        this.type.listeCbtEntities.remove(index);
    }

    public ArrayList<Item> getInventaire(){
        return type.inventaire;
    }

    public void addItem(Item objet){
        type.inventaire.add(objet);
    }

    public void removeItem(Item objet){
        type.inventaire.remove(objet);
    }

    public void removeItem(int index){
        type.inventaire.remove(index);
    }

    public void setItem(Item objet, int index){
        type.inventaire.set(index, objet);
    }

    public ArrayList<Combattant> getListeCbtEntities() {
        return type.listeCbtEntities;
    }

    public void addArgent(Integer argentAjoutee){
        this.type.argent+=argentAjoutee;
    }

    public void removeArgent(Integer argentRetiree){
        if (this.type.argent-argentRetiree>=0){//Si le résultat de la déduction est supérieur ou égal à zero
            this.type.argent-=argentRetiree;
        }/*S'il n'y a pas assez d'argent pour en déduire*/else{
            this.type.argent=0;
        }
    }

    public Integer getArgent(){
        return this.type.argent;
    }

    public TeamSnapshot getSaveSnapshot(){
        return new TeamSnapshot(type.getId(), type.listeCbtEntities, type.inventaire, type.argent);
    }
}
