package com.esiho.world.item;

import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;

public enum DiversType {
    BALLE("I9", "balle", "Balle", 200),
    QUEUE("I10", "queue", "Queue", 50);


    private String id;
    public Texture sprite;
    public String nom;
    public Integer valeur;

    private DiversType(String id, String spriteName, String nom, Integer valeur){
        this.id = id;
        this.sprite = new Texture("items/"+spriteName+".png");
        this.nom = nom;
        this.valeur = valeur;
    }

    private static HashMap<String, DiversType> diversTypes;

    static {
        diversTypes = new HashMap<String, DiversType>();
        for (DiversType diversType : DiversType.values()){
            DiversType.diversTypes.put(diversType.id, diversType);
        }
    }
}
