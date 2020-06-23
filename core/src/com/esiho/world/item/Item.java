package com.esiho.world.item;

import com.badlogic.gdx.graphics.Texture;
import com.esiho.combat.entities.CombatEntity;
import com.esiho.combat.teams.Team;

public abstract class Item{

    public abstract CombatEntity useItemOnEntity(CombatEntity combatEntity);

    public abstract Team useItemOnTeam(Team team);
}
