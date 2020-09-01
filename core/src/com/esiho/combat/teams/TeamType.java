package com.esiho.combat.teams;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.esiho.combat.combattants.Combattant;
import com.esiho.combat.combattants.Ennemis;
import com.esiho.combat.combattants.Joueur;
import com.esiho.world.item.Item;
import com.esiho.world.scenario.Quest;

import java.util.ArrayList;
import java.util.HashMap;

public enum TeamType {
    JOUEUR("T0", Joueur.class, new ArrayList<Combattant>()),
    MONSTRES("T1", Ennemis.class, new ArrayList<Combattant>());

    private String id;
    public TeamType type;
    public ArrayList<Combattant> listeCbtEntities;
    public ArrayList<Item> inventaire;
    public Integer argent;
    public ArrayList<Quest> quests;
    private Class loaderClass;

    public static final int ENTITY_SIZE = 32;

    private TeamType(String id, Class loaderClass){
        this.id = id;
        this.loaderClass = loaderClass;
        this.listeCbtEntities = new ArrayList<>();
        this.inventaire = new ArrayList<>();
        this.quests = new ArrayList<>();
        this.argent = 0;
    }

    private TeamType(String id, Class loaderClass, ArrayList<Combattant> listeCbtEntities){
        this.id = id;
        this.loaderClass = loaderClass;
        this.listeCbtEntities = listeCbtEntities;
        this.inventaire = new ArrayList<>();
        this.quests = new ArrayList<>();
        this.argent = 0;
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
