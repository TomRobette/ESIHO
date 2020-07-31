package com.esiho.world.item;

import com.badlogic.gdx.graphics.Texture;
import com.esiho.combat.entities.Combattant;
import com.esiho.combat.teams.Team;

public class Armure extends Item {
    private ArmureType type;

    public Armure(ArmureType type){
        this.type=type;
    }

    public ArmureType getType() {
        return type;
    }

    public void setType(ArmureType type) {
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

    public double getCoeffProtection(){
        return type.coeffProtection;
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
