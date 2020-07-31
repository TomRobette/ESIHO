package com.esiho.world.item;

import com.badlogic.gdx.graphics.Texture;
import com.esiho.combat.entities.Combattant;
import com.esiho.combat.teams.Team;

public class Divers extends Item {
    private DiversType type;

    public Divers(DiversType type){
        this.type=type;
    }

    public DiversType getType() {
        return type;
    }

    public void setType(DiversType type) {
        this.type = type;
    }

    @Override
    public Combattant useItemOnEntity(Combattant combattant) {
        return combattant;
    }

    @Override
    public Team useItemOnTeam(Team team) {
        return team;
    }

    @Override
    public String getNom() {
        return type.nom;
    }

    @Override
    public Texture getSprite() {
        return type.sprite;
    }

    @Override
    public int getValeur() {
        return type.valeur;
    }
}
