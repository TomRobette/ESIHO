package com.esiho.world.item;

import com.esiho.combat.combattants.Combattant;
import com.esiho.combat.teams.Team;

public abstract class UseItem {
    public abstract Combattant useItemOnEntity(Combattant combattant);

    public abstract Team useItemOnTeam(Team team);
}
