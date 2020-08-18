package com.esiho.world.item;

import com.badlogic.gdx.graphics.Texture;
import com.esiho.combat.entities.Combattant;
import com.esiho.combat.teams.Team;

import java.util.HashMap;

public enum ConsommableType {
    POTION_VIE("I11", "potion_vie", "Potion de vie", 50, new UseItem() {
        @Override
        public Combattant useItemOnEntity(Combattant combattant) {
            combattant.regenPVprct((float) 0.2);
            return combattant;
        }

        @Override
        public Team useItemOnTeam(Team team) {
            return team;
        }
    }),
    POISON_VIE("I12", "poison_vie", "Poison de vie", 50, new UseItem() {
        @Override
        public Combattant useItemOnEntity(Combattant combattant) {
            combattant.degatsPVprct((float) 0.2);
            return combattant;
        }

        @Override
        public Team useItemOnTeam(Team team) {
            return team;
        }
    });


    private String id;
    public Texture sprite;
    public String nom;
    public Integer valeur;
    private UseItem useItem;

    private ConsommableType(String id, String spriteName, String nom, Integer valeur, UseItem useItem){
        this.id = id;
        this.sprite = new Texture("items/"+spriteName+".png");
        this.nom = nom;
        this.valeur = valeur;
        this.useItem = useItem;
    }

    public Combattant useItemOnEntity(Combattant combattant){
        return this.useItem.useItemOnEntity(combattant);
    }

    public static ConsommableType getElement(String key){
        try{
            if (consommableTypes.get(key).nom!=null){
                return consommableTypes.get(key);
            }
        }catch (Exception e){
            return null;
        }
        return null;
    }

    public Team useItemOnTeam(Team team){
        return this.useItem.useItemOnTeam(team);
    }

    private static HashMap<String, ConsommableType> consommableTypes;

    static {
        consommableTypes = new HashMap<String, ConsommableType>();
        for (ConsommableType armureType : ConsommableType.values()){
            ConsommableType.consommableTypes.put(armureType.id, armureType);
        }
    }
}
