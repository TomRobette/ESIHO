package com.esiho.world.item;

import com.esiho.combat.entities.CombatEntity;
import com.esiho.combat.teams.Team;

public class Consommable extends Item{
    private ConsommableType type;

    public Consommable(ConsommableType type){
        this.type=type;
    }


    @Override
    public CombatEntity useItemOnEntity(CombatEntity combatEntity) {
        return type.useItemOnEntity(combatEntity);
    }

    @Override
    public Team useItemOnTeam(Team team) {
        return type.useItemOnTeam(team);
    }
}
