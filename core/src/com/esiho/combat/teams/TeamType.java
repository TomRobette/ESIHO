package com.esiho.combat.teams;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.esiho.combat.entities.CombatEntity;
import com.esiho.world.entities.Entity;
import com.esiho.world.entities.EntitySnapshot;
import com.esiho.world.entities.EntityType;
import com.esiho.world.entities.Player;
import com.esiho.world.item.Item;
import com.esiho.world.map.GameMap;

import java.util.ArrayList;
import java.util.HashMap;

public enum TeamType {
    JOUEUR("T0", Player.class, "Actor1");

    private String id;
    public TeamType type;
    public ArrayList<CombatEntity> listeCbtEntities;
    public ArrayList<Item> inventaire;
    public Integer argent;
    private Class loaderClass;
    private Texture sprite;

    public static final int ENTITY_SIZE = 32;

    private TeamType(String id, Class loaderClass, String spriteName){
        this.id = id;
        this.loaderClass = loaderClass;
        this.sprite = new Texture("combat/"+spriteName+".png");
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

    public Texture getSprite(){
        Texture texture = sprite;
        return texture;
    }

    public String getId() {
        return this.id;
    }
}
