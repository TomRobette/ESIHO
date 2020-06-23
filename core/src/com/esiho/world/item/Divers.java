package com.esiho.world.item;

import com.esiho.combat.entities.CombatEntity;
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
    public CombatEntity useItemOnEntity(CombatEntity combatEntity) {
        return combatEntity;
    }

    @Override
    public Team useItemOnTeam(Team team) {
        return team;
    }
}
