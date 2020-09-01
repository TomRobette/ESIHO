package com.esiho.world.entities.activables.Npcs;

import com.esiho.ScreenLoader;
import com.esiho.combat.CombatState;
import com.esiho.combat.entities.Combattant;
import com.esiho.combat.entities.CombattantType;
import com.esiho.combat.teams.Team;
import com.esiho.combat.teams.TeamType;
import com.esiho.world.entities.activables.Npc;
import com.esiho.world.item.Divers;
import com.esiho.world.item.DiversType;

public class Monstre extends Npc {
    @Override
    public void onUse() {
        Team team1 = new Team();
        team1.create(TeamType.JOUEUR);
        if (team1.getListeCbtEntities().size()==0){
            Combattant joueur = new Combattant();
            joueur.create(CombattantType.JOUEUR);
            team1.addPNJ(joueur);
        }
        Team team2 = new Team();
        team2.create(TeamType.MONSTRES);
        Combattant slime = new Combattant();
        for (int a = 0; a<3; a++) {
            slime.create(CombattantType.SLIME);
            team2.addPNJ(slime);
        }
        team2.addArgent(100);
        team2.addItem(new Divers(DiversType.BALLE));
        ScreenLoader.cbt = new CombatState(team1, team2, this);
        ScreenLoader.game.cbtScreen(ScreenLoader.cbt);
    }

    @Override
    public void onDeath() {

    }
}
