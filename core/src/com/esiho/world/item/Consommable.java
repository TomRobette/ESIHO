package com.esiho.world.item;

import com.esiho.combat.entities.Combattant;
import com.esiho.combat.teams.Team;

public class Consommable extends Item{
    private ConsommableType type;

    public Consommable(ConsommableType type){
        this.type=type;
    }


    @Override
    public Combattant useItemOnEntity(Combattant combattant) {
        return type.useItemOnEntity(combattant);
    }

    @Override
    public Team useItemOnTeam(Team team) {
        return type.useItemOnTeam(team);
    }

    @Override
    public String getNom() {
        return type.nom;
    }
}
