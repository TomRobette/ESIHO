package com.esiho.world.item;

import com.esiho.combat.entities.CombatEntity;
import com.esiho.combat.teams.Team;

public abstract class UseItem {
    public abstract CombatEntity useItemOnEntity(CombatEntity combatEntity);

    public abstract Team useItemOnTeam(Team team);
}
