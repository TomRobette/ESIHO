package com.esiho.world.item;

import com.esiho.combat.entities.Combattant;
import com.esiho.combat.teams.Team;

public abstract class Item{

    public abstract Combattant useItemOnEntity(Combattant combattant);

    public abstract Team useItemOnTeam(Team team);
}
