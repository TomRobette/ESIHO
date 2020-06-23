package com.esiho.world.item;

import com.esiho.combat.entities.CombatEntity;
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
    public CombatEntity useItemOnEntity(CombatEntity combatEntity) {
        return combatEntity;
    }

    @Override
    public Team useItemOnTeam(Team team) {
        return team;
    }
}
