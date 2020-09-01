package com.esiho;

import com.badlogic.gdx.Screen;
import com.esiho.combat.CombatState;
import com.esiho.combat.combattants.Combattant;
import com.esiho.combat.combattants.CombattantType;
import com.esiho.combat.teams.Team;
import com.esiho.combat.teams.TeamType;
import com.esiho.world.item.Arme;
import com.esiho.world.item.ArmeType;

public class ScreenLoader {
    public static Game game;
    public static CombatState cbt;

    public ScreenLoader(Game game){
        ScreenLoader.game=game;
    }

    public static void team(){
        Team team1 = new Team();
        team1.create(TeamType.JOUEUR);
        Combattant rat = new Combattant();
        rat.create(CombattantType.RAT);
        Team team2 = new Team();
        team2.create(TeamType.MONSTRES);
        for (int a = 0; a<3; a++) {
            rat = new Combattant();
            rat.create(CombattantType.RAT);
            team2.addPNJ(rat);
        }
        team2.addArgent(10);
        team2.addItem(new Arme(ArmeType.BATON));
        ScreenLoader.cbt = new CombatState(team1, team2);
    }

    public static void changeScreen(Screen screen){
        ScreenLoader.game.changeScreen(screen);
    }
}
