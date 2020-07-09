package com.esiho.combat.teams;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.esiho.combat.entities.CombatEntity;
import com.esiho.combat.entities.Joueur;
import com.esiho.world.entities.Player;
import com.esiho.world.item.Item;

import java.util.ArrayList;
import java.util.HashMap;

public enum TeamType {
    JOUEUR("T0", Joueur.class, new ArrayList<CombatEntity>());

    private String id;
    public TeamType type;
    public ArrayList<CombatEntity> listeCbtEntities;
    public ArrayList<Item> inventaire;
    public Integer argent;
    private Class loaderClass;

    public static final int ENTITY_SIZE = 32;

    private TeamType(String id, Class loaderClass){
        this.id = id;
        this.loaderClass = loaderClass;
    }

    private TeamType(String id, Class loaderClass, ArrayList<CombatEntity> listeCbtEntities){
        this.id = id;
        this.loaderClass = loaderClass;
        this.listeCbtEntities = listeCbtEntities;
    }

    public static Team createTeamUsingSnapshot(TeamSnapshot teamSnapshot){
        TeamType type = teamTypes.get(teamSnapshot.type);
        try{
            Team team = (Team) ClassReflection.newInstance(type.loaderClass);
            team.create(teamSnapshot, type);
            return team;
        }catch (Exception e){
            Gdx.app.error("TeamLoader", "N'a pas pu charger le type"+type.id+" depuis la snapshot");
            return null;
        }
    }


    private static HashMap<String, TeamType> teamTypes;

    static {
        teamTypes = new HashMap<String, TeamType>();
        for (TeamType teamType : TeamType.values()){
            teamTypes.put(teamType.id, teamType);
        }
    }

    public String getId() {
        return this.id;
    }
}
