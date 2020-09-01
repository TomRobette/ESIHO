package com.esiho.world.scenario;

import com.esiho.combat.teams.Team;
import com.esiho.combat.teams.TeamType;
import com.esiho.world.item.Item;

import java.util.ArrayList;

public abstract class Quest {
    private String nom;
    private String description;
    public boolean status;
    private int sommeVictoire;
    private ArrayList<Item> cadeauxVictoire;

    public Quest(String nom, String description, int sommeVictoire, ArrayList<Item> cadeauxVictoire){
        this.nom=nom;
        this.description=description;
        this.status = false;
        this.sommeVictoire=sommeVictoire;
        this.cadeauxVictoire=cadeauxVictoire;
    }

    public abstract void howToComplete();

    public void refreshStatus(){
        if (status)
            end();
    }

    private void end(){
        TeamType.JOUEUR.argent+=sommeVictoire;
        TeamType.JOUEUR.inventaire.addAll(cadeauxVictoire);
    }
}
