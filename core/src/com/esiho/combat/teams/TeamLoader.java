package com.esiho.combat.teams;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.esiho.world.entities.Entity;
import com.esiho.world.entities.EntitySnapshot;
import com.esiho.world.entities.EntityType;
import com.esiho.world.map.GameMap;

import java.util.ArrayList;

public class TeamLoader {
    private static Json json = new Json();

    public static ArrayList<Team> loadTeam(String id, ArrayList<Team> currentTeams){
        Gdx.files.local("teams/").file().mkdirs();
        FileHandle file = Gdx.files.local("teams/"+id+".team");
        if (file.exists()){
            TeamSnapshot[] snapshots = json.fromJson(TeamSnapshot[].class, file.readString());
            ArrayList<Team> teams = new ArrayList<>();
            for (TeamSnapshot snapshot: snapshots){
                teams.add(TeamType.createTeamUsingSnapshot(snapshot));
            }
            return teams;
        }else{
            saveTeams(id, currentTeams);
            return currentTeams;
        }
    }

    public static void saveTeams(String id, ArrayList<Team> teams){
        ArrayList<TeamSnapshot> snapshots = new ArrayList<>();
        for (Team team:teams){
            snapshots.add(team.getSaveSnapshot());
        }
        Gdx.files.local("teams/").file().mkdirs();
        FileHandle file = Gdx.files.local("teams/"+id+".team");
        file.writeString(json.prettyPrint(snapshots), false);
    }
}
